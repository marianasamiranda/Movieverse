package controller;

import business.NewsManager;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.CrossOrigin;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import javax.persistence.EntityManager;
import javax.persistence.Persistence;

import java.util.ArrayList;
import java.util.HashMap;
import java.util.List;
import java.util.Map;

import static org.springframework.web.bind.annotation.RequestMethod.GET;

@CrossOrigin(origins = "*", allowCredentials = "true", allowedHeaders = "*")
@RestController
public class News {

    @Autowired
    NewsManager newsManager;

    @RequestMapping(method = GET, value = "/news")
    public ResponseEntity<Object> news() {
        try {
            List<News> l = newsManager.getNews();
            return Util.ok(l);
        }
        catch (Exception e) {
            return Util.badRequest("");
        }
    }
}
