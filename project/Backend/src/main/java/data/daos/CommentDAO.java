package data.daos;

import data.entities.Comment;

import java.util.List;


public interface CommentDAO extends DAO<Integer , Comment> {

    List getCommentsMovie(int movieId);

}
