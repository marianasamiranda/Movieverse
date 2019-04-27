package data.daos.impl;

import data.daos.MovieDAO;
import data.entities.Movie;
import org.springframework.stereotype.Component;


@Component("movieDAO")
public class MovieDAOImpl extends DAOImpl<Integer , Movie> implements MovieDAO {


}
