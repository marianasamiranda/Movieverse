package data.daos;

import data.entities.Media;

import java.util.List;


public interface MediaDAO extends DAO<Integer , Media> {

    List<Object> getMemberBackdrops(int id);

}
