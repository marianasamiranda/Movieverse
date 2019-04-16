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

public class UserMovieDetachedCriteria extends AbstractORMDetachedCriteria {
	public final IntegerExpression id;
	public final BooleanExpression status;
	public final IntegerExpression rating;
	public final BooleanExpression favourite;
	public final DateExpression dateWatched;
	public final DateExpression dateFavourite;
	public final BooleanExpression visible;
	public final IntegerExpression movieId;
	public final AssociationExpression movie;
	public final IntegerExpression mUserId;
	public final AssociationExpression mUser;
	
	public UserMovieDetachedCriteria() {
		super(data.movieverse.UserMovie.class, data.movieverse.UserMovieCriteria.class);
		id = new IntegerExpression("id", this.getDetachedCriteria());
		status = new BooleanExpression("status", this.getDetachedCriteria());
		rating = new IntegerExpression("rating", this.getDetachedCriteria());
		favourite = new BooleanExpression("favourite", this.getDetachedCriteria());
		dateWatched = new DateExpression("dateWatched", this.getDetachedCriteria());
		dateFavourite = new DateExpression("dateFavourite", this.getDetachedCriteria());
		visible = new BooleanExpression("visible", this.getDetachedCriteria());
		movieId = new IntegerExpression("ORM_Movie.null", this.getDetachedCriteria());
		movie = new AssociationExpression("ORM_Movie", this.getDetachedCriteria());
		mUserId = new IntegerExpression("ORM_mUser.null", this.getDetachedCriteria());
		mUser = new AssociationExpression("ORM_mUser", this.getDetachedCriteria());
	}
	
	public UserMovieDetachedCriteria(DetachedCriteria aDetachedCriteria) {
		super(aDetachedCriteria, data.movieverse.UserMovieCriteria.class);
		id = new IntegerExpression("id", this.getDetachedCriteria());
		status = new BooleanExpression("status", this.getDetachedCriteria());
		rating = new IntegerExpression("rating", this.getDetachedCriteria());
		favourite = new BooleanExpression("favourite", this.getDetachedCriteria());
		dateWatched = new DateExpression("dateWatched", this.getDetachedCriteria());
		dateFavourite = new DateExpression("dateFavourite", this.getDetachedCriteria());
		visible = new BooleanExpression("visible", this.getDetachedCriteria());
		movieId = new IntegerExpression("ORM_Movie.null", this.getDetachedCriteria());
		movie = new AssociationExpression("ORM_Movie", this.getDetachedCriteria());
		mUserId = new IntegerExpression("ORM_mUser.null", this.getDetachedCriteria());
		mUser = new AssociationExpression("ORM_mUser", this.getDetachedCriteria());
	}
	
	public MovieDetachedCriteria createMovieCriteria() {
		return new MovieDetachedCriteria(createCriteria("movie"));
	}
	
	public MUserDetachedCriteria createMUserCriteria() {
		return new MUserDetachedCriteria(createCriteria("mUser"));
	}
	
	public UserMovie uniqueUserMovie(PersistentSession session) {
		return (UserMovie) super.createExecutableCriteria(session).uniqueResult();
	}
	
	public UserMovie[] listUserMovie(PersistentSession session) {
		List list = super.createExecutableCriteria(session).list();
		return (UserMovie[]) list.toArray(new UserMovie[list.size()]);
	}
}

