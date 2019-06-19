package business;

import data.entities.News;

import java.util.List;

public interface NewsService {
    Object getNews();

    List<News> getNewsList();
}
