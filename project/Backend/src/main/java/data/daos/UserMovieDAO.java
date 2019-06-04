package data.daos;

import data.entities.UserMovie;


public interface UserMovieDAO extends DAO<Integer , UserMovie> {
    int totalMovieHours();
}
