package data.daos.impl;

import data.daos.GenreDAO;
import data.entities.Genre;
import org.springframework.stereotype.Repository;


@Repository
public class GenreDAOImpl extends DAOImpl<Integer , Genre> implements GenreDAO {


}
