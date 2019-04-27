package data.daos.impl;

import data.daos.TheaterDAO;
import data.daos.UserMovieDAO;
import data.entities.Theater;
import data.entities.UserMovie;
import org.springframework.stereotype.Component;


@Component("userMovieDAO")
public class UserMovieDAOImpl extends DAOImpl<Integer , UserMovie> implements UserMovieDAO {


}
