package data.daos.impl;

import data.daos.MovieMemberDAO;
import data.entities.MovieMember;
import org.springframework.stereotype.Repository;

import javax.persistence.EntityManager;
import javax.persistence.PersistenceContext;
import javax.persistence.Query;
import java.util.ArrayList;
import java.util.HashMap;
import java.util.List;
import java.util.Map;


@Repository
public class MovieMemberDAOImpl extends DAOImpl<Integer , MovieMember> implements MovieMemberDAO {

    @PersistenceContext
    private EntityManager entityManager;


    public Map listMainMovieMembers(int movieId, Integer limit) {
        Query query = entityManager.createNativeQuery( "WITH tempm AS (" +
                        "SELECT m.tmdb, m.name, mm.role, m.image, mm.isactor, mm.id " +
                        "FROM moviemember as mm " +
                        "LEFT JOIN member as m " +
                        "ON mm.memberid = m.tmdb where movieid=?1) " +
                        "(SELECT * FROM tempm where tempm.isactor=true order by tempm.id limit ?2) " +
                        "UNION ALL " +
                        "(SELECT * FROM tempm where tempm.isactor=false order by tempm.id limit ?2);\n")
                    .setParameter(1, movieId)
                    .setParameter(2, limit);

        List<Object[]> results = query.getResultList();

        List<Map> actors = new ArrayList<>();
        List<Map> crew = new ArrayList<>();

        results.forEach((record) -> {
            var map = new HashMap<>();
            map.put("memberId", record[0]);
            map.put("memberName", record[1]);
            map.put("memberRole", record[2]);
            map.put("memberImage", record[3]);
            if ((Boolean) record[4]){
                actors.add(map);
            }
            else{
                crew.add(map);
            }
        });

        Map<Integer, List> res = new HashMap<>();
        res.put(1, actors);
        res.put(0, crew);

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
