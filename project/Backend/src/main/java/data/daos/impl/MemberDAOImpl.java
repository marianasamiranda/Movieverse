package data.daos.impl;

import data.DataUtil;
import data.daos.MemberDAO;
import data.entities.Member;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Component;

import javax.persistence.EntityManager;
import javax.persistence.PersistenceContext;
import javax.persistence.Query;
import java.util.Arrays;
import java.util.List;


@Component("memberDAO")
public class MemberDAOImpl extends DAOImpl<Integer , Member> implements MemberDAO {

    @PersistenceContext
    private EntityManager entityManager;

    @Autowired
    private DataUtil dataUtil;


    public List bornToday(int begin, int limit) {
        Query query = entityManager.createNativeQuery(
            "SELECT * " +
            "FROM BornToday " +
            "OFFSET ?1 " +
            "LIMIT ?2"
        ).setParameter(1, begin)
         .setParameter(2, limit);

        List<Object[]> l = query.getResultList();
        return dataUtil.queryListToListMap(l, Arrays.asList("id", "name", "image", "age"));
    }

    public List mostCredits(int begin, int limit) {
        Query query = entityManager.createNativeQuery(
            "SELECT * " +
            "FROM MostCredits " +
            "OFFSET ?1 " +
            "LIMIT ?2"
        ).setParameter(1, begin)
         .setParameter(2, limit);

        List<Object[]> l = query.getResultList();
        return dataUtil.queryListToListMap(l, Arrays.asList("id", "name", "image", "total"));
    }
}
