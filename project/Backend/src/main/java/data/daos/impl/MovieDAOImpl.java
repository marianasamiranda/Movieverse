package data.daos.impl;

import data.DataUtil;
import data.daos.MovieDAO;
import data.entities.Movie;
import org.hibernate.Hibernate;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Component;
import org.springframework.transaction.annotation.Transactional;

import javax.persistence.Query;
import javax.persistence.EntityManager;
import javax.persistence.PersistenceContext;
import java.util.*;


@Component("movieDAO")
public class MovieDAOImpl extends DAOImpl<Integer , Movie> implements MovieDAO {

    @PersistenceContext
    private EntityManager entityManager;

    @Autowired
    private DataUtil dataUtil;


    @Transactional(readOnly=true)
    public Movie loadEntityEager(String condition) {
        Query query = entityManager.createQuery("SELECT c FROM " + entityClass.getName() + " c WHERE " + condition);
        Movie result = (Movie) query.getSingleResult();
        // TODO: mudar para fetch
        Hibernate.initialize(result.getGenres());
        Hibernate.initialize(result.getCompanies());
        return result;
    }


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


    private List allFrom(String table, int begin, int limit) {
        return entityManager
                .createNativeQuery("SELECT * FROM " + table)
                .setFirstResult(begin)
                .setMaxResults(limit)
                .getResultList();
    }


    public List getLatestMovies(int begin, int limit) {
        List l = allFrom("latestMovies", begin, limit);
        return dataUtil.queryListToListMap(l, Arrays.asList("id", "name", "poster", "release"));
    }


    public List getPopularMovies(int begin, int limit) {
        List l = allFrom("PopularMovies", begin, limit);
        return dataUtil.queryListToListMap(l, Arrays.asList("id", "name", "poster", "rating"));
    }


    public List getUpcomingMovies(int begin, int limit) {
        List l = allFrom("UpcomingMovies", begin, limit);
        return dataUtil.queryListToListMap(l, Arrays.asList("id", "name", "poster", "release"));
    }


    public List getRandomUpcomingMovies(int limit) {
        Query query = entityManager.createNativeQuery(
                "SELECT * " +
                   "FROM UpcomingMovies " + "" +
                   "OFFSET floor(random() * (select count(*) from upcomingmovies))"
        ).setMaxResults(limit);

        List<Object[]> l = query.getResultList();
        return dataUtil.queryListToListMap(l, Arrays.asList("id", "name", "poster", "release"));
    }
}


