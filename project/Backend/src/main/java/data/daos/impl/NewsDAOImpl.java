package data.daos.impl;

import data.daos.NewsDAO;
import data.entities.News;
import org.springframework.stereotype.Component;

import java.util.ArrayList;
import java.util.HashMap;
import java.util.List;
import java.util.Map;


@Component("newsDAO")
public class NewsDAOImpl extends DAOImpl<Integer , News> implements NewsDAO {

}
