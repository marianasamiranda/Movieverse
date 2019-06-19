package data.daos;

import data.entities.UserMovie;


public interface UserMovieDAO extends DAO<Integer , UserMovie> {
    int totalMovieHours();
    void removeMovieFromUser(int muserId, int movieId);
    UserMovie getUserMovie(int muserId, int movieId);
}
