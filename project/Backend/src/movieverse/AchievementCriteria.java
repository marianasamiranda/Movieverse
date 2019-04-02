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

public class AchievementCriteria extends AbstractORMCriteria {
	public final IntegerExpression id;
	public final DateExpression date;
	public final IntegerExpression badgeId;
	public final AssociationExpression badge;
	public final IntegerExpression mUserId;
	public final AssociationExpression mUser;
	
	public AchievementCriteria(Criteria criteria) {
		super(criteria);
		id = new IntegerExpression("id", this);
		date = new DateExpression("date", this);
		badgeId = new IntegerExpression("ORM_Badge.null", this);
		badge = new AssociationExpression("ORM_Badge", this);
		mUserId = new IntegerExpression("ORM_mUser.null", this);
		mUser = new AssociationExpression("ORM_mUser", this);
	}
	
	public AchievementCriteria(PersistentSession session) {
		this(session.createCriteria(Achievement.class));
	}
	
	public AchievementCriteria() throws PersistentException {
		this(MovieversePersistentManager.instance().getSession());
	}
	
	public BadgeCriteria createBadgeCriteria() {
		return new BadgeCriteria(createCriteria("ORM_Badge"));
	}
	
	public MUserCriteria createMUserCriteria() {
		return new MUserCriteria(createCriteria("ORM_mUser"));
	}
	
	public Achievement uniqueAchievement() {
		return (Achievement) super.uniqueResult();
	}
	
	public Achievement[] listAchievement() {
		java.util.List list = super.list();
		return (Achievement[]) list.toArray(new Achievement[list.size()]);
	}
}

