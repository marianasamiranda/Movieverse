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

public class AchievementDetachedCriteria extends AbstractORMDetachedCriteria {
	public final IntegerExpression id;
	public final DateExpression date;
	public final IntegerExpression badgeId;
	public final AssociationExpression badge;
	public final IntegerExpression mUserId;
	public final AssociationExpression mUser;
	
	public AchievementDetachedCriteria() {
		super(Achievement.class, AchievementCriteria.class);
		id = new IntegerExpression("id", this.getDetachedCriteria());
		date = new DateExpression("date", this.getDetachedCriteria());
		badgeId = new IntegerExpression("ORM_Badge.null", this.getDetachedCriteria());
		badge = new AssociationExpression("ORM_Badge", this.getDetachedCriteria());
		mUserId = new IntegerExpression("ORM_mUser.null", this.getDetachedCriteria());
		mUser = new AssociationExpression("ORM_mUser", this.getDetachedCriteria());
	}
	
	public AchievementDetachedCriteria(DetachedCriteria aDetachedCriteria) {
		super(aDetachedCriteria, AchievementCriteria.class);
		id = new IntegerExpression("id", this.getDetachedCriteria());
		date = new DateExpression("date", this.getDetachedCriteria());
		badgeId = new IntegerExpression("ORM_Badge.null", this.getDetachedCriteria());
		badge = new AssociationExpression("ORM_Badge", this.getDetachedCriteria());
		mUserId = new IntegerExpression("ORM_mUser.null", this.getDetachedCriteria());
		mUser = new AssociationExpression("ORM_mUser", this.getDetachedCriteria());
	}
	
	public BadgeDetachedCriteria createBadgeCriteria() {
		return new BadgeDetachedCriteria(createCriteria("badge"));
	}
	
	public MUserDetachedCriteria createMUserCriteria() {
		return new MUserDetachedCriteria(createCriteria("mUser"));
	}
	
	public Achievement uniqueAchievement(PersistentSession session) {
		return (Achievement) super.createExecutableCriteria(session).uniqueResult();
	}
	
	public Achievement[] listAchievement(PersistentSession session) {
		List list = super.createExecutableCriteria(session).list();
		return (Achievement[]) list.toArray(new Achievement[list.size()]);
	}
}

