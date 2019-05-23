package data.daos;

import data.entities.Media;

import java.util.List;
import java.util.Map;


public interface MediaDAO extends DAO<Integer , Media> {

    List<Object> getMemberBackdrops(int id);

    Map getMovieMedia(int id);

    List<Object> getMovieMedia(int id, char type);
}
