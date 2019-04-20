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

import java.util.List;
import org.hibernate.criterion.DetachedCriteria;
import org.orm.PersistentSession;
import org.orm.criteria.*;

public class MediaDetachedCriteria extends AbstractORMDetachedCriteria {
	public final IntegerExpression id;
	public final IntegerExpression idType;
	public final StringExpression path;
	
	public MediaDetachedCriteria() {
		super(data.movieverse.Media.class, data.movieverse.MediaCriteria.class);
		id = new IntegerExpression("id", this.getDetachedCriteria());
		idType = new IntegerExpression("idType", this.getDetachedCriteria());
		path = new StringExpression("path", this.getDetachedCriteria());
	}
	
	public MediaDetachedCriteria(DetachedCriteria aDetachedCriteria) {
		super(aDetachedCriteria, data.movieverse.MediaCriteria.class);
		id = new IntegerExpression("id", this.getDetachedCriteria());
		idType = new IntegerExpression("idType", this.getDetachedCriteria());
		path = new StringExpression("path", this.getDetachedCriteria());
	}
	
	public Media uniqueMedia(PersistentSession session) {
		return (Media) super.createExecutableCriteria(session).uniqueResult();
	}
	
	public Media[] listMedia(PersistentSession session) {
		List list = super.createExecutableCriteria(session).list();
		return (Media[]) list.toArray(new Media[list.size()]);
	}
}

