package data.daos.impl;

import data.daos.CompanyDAO;
import data.entities.Company;
import org.springframework.stereotype.Repository;


@Repository
public class CompanyDAOImpl extends DAOImpl<Integer , Company> implements CompanyDAO {


}
