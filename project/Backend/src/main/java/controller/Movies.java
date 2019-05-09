package controller;

import business.MovieManager;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import java.io.IOException;
import java.util.Map;

import static org.springframework.web.bind.annotation.RequestMethod.GET;
import static org.springframework.web.bind.annotation.RequestMethod.POST;
import static org.springframework.web.bind.annotation.RequestMethod.DELETE;

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

    @RequestMapping(method = POST, value = "/movie/{id}/watched")
    public ResponseEntity<Object> markWatched(@RequestHeader(value = "Authorization") String t, @PathVariable("id") String id) {

        try {
            return Util.ok(movieManager.markWatched(t, Integer.parseInt(id)));
        }
        catch(IOException e) {
            return Util.badRequest("");
        }
    }

    @RequestMapping(method = DELETE, value = "/movie/{id}/watched")
    public ResponseEntity<Object> removeWatched(@RequestHeader(value = "Authorization") String t, @PathVariable("id") String id) {
        try {
            return Util.ok(movieManager.markUnwatched(t, Integer.parseInt(id)));
        }
        catch(IOException e) {
            return Util.badRequest("");
        }
    }

    @RequestMapping(method = POST, value = "/movie/{id}/rating/{value}")
    public ResponseEntity<Object> postRating(@RequestHeader(value = "Authorization") String t, @PathVariable("id") String id, @PathVariable("value") String value) {
        try {
            return Util.ok(movieManager.rateMovie(t, Integer.parseInt(id), Integer.parseInt(value)));
        }
        catch(IOException e) {
            return Util.badRequest("");
        }
    }

    /*@RequestMapping(method = POST, value = "/movie/{id}/watchlist")
    public ResponseEntity<Object> addToWatchlist(@RequestHeader(value = "Authorization") String t, @PathVariable("id") String id) {
        try {
            return Util.ok(movieManager.markWatched(t, Integer.parseInt(id)));
        }
        catch(IOException e) {
            return Util.badRequest("");
        }
    }

    @RequestMapping(method = DELETE, value = "/movie/{id}/watchlist")
    public ResponseEntity<Object> removeFromWatchlist(@RequestHeader(value = "Authorization") String t, @PathVariable("id") String id) {
        try {
            return Util.ok(movieManager.markWatched(t, Integer.parseInt(id)));
        }
        catch(IOException e) {
            return Util.badRequest("");
        }
    }*/


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
