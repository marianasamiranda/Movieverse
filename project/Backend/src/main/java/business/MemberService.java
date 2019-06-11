package business;

import data.ElasticSearch;
import data.RedisCache;
import data.daos.MediaDAO;
import data.daos.MemberDAO;
import data.daos.MovieDAO;
import data.entities.Member;
import org.elasticsearch.action.search.SearchRequest;
import org.elasticsearch.client.RequestOptions;
import org.elasticsearch.index.query.QueryBuilders;
import org.elasticsearch.search.builder.SearchSourceBuilder;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.text.DateFormat;
import java.text.SimpleDateFormat;
import java.util.ArrayList;
import java.util.HashMap;
import java.util.List;
import java.util.Map;

@Service
public class MemberService {

    @Autowired
    private ElasticSearch elasticSearch;

    @Autowired
    private MemberDAO memberDAO;

    @Autowired
    private MovieDAO movieDAO;

    @Autowired
    private MediaDAO mediaDAO;

    @Autowired
    private Util util;

    @Autowired
    private RedisCache redisCache;


    public Object memberInfo(int id) {

        String datePattern = "dd/MM/yyy";
        DateFormat df = new SimpleDateFormat(datePattern);

        Member m = memberDAO.findById(id);

        List<Map<String, Object>> moviesInfo = movieDAO.getMemberMoviesFromTo(id, 0, 50);
        boolean moreMovies = !(moviesInfo.size() < 50);
        List<Object> backdrops = mediaDAO.getMemberBackdrops(id);


        Map<String, Object> info = new HashMap<>();
        info.put("biography", m.getBiography());
        info.put("birthdate", m.getBirthDate() == null? null : df.format(m.getBirthDate()));
        info.put("birthplace", m.getBirthPlace());
        info.put("gender", m.getGender());
        info.put("image", "https://image.tmdb.org/t/p/w600_and_h900_bestv2" + m.getImage());
        info.put("imdb", "https://www.imdb.com/name/" + m.getImdb());
        info.put("name", m.getName());
        info.put("movies", moviesInfo);
        info.put("backdrops", backdrops);
        info.put("moreMovies", moreMovies);

        return info;

    }


    public List search(String name) throws Exception {
        if (name == null || name.equals(""))
            return null;

        var search = new SearchRequest("movieverse_people");
        var builder = new SearchSourceBuilder();
        var boolQuery = QueryBuilders.boolQuery();

        for (var n: name.split("\\s+"))
            boolQuery.should(QueryBuilders.fuzzyQuery("name", n));
        boolQuery.should(QueryBuilders.existsQuery("image"));

        builder.query(boolQuery);
        builder.size(30);
        builder.minScore(1.001f);
        search.source(builder);
        var response = elasticSearch.search(search);

        var result = new ArrayList<>();
        for (var r: response.getHits()) {
            var m = r.getSourceAsMap();
            m.put("id", r.getId());
            result.add(m);
        }
        return result;
    }


    public Object memberMovies(int id, int page) {
        List<Map<String, Object>> moviesInfo = movieDAO.getMemberMoviesFromTo(id, page * 50, 50);
        boolean moreMovies = !(moviesInfo.size() < 50);

        Map<String, Object> info = new HashMap<>();
        info.put("movies", moviesInfo);
        info.put("moreMovies", moreMovies);

        return info;
    }


    public int estimatedCount() {
        return memberDAO.estimatedSize();
    }


    public List bornToday(int begin, int limit) {
        return memberDAO.bornToday(begin, limit);
    }


    public List mostCredits(int begin, int limit) {
        return memberDAO.mostCredits(begin, limit);
    }


    public Object memberSearchPage() {
        String cachedInfo = redisCache.get("memberSearchPageInfo");

        if (cachedInfo != null)
            return cachedInfo;

        Map m = new HashMap();
        m.put("bornToday", bornToday(0, 100));
        m.put("mostCredits", mostCredits(0, 100));

        String json = util.toJson(m);
        redisCache.set("memberSearchPageInfo", json);
        redisCache.set("memberSearchPageInfo_date", Long.toString(util.unixTimeSeconds() + 3600)); //1 hour

        return m;
    }
}
