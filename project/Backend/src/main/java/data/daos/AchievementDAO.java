package data.daos;

import data.entities.Achievement;


public interface AchievementDAO extends DAO<Integer , Achievement> {

    public int getMaxLikeBadge(int muserId) ;

}
