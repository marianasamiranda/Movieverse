package data;

import data.entities.MUser;
import org.apache.http.HttpHost;
import org.elasticsearch.action.index.IndexRequest;
import org.elasticsearch.action.search.SearchRequest;
import org.elasticsearch.action.search.SearchResponse;
import org.elasticsearch.action.update.UpdateRequest;
import org.elasticsearch.client.RequestOptions;
import org.elasticsearch.client.RestClient;
import org.elasticsearch.client.RestHighLevelClient;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.stereotype.Repository;

import java.util.HashMap;
import java.util.Map;

@Repository
public class ElasticSearch {

    private RestHighLevelClient client;

    public ElasticSearch(@Value("${elasticsearch.host}") String host,
                         @Value("${elasticsearch.port}") int port) {
        client = new RestHighLevelClient(
                RestClient.builder(new HttpHost(host, port, "http"))
        );
    }

    public void addUser(MUser u) {
        try {
            Map<String, Object> map = new HashMap<>();
            map.put("username", u.getUsername());
            map.put("name", u.getName());
            map.put("country", u.getUserCountry().getAlphaCode());
            map.put("avatar", u.getGender() + ".svg");
            IndexRequest request = new IndexRequest("movieverse_users").id(u.getId() + "").source(map);
            client.index(request, RequestOptions.DEFAULT);
        }
        catch (Exception e) {
            e.printStackTrace();
        }
    }


    public void updateAvatar(MUser u) {
        try {
            Map<String, Object> map = new HashMap<>();
            map.put("avatar", u.getAvatar());
            UpdateRequest request = new UpdateRequest("movieverse_users", u.getId() + "").doc(map);
            client.update(request, RequestOptions.DEFAULT);
        }
        catch (Exception e) {
            e.printStackTrace();
        }
    }


    public SearchResponse search(SearchRequest searchRequest) {
        try {
            return client.search(searchRequest, RequestOptions.DEFAULT);
        }
        catch (Exception e) {
            e.printStackTrace();
            return null;
        }
    }
}
