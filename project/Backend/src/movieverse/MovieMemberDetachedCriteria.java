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

import java.util.List;
import org.hibernate.criterion.DetachedCriteria;
import org.orm.PersistentSession;
import org.orm.criteria.*;

public class MovieMemberDetachedCriteria extends AbstractORMDetachedCriteria {
	public final IntegerExpression id;
	public final StringExpression role;
	public final IntegerExpression memberId;
	public final AssociationExpression member;
	public final IntegerExpression movieId;
	public final AssociationExpression movie;
	
	public MovieMemberDetachedCriteria() {
		super(movieverse.MovieMember.class, movieverse.MovieMemberCriteria.class);
		id = new IntegerExpression("id", this.getDetachedCriteria());
		role = new StringExpression("role", this.getDetachedCriteria());
		memberId = new IntegerExpression("ORM_Member.null", this.getDetachedCriteria());
		member = new AssociationExpression("ORM_Member", this.getDetachedCriteria());
		movieId = new IntegerExpression("ORM_Movie.null", this.getDetachedCriteria());
		movie = new AssociationExpression("ORM_Movie", this.getDetachedCriteria());
	}
	
	public MovieMemberDetachedCriteria(DetachedCriteria aDetachedCriteria) {
		super(aDetachedCriteria, movieverse.MovieMemberCriteria.class);
		id = new IntegerExpression("id", this.getDetachedCriteria());
		role = new StringExpression("role", this.getDetachedCriteria());
		memberId = new IntegerExpression("ORM_Member.null", this.getDetachedCriteria());
		member = new AssociationExpression("ORM_Member", this.getDetachedCriteria());
		movieId = new IntegerExpression("ORM_Movie.null", this.getDetachedCriteria());
		movie = new AssociationExpression("ORM_Movie", this.getDetachedCriteria());
	}
	
	public MemberDetachedCriteria createMemberCriteria() {
		return new MemberDetachedCriteria(createCriteria("member"));
	}
	
	public MovieDetachedCriteria createMovieCriteria() {
		return new MovieDetachedCriteria(createCriteria("movie"));
	}
	
	public MovieMember uniqueMovieMember(PersistentSession session) {
		return (MovieMember) super.createExecutableCriteria(session).uniqueResult();
	}
	
	public MovieMember[] listMovieMember(PersistentSession session) {
		List list = super.createExecutableCriteria(session).list();
		return (MovieMember[]) list.toArray(new MovieMember[list.size()]);
	}
}

