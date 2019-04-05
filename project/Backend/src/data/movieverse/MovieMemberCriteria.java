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

public class MovieMemberCriteria extends AbstractORMCriteria {
	public final IntegerExpression id;
	public final StringExpression role;
	public final IntegerExpression memberId;
	public final AssociationExpression member;
	public final IntegerExpression movieId;
	public final AssociationExpression movie;
	
	public MovieMemberCriteria(Criteria criteria) {
		super(criteria);
		id = new IntegerExpression("id", this);
		role = new StringExpression("role", this);
		memberId = new IntegerExpression("ORM_Member.null", this);
		member = new AssociationExpression("ORM_Member", this);
		movieId = new IntegerExpression("ORM_Movie.null", this);
		movie = new AssociationExpression("ORM_Movie", this);
	}
	
	public MovieMemberCriteria(PersistentSession session) {
		this(session.createCriteria(MovieMember.class));
	}
	
	public MovieMemberCriteria() throws PersistentException {
		this(MovieversePersistentManager.instance().getSession());
	}
	
	public MemberCriteria createMemberCriteria() {
		return new MemberCriteria(createCriteria("ORM_Member"));
	}
	
	public MovieCriteria createMovieCriteria() {
		return new MovieCriteria(createCriteria("ORM_Movie"));
	}
	
	public MovieMember uniqueMovieMember() {
		return (MovieMember) super.uniqueResult();
	}
	
	public MovieMember[] listMovieMember() {
		java.util.List list = super.list();
		return (MovieMember[]) list.toArray(new MovieMember[list.size()]);
	}
}

