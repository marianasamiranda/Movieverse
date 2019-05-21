package business;

import data.RedisCache;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.HashMap;
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


    public Object frontPageInfo() {
        String cachedInfo = redisCache.get("frontPageInfo");

        if (cachedInfo != null)
            return cachedInfo;

        Map m = new HashMap<>();
        m.put("users", usersManager.estimatedCount());
        m.put("movies", movieManager.estimatedCount());
        m.put("members", memberManager.estimatedCount());
        m.put("comments", movieManager.estimatedNumberOfComments());
        m.put("releases", movieManager.latestMovies(0, 4));

        String json = util.toJson(m);
        redisCache.set("frontPageInfo", json);
        redisCache.set("frontPageInfo_date", Long.toString(util.unixTimeSeconds() + 3600)); //1 hour

        return m;
    }
}
