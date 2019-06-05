package business;

import data.ElasticSearch;
import data.RedisCache;
import data.daos.*;
import org.elasticsearch.action.search.SearchRequest;
import org.elasticsearch.index.query.QueryBuilders;
import org.elasticsearch.search.aggregations.AggregationBuilders;
import org.elasticsearch.search.aggregations.bucket.histogram.ParsedHistogram;
import org.elasticsearch.search.builder.SearchSourceBuilder;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.time.LocalDateTime;
import java.util.ArrayList;
import java.util.HashMap;
import java.util.List;
import java.util.Map;

@Service
public class AdminManager {

    @Autowired
    private ElasticSearch elasticSearch;

    @Autowired
    private MovieDAO movieDAO;

    @Autowired
    private MUserDAO mUserDAO;

    @Autowired
    private CommentDAO commentDAO;

    @Autowired
    private UserMovieDAO userMovieDAO;

    @Autowired
    private MediaDAO mediaDAO;

    @Autowired
    private RedisCache redisCache;

    @Autowired
    private MemberDAO memberDAO;


    private void validateToken(String token) throws Exception {
        String t = redisCache.get("admin_token");
        if (t == null || !t.equals(token))
            throw new Exception("Wrong token");
    }


    private List<Map> methodData(String methodName, int time) {
        var search = new SearchRequest("movieverse_logs");
        var builder = new SearchSourceBuilder();

        var boolQuery = QueryBuilders.boolQuery();
        boolQuery.must(QueryBuilders.matchQuery("method", methodName));
        boolQuery.must(QueryBuilders.rangeQuery("timestamp")
                                    .gt(LocalDateTime.now().plusMinutes(-time)));

        var aggs = AggregationBuilders.histogram("date_histogram")
                                      .field("timestamp")
                                      .interval(time * 1000)
                                      .format("yyyy-MM-dd'T'hh:mm:ss");

        builder.query(boolQuery);
        builder.aggregation(aggs);
        builder.size(0);
        search.source(builder);
        var response = elasticSearch.search(search);

        List<Map> results = new ArrayList<>();

        ParsedHistogram histogram = response.getAggregations().get("date_histogram");
        for (var bucket: histogram.getBuckets()) {
            Map m = new HashMap();
            m.put("timestamp", bucket.getKeyAsString());
            m.put("count", bucket.getDocCount());
            results.add(m);
        }

        return results;
    }


    private Map plotsData(Integer time) {
        if (time == null)
            time = 12 * 60;

        Map m = new HashMap();
        m.put("login", methodData("login", time));
        m.put("frontpage", methodData("frontpage", time));
        m.put("register", methodData("register", time));
        m.put("profile", methodData("profile", time));
        m.put("feed", methodData("feed", time));
        m.put("news", methodData("news", time));
        m.put("showtimes", methodData("showtimes", time));
        m.put("membersSearch", methodData("membersSearch", time));
        m.put("movieSearch", methodData("movieSearch", time));
        return m;
    }


    public Map plots(String token, Integer time) throws Exception {
        validateToken(token);
        return plotsData(time);
    }


    public Map stats(String token, Integer time) throws Exception {
        validateToken(token);
        Map m = new HashMap();
        m.put("plotsData", plotsData(time));
        m.put("users", mUserDAO.estimatedSize());
        m.put("movies", movieDAO.estimatedSize());
        m.put("people", memberDAO.estimatedSize());
        m.put("comments", commentDAO.estimatedSize());
        m.put("media", mediaDAO.estimatedSize());
        m.put("hours", userMovieDAO.totalMovieHours());
        m.put("likes", mUserDAO.totalNumberOfLikes());
        m.put("genders", mUserDAO.genderCount());
        m.put("countries", mUserDAO.countryCount());
        return m;
    }
}
