package data.daos.impl;

import data.daos.MovieDAO;
import data.entities.Movie;
import org.springframework.stereotype.Component;
import org.springframework.transaction.annotation.Transactional;

import javax.persistence.EntityManager;
import javax.persistence.PersistenceContext;
import javax.persistence.Query;
import java.util.ArrayList;
import java.util.HashMap;
import java.util.List;
import java.util.Map;


@Component("movieDAO")
public class MovieDAOImpl extends DAOImpl<Integer , Movie> implements MovieDAO {

    @PersistenceContext
    private EntityManager entityManager;

    @Transactional(readOnly=true)
    public List<Map<String, Object>> getMemberMoviesFromTo(int memberId, int offset, int limit){

        Query query = entityManager.createNativeQuery("SELECT m.name, m.poster, mm.role FROM Movie m join MovieMember mm on (m.tmdb = mm.movieid) where mm.memberid = " + memberId + "order by m.tmdb")
                .setFirstResult(offset)
                .setMaxResults(limit);

        List<Object[]> results = query.getResultList();

        List<Map<String, Object>> res = new ArrayList<>();

        results.stream().forEach((record) -> {
            String name = (String) record[0];
            String poster = (String) record[1];
            String role = (String) record[2];

            Map<String, Object> mv = new HashMap<>();
            mv.put("name", name);
            mv.put("poster", poster);
            mv.put("role", role);

            res.add(mv);
        });

        return res;
    }

}


