package data.daos.impl;

import data.daos.MemberDAO;
import data.entities.Member;
import org.springframework.stereotype.Component;


@Component("memberDAO")
public class MemberDAOImpl extends DAOImpl<Integer , Member> implements MemberDAO {


}
