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
public class MiscService {

    @Autowired
    private Util util;

    @Autowired
    private RedisCache redisCache;

    @Autowired
    private MemberService memberService;

    @Autowired
    private MovieService movieService;

    @Autowired
    private UserService userService;

    @Autowired
    private NewsService newsService;


    public Object frontPageInfo() {
        String cachedInfo = redisCache.get("frontPageInfo");
            
        if (cachedInfo != null)
            return cachedInfo;

        Map m = new HashMap<>();
        m.put("users", userService.estimatedCount());
        m.put("movies", movieService.estimatedCount());
        m.put("members", memberService.estimatedCount());
        m.put("comments", movieService.estimatedNumberOfComments());
        m.put("releases", movieService.latestMovies(0, 6));

        List<News> news = newsService.getNewsList().subList(0, 3);
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
