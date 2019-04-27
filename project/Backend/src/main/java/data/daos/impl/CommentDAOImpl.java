package data.daos.impl;

import data.daos.CommentDAO;
import data.entities.Comment;
import org.springframework.stereotype.Component;


@Component("commentDAO")
public class CommentDAOImpl extends DAOImpl<Integer , Comment> implements CommentDAO {


}
