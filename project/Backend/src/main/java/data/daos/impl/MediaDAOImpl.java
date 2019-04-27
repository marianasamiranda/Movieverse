package data.daos.impl;

import data.daos.MediaDAO;
import data.entities.Media;
import org.springframework.stereotype.Component;


@Component("mediaDAO")
public class MediaDAOImpl extends DAOImpl<Integer , Media> implements MediaDAO {


}
