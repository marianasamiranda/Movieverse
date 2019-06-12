package data.daos;

import data.entities.Comment;

import java.util.List;


public interface CommentDAO extends DAO<Integer , Comment> {

    List getCommentsMovie(int movieId, int offset, int limit);
    List getCommentsMovieWithUserLikes(int movieId, int offset, int limit, int userId);
    List getRepliesComment(int commentId, int offset, int limit);
    List getRepliesCommentWithUserLikes(int commentId, int offset, int limit, int userId);

}
