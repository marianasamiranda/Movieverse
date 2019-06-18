package data.daos.impl;

import business.Util;
import data.daos.MediaDAO;
import data.entities.Media;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Repository;
import org.springframework.transaction.annotation.Transactional;

import javax.persistence.EntityManager;
import javax.persistence.PersistenceContext;
import javax.persistence.Query;
import java.util.*;


@Repository("mediaDAO")
public class MediaDAOImpl extends DAOImpl<Integer , Media> implements MediaDAO {

    @PersistenceContext
    private EntityManager entityManager;

    @Autowired
    private Util util;

    @Transactional(readOnly=true)
    public List<Object> getMemberBackdrops(int id) {
        Query query = entityManager.createNativeQuery("SELECT m.path FROM Media m where m.memberid = " + id);

        List<Object> results = query.getResultList();

        List<Object> res = new ArrayList<>();

        results.forEach((record) -> {
            String path = (String) record;
            System.out.println(path);

            res.add(path);
        });

        return res;
    }

    @Transactional(readOnly=true)
    //TODO limitar logo na base de dados
    public Map getMovieMedia(int id) {
        Query query = entityManager.createNativeQuery("SELECT m.type, m.path FROM Media m WHERE m.movieid = ?1")
                .setParameter(1, id);

        Map<Character, ArrayList<String>> res = new HashMap<>();

        List<Object[]> results = query.getResultList();

        results.forEach((record) -> {
            var type = (Character) record[0];
            var path = (String) record[1];
            var list = res.get(type);
            if (list == null) {
                list = new ArrayList<>();
            }
            list.add(path);
            res.put(type, list);
        });

        return res;
    }

    @Transactional(readOnly=true)
    public List<Object> getMovieMedia(int id, char type, int offset, int limit) {
        Query query = entityManager.createNativeQuery("SELECT m.path " +
                "FROM Media m " +
                "WHERE m.movieid = ?1 AND m.type = ?2")
                .setParameter(1, id)
                .setParameter(2, type)
                .setFirstResult(offset)
                .setMaxResults(limit);
        return getObjects(query);
    }

    private List<Object> getObjects(Query query) {
        var results = query.getResultList();

        var res = new ArrayList<>();

        results.forEach((record) -> {
            String path = (String) record;

            res.add(path);
        });

        return res;
    }
}
