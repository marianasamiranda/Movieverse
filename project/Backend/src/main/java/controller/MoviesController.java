package controller;

import log.LogMethod;
import business.MovieService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import java.io.IOException;
import java.util.Map;

import static org.springframework.web.bind.annotation.RequestMethod.*;

@CrossOrigin(origins = "*", allowCredentials = "true", allowedHeaders = "*")
@RestController
public class MoviesController {

    @Autowired
    MovieService movieService;

    @RequestMapping(method = GET, value = "/movie/{id}")
    public ResponseEntity<Object> getMovie(@PathVariable("id") String id) {
        try {
            return Util.ok(movieService.get(Integer.parseInt(id)));
        }
        catch(Exception e) {
            return Util.badRequest("");
        }
    }

    @RequestMapping(method = GET, value = "/movie/{id}/short")
    public ResponseEntity<Object> getMovieShort(@PathVariable("id") String id) {
        try {
            return Util.ok(movieService.getShort(Integer.parseInt(id)));
        }
        catch(Exception e) {
            return Util.badRequest("");
        }
    }


    @RequestMapping(method = GET, value = "/movie/{id}/me")
    public ResponseEntity<Object> getMovieMeInfo(@RequestHeader(value = "Authorization") String t, @PathVariable("id") String id) {

        String token = t.split(" ")[1];

        try {
            return Util.ok(movieService.getMovieMeInfo(token, Integer.parseInt(id)));
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
            return Util.ok(movieService.patchMovieMeInfo(token, Integer.parseInt(id), updates));
        }
        catch(IOException e) {
            return Util.badRequest("");
        }
    }

    @LogMethod
    @RequestMapping(method = GET, value = "/movie-search")
    public ResponseEntity<Object> movieSearch(@RequestParam (value = "title") String title,
                                        @RequestParam (value = "sort", required = false) String sort,
                                        @RequestParam (value = "genre", required = false) String genre) {
        try {
            return Util.ok(movieService.search(title, sort, genre));
        }
        catch (Exception e) {
            e.printStackTrace();
            return Util.badRequest("");
        }
    }

    @RequestMapping(method = GET, value = "/movie-search-page")
    public ResponseEntity<Object> movieSearchPage() {
        try {
            return Util.ok(movieService.movieSearchPage());
        }
        catch (Exception e) {
            e.printStackTrace();
            return Util.badRequest("");
        }
    }

    @LogMethod
    @RequestMapping(method = GET, value = "/showtimes")
    public ResponseEntity<Object> showtimes(@RequestParam (value = "theater") int theater) {
        try {
            return Util.ok(movieService.showtimes(theater));
        }
        catch (Exception e) {
            return Util.badRequest("");
        }
    }

    @LogMethod
    @RequestMapping(method = POST, value = "/movie/{movieid}/comment")
    public ResponseEntity<Object> postComment(@PathVariable(value = "movieid") Integer id,
                                              @RequestHeader(value = "Authorization") String t,
                                              @RequestBody Map<String, Object> content) {

        String token = t.split(" ")[1];
        System.out.println(content);

        try {
            return Util.ok(movieService.postComment(id, token, content));
        }
        catch (Exception e) {
            return Util.badRequest(e.getMessage());
        }
    }

    @LogMethod
    @RequestMapping(method = GET, value = "/movie/{movieid}/comments/{page}")
    public ResponseEntity<Object> getMovieComments(@PathVariable(value = "movieid", required = true) Integer id,
                                                   @PathVariable(value = "page", required = true) int page) {

        try {
            return Util.ok(movieService.getMovieComments(id, page));
        }
        catch (Exception e) {
            return Util.badRequest(e.getMessage());
        }
    }

}
