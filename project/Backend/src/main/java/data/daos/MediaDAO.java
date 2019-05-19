package data.daos;

import data.entities.Media;

import java.util.List;


public interface MediaDAO extends DAO<Integer , Media> {

    List<Object> getMemberBackdrops(int id);

    List<Object> getMovieMedia(int id, char type, int limit);

    List<Object> getMovieMedia(int id, char type);
}
