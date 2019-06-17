package data.daos.impl;

import data.daos.FeedDAO;
import data.entities.Feed;
import org.springframework.stereotype.Component;


@Component("feedDAO")
public class FeedDAOImpl extends DAOImpl<Integer , Feed> implements FeedDAO {

    public Feed getFeedWithType(Integer contentId, Integer type) {
        try {
            return loadEntity("idContent=" + contentId + " and type=" + type);
        }
        catch (Exception e) {
            return null;
        }
    }
}
