package data.daos.impl;

import data.daos.MediaDAO;
import data.entities.Media;
import org.springframework.stereotype.Component;
import org.springframework.transaction.annotation.Transactional;

import javax.persistence.EntityManager;
import javax.persistence.PersistenceContext;
import javax.persistence.Query;
import java.util.ArrayList;
import java.util.List;


@Component("mediaDAO")
public class MediaDAOImpl extends DAOImpl<Integer , Media> implements MediaDAO {

    @PersistenceContext
    private EntityManager entityManager;

    @Transactional(readOnly=true)
    public List<Object> getMemberBackdrops(int id){
        Query query = entityManager.createNativeQuery("SELECT m.path FROM Media m where m.memberid = " + id);

        List<Object> results = query.getResultList();

        List<Object> res = new ArrayList<>();

        results.stream().forEach((record) -> {
            String path = (String) record;
            System.out.println(path);

            res.add(path);
        });

        return res;
    }


}
