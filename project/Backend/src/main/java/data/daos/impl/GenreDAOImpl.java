package data.daos.impl;

import data.daos.GenreDAO;
import data.entities.Country;
import data.entities.Genre;
import org.springframework.stereotype.Component;

import javax.persistence.Query;



@Component("genreDAO")
public class GenreDAOImpl extends DAOImpl<Integer , Genre> implements GenreDAO {


}
