package controller;

import log.LogMethod;
import business.CommentService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import java.util.Map;

import static org.springframework.web.bind.annotation.RequestMethod.GET;
import static org.springframework.web.bind.annotation.RequestMethod.POST;

@CrossOrigin(origins = "*", allowCredentials = "true", allowedHeaders = "*")
@RestController
public class CommentController {
    @Autowired
    CommentService commentService;

    @LogMethod
    @RequestMapping(method = POST, value = "/comment/{commentid}/like")
    public ResponseEntity<Object> likeComment(@PathVariable(value = "commentid", required = true) Integer id,
                                              @RequestHeader(value = "Authorization") String t) {

        String token = t.split(" ")[1];

        try {
            return Util.ok(commentService.likeAComment(id, token));
        }
        catch (Exception e) {
            return Util.badRequest(e.getMessage());
        }
    }

    @LogMethod
    @RequestMapping(method = POST, value = "/comment/{commentid}/dislike")
    public ResponseEntity<Object> dislikeComment(@PathVariable(value = "commentid", required = true) Integer id,
                                                 @RequestHeader(value = "Authorization") String t) {

        String token = t.split(" ")[1];

        try {
            return Util.ok(commentService.dislikeComment(id, token));
        }
        catch (Exception e) {
            return Util.badRequest(e.getMessage());
        }
    }

    @LogMethod
    @RequestMapping(method = GET, value = "/comment/{commentid}/replies/{page}")
    public ResponseEntity<Object> getCommentReplies(@PathVariable(value = "commentid", required = true) Integer id,
                                                    @PathVariable(value = "page", required = true) int page) {

        try {
            return Util.ok(commentService.getCommentReplies(id, page));
        }
        catch (Exception e) {
            return Util.badRequest(e.getMessage());
        }
    }


}
