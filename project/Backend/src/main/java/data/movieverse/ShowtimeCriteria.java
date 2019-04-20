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

public class ShowtimeCriteria extends AbstractORMCriteria {
	public final IntegerExpression id;
	public final IntegerExpression movieId;
	public final AssociationExpression movie;
	public final IntegerExpression theaterId;
	public final AssociationExpression theater;
	public final DateExpression date;
	
	public ShowtimeCriteria(Criteria criteria) {
		super(criteria);
		id = new IntegerExpression("id", this);
		movieId = new IntegerExpression("movie.id", this);
		movie = new AssociationExpression("movie", this);
		theaterId = new IntegerExpression("theater.id", this);
		theater = new AssociationExpression("theater", this);
		date = new DateExpression("date", this);
	}
	
	public ShowtimeCriteria(PersistentSession session) {
		this(session.createCriteria(Showtime.class));
	}
	
	public ShowtimeCriteria() throws PersistentException {
		this(MovieversePersistentManager.instance().getSession());
	}
	
	public MovieCriteria createMovieCriteria() {
		return new MovieCriteria(createCriteria("movie"));
	}
	
	public TheaterCriteria createTheaterCriteria() {
		return new TheaterCriteria(createCriteria("theater"));
	}
	
	public Showtime uniqueShowtime() {
		return (Showtime) super.uniqueResult();
	}
	
	public Showtime[] listShowtime() {
		java.util.List list = super.list();
		return (Showtime[]) list.toArray(new Showtime[list.size()]);
	}
}

