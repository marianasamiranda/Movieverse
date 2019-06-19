package data.daos.impl;

import data.daos.CountryDAO;
import data.entities.Country;
import org.springframework.stereotype.Repository;


@Repository
public class CountryDAOImpl extends DAOImpl<Integer , Country> implements CountryDAO {


}
