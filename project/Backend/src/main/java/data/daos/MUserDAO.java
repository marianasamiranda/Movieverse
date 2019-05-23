package data.daos;


import data.entities.MUser;

import java.util.List;
import java.util.Map;


public interface MUserDAO extends DAO<Integer , MUser> {

    MUser queryMUser(String condition);
    List<MUser> listRequestedMUser(int muserId);
    List<MUser> listReceivedMUser(int muserId);
    List<MUser> listFriends(int muserId);
    List<Map> favouriteMovies(int muserId, int begin, int limit);
    List<Map> recentMovies(int muserId, int begin, int limit);
    List<Map> watchlist(int muserId, int begin, int limit);
    List<Map> recommendedMovies(int muserId, int begin, int limit);
    List<Map> allMovieTypes(int muserId, int begin, int limit);
}
