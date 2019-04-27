package data.daos.impl;

import data.daos.AchievementDAO;
import data.entities.Achievement;
import org.springframework.stereotype.Component;


@Component("achievementDAO")
public class AchievementDAOImpl extends DAOImpl<Integer , Achievement> implements AchievementDAO {


}
