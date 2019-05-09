package data.daos.impl;

import data.daos.MovieDAO;
import data.entities.Movie;
import org.hibernate.Hibernate;
import org.springframework.stereotype.Component;
import org.springframework.transaction.annotation.Transactional;

import javax.persistence.Query;


@Component("movieDAO")
public class MovieDAOImpl extends DAOImpl<Integer , Movie> implements MovieDAO {

    @Transactional(readOnly=true)
    public Movie loadEntityEager(String condition) {
        Query query =  entityManager.createQuery("SELECT c FROM "+ entityClass.getName() + " c WHERE " + condition);
        Movie result = (Movie) query.getSingleResult();
        // TODO: mudar para fetch
        Hibernate.initialize(result.getGenres());
        Hibernate.initialize(result.getCompanies());
        return result;

    }

}
