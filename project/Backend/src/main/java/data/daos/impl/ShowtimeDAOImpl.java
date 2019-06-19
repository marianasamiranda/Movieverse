package data.daos.impl;

import data.daos.ShowtimeDAO;
import data.entities.Showtime;
import org.springframework.stereotype.Repository;

import javax.persistence.EntityManager;
import javax.persistence.PersistenceContext;
import javax.persistence.Query;
import java.util.ArrayList;
import java.util.HashMap;
import java.util.List;
import java.util.Map;


@Repository
public class ShowtimeDAOImpl extends DAOImpl<Integer , Showtime> implements ShowtimeDAO {

    @PersistenceContext
    private EntityManager entityManager;

    public List getShowtimes(int theater) {
        Query query = entityManager.createNativeQuery(
                "SELECT \"Date\", movieid, Movie.name, poster " +
                "FROM Showtime " +
                "JOIN Theater ON Theater.id = Showtime.theaterid " +
                "JOIN Movie On Movie.tmdb = Showtime.movieid " +
                "WHERE Showtime.theaterid = ?1")
                .setParameter(1, theater);

        List<Object[]> l = query.getResultList();
        List<Map> r = new ArrayList<>();

        l.forEach(x -> {
            Map m = new HashMap();
            m.put("date", x[0]);
            m.put("id", x[1]);
            m.put("name", x[2]);
            m.put("poster", x[3]);
            r.add(m);
        });

        return r;
    }

    public boolean isShowing(int movieId) {
        Query query = entityManager.createNativeQuery("SELECT EXISTS(SELECT 1 FROM showtime AS s WHERE s.movieid=?1)")
                .setParameter(1, movieId);

        return (boolean) query.getSingleResult();
    }
}
