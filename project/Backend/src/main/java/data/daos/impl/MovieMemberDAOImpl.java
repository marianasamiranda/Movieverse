package data.daos.impl;

import data.daos.MovieMemberDAO;
import data.entities.MovieMember;
import org.springframework.stereotype.Component;


@Component("movieMemberDAO")
public class MovieMemberDAOImpl extends DAOImpl<Integer , MovieMember> implements MovieMemberDAO {


}
