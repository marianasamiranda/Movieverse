package business;

import data.daos.NewsDAO;
import data.entities.News;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.List;

@Service
public class NewsManager {

    @Autowired
    NewsDAO newsDAO;

    public List getNews() {
        return newsDAO.findAll();
    }
}
