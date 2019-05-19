package data.daos.impl;

import data.daos.MUserDAO;
import data.entities.Friendship;
import data.entities.MUser;
import org.apache.lucene.analysis.hi.HindiAnalyzer;
import org.hibernate.Hibernate;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Component;
import org.springframework.transaction.annotation.Transactional;

import javax.persistence.EntityManager;
import javax.persistence.PersistenceContext;
import javax.persistence.Query;
import java.util.List;
import java.util.Set;
import java.util.stream.Collectors;


@Component("muserDAO")
public class MUserDAOImpl extends DAOImpl<Integer , MUser> implements MUserDAO {


    @PersistenceContext
	private EntityManager entityManager;

    @Transactional(readOnly=true)
    public MUser queryMUser(String condition){
        Query query =  entityManager.createQuery("SELECT c FROM "+ entityClass.getName() + " c left join fetch c.userCountry left join fetch c.favouriteGenre WHERE " + condition);
        query.setHint("org.hibernate.cacheable", true);
        List<MUser> result = (List<MUser>) query.getResultList();
        return result.isEmpty() ? null : result.get(0);
    }


    public List<MUser> listReceivedMUser(int muserId) {
        String query = "select m.* from friendship as f inner join muser as m on (f.pending='t' and f.receiver=" +  muserId + " and f.sender=m.id)";
        Query e_query =  entityManager.createNativeQuery(query, MUser.class);
        e_query.setHint("org.hibernate.cacheable", true);
        return e_query.getResultList();
    }

    public List<MUser> listRequestedMUser(int muserId) {
        String query = "SELECT m.* FROM friendship as f JOIN muser as m on (f.pending='t' and f.sender="+  muserId + " and f.receiver=m.id)"; ;
        Query e_query =  entityManager.createNativeQuery(query,MUser.class);
        e_query.setHint("org.hibernate.cacheable", true);
        return e_query.getResultList();
    }


    public List<MUser> listFriends(int muserId){
        String query = "SELECT m.* FROM friendship as f JOIN muser as m on (f.pending='f' and ((f.sender="+  muserId + " and f.receiver=m.id) or (f.sender=m.id and f.receiver="+  muserId + ")))";
        Query e_query =  entityManager.createNativeQuery(query,MUser.class);
        e_query.setHint("org.hibernate.cacheable", true);
        return e_query.getResultList();
    }

   
}
