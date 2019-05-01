package data.daos;


import data.entities.MUser;


public interface MUserDAO extends DAO<Integer , MUser> {

    public MUser loadEntityInitalize(String condition, String orderBy);
    
}
