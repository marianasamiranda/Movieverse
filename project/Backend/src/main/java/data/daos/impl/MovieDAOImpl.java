package data.daos.impl;

import data.DataUtil;
import data.daos.MovieDAO;
import data.entities.Movie;
import org.hibernate.Hibernate;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Repository;
import org.springframework.transaction.annotation.Transactional;

import javax.persistence.Query;
import javax.persistence.EntityManager;
import javax.persistence.PersistenceContext;
import java.util.*;


@Repository
public class MovieDAOImpl extends DAOImpl<Integer , Movie> implements MovieDAO {

    @PersistenceContext
    private EntityManager entityManager;

    @Autowired
    private DataUtil dataUtil;


    @Transactional(readOnly=true)
    public Movie loadEntityEager(int id) {
        Query query = entityManager.createQuery("SELECT c FROM " + entityClass.getName() + " c WHERE tmdb=" + id);
        Movie result = (Movie) query.getSingleResult();
        Hibernate.initialize(result.getGenres());
        Hibernate.initialize(result.getCompanies());
        return result;
    }


    @Transactional(readOnly=true)
    public List<Map<String, Object>> getMemberMoviesFromTo(int memberId, int offset, int limit){

        Query query = entityManager.createNativeQuery("SELECT m.tmdb, m.name, m.poster, mm.role FROM Movie m join MovieMember mm on (m.tmdb = mm.movieid) where mm.memberid = " + memberId + " order by m.tmdb desc")
                .setFirstResult(offset)
                .setMaxResults(limit);

        List<Object[]> results = query.getResultList();

        List<Map<String, Object>> res = new ArrayList<>();

        results.stream().forEach((record) -> {
            int id = (int) record[0];
            String name = (String) record[1];
            String poster = (String) record[2];
            String role = (String) record[3];

            Map<String, Object> mv = new HashMap<>();
            mv.put("id", id);
            mv.put("name", name);
            mv.put("poster", poster);
            mv.put("role", role);

            res.add(mv);
        });

        return res;
    }

    @Transactional(readOnly=true)
    public List<Map> getCompanyMoviesFromTo(int companyId, int offset, int limit) {

        Query query = entityManager.createNativeQuery(
            "SELECT m.tmdb, m.name, m.poster " +
            "FROM Movie m " +
            "JOIN MovieCompany mc ON m.tmdb = mc.movieid " +
            "WHERE mc.companyid = ?1 " +
            "ORDER BY m.tmdb DESC")
        .setParameter(1, companyId)
        .setFirstResult(offset)
        .setMaxResults(limit);

        List<Object[]> results = query.getResultList();

        return dataUtil.queryListToListMap(results, Arrays.asList("id", "name", "poster"));
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


