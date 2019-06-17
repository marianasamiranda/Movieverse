package data.daos.impl;

import data.daos.CommentDAO;
import data.entities.Comment;
import org.springframework.stereotype.Component;
import org.springframework.transaction.annotation.Transactional;

import javax.persistence.EntityManager;
import javax.persistence.PersistenceContext;
import javax.persistence.Query;
import java.util.ArrayList;
import java.util.HashMap;
import java.util.List;
import java.util.Map;


@Component("commentDAO")
public class CommentDAOImpl extends DAOImpl<Integer , Comment> implements CommentDAO {

    @PersistenceContext
    private EntityManager entityManager;

    @Transactional(readOnly=true)
    public List getCommentsMovie(int movieId, int offset, int limit) {
        Query query = entityManager.createNativeQuery("SELECT d.id, d.muserid, d.timestamp, d.content, d.likes, m.username, m.avatar, m.gender, (SELECT COUNT(*) FROM comment WHERE parent=d.id) FROM (SELECT c.* FROM comment c WHERE c.parent=0 and c.movieid = ?1) d INNER JOIN muser m ON (m.id=d.muserid) ORDER BY d.id DESC")
                .setParameter(1, movieId)
                .setFirstResult(offset)
                .setMaxResults(limit);

        List<Map> res = new ArrayList<>();

        List<Object[]> results = query.getResultList();

        results.forEach((record) -> {
            var map = new HashMap<>();
            map.put("id", record[0]);
            map.put("userId", record[1]);
            map.put("date", record[2]);
            map.put("content", record[3]);
            map.put("likes", record[4]);
            map.put("username", record[5]);
            map.put("userAvatar", record[6]);
            map.put("userGender", record[7]);
            map.put("numberReplies", record[8]);
            res.add(map);
        });

        return res;
    }

    @Transactional(readOnly=true)
    public List getCommentsMovieWithUserLikes(int movieId, int offset, int limit, int userId) {
        Query query = entityManager.createNativeQuery("SELECT d.id, d.muserid, d.timestamp, d.content, d.likes, m.username, m.avatar, m.gender, (SELECT EXISTS(SELECT 1 FROM musercomment AS mc WHERE mc.commentid=d.id AND mc.muserid = ?1)), (SELECT COUNT(*) FROM comment WHERE parent=d.id) FROM (SELECT c.* FROM comment c WHERE c.parent=0 and c.movieid = ?2) d INNER JOIN muser m ON (m.id=d.muserid) ORDER BY d.id DESC")
                .setParameter(1, userId)
                .setParameter(2, movieId)
                .setFirstResult(offset)
                .setMaxResults(limit);

        List<Map> res = new ArrayList<>();

        List<Object[]> results = query.getResultList();

        results.forEach((record) -> {
            var map = new HashMap<>();
            map.put("id", record[0]);
            map.put("userId", record[1]);
            map.put("date", record[2]);
            map.put("content", record[3]);
            map.put("likes", record[4]);
            map.put("username", record[5]);
            map.put("userAvatar", record[6]);
            map.put("userGender", record[7]);
            map.put("isLiked", record[8]);
            map.put("numberReplies", record[9]);
            res.add(map);
        });

        return res;
    }

    @Override
    public List getRepliesComment(int commentId, int offset, int limit) {
        Query query = entityManager.createNativeQuery("SELECT d.id, d.muserid, d.timestamp, d.content, d.likes, m.username, m.avatar, m.gender FROM (SELECT c.* FROM comment c WHERE c.parent=?1) d INNER JOIN muser m ON (m.id=d.muserid) ORDER BY d.id DESC")
                .setParameter(1, commentId)
                .setFirstResult(offset)
                .setMaxResults(limit);

        List<Map> res = new ArrayList<>();

        List<Object[]> results = query.getResultList();

        results.forEach((record) -> {
            var map = new HashMap<>();
            map.put("id", record[0]);
            map.put("userId", record[1]);
            map.put("date", record[2]);
            map.put("content", record[3]);
            map.put("likes", record[4]);
            map.put("username", record[5]);
            map.put("userAvatar", record[6]);
            map.put("userGender", record[7]);
            res.add(map);
        });

        return res;
    }

    @Override
    public List getRepliesCommentWithUserLikes(int commentId, int offset, int limit, int userId) {
        Query query = entityManager.createNativeQuery("SELECT d.id, d.muserid, d.timestamp, d.content, d.likes, m.username, m.avatar, m.gender, (SELECT EXISTS(SELECT 1 FROM musercomment AS mc WHERE mc.commentid=d.id AND mc.muserid = ?1)) FROM (SELECT c.* FROM comment c WHERE c.parent = ?2) d INNER JOIN muser m ON (m.id=d.muserid) ORDER BY d.id DESC")
                .setParameter(1, userId)
                .setParameter(2, commentId)
                .setFirstResult(offset)
                .setMaxResults(limit);

        List<Map> res = new ArrayList<>();

        List<Object[]> results = query.getResultList();

        results.forEach((record) -> {
            var map = new HashMap<>();
            map.put("id", record[0]);
            map.put("userId", record[1]);
            map.put("date", record[2]);
            map.put("content", record[3]);
            map.put("likes", record[4]);
            map.put("username", record[5]);
            map.put("userAvatar", record[6]);
            map.put("userGender", record[7]);
            map.put("isLiked", record[8]);
            res.add(map);
        });

        return res;
    }
}
