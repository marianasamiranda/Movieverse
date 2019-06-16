package data.daos;


import data.entities.MUser;

import java.util.List;
import java.util.Map;


public interface MUserDAO extends DAO<Integer , MUser> {

    MUser queryMUser(String condition);
    MUser getUserByEmail(String email);
    MUser getUserByUsername(String username);
    MUser validateToken(String token) throws InvalidTokenException;
    MUser getSimpleUserByUsername(String username);
    MUser getSimpleUserByToken(String token) throws InvalidTokenException;
    List<MUser> listRequestedMUser(int muserId);
    List<MUser> listReceivedMUser(int muserId);
    List<MUser> listFriends(int muserId);
    List<MUser> listFriends(int muserId, int begin, int limit);
    List<Map> favouriteMovies(int muserId, int begin, int limit);
    List<Map> recentMovies(int muserId, int begin, int limit);
    List<Map> watchlist(int muserId, int begin, int limit);
    List<Map> recommendedMovies(int muserId, int begin, int limit);
    List<Map> allMovieTypes(int muserId, int begin, int limit);
    List<Map> getFeedEntries(int userId, int offset, int limit);
    int totalNumberOfLikes();
    Map genderCount();
    Map countryCount();
    List<MUser> nYearsWithoutBadge(int years);
    boolean hasBadge(String username, String badgeName);
    List<Map> topUpvotedUsers(int size);
}
