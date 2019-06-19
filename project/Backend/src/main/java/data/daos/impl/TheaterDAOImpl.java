package data.daos.impl;

import data.daos.TheaterDAO;
import data.entities.Theater;
import org.springframework.stereotype.Repository;

@Repository
public class TheaterDAOImpl extends DAOImpl<Integer , Theater> implements TheaterDAO {

}
