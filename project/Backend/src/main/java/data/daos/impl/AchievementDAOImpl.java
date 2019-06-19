package data.daos.impl;

import data.daos.AchievementDAO;
import data.entities.Achievement;
import org.springframework.stereotype.Repository;

import javax.persistence.EntityManager;
import javax.persistence.PersistenceContext;
import javax.persistence.Query;


@Repository
public class AchievementDAOImpl extends DAOImpl<Integer , Achievement> implements AchievementDAO {

    @PersistenceContext
    private EntityManager entityManager;

    public int getMaxLikeBadge(int muserId) {
        Query query = entityManager.createNativeQuery("select count(*) from achievement as a inner join badge as b on a.badgeid = b.id where a.muserid=?1 and b.name LIKE '%like%';")
                .setParameter(1, muserId);
        return query.getFirstResult();
    }



}
