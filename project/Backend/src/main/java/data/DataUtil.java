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
                    "AND poster IS NOT NULL " +
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
                    "AND poster IS NOT NULL " +
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

        entityManager.createNativeQuery(
            "CREATE MATERIALIZED VIEW IF NOT EXISTS TotalWatchedHours " +
            "AS (" +
                "SELECT SUM(runtime) / 60" +
                "FROM Usermovie " +
                "JOIN Movie ON Usermovie.movieid = Movie.tmdb" +
            ")"
        ).executeUpdate();

        entityManager.createNativeQuery(
            "CREATE MATERIALIZED VIEW IF NOT EXISTS TotalLikes " +
            "AS (" +
                "SELECT SUM(likescount) " +
                "FROM Muser" +
            ")"
        ).executeUpdate();

        entityManager.createNativeQuery(
            "CREATE MATERIALIZED VIEW IF NOT EXISTS CountryCount " +
            "AS (" +
                "SELECT alphacode, COUNT(country.id) AS count " +
                "FROM country " +
                "JOIN muser ON muser.countryid = country.id " +
                "GROUP BY country.id" +
            ")"
        ).executeUpdate();

        entityManager.createNativeQuery(
            "CREATE MATERIALIZED VIEW IF NOT EXISTS GenderCount " +
            "AS (" +
                "SELECT gender, COUNT(id) AS count " +
                "FROM Muser " +
                "GROUP BY gender" +
            ")"
        ).executeUpdate();
    }

    @Transactional
    public void createFunctions() {
        entityManager.createNativeQuery(
            "CREATE OR REPLACE FUNCTION feedEntries(userId INTEGER,offst INTEGER,limt INTEGER) " +
                      "RETURNS TABLE (" +
                         "username VARCHAR," +
                         "usergender CHAR," +
                         "avatar VARCHAR," +
                         "timestmp TIMESTAMP," +
                         "type INTEGER," +
                         "moviename VARCHAR," +
                         "movieposter VARCHAR," +
                         "rating INTEGER"+
                      ") " +
                    "AS $$ " +
                    "DECLARE " +
                      "rec_friendactivity RECORD; " +
                      "cur_friendsactivity CURSOR (userId INTEGER, offst INTEGER, limt INTEGER) FOR (" +
                        "WITH friends as (" +
                            "SELECT m.id, m.username, m.avatar, m.gender " +
                            "FROM Friendship AS f " +
                            "JOIN Muser AS m ON (f.pending=FALSE " +
                                                 "AND ((f.sender=userId AND f.receiver=m.id) " +
                                                 "OR (f.sender=m.id AND f.receiver=userId)))" +
                        ") " +
                        "SELECT * FROM friends as fr " +
                        "JOIN Feed as fd " +
                        "ON fr.id = fd.muserid " +
                        "ORDER BY fd.timestamp DESC "+
                        "OFFSET offst " +
                        "LIMIT limt " +
                      "); " +
                    "BEGIN " +
                      "OPEN cur_friendsactivity(userId, offst, limt); " +
                      "LOOP " +
                        "FETCH cur_friendsactivity INTO rec_friendactivity; " +
                        "EXIT WHEN NOT FOUND; " +
                        "username\\:=rec_friendactivity.username; " +
                        "usergender\\:=rec_friendactivity.gender; " +
                        "avatar\\:=rec_friendactivity.avatar; " +
                        "timestmp\\:=rec_friendactivity.timestamp;" +
                        "type\\:=rec_friendactivity.type; " +
                        "SELECT m.name, m.poster INTO moviename, movieposter " +
                        "FROM Movie as m " +
                         "WHERE m.tmdb = rec_friendactivity.idcontent; " +
                        "IF (rec_friendactivity.type = 0 ) THEN " +
                          "SELECT u.rating INTO rating " +
                          "FROM UserMovie as u " +
                          "WHERE u.movieid = rec_friendactivity.idcontent and u.muserid = rec_friendactivity.muserid; " +
                        "END IF; " +
                        "RETURN NEXT; " +
                        "moviename\\:=NULL; " +
                        "movieposter\\:=NULL; " +
                        "rating\\:=NULL;"+
                      "END LOOP;" +
                      "CLOSE cur_friendsactivity; " +
                      "RETURN; " +
                    "END $$ " +
                    "LANGUAGE 'plpgsql';"
        ).executeUpdate();

        entityManager.createNativeQuery(
        "CREATE OR REPLACE FUNCTION requestsSent(userid INTEGER)\n" +
                " RETURNS TABLE(" +
                    "username VARCHAR," +
                    "name VARCHAR," +
                    "country VARCHAR," +
                    "avatar VARCHAR," +
                    "common INTEGER) AS\n" +
                "$BODY$\n" +
                "DECLARE\n" +
                    "countryid INTEGER;\n" +
                    "idx INTEGER;\n" +
                    "gender CHAR;\n" +
                "BEGIN\n" +
                "FOR idx, username, name, gender, countryid, avatar IN\n" +
                    "SELECT m.id, m.username, m.name, m.gender, m.countryid, m.avatar FROM muser AS m INNER JOIN friendship AS f ON (f.pending='t' AND f.receiver=m.id AND f.sender=userid)\n" +
                "LOOP\n" +
                    "IF avatar IS NULL THEN avatar \\:= concat(gender,'.svg');\n" +
                    "END IF;\n" +
                    "SELECT country.alphacode INTO country FROM country WHERE country.id = countryid;\n" +
                    "SELECT count(*) INTO common FROM (\n" +
                        "(SELECT f.receiver FROM friendship AS f WHERE (f.pending='f' AND f.sender=userid)\n" +
                            "UNION\n" +
                        "SELECT f.sender FROM friendship AS f WHERE (f.pending='f' AND f.receiver=userid))\n" +
                            "INTERSECT\n" +
                        "(SELECT f.receiver FROM friendship AS f WHERE (f.pending='f' AND f.sender=idx)\n" +
                            "UNION\n" +
                        "SELECT f.sender FROM friendship AS f WHERE (f.pending='f' AND f.receiver=idx))\n" +
                    ") AS temp;\n" +
                    "RETURN NEXT;\n" +
                "END LOOP;\n" +
                "\n" +
                "END;\n" +
                "$BODY$\n" +
                "LANGUAGE plpgsql;"
        ).executeUpdate();

         entityManager.createNativeQuery(
        "CREATE OR REPLACE FUNCTION requestsReceived(userid INTEGER)\n" +
                " RETURNS TABLE(" +
                    "username VARCHAR," +
                    "name VARCHAR," +
                    "country VARCHAR," +
                    "avatar VARCHAR," +
                    "common INTEGER) AS\n" +
                "$BODY$\n" +
                "DECLARE\n" +
                    "countryid INTEGER;\n" +
                    "idx INTEGER;\n" +
                    "gender CHAR;\n" +
                "BEGIN\n" +
                "FOR idx, username, name, gender,countryid, avatar IN\n" +
                    "SELECT m.id, m.username, m.name, m.gender, m.countryid, m.avatar FROM muser AS m INNER JOIN friendship AS f ON (f.pending='t' AND f.sender=m.id AND f.receiver=userid)\n" +
                "LOOP\n" +
                    "IF avatar IS NULL THEN avatar \\:= concat(gender,'.svg');\n" +
                    "END IF;\n" +
                    "SELECT country.alphacode INTO country FROM country WHERE country.id = countryid;\n" +
                    "SELECT count(*) INTO common FROM (\n" +
                        "(SELECT f.receiver FROM friendship AS f WHERE (f.pending='f' AND f.sender=userid)\n" +
                            "UNION\n" +
                        "SELECT f.sender FROM friendship AS f WHERE (f.pending='f' AND f.receiver=userid))\n" +
                            "INTERSECT\n" +
                        "(SELECT f.receiver FROM friendship AS f WHERE (f.pending='f' AND f.sender=idx)\n" +
                            "UNION\n" +
                        "SELECT f.sender FROM friendship AS f WHERE (f.pending='f' AND f.receiver=idx))\n" +
                    ") AS temp;\n" +
                    "RETURN NEXT;\n" +
                "END LOOP;\n" +
                "\n" +
                "END;\n" +
                "$BODY$\n" +
                "LANGUAGE plpgsql;"
         ).executeUpdate();

    }

    @Transactional
    public void refreshViews() {
        List<String> views = Arrays.asList(
            "LatestMovies", "PopularMovies", "UpcomingMovies", "BornToday", "UpcomingMovies",
                "BornToday", "MostCredits", "TotalWatchedHours", "TotalLikes", "CountryCount",
                "GenderCount"
        );

        views.forEach(x -> entityManager.createNativeQuery("REFRESH MATERIALIZED VIEW " + x).executeUpdate());
    }

    @Transactional
    public void createTriggers() {
        //i am stupid ja esta a ser feito friendsCount++ na classe MUser
      /*  entityManager.createNativeQuery(
            "CREATE OR REPLACE FUNCTION update_friendscount_function() RETURNS TRIGGER AS\n" +
                "'BEGIN\n" +
                "    IF TG_OP = ''INSERT'' then\n" +
                "       UPDATE muser SET friendscount = friendscount + 1 WHERE id=NEW.muserid;\n" +
                "    ELSIF TG_OP = ''DELETE'' then\n" +
                "       UPDATE muser SET friendscount = friendscount - 1 WHERE id=OLD.muserid;\n" +
                "    END IF;\n" +
                "RETURN NULL;\n" +
                "END;'\n" +
                "LANGUAGE plpgsql;\n" +
                "\n" +
                "DROP TRIGGER IF EXISTS update_friendscount_trigger ON muserfriendship;\n" +
                "CREATE TRIGGER update_friendscount_trigger AFTER INSERT OR DELETE\n" +
            "ON muserfriendship FOR EACH ROW EXECUTE PROCEDURE update_friendscount_function();"
        ).executeUpdate();
*/


        entityManager.createNativeQuery(
        "CREATE OR REPLACE FUNCTION update_hourscount_function() RETURNS TRIGGER AS " +
                "'BEGIN\n" +
                " \tIF TG_OP = ''INSERT'' OR (TG_OP = ''UPDATE'' AND NEW.status = true AND OLD.status = false) then\n" +
                "       UPDATE muser SET hourscount = hourscount + movie.runtime, moviecount = moviecount + 1 FROM movie WHERE id=NEW.muserid AND movie.tmdb=NEW.movieid;\n" +
                "    ELSIF TG_OP = ''DELETE'' OR (TG_OP = ''UPDATE'' AND NEW.status = false AND OLD.status = true) then\n" +
                "       UPDATE muser SET hourscount = hourscount - movie.runtime, moviecount = moviecount - 1 FROM movie WHERE id=OLD.muserid AND movie.tmdb=OLD.movieid;\n" +
                "    END IF;\n" +
                "RETURN NULL; \n" +
                "END;'\n" +
                "LANGUAGE plpgsql;\n" +
                "" +
                "DROP TRIGGER IF EXISTS update_hourscount_trigger ON usermovie;\n" +
                "CREATE TRIGGER update_hourscount_trigger AFTER UPDATE ON usermovie FOR EACH ROW WHEN (OLD.status IS DISTINCT FROM NEW.status) EXECUTE PROCEDURE update_hourscount_function();\n" +
                "\n" +
                "DROP TRIGGER IF EXISTS update_hourscount_trigger2 ON usermovie;\n" +
                "CREATE TRIGGER update_hourscount_trigger2 AFTER INSERT ON usermovie FOR EACH ROW WHEN (NEW.status=true) EXECUTE PROCEDURE update_hourscount_function();\n" +
                "\n" +
                "DROP TRIGGER IF EXISTS update_hourscount_trigger3 ON usermovie;\n" +
                "CREATE TRIGGER update_hourscount_trigger3 AFTER DELETE ON usermovie FOR EACH ROW WHEN (OLD.status=true) EXECUTE PROCEDURE update_hourscount_function();"
        ).executeUpdate();

        entityManager.createNativeQuery(
        "CREATE OR REPLACE FUNCTION update_ratingscount_function() RETURNS TRIGGER AS\n" +
                "'BEGIN\n" +
                " \tIF TG_OP = ''INSERT'' OR (TG_OP = ''UPDATE'' AND OLD.rating IS NULL) then\n" +
                "       UPDATE muser SET ratingscount = ratingscount + 1 WHERE id=NEW.muserid;\n" +
                "       UPDATE movie SET ratingcount = ratingcount + 1, ratingsum = ratingsum + NEW.rating WHERE movie.tmdb=NEW.movieid;\n" +
                "    ELSIF TG_OP = ''DELETE'' OR (TG_OP = ''UPDATE'' AND NEW.rating IS NULL) then\n" +
                "       UPDATE muser SET ratingscount = ratingscount - 1 WHERE id=OLD.muserid;\n" +
                "       UPDATE movie SET ratingcount = ratingcount - 1, ratingsum = ratingsum - OLD.rating WHERE movie.tmdb=OLD.movieid;\n" +
                "    ELSIF TG_OP = ''UPDATE'' then\n" +
                "       UPDATE movie SET ratingsum = ratingsum - OLD.rating + NEW.rating WHERE movie.tmdb=OLD.movieid;\n" +
                "    END IF;\n" +
                "RETURN NULL; \n" +
                "END;'\n" +
                "LANGUAGE plpgsql;" +
                "" +
                "DROP TRIGGER IF EXISTS update_ratingscount_trigger ON usermovie;\n" +
                "CREATE TRIGGER update_ratingscount_trigger AFTER UPDATE ON usermovie FOR EACH ROW WHEN (OLD.rating IS DISTINCT FROM NEW.rating) EXECUTE PROCEDURE update_ratingscount_function();\n" +
                "\n" +
                "DROP TRIGGER IF EXISTS update_ratingscount_trigger2 ON usermovie;\n" +
                "CREATE TRIGGER update_ratingscount_trigger2 AFTER INSERT ON usermovie FOR EACH ROW WHEN (NEW.rating IS NOT NULL) EXECUTE PROCEDURE update_ratingscount_function();\n" +
                "\n" +
                "DROP TRIGGER IF EXISTS update_ratingscount_trigger3 ON usermovie;\n" +
                "CREATE TRIGGER update_ratingscount_trigger3 AFTER DELETE ON usermovie FOR EACH ROW WHEN (OLD.rating IS NOT NULL) EXECUTE PROCEDURE update_ratingscount_function();\n"
        ).executeUpdate();

        entityManager.createNativeQuery(
        "CREATE OR REPLACE FUNCTION update_commentscount_function() RETURNS TRIGGER AS\n" +
                "'BEGIN\n" +
                " \tIF TG_OP = ''INSERT'' then\n" +
                "       UPDATE muser SET commentscount = commentscount + 1 WHERE id=NEW.muserid;\n" +
                "    ELSIF TG_OP = ''DELETE'' then\n" +
                "       UPDATE muser SET commentscount = commentscount - 1 WHERE id=OLD.muserid;\n" +
                "    END IF;\n" +
                "RETURN NULL; \n" +
                "END;'\n" +
                "LANGUAGE plpgsql;" +
                "" +
                "\n" +
                "DROP TRIGGER IF EXISTS update_commentscount_trigger ON comment;\n" +
                "CREATE TRIGGER update_commentscount_trigger AFTER INSERT OR DELETE ON comment FOR EACH ROw EXECUTE PROCEDURE update_commentscount_function();\n"
        ).executeUpdate();

        /*entityManager.createNativeQuery(
        "CREATE OR REPLACE FUNCTION update_likescount_function() RETURNS TRIGGER AS\n" +
                "'BEGIN\n" +
                " \tIF TG_OP = ''INSERT'' then\n" +
                "       UPDATE muser SET likescount = likescount + 1 WHERE id=NEW.muserid;\n" +
                "       UPDATE comment SET likes = likes + 1 WHERE id=NEW.commentid;\n" +
                "    ELSIF TG_OP = ''DELETE'' then\n" +
                "       UPDATE muser SET likescount = likescount - 1 WHERE id=OLD.muserid;\n" +
                "       UPDATE comment SET likes = likes - 1 WHERE id=OLD.commentid;\n" +
                "    END IF;\n" +
                "RETURN NULL; \n" +
                "END;'\n" +
                "LANGUAGE plpgsql;" +
                "" +
                "\n" +
                "DROP TRIGGER IF EXISTS update_likescount_trigger ON musercomment;\n" +
                "CREATE TRIGGER update_likescount_trigger AFTER INSERT OR DELETE ON musercomment FOR EACH ROw EXECUTE PROCEDURE update_likescount_function();\n"
        ).executeUpdate();
*/
    }

    @Transactional
    public void createIndexes() {
        entityManager.createNativeQuery(
                "CREATE INDEX IF NOT EXISTS media_memberid ON media USING btree (memberid)"
        ).executeUpdate();

        entityManager.createNativeQuery(
                "CREATE INDEX IF NOT EXISTS media_movieid ON media USING btree (movieid)"
        ).executeUpdate();
    }


    public List<Map> queryListToListMap(List<Object[]> objects, List params) {
        List l = new ArrayList();
        /*if (!objects.isEmpty())
            System.out.println(objects.get(0)[params.indexOf("timestmp")].toString());*/
        objects.forEach(x -> {
            Map m = new HashMap();
            params.forEach(p -> {
                System.out.println(x[params.indexOf(p)]);m.put(p, x[params.indexOf(p)]);});
            l.add(m);
        });
        System.out.println(l.toString());
        return l;
    }
}
