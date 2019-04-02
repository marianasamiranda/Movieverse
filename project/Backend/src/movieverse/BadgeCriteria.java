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

public class BadgeCriteria extends AbstractORMCriteria {
	public final IntegerExpression id;
	public final StringExpression name;
	public final StringExpression image;
	public final CollectionExpression achievements;
	
	public BadgeCriteria(Criteria criteria) {
		super(criteria);
		id = new IntegerExpression("id", this);
		name = new StringExpression("name", this);
		image = new StringExpression("image", this);
		achievements = new CollectionExpression("ORM_Achievements", this);
	}
	
	public BadgeCriteria(PersistentSession session) {
		this(session.createCriteria(Badge.class));
	}
	
	public BadgeCriteria() throws PersistentException {
		this(MovieversePersistentManager.instance().getSession());
	}
	
	public AchievementCriteria createAchievementsCriteria() {
		return new AchievementCriteria(createCriteria("ORM_Achievements"));
	}
	
	public Badge uniqueBadge() {
		return (Badge) super.uniqueResult();
	}
	
	public Badge[] listBadge() {
		java.util.List list = super.list();
		return (Badge[]) list.toArray(new Badge[list.size()]);
	}
}

