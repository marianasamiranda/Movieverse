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

public class MemberDetachedCriteria extends AbstractORMDetachedCriteria {
	public final IntegerExpression id;
	public final StringExpression name;
	public final StringExpression image;
	public final DateExpression birthDate;
	public final CharacterExpression gender;
	public final StringExpression imdb;
	public final StringExpression biography;
	public final StringExpression BirthPlace;
	public final CollectionExpression movieMembers;
	public final CollectionExpression media;
	
	public MemberDetachedCriteria() {
		super(data.movieverse.Member.class, data.movieverse.MemberCriteria.class);
		id = new IntegerExpression("id", this.getDetachedCriteria());
		name = new StringExpression("name", this.getDetachedCriteria());
		image = new StringExpression("image", this.getDetachedCriteria());
		birthDate = new DateExpression("birthDate", this.getDetachedCriteria());
		gender = new CharacterExpression("gender", this.getDetachedCriteria());
		imdb = new StringExpression("imdb", this.getDetachedCriteria());
		biography = new StringExpression("biography", this.getDetachedCriteria());
		BirthPlace = new StringExpression("BirthPlace", this.getDetachedCriteria());
		movieMembers = new CollectionExpression("ORM_MovieMembers", this.getDetachedCriteria());
		media = new CollectionExpression("ORM_Media", this.getDetachedCriteria());
	}
	
	public MemberDetachedCriteria(DetachedCriteria aDetachedCriteria) {
		super(aDetachedCriteria, data.movieverse.MemberCriteria.class);
		id = new IntegerExpression("id", this.getDetachedCriteria());
		name = new StringExpression("name", this.getDetachedCriteria());
		image = new StringExpression("image", this.getDetachedCriteria());
		birthDate = new DateExpression("birthDate", this.getDetachedCriteria());
		gender = new CharacterExpression("gender", this.getDetachedCriteria());
		imdb = new StringExpression("imdb", this.getDetachedCriteria());
		biography = new StringExpression("biography", this.getDetachedCriteria());
		BirthPlace = new StringExpression("BirthPlace", this.getDetachedCriteria());
		movieMembers = new CollectionExpression("ORM_MovieMembers", this.getDetachedCriteria());
		media = new CollectionExpression("ORM_Media", this.getDetachedCriteria());
	}
	
	public MovieMemberDetachedCriteria createMovieMembersCriteria() {
		return new MovieMemberDetachedCriteria(createCriteria("ORM_MovieMembers"));
	}
	
	public MediaDetachedCriteria createMediaCriteria() {
		return new MediaDetachedCriteria(createCriteria("ORM_Media"));
	}
	
	public Member uniqueMember(PersistentSession session) {
		return (Member) super.createExecutableCriteria(session).uniqueResult();
	}
	
	public Member[] listMember(PersistentSession session) {
		List list = super.createExecutableCriteria(session).list();
		return (Member[]) list.toArray(new Member[list.size()]);
	}
}

