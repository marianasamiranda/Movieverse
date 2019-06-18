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
    public ResponseEntity<Object> getMovie(@PathVariable("id") String id,
                                           @RequestHeader(value = "Authorization", required = false) String t) {
        try {
            int movieID = Integer.parseInt(id);
            if (t != null) {
                String token = t.split(" ")[1];
                return Util.ok(movieService.getMovieMeInfo(token, movieID));
            }
            else
                return Util.ok(movieService.get(movieID));
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


    @RequestMapping(method = PATCH, value = "/movie/{id}")
    public ResponseEntity<Object> patchMovieMeInfo(
            @RequestHeader(value = "Authorization") String t,
            @PathVariable("id") String id,
            @RequestBody Map<String, Object> updates) {

        String token = t.split(" ")[1];

        try {
            return Util.ok(movieService.patchMovieMeInfo(token, Integer.parseInt(id), updates));
        }
        catch(Exception e) {
            return Util.badRequest("");
        }
    }

    @LogMethod
    @RequestMapping(method = GET, value = "/movie/search")
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

    @RequestMapping(method = GET, value = "/movie/search-page")
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

        try {
            return Util.ok(movieService.postComment(id, token, content));
        }
        catch (Exception e) {
            return Util.badRequest(e.getMessage());
        }
    }

    @RequestMapping(method = GET, value = "/movie/{movieid}/comments")
    public ResponseEntity<Object> getMovieComments(@PathVariable(value = "movieid") Integer id,
                                                   @RequestHeader(value = "Authorization", required = false) String t,
                                                   @RequestParam(value = "page") int page) {
        String token = null;
        if(t != null)
            token = t.split(" ")[1];

        try {
            return Util.ok(movieService.getMovieComments(id, page, token));
        }
        catch (Exception e) {
            return Util.badRequest(e.getMessage());
        }
    }

    @RequestMapping(method = GET, value = "/movie/{id}/posters")
    public ResponseEntity<Object> getPosters(@PathVariable(value = "id") int id,
                                             @RequestParam(value = "page") int page) {
        try {
            return Util.ok(movieService.getPosters(id, page));
        }
        catch (Exception e) {
            return Util.badRequest("");
        }
    }

    @RequestMapping(method = GET, value = "/movie/{id}/videos")
    public ResponseEntity<Object> getVideos(@PathVariable(value = "id") int id,
                                            @RequestParam(value = "page") int page) {
        try {
            return Util.ok(movieService.getVideos(id, page));
        }
        catch (Exception e) {
            return Util.badRequest("");
        }
    }


    @RequestMapping(method = GET, value = "/movie/{id}/backdrops")
    public ResponseEntity<Object> getBackdrops(@PathVariable(value = "id") int id,
                                               @RequestParam(value = "page") int page) {
        try {
            return Util.ok(movieService.getBackdrops(id, page));
        }
        catch (Exception e) {
            return Util.badRequest("");
        }
    }


    @RequestMapping(method = GET, value = "/movie/{id}/cast")
    public ResponseEntity<Object> getMovieActors(@PathVariable(value = "id") int id,
                                                 @RequestParam(value = "page") int page) {
        try {
            return Util.ok(movieService.getMovieMembers(id, page, true));
        }
        catch (Exception e) {
            return Util.badRequest("");
        }
    }

    @RequestMapping(method = GET, value = "/movie/{id}/crew")
    public ResponseEntity<Object> getMovieCrew(@PathVariable(value = "id") int id,
                                               @RequestParam(value = "page") int page) {
        try {
            return Util.ok(movieService.getMovieMembers(id, page, false));
        }
        catch (Exception e) {
            return Util.badRequest("");
        }
    }


    //comments

    @RequestMapping(method = POST, value = "/movie/comment/{commentid}/like")
    public ResponseEntity<Object> likeComment(@PathVariable(value = "commentid") int id,
                                              @RequestHeader(value = "Authorization") String t) {

        String token = t.split(" ")[1];


        try {
            return Util.ok(movieService.likeAComment(id, token));
        }
        catch (Exception e) {
            return Util.badRequest(e.getMessage());
        }
    }


    @RequestMapping(method = POST, value = "/movie/comment/{commentid}/dislike")
    public ResponseEntity<Object> dislikeComment(@PathVariable(value = "commentid") int id,
                                                 @RequestHeader(value = "Authorization") String t) {

        String token = t.split(" ")[1];

        try {
            return Util.ok(movieService.dislikeComment(id, token));
        }
        catch (Exception e) {
            return Util.badRequest(e.getMessage());
        }
    }


    @RequestMapping(method = GET, value = "/movie/comment/{commentid}/replies")
    public ResponseEntity<Object> getCommentReplies(@PathVariable(value = "commentid") Integer id,
                                                    @RequestHeader(value = "Authorization", required = false) String t,
                                                    @RequestParam(value = "page") int page) {

        String token;
        if(t != null) {
            token = t.split(" ")[1];
        }
        else {
            token = null;
        }

        try {
            return Util.ok(movieService.getCommentReplies(id, page, token));
        }
        catch (Exception e) {
            return Util.badRequest(e.getMessage());
        }
    }


    @RequestMapping(method = POST, value = "/movie/comment/{commentid}/reply")
    public ResponseEntity<Object> replyToComment(@PathVariable(value = "commentid") int id,
                                                 @RequestHeader(value = "Authorization") String t,
                                                 @RequestBody Map<String, Object> content) {
        String token = t.split(" ")[1];

        try {
            return Util.ok(movieService.replyToComment(id, token, content));
        }
        catch (Exception e) {
            return Util.badRequest(e.getMessage());
        }
    }
}
