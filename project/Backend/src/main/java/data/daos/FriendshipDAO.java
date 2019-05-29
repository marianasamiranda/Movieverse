package data.daos;

import data.entities.Friendship;


public interface FriendshipDAO extends DAO<Integer , Friendship> {

    public Friendship getFriendship(int sender, int receiver);
}
