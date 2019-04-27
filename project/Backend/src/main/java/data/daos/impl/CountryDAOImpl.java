package data.daos.impl;

import data.daos.CountryDAO;
import data.entities.Country;
import org.springframework.stereotype.Component;


@Component("countryDAO")
public class CountryDAOImpl extends DAOImpl<Integer , Country> implements CountryDAO {


}
