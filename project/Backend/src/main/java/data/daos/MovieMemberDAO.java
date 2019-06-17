package data.daos;

import data.entities.MovieMember;

import java.util.List;


public interface MovieMemberDAO extends DAO<Integer , MovieMember> {

    List listMainMovieMembers(int movieId, boolean isActor, Integer limit);
    List getMovieMembers(int movieId, boolean isActor, int offset, int limit);

}
