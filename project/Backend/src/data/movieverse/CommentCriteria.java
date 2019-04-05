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

public class CommentCriteria extends AbstractORMCriteria {
	public final IntegerExpression id;
	public final IntegerExpression movieId;
	public final AssociationExpression movie;
	public final IntegerExpression commenterId;
	public final AssociationExpression commenter;
	public final DateExpression date;
	public final StringExpression content;
	public final IntegerExpression parent;
	public final IntegerExpression likes;
	public final CollectionExpression upvoter;
	
	public CommentCriteria(Criteria criteria) {
		super(criteria);
		id = new IntegerExpression("id", this);
		movieId = new IntegerExpression("movie.id", this);
		movie = new AssociationExpression("movie", this);
		commenterId = new IntegerExpression("commenter.id", this);
		commenter = new AssociationExpression("commenter", this);
		date = new DateExpression("date", this);
		content = new StringExpression("content", this);
		parent = new IntegerExpression("parent", this);
		likes = new IntegerExpression("likes", this);
		upvoter = new CollectionExpression("ORM_Upvoter", this);
	}
	
	public CommentCriteria(PersistentSession session) {
		this(session.createCriteria(Comment.class));
	}
	
	public CommentCriteria() throws PersistentException {
		this(MovieversePersistentManager.instance().getSession());
	}
	
	public MovieCriteria createMovieCriteria() {
		return new MovieCriteria(createCriteria("movie"));
	}
	
	public MUserCriteria createCommenterCriteria() {
		return new MUserCriteria(createCriteria("commenter"));
	}
	
	public MUserCriteria createUpvoterCriteria() {
		return new MUserCriteria(createCriteria("ORM_Upvoter"));
	}
	
	public Comment uniqueComment() {
		return (Comment) super.uniqueResult();
	}
	
	public Comment[] listComment() {
		java.util.List list = super.list();
		return (Comment[]) list.toArray(new Comment[list.size()]);
	}
}

