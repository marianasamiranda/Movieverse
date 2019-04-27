package data.daos.impl;

import data.daos.ShowtimeDAO;
import data.entities.Showtime;
import org.springframework.stereotype.Component;


@Component("showtimeDAO")
public class ShowtimeDAOImpl extends DAOImpl<Integer , Showtime> implements ShowtimeDAO {


}
