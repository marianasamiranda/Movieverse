package data.daos.impl;

import data.DataUtil;
import data.daos.InvalidTokenException;
import data.daos.MUserDAO;
import data.entities.MUser;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Repository;
import org.springframework.transaction.annotation.Transactional;

import javax.persistence.EntityManager;
import javax.persistence.PersistenceContext;
import javax.persistence.Query;
import java.math.BigInteger;
import java.util.Arrays;
import java.util.HashMap;
import java.util.List;
import java.util.Map;
import java.util.stream.Collectors;


@Repository
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
            .setMaxResults(1);
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
        String query = "SELECT m FROM " + entityClass.getName() + " as m inner join m.receivedFriendships as f where f.pending='t' and f.receivedMuser.id=" +  muserId;
        Query e_query =  entityManager.createQuery(query);
        return e_query.getResultList();
    }

    public List<MUser> listRequestedMUser(int muserId) {
        String query = "SELECT m FROM " + entityClass.getName() + " as m inner join m.requestedFriendships as f where f.pending='t' and f.requestedMuser.id=" +  muserId;
        Query e_query =  entityManager.createQuery(query);
        return e_query.getResultList();
    }

    public List<Map> getFeedEntries(int userId, int offset, int limit) {

        Query query = entityManager.createNativeQuery("SELECT * from feedEntries(?1, ?2, ?3);")
                .setParameter(1, userId)
                .setParameter(2, offset)
                .setParameter(3, limit);
        List<Object[]> results = query.getResultList();

        return dataUtil.queryListToListMap(results, Arrays.asList("userid", "username","usergender", "avatar", "timestmp", "type", "movieid","moviename", "movieposter","rating", "commentid", "comment")).stream()
                .map(entry -> {
                    if (entry.get("avatar") == null) entry.put("avatar", entry.get("usergender") + ".svg");
                    entry.remove("usergender");
                    return entry;
                }).collect(Collectors.toList());
    }

    private static final String listFriendsQuery =
        "SELECT m.* " +
        "FROM Friendship AS f " +
        "JOIN Muser AS m ON (f.pending=FALSE " +
                             "AND ((f.sender=?1 AND f.receiver=m.id) " +
                                    "OR (f.sender=m.id AND f.receiver=?1)))";

    public List<MUser> listFriends(int muserId){
        Query query = entityManager.createNativeQuery(listFriendsQuery, MUser.class)
                                   .setParameter(1, muserId);
        return query.getResultList();
    }

    public List<MUser> listFriends(int muserId, int begin, int limit) {
        Query query = entityManager.createNativeQuery(listFriendsQuery, MUser.class)
                                   .setParameter(1, muserId)
                                   .setFirstResult(begin)
                                   .setMaxResults(limit);
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


    private final static List<String> badgesNameYears = Arrays.asList(
        "1 year old", "2 years old", "3 years old", "4 years old", "5 years old"
    );

    @Transactional
    public List<MUser> nYearsWithoutBadge(int years) {
        Query query = entityManager.createNativeQuery(
            "SELECT Muser.* " +
            "FROM Muser " +
            "LEFT JOIN " +
                "(SELECT muserid " +
                "FROM Badge INNER JOIN Achievement " +
                "ON Badge.name=?1 AND " +
                "Achievement.badgeid = Badge.id) as T " +
                "ON T.muserid = Muser.id " +
            "WHERE T IS NULL " +
                "AND CURRENT_DATE - muser.joindate >= ?2",
            MUser.class
        ).setParameter(1, badgesNameYears.get(years - 1))
         .setParameter(2, 365 * years);

        return (List<MUser>) query.getResultList();
    }


    public boolean hasBadge(String username, String badgeName) {
        Query query = entityManager.createNativeQuery(
            "SELECT 1 " +
            "FROM Muser " +
            "JOIN Achievement ON Achievement.muserid = Muser.id " +
            "JOIN Badge ON Badge.id = Achievement.badgeid " +
            "WHERE Muser.username = ?1 " +
                "AND badge.name = ?2"
        ).setParameter(1, username)
         .setParameter(2, badgeName);

        return !query.getResultList().isEmpty();
    }


    public List<Map> topUpvotedUsers(int size) {
        Query query = entityManager.createNativeQuery("SELECT * FROM TopLikedUsers")
                                   .setMaxResults(size);

        List<Object[]> results = query.getResultList();
        return dataUtil.queryListToListMap(results,
                Arrays.asList("username", "name", "avatar", "country", "gender", "count"));
    }
}
