package controller;

import log.LogMethod;
import business.NewsService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.CrossOrigin;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import static org.springframework.web.bind.annotation.RequestMethod.GET;

@CrossOrigin(origins = "*", allowCredentials = "true", allowedHeaders = "*")
@RestController
public class NewsController {

    @Autowired
    NewsService newsService;

    @LogMethod
    @RequestMapping(method = GET, value = "/news")
    public ResponseEntity<Object> news() {
        return Util.callServiceAndReturn(()->newsService.getNews());
    }
}
