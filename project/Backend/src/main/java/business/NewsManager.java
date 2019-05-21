package business;

import data.RedisCache;
import data.daos.NewsDAO;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.List;

@Service
public class NewsManager {

    @Autowired
    private NewsDAO newsDAO;

    @Autowired
    private Util util;

    @Autowired
    private RedisCache redisCache;


    public Object getNews() {
        String cachedNews = redisCache.get("news");

        //in cache
        if (cachedNews != null)
            return cachedNews;

        //not in cache
        List news = newsDAO.findAll();
        String json = util.toJson(news);
        redisCache.set("news", json);
        redisCache.set("news_date", Long.toString(util.unixTimeSeconds() + 3600)); //validity = 1 hour
        return news;
    }
}
