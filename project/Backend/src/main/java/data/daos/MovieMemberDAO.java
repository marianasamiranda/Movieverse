package data.daos;

import data.entities.MovieMember;

import java.util.List;
import java.util.Map;


public interface MovieMemberDAO extends DAO<Integer , MovieMember> {

    public Map listMainMovieMembers(int movieId, Integer limit) ;
    List getMovieMembers(int movieId, boolean isActor, int offset, int limit);

}
