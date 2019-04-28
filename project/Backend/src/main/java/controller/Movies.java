package controller;

import business.MovieManager;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import java.io.IOException;

import static org.springframework.web.bind.annotation.RequestMethod.GET;

@CrossOrigin(origins = "*", allowCredentials = "true", allowedHeaders = "*")
@RestController
public class Movies {

    @Autowired
    MovieManager movieManager;

    @RequestMapping(method = GET, value = "/movie-search")
    public ResponseEntity<Object> login(@RequestParam (value = "title") String title,
                                        @RequestParam (value = "sort", required = false) String sort,
                                        @RequestParam (value = "genre", required = false) String genre) {

        try {
            return Util.ok(movieManager.search(title, sort, genre));
        }
        catch (IOException e) {
            return Util.badRequest("");
        }
    }
}
