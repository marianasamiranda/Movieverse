package data.daos.impl;

import data.daos.FeedDAO;
import data.entities.Feed;
import org.springframework.stereotype.Component;


@Component("feedDAO")
public class FeedDAOImpl extends DAOImpl<Integer , Feed> implements FeedDAO {


}
