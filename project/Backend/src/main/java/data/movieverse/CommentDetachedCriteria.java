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

public class CommentDetachedCriteria extends AbstractORMDetachedCriteria {
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
	
	public CommentDetachedCriteria() {
		super(data.movieverse.Comment.class, data.movieverse.CommentCriteria.class);
		id = new IntegerExpression("id", this.getDetachedCriteria());
		movieId = new IntegerExpression("movie.id", this.getDetachedCriteria());
		movie = new AssociationExpression("movie", this.getDetachedCriteria());
		commenterId = new IntegerExpression("commenter.id", this.getDetachedCriteria());
		commenter = new AssociationExpression("commenter", this.getDetachedCriteria());
		date = new DateExpression("date", this.getDetachedCriteria());
		content = new StringExpression("content", this.getDetachedCriteria());
		parent = new IntegerExpression("parent", this.getDetachedCriteria());
		likes = new IntegerExpression("likes", this.getDetachedCriteria());
		upvoter = new CollectionExpression("ORM_Upvoter", this.getDetachedCriteria());
	}
	
	public CommentDetachedCriteria(DetachedCriteria aDetachedCriteria) {
		super(aDetachedCriteria, data.movieverse.CommentCriteria.class);
		id = new IntegerExpression("id", this.getDetachedCriteria());
		movieId = new IntegerExpression("movie.id", this.getDetachedCriteria());
		movie = new AssociationExpression("movie", this.getDetachedCriteria());
		commenterId = new IntegerExpression("commenter.id", this.getDetachedCriteria());
		commenter = new AssociationExpression("commenter", this.getDetachedCriteria());
		date = new DateExpression("date", this.getDetachedCriteria());
		content = new StringExpression("content", this.getDetachedCriteria());
		parent = new IntegerExpression("parent", this.getDetachedCriteria());
		likes = new IntegerExpression("likes", this.getDetachedCriteria());
		upvoter = new CollectionExpression("ORM_Upvoter", this.getDetachedCriteria());
	}
	
	public MovieDetachedCriteria createMovieCriteria() {
		return new MovieDetachedCriteria(createCriteria("movie"));
	}
	
	public MUserDetachedCriteria createCommenterCriteria() {
		return new MUserDetachedCriteria(createCriteria("commenter"));
	}
	
	public MUserDetachedCriteria createUpvoterCriteria() {
		return new MUserDetachedCriteria(createCriteria("ORM_Upvoter"));
	}
	
	public Comment uniqueComment(PersistentSession session) {
		return (Comment) super.createExecutableCriteria(session).uniqueResult();
	}
	
	public Comment[] listComment(PersistentSession session) {
		List list = super.createExecutableCriteria(session).list();
		return (Comment[]) list.toArray(new Comment[list.size()]);
	}
}

