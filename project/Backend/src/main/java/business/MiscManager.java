package business;

import data.RedisCache;
import data.entities.News;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.ArrayList;
import java.util.HashMap;
import java.util.List;
import java.util.Map;

@Service
public class MiscManager {

    @Autowired
    private Util util;

    @Autowired
    private RedisCache redisCache;

    @Autowired
    private MemberManager memberManager;

    @Autowired
    private MovieManager movieManager;

    @Autowired
    private UsersManager usersManager;

    @Autowired
    private NewsManager newsManager;


    public Object frontPageInfo() {
        String cachedInfo = redisCache.get("frontPageInfo");
            
        if (cachedInfo != null)
            return cachedInfo;

        Map m = new HashMap<>();
        m.put("users", usersManager.estimatedCount());
        m.put("movies", movieManager.estimatedCount());
        m.put("members", memberManager.estimatedCount());
        m.put("comments", movieManager.estimatedNumberOfComments());
        m.put("releases", movieManager.latestMovies(0, 6));

        List<News> news = newsManager.getNewsList().subList(0, 3);
        List<Map> newsCompact = new ArrayList<>();
        news.forEach(x -> {
            Map map = new HashMap();
            map.put("title", x.getTitle());
            map.put("image", x.getImage());
            map.put("link", x.getLink());
            newsCompact.add(map);
        });
        m.put("news", newsCompact);

        String json = util.toJson(m);
        redisCache.set("frontPageInfo", json);
        redisCache.set("frontPageInfo_date", Long.toString(util.unixTimeSeconds() + 3600)); //1 hour

        return m;
    }
}
