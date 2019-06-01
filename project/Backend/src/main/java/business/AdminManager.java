package business;

import data.ElasticSearch;
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


    private Object methodData(String methodName, int time) {
        var search = new SearchRequest("movieverse_logs");
        var builder = new SearchSourceBuilder();

        var boolQuery = QueryBuilders.boolQuery();
        boolQuery.must(QueryBuilders.matchQuery("method", methodName));
        boolQuery.must(QueryBuilders.rangeQuery("timestamp")
                                    .gt("now-" + time + "m"));

        var aggs = AggregationBuilders.histogram("date_histogram")
                                      .field("timestamp")
                                      .interval(time * 1000)
                                      .minDocCount(1);

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



    public Object plotsData(Integer time) {
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
        m.put("memberSearch", methodData("memberSearch", time));
        m.put("movieSearch", methodData("movieSearch", time));
        return m;
    }


    public Object stats() {
        return methodData("frontpage", 12);
    }
}
