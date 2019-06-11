package data.daos;

import data.entities.Friendship;

import java.util.List;


public interface FriendshipDAO extends DAO<Integer , Friendship> {

    Friendship getFriendship(int sender, int receiver);
    void removeFriendship(int sender, int receiver);
    List requestsReceived(int id);
    List requestsSent(int id);
    String friendshipStatus(int selfId, String requestedUsername);

}
