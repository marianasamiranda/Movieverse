package data.daos.impl;

import data.daos.FriendshipDAO;
import data.entities.Friendship;
import org.springframework.stereotype.Component;


@Component("friendshipDAO")
public class FriendshipDAOImpl extends DAOImpl<Integer , Friendship> implements FriendshipDAO {

    public Friendship getFriendship(int sender, int receiver) {
        return loadEntity("sender=" + sender + "and receiver= " +  receiver);
    }
}
