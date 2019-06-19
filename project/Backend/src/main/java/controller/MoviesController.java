package controller;

import business.MovieService;
import log.LogMethod;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

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
        int movieID = Integer.parseInt(id);
        if (t != null) {
            String token = t.split(" ")[1];
            return Util.callServiceAndReturn(() -> movieService.getMovieMeInfo(token, movieID));
        }
        else
            return Util.callServiceAndReturn(() -> movieService.get(movieID));
    }

    @RequestMapping(method = GET, value = "/movie/{id}/short")
    public ResponseEntity<Object> getMovieShort(@PathVariable("id") String id) {
        return Util.callServiceAndReturn(() -> movieService.getShort(Integer.parseInt(id)));
    }


    @RequestMapping(method = PATCH, value = "/movie/{id}")
    public ResponseEntity<Object> patchMovieMeInfo(@RequestHeader(value = "Authorization") String t,
                                                   @PathVariable("id") String id,
                                                   @RequestBody Map<String, Object> updates) {
        String token = t.split(" ")[1];
        return Util.callServiceAndReturn(() -> movieService.patchMovieMeInfo(token, Integer.parseInt(id), updates));
    }

    @LogMethod
    @RequestMapping(method = GET, value = "/movie/search")
    public ResponseEntity<Object> movieSearch(@RequestParam (value = "title") String title,
                                              @RequestParam (value = "sort", required = false) String sort,
                                              @RequestParam (value = "genre", required = false) String genre) {
        return Util.callServiceAndReturn(() -> movieService.search(title, sort, genre));
    }

    @RequestMapping(method = GET, value = "/movie/search-page")
    public ResponseEntity<Object> movieSearchPage() {
        return Util.callServiceAndReturn(() -> movieService.movieSearchPage());
    }

    @LogMethod
    @RequestMapping(method = GET, value = "/showtimes")
    public ResponseEntity<Object> showtimes(@RequestParam (value = "theater") int theater) {
        return Util.callServiceAndReturn(() -> movieService.showtimes(theater));
    }

    @LogMethod
    @RequestMapping(method = POST, value = "/movie/{movieid}/comment")
    public ResponseEntity<Object> postComment(@PathVariable(value = "movieid") Integer id,
                                              @RequestHeader(value = "Authorization") String t,
                                              @RequestBody Map<String, Object> content) {
        String token = t.split(" ")[1];
        return Util.callServiceAndReturnError(() -> movieService.postComment(id, token, content));
    }

    @RequestMapping(method = GET, value = "/movie/{movieid}/comments")
    public ResponseEntity<Object> getMovieComments(@PathVariable(value = "movieid") Integer id,
                                                   @RequestHeader(value = "Authorization", required = false) String t,
                                                   @RequestParam(value = "page") int page) {
        String token = t != null ? t.split(" ")[1] : null;
        return Util.callServiceAndReturnError(() -> movieService.getMovieComments(id, page, token));

    }

    @RequestMapping(method = GET, value = "/movie/{id}/posters")
    public ResponseEntity<Object> getPosters(@PathVariable(value = "id") int id,
                                             @RequestParam(value = "page") int page) {
        return Util.callServiceAndReturn(() -> movieService.getPosters(id, page));
    }

    @RequestMapping(method = GET, value = "/movie/{id}/videos")
    public ResponseEntity<Object> getVideos(@PathVariable(value = "id") int id,
                                            @RequestParam(value = "page") int page) {
        return Util.callServiceAndReturn(() -> movieService.getVideos(id, page));
    }


    @RequestMapping(method = GET, value = "/movie/{id}/backdrops")
    public ResponseEntity<Object> getBackdrops(@PathVariable(value = "id") int id,
                                               @RequestParam(value = "page") int page) {
        return Util.callServiceAndReturn(() -> movieService.getBackdrops(id, page));
    }


    @RequestMapping(method = GET, value = "/movie/{id}/cast")
    public ResponseEntity<Object> getMovieActors(@PathVariable(value = "id") int id,
                                                 @RequestParam(value = "page") int page) {
        return Util.callServiceAndReturn(() -> movieService.getMovieMembers(id, page, true));
    }

    @RequestMapping(method = GET, value = "/movie/{id}/crew")
    public ResponseEntity<Object> getMovieCrew(@PathVariable(value = "id") int id,
                                               @RequestParam(value = "page") int page) {
        return Util.callServiceAndReturn(() -> movieService.getMovieMembers(id, page, false));
    }


    //comments

    @RequestMapping(method = POST, value = "/movie/comment/{commentid}/like")
    public ResponseEntity<Object> likeComment(@PathVariable(value = "commentid") int id,
                                              @RequestHeader(value = "Authorization") String t) {
        String token = t.split(" ")[1];
        return Util.callServiceAndReturnError(() -> movieService.likeAComment(id, token));
    }


    @RequestMapping(method = POST, value = "/movie/comment/{commentid}/dislike")
    public ResponseEntity<Object> dislikeComment(@PathVariable(value = "commentid") int id,
                                                 @RequestHeader(value = "Authorization") String t) {
        String token = t.split(" ")[1];
        return Util.callServiceAndReturnError(() -> movieService.dislikeComment(id, token));
    }


    @RequestMapping(method = GET, value = "/movie/comment/{commentid}/replies")
    public ResponseEntity<Object> getCommentReplies(@PathVariable(value = "commentid") Integer id,
                                                    @RequestHeader(value = "Authorization", required = false) String t,
                                                    @RequestParam(value = "page") int page) {
        String token = t != null ? t.split(" ")[1] : null;
        return Util.callServiceAndReturnError(() -> movieService.getCommentReplies(id, page, token));
    }


    @RequestMapping(method = POST, value = "/movie/comment/{commentid}/reply")
    public ResponseEntity<Object> replyToComment(@PathVariable(value = "commentid") int id,
                                                 @RequestHeader(value = "Authorization") String t,
                                                 @RequestBody Map<String, Object> content) {
        String token = t.split(" ")[1];
        return Util.callServiceAndReturnError(() -> movieService.replyToComment(id, token, content));
    }
}
