package data.daos.impl;

import data.daos.TheaterDAO;
import data.entities.Theater;
import org.springframework.stereotype.Component;

import javax.persistence.EntityManager;
import javax.persistence.PersistenceContext;
import javax.persistence.Query;


@Component("theaterDAO")
public class TheaterDAOImpl extends DAOImpl<Integer , Theater> implements TheaterDAO {

}
