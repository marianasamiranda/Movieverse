package data.daos.impl;

import business.Util;
import data.daos.MovieDAO;
import data.entities.Movie;
import org.hibernate.Hibernate;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Component;
import org.springframework.transaction.annotation.Transactional;

import javax.persistence.Query;
import javax.persistence.EntityManager;
import javax.persistence.PersistenceContext;
import javax.persistence.Query;
import java.lang.reflect.Array;
import java.util.*;


@Component("movieDAO")
public class MovieDAOImpl extends DAOImpl<Integer , Movie> implements MovieDAO {

    @PersistenceContext
    private EntityManager entityManager;

    @Autowired
    private Util util;

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
/*
        Query query = entityManager.createQuery("SELECT m FROM Movie m join fetch m.movieMembers mm where mm.member.tmdb = " + memberId)
                .setFirstResult(offset)
                .setMaxResults(limit);

        List<Movie> movies = query.getResultList();
*/

        Query query = entityManager.createNativeQuery("SELECT m.name, m.poster, mm.role FROM Movie m join MovieMember mm on (m.tmdb = mm.movieid) where mm.memberid = " + memberId)
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


    public List getLatestMovies(int begin, int limit) {
        Query query = entityManager.createNativeQuery(
                "SELECT * " +
                "FROM LatestMovies " +
                "OFFSET ?1 " +
                "LIMIT ?2"
        ).setParameter(1, begin)
         .setParameter(2, limit);

        List<Object[]> l = query.getResultList();
        return util.queryListToListMap(l, Arrays.asList("id", "name", "poster", "release"));
    }


    public List getPopularMovies(int begin, int limit) {
        Query query = entityManager.createNativeQuery(
                "SELECT * " +
                "FROM PopularMovies " +
                "OFFSET ?1 " +
                "LIMIT ?2"
        ).setParameter(1, begin)
         .setParameter(2, limit);

        List<Object[]> l = query.getResultList();
        return util.queryListToListMap(l, Arrays.asList("id", "name", "poster", "rating"));
    }


    public List getUpcomingMovies(int begin, int limit) {
        Query query = entityManager.createNativeQuery(
            "SELECT * " +
            "FROM UpcomingMovies " +
            "OFFSET ?1 " +
            "LIMIT ?2"
        ).setParameter(1, begin)
                .setParameter(2, limit);

        List<Object[]> l = query.getResultList();
        return util.queryListToListMap(l, Arrays.asList("id", "name", "poster", "release"));
    }
}


