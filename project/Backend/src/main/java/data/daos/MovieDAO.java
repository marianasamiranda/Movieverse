package data.daos;

import data.entities.Movie;

import java.util.List;
import java.util.Map;


public interface MovieDAO extends DAO<Integer, Movie> {

    Movie loadEntityEager(String condition);
    List<Map<String, Object>> getMemberMoviesFromTo(int memberId, int offset, int limit);
    List getLatestMovies(int begin, int limit);
    List getPopularMovies(int begin, int limit);
    List getUpcomingMovies(int begin, int limit);
    List getRandomUpcomingMovies(int limit);

}
