package data.daos.impl;

import data.DataUtil;
import data.daos.FriendshipDAO;
import data.entities.Friendship;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Repository;

import javax.persistence.EntityManager;
import javax.persistence.PersistenceContext;
import javax.persistence.Query;
import java.util.*;


@Repository
public class FriendshipDAOImpl extends DAOImpl<Integer , Friendship> implements FriendshipDAO {

    @PersistenceContext
	private EntityManager entityManager;

    @Autowired
    private DataUtil dataUtil;

    public Friendship getFriendship(int sender, int receiver) {
        return loadEntity("sender=" + sender + "and receiver= " +  receiver);
    }


    public void removeFriendship(int sender, int receiver) {
        removeEntity("sender=" + sender + "and receiver= " +  receiver);
    }


    public List requestsReceived(int id) {
        Query query = entityManager.createNativeQuery("SELECT * FROM requestsReceived(?1)")
                                   .setParameter(1, id);
        List<Object[]> l = query.getResultList();
        return dataUtil.queryListToListMap(l, Arrays.asList("username", "name", "country", "avatar", "common"));
    }


    public List requestsSent(int id) {
        Query query = entityManager.createNativeQuery("SELECT * FROM requestsSent(?1)")
                                   .setParameter(1, id);
        List<Object[]> l = query.getResultList();
        return dataUtil.queryListToListMap(l, Arrays.asList("username", "name", "country", "avatar", "common"));
    }


    public String friendshipStatus(int selfId, String requestedUsername) {
        Query query = entityManager.createNativeQuery(
         "SELECT f.pending, f.sender " +
            "FROM Friendship AS f " +
            "JOIN Muser AS m ON (m.username = ?2 AND f.sender=?1 AND f.receiver=m.id) " +
                                        "OR (m.username = ?2 AND f.sender=m.id AND f.receiver=?1)")
                .setParameter(1,selfId)
                .setParameter(2,requestedUsername);
         List<Object[]> results = query.getResultList();

         if (results.isEmpty())
                return null;

         Boolean pending = (Boolean) results.get(0)[0];
         Integer sender = (Integer) results.get(0)[1];

         if (pending)
             return sender == selfId ? "requested" : "received";
         else
             return "friends";
    }
}
