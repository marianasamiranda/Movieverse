package controller;

import Log.LogMethod;
import business.NewsManager;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.CrossOrigin;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import static org.springframework.web.bind.annotation.RequestMethod.GET;

@CrossOrigin(origins = "*", allowCredentials = "true", allowedHeaders = "*")
@RestController
public class News {

    @Autowired
    NewsManager newsManager;

    @LogMethod
    @RequestMapping(method = GET, value = "/news")
    public ResponseEntity<Object> news() {
        try {
            return Util.ok(newsManager.getNews());
        }
        catch (Exception e) {
            e.printStackTrace();
            return Util.badRequest("");
        }
    }
}
