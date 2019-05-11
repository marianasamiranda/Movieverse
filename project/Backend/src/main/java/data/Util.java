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
        entityManager.getTransaction().commit();
        entityManager.close();
    }
}
