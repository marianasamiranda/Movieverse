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
    public List getCommentsMovie(int movieId) {
        Query query = entityManager.createNativeQuery("SELECT * FROM Comment m WHERE m.movieid = ?1")
                .setParameter(1, movieId);

        List<Map> res = new ArrayList<>();

        List<Object[]> results = query.getResultList();

        results.forEach((record) -> {
            var map = new HashMap<>();
            map.put("id", record[0]);
            map.put("user", record[2]);
            map.put("date", record[3]);
            map.put("content", record[4]);
            map.put("parent", record[5]);
            map.put("likes", record[6]);
            res.add(map);
        });

        return res;
    }


}
