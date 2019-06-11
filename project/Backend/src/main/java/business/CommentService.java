package business;

import data.daos.CommentDAO;
import data.daos.MUserDAO;
import data.daos.MovieDAO;
import data.entities.MUser;
import data.entities.Comment;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import java.text.SimpleDateFormat;
import java.util.List;
import java.util.Map;
import java.util.Set;

@Service
public class CommentService {

    @Autowired
    CommentDAO commentDAO;

    @Autowired
    MUserDAO mUserDAO;

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
    public List<Map> getCommentReplies(Integer id, int page) {
        return null;
    }
}