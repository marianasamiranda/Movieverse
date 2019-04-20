/**
 * "Visual Paradigm: DO NOT MODIFY THIS FILE!"
 * 
 * This is an automatic generated file. It will be regenerated every time 
 * you generate persistence class.
 * 
 * Modifying its content may cause the program not work, or your work may lost.
 */

/**
 * Licensee: mariana(Universidade do Minho)
 * License Type: Academic
 */
package data.movieverse;

import org.hibernate.Criteria;
import org.orm.PersistentException;
import org.orm.PersistentSession;
import org.orm.criteria.*;

public class MediaCriteria extends AbstractORMCriteria {
	public final IntegerExpression id;
	public final IntegerExpression idType;
	public final StringExpression path;
	
	public MediaCriteria(Criteria criteria) {
		super(criteria);
		id = new IntegerExpression("id", this);
		idType = new IntegerExpression("idType", this);
		path = new StringExpression("path", this);
	}
	
	public MediaCriteria(PersistentSession session) {
		this(session.createCriteria(Media.class));
	}
	
	public MediaCriteria() throws PersistentException {
		this(MovieversePersistentManager.instance().getSession());
	}
	
	public Media uniqueMedia() {
		return (Media) super.uniqueResult();
	}
	
	public Media[] listMedia() {
		java.util.List list = super.list();
		return (Media[]) list.toArray(new Media[list.size()]);
	}
}

