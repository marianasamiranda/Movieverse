package data.daos.impl;

import data.daos.UserMovieDAO;
import data.entities.UserMovie;
import org.springframework.stereotype.Component;

import javax.persistence.EntityManager;
import javax.persistence.PersistenceContext;
import javax.persistence.Query;
import java.math.BigInteger;


@Component("userMovieDAO")
public class UserMovieDAOImpl extends DAOImpl<Integer , UserMovie> implements UserMovieDAO {

    @PersistenceContext
    private EntityManager entityManager;

    public int totalMovieHours() {
        Query query = entityManager.createNativeQuery("SELECT * FROM TotalWatchedHours");
        return ((BigInteger) query.getSingleResult()).intValue();
    }
}
