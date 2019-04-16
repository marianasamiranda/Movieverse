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

public class BadgeDetachedCriteria extends AbstractORMDetachedCriteria {
	public final IntegerExpression id;
	public final StringExpression name;
	public final StringExpression image;
	public final CollectionExpression achievements;
	
	public BadgeDetachedCriteria() {
		super(data.movieverse.Badge.class, data.movieverse.BadgeCriteria.class);
		id = new IntegerExpression("id", this.getDetachedCriteria());
		name = new StringExpression("name", this.getDetachedCriteria());
		image = new StringExpression("image", this.getDetachedCriteria());
		achievements = new CollectionExpression("ORM_Achievements", this.getDetachedCriteria());
	}
	
	public BadgeDetachedCriteria(DetachedCriteria aDetachedCriteria) {
		super(aDetachedCriteria, data.movieverse.BadgeCriteria.class);
		id = new IntegerExpression("id", this.getDetachedCriteria());
		name = new StringExpression("name", this.getDetachedCriteria());
		image = new StringExpression("image", this.getDetachedCriteria());
		achievements = new CollectionExpression("ORM_Achievements", this.getDetachedCriteria());
	}
	
	public AchievementDetachedCriteria createAchievementsCriteria() {
		return new AchievementDetachedCriteria(createCriteria("ORM_Achievements"));
	}
	
	public Badge uniqueBadge(PersistentSession session) {
		return (Badge) super.createExecutableCriteria(session).uniqueResult();
	}
	
	public Badge[] listBadge(PersistentSession session) {
		List list = super.createExecutableCriteria(session).list();
		return (Badge[]) list.toArray(new Badge[list.size()]);
	}
}

