package data.daos.impl;

import data.daos.FeedDAO;
import data.entities.Feed;
import org.springframework.stereotype.Repository;

import javax.persistence.EntityManager;
import javax.persistence.PersistenceContext;
import javax.persistence.Query;
import java.util.List;


@Repository
public class FeedDAOImpl extends DAOImpl<Integer , Feed> implements FeedDAO {

    @PersistenceContext
	private EntityManager entityManager;
    

    public Feed getFeedWithType(int muserId, int contentId, int type) {
       Query query =  entityManager.createQuery(
            "SELECT c FROM Feed c WHERE muserid=?1 and idContent=?2 and type=?3")
               .setParameter(1, muserId)
               .setParameter(2, contentId)
               .setParameter(3, type);

        List<Feed> result = (List<Feed>) query.getResultList();
        return result.isEmpty() ? null : result.get(0);

    }

    public void removeFromFeed(int muserId, int contentId){
        Query query =  entityManager.createQuery(
                "DELETE FROM Feed c WHERE muserid=?1 and idcontent=?2")
                .setParameter(1, muserId)
                .setParameter(2, contentId);
        query.executeUpdate();

    }

    public void removeFromFeed(int muserId, int contentId, int type){
        Query query =  entityManager.createQuery(
                "DELETE FROM Feed c WHERE muserid=?1 and idcontent=?2 and type=?3")
                .setParameter(1, muserId)
                .setParameter(2, contentId)
                .setParameter(3, type);
        query.executeUpdate();

    }
}
