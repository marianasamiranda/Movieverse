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

public class FriendshipCriteria extends AbstractORMCriteria {
	public final IntegerExpression id;
	public final DateExpression date;
	public final IntegerExpression receivedMuserId;
	public final AssociationExpression receivedMuser;
	public final IntegerExpression requestedMuserId;
	public final AssociationExpression requestedMuser;
	
	public FriendshipCriteria(Criteria criteria) {
		super(criteria);
		id = new IntegerExpression("id", this);
		date = new DateExpression("date", this);
		receivedMuserId = new IntegerExpression("ORM_ReceivedMuser.null", this);
		receivedMuser = new AssociationExpression("ORM_ReceivedMuser", this);
		requestedMuserId = new IntegerExpression("ORM_RequestedMuser.null", this);
		requestedMuser = new AssociationExpression("ORM_RequestedMuser", this);
	}
	
	public FriendshipCriteria(PersistentSession session) {
		this(session.createCriteria(Friendship.class));
	}
	
	public FriendshipCriteria() throws PersistentException {
		this(MovieversePersistentManager.instance().getSession());
	}
	
	public MUserCriteria createReceivedMuserCriteria() {
		return new MUserCriteria(createCriteria("ORM_ReceivedMuser"));
	}
	
	public MUserCriteria createRequestedMuserCriteria() {
		return new MUserCriteria(createCriteria("ORM_RequestedMuser"));
	}
	
	public Friendship uniqueFriendship() {
		return (Friendship) super.uniqueResult();
	}
	
	public Friendship[] listFriendship() {
		java.util.List list = super.list();
		return (Friendship[]) list.toArray(new Friendship[list.size()]);
	}
}

