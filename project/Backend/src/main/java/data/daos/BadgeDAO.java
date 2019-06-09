package data.daos;

import data.entities.Badge;


public interface BadgeDAO extends DAO<Integer , Badge> {
    Badge getBadgeByName(String name);
}
