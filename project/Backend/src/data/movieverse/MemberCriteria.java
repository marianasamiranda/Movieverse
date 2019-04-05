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

public class MemberCriteria extends AbstractORMCriteria {
	public final IntegerExpression id;
	public final StringExpression name;
	public final StringExpression image;
	public final DateExpression birthDate;
	public final BooleanExpression gender;
	public final StringExpression imdb;
	public final StringExpression biography;
	public final StringExpression BirthPlace;
	public final CollectionExpression movieMembers;
	
	public MemberCriteria(Criteria criteria) {
		super(criteria);
		id = new IntegerExpression("id", this);
		name = new StringExpression("name", this);
		image = new StringExpression("image", this);
		birthDate = new DateExpression("birthDate", this);
		gender = new BooleanExpression("gender", this);
		imdb = new StringExpression("imdb", this);
		biography = new StringExpression("biography", this);
		BirthPlace = new StringExpression("BirthPlace", this);
		movieMembers = new CollectionExpression("ORM_MovieMembers", this);
	}
	
	public MemberCriteria(PersistentSession session) {
		this(session.createCriteria(Member.class));
	}
	
	public MemberCriteria() throws PersistentException {
		this(MovieversePersistentManager.instance().getSession());
	}
	
	public MovieMemberCriteria createMovieMembersCriteria() {
		return new MovieMemberCriteria(createCriteria("ORM_MovieMembers"));
	}
	
	public Member uniqueMember() {
		return (Member) super.uniqueResult();
	}
	
	public Member[] listMember() {
		java.util.List list = super.list();
		return (Member[]) list.toArray(new Member[list.size()]);
	}
}

