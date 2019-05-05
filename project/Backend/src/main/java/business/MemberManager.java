package business;

import data.daos.MemberDAO;
import data.entities.Member;
import org.elasticsearch.action.search.SearchRequest;
import org.elasticsearch.client.RequestOptions;
import org.elasticsearch.client.RestHighLevelClient;
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
public class MemberManager {

    @Autowired
    private RestHighLevelClient client;

    @Autowired
    private MemberDAO memberDAO;

    public MemberManager() {}

    public Object memberInfo(int id) {

        String datePattern = "dd/MM/yyy";
        DateFormat df = new SimpleDateFormat(datePattern);

        Member m = memberDAO.loadEntity("tmdb=" + id);

        Map<String, Object> info = new HashMap<>();
        info.put("biography", m.getBiography());
        info.put("birthdate", df.format(m.getBirthDate()));
        info.put("birthplace", m.getBirthPlace());
        info.put("gender", m.getGender());
        info.put("image", "https://image.tmdb.org/t/p/w600_and_h900_bestv2" + m.getImage());
        info.put("imdb", "https://www.imdb.com/name/" + m.getImdb());
        info.put("name", m.getName());
        //info.put("movies", m.getMovies());

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

        builder.query(boolQuery);
        builder.size(30);
        search.source(builder);
        var response = client.search(search, RequestOptions.DEFAULT);

        var result = new ArrayList<>();
        for (var r: response.getHits()) {
            var m = r.getSourceAsMap();
            m.put("id", r.getId());
            result.add(m);
        }
        return result;
    }


}
