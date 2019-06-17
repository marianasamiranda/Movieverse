package data.daos;

import data.entities.Feed;


public interface FeedDAO extends DAO<Integer , Feed> {

    Feed getFeedWithType(Integer contentId, Integer type);

}
