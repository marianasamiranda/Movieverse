package data;

import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import javax.persistence.EntityManager;
import javax.persistence.PersistenceContext;
import java.util.*;

@Service
public class DataUtil {

    @PersistenceContext
    private EntityManager entityManager;

    @Transactional
    public void createViews() {
        entityManager.createNativeQuery(
            "CREATE MATERIALIZED VIEW IF NOT EXISTS LatestMovies " +
            "AS (" +
                "SELECT tmdb, name, poster, release " +
                "FROM movie " +
                "WHERE release <= NOW() " +
                "ORDER BY release DESC " +
                "LIMIT 100" +
            ")"
        ).executeUpdate();

        entityManager.createNativeQuery(
            "CREATE MATERIALIZED VIEW IF NOT EXISTS PopularMovies " +
            "AS (" +
                "SELECT tmdb, name, poster, ratingsum / NULLIF(ratingcount,0) AS rating, COUNT(tmdb) AS total " +
                "FROM movie " +
                "LEFT JOIN usermovie ON usermovie.movieid = movie.tmdb " +
                "WHERE release >= NOW() - '1 month'\\:\\:interval " +
                "GROUP BY tmdb " +
                "ORDER BY total DESC " +
                "LIMIT 100" +
            ")"
        ).executeUpdate();

        entityManager.createNativeQuery(
            "CREATE MATERIALIZED VIEW IF NOT EXISTS UpcomingMovies " +
            "AS (" +
                "SELECT tmdb, name, poster, release " +
                "FROM movie " +
                "WHERE release > NOW() " +
                "ORDER BY release " +
                "LIMIT 100" +
            ")"
        ).executeUpdate();

        entityManager.createNativeQuery(
            "CREATE MATERIALIZED VIEW IF NOT EXISTS BornToday " +
            "AS (" +
                "SELECT tmdb, name, image, DATE_PART('year', NOW()) - DATE_PART('year', birthdate) AS age, COUNT(tmdb) AS total " +
                "FROM member " +
                "JOIN moviemember ON memberid = tmdb " +
                "WHERE TO_CHAR(birthdate, 'MM-DD') = TO_CHAR(NOW(), 'MM-DD') " +
                    "AND image IS NOT NULL " +
                "GROUP BY tmdb " +
                "ORDER BY total DESC " +
                "LIMIT 100" +
            ")"
        ).executeUpdate();

        entityManager.createNativeQuery(
            "CREATE MATERIALIZED VIEW IF NOT EXISTS MostCredits " +
            "AS (" +
                "SELECT tmdb, name, image, COUNT(tmdb) AS total " +
                "FROM member " +
                "JOIN moviemember ON memberid = tmdb " +
                "GROUP BY tmdb " +
                "ORDER BY total DESC " +
                "LIMIT 100" +
            ")"
        ).executeUpdate();
    }

    @Transactional
    public void refreshViews() {
        List<String> views = Arrays.asList(
            "LatestMovies", "PopularMovies", "UpcomingMovies", "BornToday", "UpcomingMovies"
        );

        views.forEach(x -> entityManager.createNativeQuery("REFRESH MATERIALIZED VIEW " + x).executeUpdate());
    }

    public List<Map> queryListToListMap(List<Object[]> objects, List params) {
        List l = new ArrayList();
        objects.forEach(x -> {
            Map m = new HashMap();
            params.forEach(p -> m.put(p, x[params.indexOf(p)]));
            l.add(m);
        });
        return l;
    }
}
