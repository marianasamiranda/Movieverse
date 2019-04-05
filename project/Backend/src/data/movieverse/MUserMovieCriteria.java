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

public class MUserMovieCriteria extends AbstractORMCriteria {
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
	
	public MUserMovieCriteria(Criteria criteria) {
		super(criteria);
		id = new IntegerExpression("id", this);
		status = new BooleanExpression("status", this);
		rating = new IntegerExpression("rating", this);
		favourite = new BooleanExpression("favourite", this);
		dateWatched = new DateExpression("dateWatched", this);
		dateFavourite = new DateExpression("dateFavourite", this);
		visible = new BooleanExpression("visible", this);
		movieId = new IntegerExpression("ORM_Movie.null", this);
		movie = new AssociationExpression("ORM_Movie", this);
		mUserId = new IntegerExpression("ORM_mUser.null", this);
		mUser = new AssociationExpression("ORM_mUser", this);
	}
	
	public MUserMovieCriteria(PersistentSession session) {
		this(session.createCriteria(MUserMovie.class));
	}
	
	public MUserMovieCriteria() throws PersistentException {
		this(MovieversePersistentManager.instance().getSession());
	}
	
	public MovieCriteria createMovieCriteria() {
		return new MovieCriteria(createCriteria("ORM_Movie"));
	}
	
	public MUserCriteria createMUserCriteria() {
		return new MUserCriteria(createCriteria("ORM_mUser"));
	}
	
	public MUserMovie uniqueMUserMovie() {
		return (MUserMovie) super.uniqueResult();
	}
	
	public MUserMovie[] listMUserMovie() {
		java.util.List list = super.list();
		return (MUserMovie[]) list.toArray(new MUserMovie[list.size()]);
	}
}

