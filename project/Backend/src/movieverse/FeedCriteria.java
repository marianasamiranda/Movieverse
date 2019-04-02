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
package movieverse;

import org.hibernate.Criteria;
import org.orm.PersistentException;
import org.orm.PersistentSession;
import org.orm.criteria.*;

public class FeedCriteria extends AbstractORMCriteria {
	public final IntegerExpression id;
	public final IntegerExpression userId;
	public final AssociationExpression user;
	public final IntegerExpression type;
	public final IntegerExpression idContent;
	
	public FeedCriteria(Criteria criteria) {
		super(criteria);
		id = new IntegerExpression("id", this);
		userId = new IntegerExpression("user.id", this);
		user = new AssociationExpression("user", this);
		type = new IntegerExpression("type", this);
		idContent = new IntegerExpression("idContent", this);
	}
	
	public FeedCriteria(PersistentSession session) {
		this(session.createCriteria(Feed.class));
	}
	
	public FeedCriteria() throws PersistentException {
		this(MovieversePersistentManager.instance().getSession());
	}
	
	public MUserCriteria createUserCriteria() {
		return new MUserCriteria(createCriteria("user"));
	}
	
	public Feed uniqueFeed() {
		return (Feed) super.uniqueResult();
	}
	
	public Feed[] listFeed() {
		java.util.List list = super.list();
		return (Feed[]) list.toArray(new Feed[list.size()]);
	}
}

