package data.daos.impl;

import data.DataUtil;
import data.daos.InvalidTokenException;
import data.daos.MUserDAO;
import data.entities.MUser;
import data.entities.Movie;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Component;
import org.springframework.transaction.annotation.Transactional;

import javax.persistence.EntityManager;
import javax.persistence.PersistenceContext;
import javax.persistence.Query;
import java.math.BigInteger;
import java.util.Arrays;
import java.util.HashMap;
import java.util.List;
import java.util.Map;


@Component("muserDAO")
public class MUserDAOImpl extends DAOImpl<Integer , MUser> implements MUserDAO {

    @PersistenceContext
	private EntityManager entityManager;

    @Autowired
    private DataUtil dataUtil;


    @Transactional(readOnly=true)
    public MUser queryMUser(String condition){
        Query query =  entityManager.createQuery(
            "SELECT c " +
            "FROM " + entityClass.getName() + " c " +
            "LEFT JOIN FETCH c.userCountry " +
            "LEFT JOIN FETCH c.favouriteGenre WHERE " + condition)
            .setMaxResults(1)
            .setHint("org.hibernate.cacheable", true);
        List<MUser> result = (List<MUser>) query.getResultList();
        return result.isEmpty() ? null : result.get(0);
    }


    public MUser getUserByEmail(String email) {
        return queryMUser("email='" + email + "'");
    }


    public MUser getUserByUsername(String username) {
        return queryMUser("username='" + username + "'");
    }


    public MUser validateToken(String token) throws InvalidTokenException {
        MUser u = queryMUser("token='" + token + "'");
        if (u == null)
            throw new InvalidTokenException();
        return u;
    }


    public MUser getSimpleUserByUsername(String username) {
        return loadEntity("username='" + username + "'");
    }


    public MUser getSimpleUserByToken(String token) throws InvalidTokenException {
        MUser u = loadEntity("token='" + token + "'");
        if (u == null)
            throw new InvalidTokenException();
        return u;
    }


    public List<MUser> listReceivedMUser(int muserId) {
        String query = "select m.* from friendship as f inner join muser as m on (f.pending='t' and f.receiver=" +  muserId + " and f.sender=m.id)";
        Query e_query =  entityManager.createNativeQuery(query, MUser.class);
        e_query.setHint("org.hibernate.cacheable", true);
        return e_query.getResultList();
    }


    public List<MUser> listRequestedMUser(int muserId) {
        String query = "SELECT m.* FROM friendship as f JOIN muser as m on (f.pending='t' and f.sender="+  muserId + " and f.receiver=m.id)"; ;
        Query e_query =  entityManager.createNativeQuery(query,MUser.class);
        e_query.setHint("org.hibernate.cacheable", true);
        return e_query.getResultList();
    }


    private static final String listFriendsQuery =
        "SELECT m.* " +
        "FROM Friendship AS f " +
        "JOIN Muser AS m ON (f.pending=FALSE " +
                             "AND ((f.sender=?1 AND f.receiver=m.id) " +
                                    "OR (f.sender=m.id AND f.receiver=?1)))";

    public List<MUser> listFriends(int muserId){
        Query query = entityManager.createNativeQuery(listFriendsQuery, MUser.class)
                                   .setParameter(1, muserId)
                                   .setHint("org.hibernate.cacheable", true);
        return query.getResultList();
    }

    public List<MUser> listFriends(int muserId, int begin, int limit) {
        Query query = entityManager.createNativeQuery(listFriendsQuery, MUser.class)
                                   .setParameter(1, muserId)
                                   .setFirstResult(begin)
                                   .setMaxResults(limit)
                                   .setHint("org.hibernate.cacheable", true);
        return query.getResultList();
    }


    private static final String favoriteMoviesQuery =
        "SELECT tmdb, poster, datefavourite, 'favourite' AS type " +
        "FROM UserMovie " +
        "JOIN Movie on Movie.tmdb = UserMovie.movieid " +
        "WHERE muserid = ?1 " +
        "AND favourite=TRUE " +
        "ORDER BY datefavourite DESC " +
        "OFFSET ?2 " +
        "LIMIT ?3";

    private static final String recentMoviesQuery =
        "SELECT tmdb, poster, datewatched, 'recent' AS type " +
        "FROM UserMovie " +
        "JOIN Movie on Movie.tmdb = UserMovie.movieid " +
        "WHERE muserid = ?1 " +
        "AND status=TRUE " +
        "ORDER BY datewatched DESC " +
        "OFFSET ?2 " +
        "LIMIT ?3";

    private static final String watchlistQuery =
        "SELECT tmdb, poster, NULL, 'watchlist' AS type " +
        "FROM UserMovie " +
        "JOIN Movie on Movie.tmdb = UserMovie.movieid " +
        "WHERE muserid = ?1 " +
        "AND status=FALSE " +
        "OFFSET ?2 " +
        "LIMIT ?3";

    //TODO: neste momento está a buscar os recomendados com todos
    //os tipos de filmes (vistos, favoritos, watchlist)
    //pode-se depois meter apenas os favoritos e ratings acima de 4 por exemplo
    private static final String recommendedMovies =
        "SELECT tmdb, poster, NULL, 'recommended' AS type " +
        "FROM movie " +
        "WHERE movie.tmdb IN (" +
            "SELECT recommended.movieid2 " +
            "FROM usermovie " +
            "JOIN movie ON movie.tmdb = usermovie.movieid " +
            "JOIN recommended ON recommended.movieid = movie.tmdb " +
            "WHERE muserid = ?1 " +
                "AND recommended.movieid2 NOT IN (" +
                    "SELECT movieid " +
                    "FROM usermovie " +
                    "WHERE muserid = ?1" +
                ") " +
                "GROUP BY recommended.movieid2 " +
                "ORDER BY COUNT(recommended.movieid2) DESC" +
        ") " +
        "OFFSET ?2 " +
        "LIMIT ?3";


    public List<Map> allMovieTypes(int muserId, int begin, int limit) {
        Query query = entityManager.createNativeQuery(
            "(" + favoriteMoviesQuery + ")" +
            "UNION ALL " +
            "(" + recentMoviesQuery + ")" +
            "UNION ALL " +
            "(" + watchlistQuery + ")" +
            "UNION ALL " +
            "(" + recommendedMovies + ")"
        ).setParameter(1, muserId)
         .setParameter(2, begin)
         .setParameter(3, limit);

        List<Object[]> l = query.getResultList();
        return dataUtil.queryListToListMap(l, Arrays.asList("id", "poster", "date", "type"));
    }


    public List<Map> favouriteMovies(int muserId, int begin, int limit) {
        Query query = entityManager.createNativeQuery(favoriteMoviesQuery)
                                   .setParameter(1, muserId)
                                   .setParameter(2, begin)
                                   .setParameter(3, limit);

        List<Object[]> l = query.getResultList();
        return dataUtil.queryListToListMap(l, Arrays.asList("id", "poster", "date"));
    }


    public List<Map> recentMovies(int muserId, int begin, int limit) {
        Query query = entityManager.createNativeQuery(recentMoviesQuery)
                                   .setParameter(1, muserId)
                                   .setParameter(2, begin)
                                   .setParameter(3, limit);

        List<Object[]> l = query.getResultList();
        return dataUtil.queryListToListMap(l, Arrays.asList("id", "poster", "date"));
    }


    public List<Map> watchlist(int muserId, int begin, int limit) {
        Query query = entityManager.createNativeQuery(watchlistQuery)
                                   .setParameter(1, muserId)
                                   .setParameter(2, begin)
                                   .setParameter(3, limit);

        List<Object[]> l = query.getResultList();
        return dataUtil.queryListToListMap(l, Arrays.asList("id", "poster"));
    }


    public List<Map> recommendedMovies(int muserId, int begin, int limit) {
        Query query = entityManager.createNativeQuery(recommendedMovies)
                                   .setParameter(1, muserId)
                                   .setParameter(2, begin)
                                   .setParameter(3, limit);

        List<Object[]> l = query.getResultList();
        l.forEach(x -> System.out.println(x[0]));
        return dataUtil.queryListToListMap(l, Arrays.asList("id", "poster"));
    }


    public int totalNumberOfLikes() {
        Query query = entityManager.createNativeQuery("SELECT * FROM TotalLikes");
        return ((BigInteger) query.getSingleResult()).intValue();
    }

    public Map genderCount() {
        Query query = entityManager.createNativeQuery("SELECT * FROM GenderCount");
        List<Object[]> results = query.getResultList();
        Map m = new HashMap();
        results.forEach(x -> m.put(x[0], x[1]));
        return m;
    }

    public Map countryCount() {
        Query query = entityManager.createNativeQuery("SELECT * FROM CountryCount");
        List<Object[]> results = query.getResultList();
        Map m = new HashMap();
        results.forEach(x -> m.put(x[0], x[1]));
        return m;
    }
}
