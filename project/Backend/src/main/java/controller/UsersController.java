package controller;

import business.UserService;
import log.LogMethod;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;
import org.springframework.web.multipart.MultipartFile;
import java.util.Map;

import static org.springframework.web.bind.annotation.RequestMethod.*;

@CrossOrigin(origins = "*", allowCredentials = "true", allowedHeaders = "*")
@RestController
public class UsersController {

    @Autowired
    UserService userService;


    @LogMethod
    @RequestMapping(method = GET, value = "/user/profile")
    public ResponseEntity<Object> profile(@RequestHeader(value = "Authorization") String t,
                                          @RequestParam(value = "username", required = false) String username) {
        String token = t.split(" ")[1];
        return Util.callServiceAndReturnError(() -> userService.profileInfo(token, username));
    }


    @LogMethod
    @RequestMapping(method = GET, value = "/user/feed")
    public ResponseEntity<Object> feed(@RequestHeader(value = "Authorization") String t) {
        String token = t.split(" ")[1];
        return Util.callServiceAndReturnError(() -> userService.feedInfo(token));
    }

    @RequestMapping(method = GET, value = "/user/feed/entries")
    public ResponseEntity<Object> feedEntries(@RequestHeader(value = "Authorization") String t,
                                              @RequestParam(value = "page") Integer page) {
        String token = t.split(" ")[1];
        return Util.callServiceAndReturn(() -> userService.feedEntries(token, page));
    }


    @RequestMapping(method = GET, value = "/user/avatar")
    public ResponseEntity<Object> getAvatar(@RequestHeader(value = "Authorization") String t) {
        String token = t.split(" ")[1];
        return Util.callServiceAndReturnError(() -> userService.getAvatar(token));
    }


    @RequestMapping(method = PUT, value = "/user/avatar")
    public ResponseEntity<Object> setAvatar(@RequestHeader(value = "Authorization") String t,
                                            @RequestParam(value = "image") MultipartFile file) {
        String token = t.split(" ")[1];
        return Util.callServiceAndReturnError(() -> userService.newAvatar(token, file));
    }


    @RequestMapping(method = PUT, value = "/user/genre")
    public ResponseEntity<Object> genre(@RequestHeader(value = "Authorization") String t,
                                        @RequestBody Map body) {
        String token = t.split(" ")[1];
        return Util.callServiceAndReturnError(() -> {
            userService.newGenre(token, (String) body.get("genre"));
            return "";
        });
    }


    @RequestMapping(method = GET, value = "/user/friends/requests")
    public ResponseEntity<Object> genre(@RequestHeader(value = "Authorization") String t,
                                        @RequestParam(value = "type") String type) {
        String token = t.split(" ")[1];
        return Util.callServiceAndReturnError(() -> userService.getFriendRequests(token, type));
    }


    @RequestMapping(method = POST, value = "/user/friends/requests/new")
    public ResponseEntity<Object> friendRequest(@RequestHeader(value = "Authorization") String t,
                                                @RequestBody Map body) {
        String token = t.split(" ")[1];
        String username = (String) body.get("username");
        return Util.callServiceAndReturnError(() -> {
            userService.newFriendRequest(token, username);
            return "";
        });
    }


    //Processes a friend request
    @RequestMapping(method = PUT, value = "/user/friends/requests/process")
    public ResponseEntity<Object> process(@RequestHeader(value = "Authorization") String t,
                                          @RequestBody Map body) {
        String token = t.split(" ")[1];
        String username = (String) body.get("username");
        boolean decision = (boolean) body.get("decision");
        return Util.callServiceAndReturnError(() -> {
            userService.processRequest(token, username, decision);
            return "";
        });
    }


    //Cancels a sent request
    @RequestMapping(method = PUT, value = "/user/friends/requests/cancel")
    public ResponseEntity<Object> cancelRequest(@RequestHeader(value = "Authorization") String t,
                                                @RequestBody Map body) {
        String token = t.split(" ")[1];
        String username = (String) body.get("username");
        return Util.callServiceAndReturnError(() -> {
            userService.cancelRequest(token, username);
            return "";
        });
    }


    @RequestMapping(method = GET, value = "/user/movies/{movieListType}")
    public ResponseEntity<Object> userMovieList(@PathVariable("movieListType") String type,
                                                @RequestHeader(value = "Authorization") String t,
                                                @RequestParam(value = "begin") int begin,
                                                @RequestParam(value = "limit") int limit) {
        String token = t.split(" ")[1];
        return Util.callServiceAndReturn(() -> userService.movieList(token, type, begin, limit));
    }


    @RequestMapping(method = GET, value = "/user/friends")
    public ResponseEntity<Object> userMovieList(@RequestHeader(value = "Authorization") String t,
                                                @RequestParam(value = "begin") int begin,
                                                @RequestParam(value = "limit") int limit) {
        String token = t.split(" ")[1];
        return Util.callServiceAndReturn(() -> userService.friendsList(token, begin, limit));
    }


    @RequestMapping(method = GET, value = "/user/search")
    public ResponseEntity<Object> search(@RequestHeader(value = "Authorization") String t,
                                         @RequestParam(value = "name") String name) {
        String token = t.split(" ")[1];
        return Util.callServiceAndReturn(() -> userService.search(token, name));
    }


    @RequestMapping(method = GET, value = "/user/search-page")
    public ResponseEntity<Object> searchPage(@RequestHeader(value = "Authorization") String t) {
        String token = t.split(" ")[1];
        return Util.callServiceAndReturnError(()->userService.searchPage(token));
    }
}
