package data.daos.impl;

import data.daos.NewsDAO;
import data.entities.News;
import org.springframework.stereotype.Repository;


@Repository
public class NewsDAOImpl extends DAOImpl<Integer , News> implements NewsDAO {

}
