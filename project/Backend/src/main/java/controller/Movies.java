package controller;

import business.MovieManager;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;
import org.springframework.web.multipart.MultipartFile;

import java.io.IOException;
import java.util.Map;

import static org.springframework.web.bind.annotation.RequestMethod.*;

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

    @RequestMapping(method = GET, value = "/movie/{id}/me")
    public ResponseEntity<Object> getMovieMeInfo(@RequestHeader(value = "Authorization") String t, @PathVariable("id") String id) {

        String token = t.split(" ")[1];

        try {
            return Util.ok(movieManager.getMovieMeInfo(token, Integer.parseInt(id)));
        }
        catch(IOException e) {
            return Util.badRequest("");
        }
    }

    @RequestMapping(method = PATCH, value = "/movie/{id}/me")

    public ResponseEntity<Object> patchMovieMeInfo(
            @RequestHeader(value = "Authorization") String t,
            @PathVariable("id") String id,
            @RequestBody Map<String, Object> updates) {

        String token = t.split(" ")[1];

        try {
            return Util.ok(movieManager.patchMovieMeInfo(token, Integer.parseInt(id), updates));
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

    @RequestMapping(method = GET, value = "/movie-search-page")
    public ResponseEntity<Object> movieSearchPage() {
        try {
            return Util.ok(movieManager.movieSearchPage());
        }
        catch (Exception e) {
            return Util.badRequest("");
        }
    }

    @RequestMapping(method = GET, value = "/showtimes")
    public ResponseEntity<Object> showtimes(@RequestParam (value = "theater") int theater) {
        try {
            return Util.ok(movieManager.showtimes(theater));
        }
        catch (Exception e) {
            return Util.badRequest("");
        }
    }
}
