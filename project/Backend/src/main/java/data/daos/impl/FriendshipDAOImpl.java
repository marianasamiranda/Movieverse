package data.daos.impl;

import data.daos.FriendshipDAO;
import data.entities.Friendship;
import org.springframework.stereotype.Component;


@Component("friendshipDAO")
public class FriendshipDAOImpl extends DAOImpl<Integer , Friendship> implements FriendshipDAO {


}
