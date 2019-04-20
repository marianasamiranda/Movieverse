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

public class FeedDetachedCriteria extends AbstractORMDetachedCriteria {
	public final IntegerExpression id;
	public final IntegerExpression userId;
	public final AssociationExpression user;
	public final IntegerExpression type;
	public final IntegerExpression idContent;
	
	public FeedDetachedCriteria() {
		super(data.movieverse.Feed.class, data.movieverse.FeedCriteria.class);
		id = new IntegerExpression("id", this.getDetachedCriteria());
		userId = new IntegerExpression("user.id", this.getDetachedCriteria());
		user = new AssociationExpression("user", this.getDetachedCriteria());
		type = new IntegerExpression("type", this.getDetachedCriteria());
		idContent = new IntegerExpression("idContent", this.getDetachedCriteria());
	}
	
	public FeedDetachedCriteria(DetachedCriteria aDetachedCriteria) {
		super(aDetachedCriteria, data.movieverse.FeedCriteria.class);
		id = new IntegerExpression("id", this.getDetachedCriteria());
		userId = new IntegerExpression("user.id", this.getDetachedCriteria());
		user = new AssociationExpression("user", this.getDetachedCriteria());
		type = new IntegerExpression("type", this.getDetachedCriteria());
		idContent = new IntegerExpression("idContent", this.getDetachedCriteria());
	}
	
	public MUserDetachedCriteria createUserCriteria() {
		return new MUserDetachedCriteria(createCriteria("user"));
	}
	
	public Feed uniqueFeed(PersistentSession session) {
		return (Feed) super.createExecutableCriteria(session).uniqueResult();
	}
	
	public Feed[] listFeed(PersistentSession session) {
		List list = super.createExecutableCriteria(session).list();
		return (Feed[]) list.toArray(new Feed[list.size()]);
	}
}

