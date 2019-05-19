package data.daos;


import data.entities.MUser;

import java.util.List;


public interface MUserDAO extends DAO<Integer , MUser> {

    MUser queryMUser(String condition);
    List<MUser> listRequestedMUser(int muserId);
    List<MUser> listReceivedMUser(int muserId);
    List<MUser> listFriends(int muserId);


}
