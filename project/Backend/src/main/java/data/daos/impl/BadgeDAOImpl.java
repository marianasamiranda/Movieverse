package data.daos.impl;

import data.daos.BadgeDAO;
import data.entities.Badge;
import org.springframework.stereotype.Component;

import javax.persistence.EntityManager;
import javax.persistence.PersistenceContext;
import javax.persistence.Query;


@Component("badgeDAO")
public class BadgeDAOImpl extends DAOImpl<Integer , Badge> implements BadgeDAO {

    @PersistenceContext
    private EntityManager entityManager;

    public Badge getBadgeByName(String name) {
        Query query = entityManager.createQuery(
            "SELECT b " +
            "FROM Badge b " +
            "WHERE name = ?1",
            Badge.class
        ).setParameter(1, name);

        return (Badge) query.getSingleResult();
    }

}
