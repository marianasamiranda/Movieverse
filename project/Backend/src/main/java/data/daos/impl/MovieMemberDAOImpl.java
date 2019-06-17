package data.daos.impl;

import data.daos.MovieMemberDAO;
import data.entities.MovieMember;
import org.springframework.stereotype.Component;

import javax.persistence.EntityManager;
import javax.persistence.PersistenceContext;
import javax.persistence.Query;
import java.util.ArrayList;
import java.util.HashMap;
import java.util.List;
import java.util.Map;


@Component("movieMemberDAO")
public class MovieMemberDAOImpl extends DAOImpl<Integer , MovieMember> implements MovieMemberDAO {

    @PersistenceContext
    private EntityManager entityManager;


    public List listMainMovieMembers(int movieId, boolean isActor, Integer limit) {
        Query query = entityManager.createNativeQuery("SELECT m.tmdb, m.name, mm.role, m.image FROM moviemember as mm" +
                    " LEFT JOIN member as m ON mm.memberid = m.tmdb" +
                    " WHERE movieid=?1 AND isActor=?2 ORDER BY mm.id")
                    .setParameter(1, movieId)
                    .setParameter(2, isActor)
                    .setMaxResults(limit);

        List<Map> res = new ArrayList<>();
        List<Object[]> results = query.getResultList();

        results.forEach((record) -> {
            var map = new HashMap<>();
            map.put("memberId", record[0]);
            map.put("memberName", record[1]);
            map.put("memberRole", record[2]);
            map.put("memberImage", record[3]);
            res.add(map);
        });

        return res;
    }

    public List getMovieMembers(int movieId, boolean isActor, int offset, int limit) {
        Query query = entityManager.createNativeQuery("SELECT m.tmdb, m.name, mm.role, m.image FROM moviemember as mm" +
                " LEFT JOIN member as m ON mm.memberid = m.tmdb" +
                " WHERE movieid=?1 AND isActor=?2 ORDER BY mm.id")
                .setParameter(1, movieId)
                .setParameter(2, isActor)
                .setFirstResult(offset)
                .setMaxResults(limit);

        List<Map> res = new ArrayList<>();
        List<Object[]> results = query.getResultList();

        results.forEach((record) -> {
            var map = new HashMap<>();
            map.put("memberId", record[0]);
            map.put("memberName", record[1]);
            map.put("memberRole", record[2]);
            map.put("memberImage", record[3]);
            res.add(map);
        });

        return res;
    }
}
