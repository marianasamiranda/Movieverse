package controller;

import business.MovieManager;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import java.io.IOException;
import java.util.Map;

import static org.springframework.web.bind.annotation.RequestMethod.GET;
import static org.springframework.web.bind.annotation.RequestMethod.POST;

@CrossOrigin(origins = "*", allowCredentials = "true", allowedHeaders = "*")
@RestController
public class Movies {

    @Autowired
    MovieManager movieManager;

    @RequestMapping(method = GET, value = "/movie/{id}")
    public ResponseEntity<Object> getMovie(@PathVariable("id") String id) {
        try {
            return Util.ok(movieManager.get(Integer.parseInt(id)));
        }
        catch(Exception e) {
            return Util.badRequest("");
        }
    }

    @RequestMapping(method = POST, value = "/movie/{id}")
    public ResponseEntity<Object> postComment(@RequestBody Map<String, String> comment) {
        try {
            return Util.ok(movieManager.addComment(comment));
        }
        catch(IOException e) {
            return Util.badRequest("");
        }
    }

    @RequestMapping(method = GET, value = "/movie-search")
    public ResponseEntity<Object> search(@RequestParam (value = "title") String title,
                                        @RequestParam (value = "sort", required = false) String sort,
                                        @RequestParam (value = "genre", required = false) String genre) {

        try {
            return Util.ok(movieManager.search(title, sort, genre));
        }
        catch (Exception e) {
            return Util.badRequest("");
        }
    }
}
