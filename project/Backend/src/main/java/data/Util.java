package data;


import org.springframework.transaction.annotation.Transactional;

import javax.persistence.EntityManager;
import javax.persistence.Persistence;

public class Util {

    @Transactional
    public static void createViews() {
        EntityManager entityManager =
                Persistence.createEntityManagerFactory("movieverse").createEntityManager();
        entityManager.getTransaction().begin();

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

        entityManager.getTransaction().commit();
        entityManager.close();
    }
}
