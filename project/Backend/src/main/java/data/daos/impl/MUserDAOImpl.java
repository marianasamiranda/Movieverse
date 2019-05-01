package data.daos.impl;

import data.daos.MUserDAO;
import data.entities.MUser;
import org.apache.lucene.analysis.hi.HindiAnalyzer;
import org.hibernate.Hibernate;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Component;
import org.springframework.transaction.annotation.Transactional;

import javax.persistence.EntityManager;
import javax.persistence.PersistenceContext;
import javax.persistence.Query;


@Component("muserDAO")
public class MUserDAOImpl extends DAOImpl<Integer , MUser> implements MUserDAO {


    @PersistenceContext
	private EntityManager entityManager;

    @Transactional(readOnly=true)
	public MUser loadEntityInitalize(String condition, String orderBy){
        Query query =  entityManager.createQuery("SELECT c FROM "+ entityClass.getName() + " c WHERE " + condition + " ORDER BY " + orderBy);
        MUser muser = (MUser) query.getSingleResult();
        Hibernate.initialize(muser.getBadges());
        Hibernate.initialize(muser.getReceivedFriendships());
        Hibernate.initialize(muser.getRequestedFriendships());
        Hibernate.initialize(muser.getFriends());
        return muser;
    }
   
}
