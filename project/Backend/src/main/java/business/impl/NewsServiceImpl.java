package business.impl;

import business.NewsService;
import business.Util;
import data.RedisCache;
import data.daos.NewsDAO;
import data.entities.News;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.List;

@Service
public class NewsServiceImpl implements NewsService {

    @Autowired
    private NewsDAO newsDAO;

    @Autowired
    private Util util;

    @Autowired
    private RedisCache redisCache;


    @Override
    public Object getNews() {
        String cachedNews = redisCache.get("news");

        //in cache
        if (cachedNews != null)
            return cachedNews;

        //not in cache
        List<News> news = newsDAO.findAll();
        news.sort((x,y) -> x.getDate().before(y.getDate()) ? 1 : -1);
        String json = util.toJson(news);
        redisCache.set("news", json);
        redisCache.set("news_date", Long.toString(util.unixTimeSeconds() + 3600)); //validity = 1 hour
        return news;
    }


    @Override
    public List<News> getNewsList() {
        return newsDAO.findAll();
    }
}
