package data.daos;

import data.entities.Showtime;

import java.util.List;


public interface ShowtimeDAO extends DAO<Integer , Showtime> {

    List getShowtimes(int theater);
}
