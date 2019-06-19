package data.daos.impl;

import data.daos.UserMovieDAO;
import data.entities.MUser;
import data.entities.UserMovie;
import org.hibernate.Criteria;
import org.hibernate.criterion.Restrictions;
import org.springframework.stereotype.Component;
import org.springframework.stereotype.Repository;
import org.springframework.transaction.annotation.Transactional;

import javax.persistence.EntityManager;
import javax.persistence.PersistenceContext;
import javax.persistence.Query;
import javax.persistence.criteria.CriteriaBuilder;
import javax.persistence.criteria.CriteriaQuery;
import javax.persistence.criteria.Predicate;
import javax.persistence.criteria.Root;
import java.math.BigInteger;
import java.util.List;


@Repository
public class UserMovieDAOImpl extends DAOImpl<Integer , UserMovie> implements UserMovieDAO {

    @PersistenceContext
    private EntityManager entityManager;

    public int totalMovieHours() {
        Query query = entityManager.createNativeQuery("SELECT * FROM TotalWatchedHours");
        return ((BigInteger) query.getSingleResult()).intValue();
    }

    public void removeMovieFromUser(int muserId, int movieId){
         Query query =  entityManager.createQuery(
                "DELETE FROM UserMovie c WHERE muserid=?1 and movieid=?2")
                .setParameter(1, muserId)
                .setParameter(2, movieId);
         query.executeUpdate();
    }

    @Transactional
    public UserMovie getUserMovie(int muserId, int movieId){
       Query query =  entityManager.createQuery(
                "SELECT c FROM UserMovie c WHERE muserid=?1 and movieid=?2")
                .setParameter(1, muserId)
                .setParameter(2, movieId);
        List<UserMovie> result = (List<UserMovie>) query.getResultList();
        return result.isEmpty() ? null : result.get(0);
    }
}
