package data.daos.impl;

import data.daos.BadgeDAO;
import data.entities.Badge;
import org.springframework.stereotype.Component;


@Component("badgeDAO")
public class BadgeDAOImpl extends DAOImpl<Integer , Badge> implements BadgeDAO {


}
