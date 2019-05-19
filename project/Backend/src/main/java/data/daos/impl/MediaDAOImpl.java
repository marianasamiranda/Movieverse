package data.daos.impl;

import data.daos.MediaDAO;
import data.entities.Media;
import org.springframework.stereotype.Component;
import org.springframework.transaction.annotation.Transactional;

import javax.persistence.EntityManager;
import javax.persistence.PersistenceContext;
import javax.persistence.Query;
import java.util.ArrayList;
import java.util.List;


@Component("mediaDAO")
public class MediaDAOImpl extends DAOImpl<Integer , Media> implements MediaDAO {

    @PersistenceContext
    private EntityManager entityManager;

    @Transactional(readOnly=true)
    public List<Object> getMemberBackdrops(int id) {
        Query query = entityManager.createNativeQuery("SELECT m.path FROM Media m where m.memberid = " + id);

        List<Object> results = query.getResultList();

        List<Object> res = new ArrayList<>();

        results.stream().forEach((record) -> {
            String path = (String) record;
            System.out.println(path);

            res.add(path);
        });

        return res;
    }

    @Transactional(readOnly=true)
    public List<Object> getMovieMedia(int id, char type, int limit) {
        Query query = entityManager.createNativeQuery("SELECT m.path " +
                    "FROM Media m " +
                    "WHERE m.movieid = ?1 AND m.type = ?2 " +
                    "LIMIT ?3")
                .setParameter(1, id)
                .setParameter(2, type)
                .setParameter(3, limit);
        return getObjects(query);
    }

    @Transactional(readOnly=true)
    public List<Object> getMovieMedia(int id, char type) {
        Query query = entityManager.createNativeQuery("SELECT m.path " +
                "FROM Media m " +
                "WHERE m.movieid = ?1 AND m.type = ?2")
                .setParameter(1, id)
                .setParameter(2, type);
        return getObjects(query);
    }

    private List<Object> getObjects(Query query) {
        var results = query.getResultList();

        var res = new ArrayList<>();

        results.stream().forEach((record) -> {
            String path = (String) record;

            res.add(path);
        });

        return res;
    }
}
