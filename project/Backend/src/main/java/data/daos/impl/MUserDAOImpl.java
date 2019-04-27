package data.daos.impl;

import data.daos.MUserDAO;
import data.entities.MUser;
import org.springframework.stereotype.Component;


@Component("muserDAO")
public class MUserDAOImpl extends DAOImpl<Integer , MUser> implements MUserDAO {


   
}
