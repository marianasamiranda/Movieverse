package business;

import data.daos.CommentDAO;
import data.daos.MUserDAO;
import data.daos.MovieDAO;
import data.entities.MUser;
import data.entities.Comment;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import java.sql.Timestamp;
import java.text.SimpleDateFormat;
import java.util.HashMap;
import java.util.List;
import java.util.Map;
import java.util.Set;

@Service
public class CommentService {

    @Autowired
    CommentDAO commentDAO;

    @Autowired
    MUserDAO mUserDAO;

    @Autowired
    private Util util;

    public MUser getUserByToken(String token) {
        try {
            return mUserDAO.loadEntity("token='" + token + "'");
        }
        catch (Exception e) {
            return null;
        }
    }

    @Transactional
    public boolean likeAComment(Integer id, String token) throws Exception {

        var user = getUserByToken(token);
        var comment = commentDAO.findById(id);

        comment.addUpvoter(user);

        commentDAO.merge(comment);
        commentDAO.flush();

        return true;
    }

    @Transactional
    public boolean dislikeComment(Integer id, String token) throws Exception {
        var user = getUserByToken(token);
        var comment = commentDAO.findById(id);

        comment.removeUpvoter(user);

        commentDAO.merge(comment);
        commentDAO.flush();

        return true;

    }

    @Transactional
    public Object getCommentReplies(Integer commentId, int page, String token) {
        List replies;

        if(token != null) {
            var user = getUserByToken(token);
            replies = commentDAO.getRepliesCommentWithUserLikes(commentId, page * 10, 10, user.getId());
        }
        else {
            replies = commentDAO.getRepliesComment(commentId, page * 10, 10);
        }

        boolean moreReplies = !(replies.size() < 10);

        Map<String, Object> result = new HashMap<>();
        result.put("comments", replies);
        result.put("moreComments", moreReplies);

        return result;
    }

    @Transactional
    public Object replyToComment(int id, String token, Map<String, Object> content) {
        var user = getUserByToken(token);
        var parentComment = commentDAO.findById(id);

        var comment = new Comment();

        comment.setCommenter(user);

        comment.setContent((String) content.get("message"));

        var dateCommented = util.parseDate((String) content.get("date"));

        comment.setTimestamp(new Timestamp(dateCommented.getTime()));
        comment.setLikes(0);
        comment.setParent(id);
        comment.setMovie(parentComment.getMovie());

        commentDAO.persist(comment);
        commentDAO.flush();

        var map = new HashMap<String, Object>();

        map.put("id", comment.getId());
        map.put("userId", user.getId());
        map.put("date", util.formatDate(dateCommented));
        map.put("content", comment.getContent());
        map.put("likes", comment.getLikes());
        map.put("username", user.getUsername());
        map.put("userAvatar", user.getAvatar());

        return map;
    }
}