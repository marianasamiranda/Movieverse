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

public class FriendshipDetachedCriteria extends AbstractORMDetachedCriteria {
	public final IntegerExpression id;
	public final DateExpression date;
	public final BooleanExpression pending;
	public final IntegerExpression requestedMuserId;
	public final AssociationExpression requestedMuser;
	public final IntegerExpression receivedMuserId;
	public final AssociationExpression receivedMuser;
	
	public FriendshipDetachedCriteria() {
		super(data.movieverse.Friendship.class, data.movieverse.FriendshipCriteria.class);
		id = new IntegerExpression("id", this.getDetachedCriteria());
		date = new DateExpression("date", this.getDetachedCriteria());
		pending = new BooleanExpression("pending", this.getDetachedCriteria());
		requestedMuserId = new IntegerExpression("ORM_RequestedMuser.null", this.getDetachedCriteria());
		requestedMuser = new AssociationExpression("ORM_RequestedMuser", this.getDetachedCriteria());
		receivedMuserId = new IntegerExpression("ORM_ReceivedMuser.null", this.getDetachedCriteria());
		receivedMuser = new AssociationExpression("ORM_ReceivedMuser", this.getDetachedCriteria());
	}
	
	public FriendshipDetachedCriteria(DetachedCriteria aDetachedCriteria) {
		super(aDetachedCriteria, data.movieverse.FriendshipCriteria.class);
		id = new IntegerExpression("id", this.getDetachedCriteria());
		date = new DateExpression("date", this.getDetachedCriteria());
		pending = new BooleanExpression("pending", this.getDetachedCriteria());
		requestedMuserId = new IntegerExpression("ORM_RequestedMuser.null", this.getDetachedCriteria());
		requestedMuser = new AssociationExpression("ORM_RequestedMuser", this.getDetachedCriteria());
		receivedMuserId = new IntegerExpression("ORM_ReceivedMuser.null", this.getDetachedCriteria());
		receivedMuser = new AssociationExpression("ORM_ReceivedMuser", this.getDetachedCriteria());
	}
	
	public MUserDetachedCriteria createRequestedMuserCriteria() {
		return new MUserDetachedCriteria(createCriteria("requestedMuser"));
	}
	
	public MUserDetachedCriteria createReceivedMuserCriteria() {
		return new MUserDetachedCriteria(createCriteria("receivedMuser"));
	}
	
	public Friendship uniqueFriendship(PersistentSession session) {
		return (Friendship) super.createExecutableCriteria(session).uniqueResult();
	}
	
	public Friendship[] listFriendship(PersistentSession session) {
		List list = super.createExecutableCriteria(session).list();
		return (Friendship[]) list.toArray(new Friendship[list.size()]);
	}
}

