package data.daos;

import data.entities.Feed;


public interface FeedDAO extends DAO<Integer , Feed> {

    Feed getFeedWithType(int muserId, int contentId, int type);
    void removeFromFeed(int muserId, int contentId);
    void removeFromFeed(int muserId, int contentId, int type);

}
