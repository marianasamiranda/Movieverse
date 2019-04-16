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

public class ShowtimeDetachedCriteria extends AbstractORMDetachedCriteria {
	public final IntegerExpression id;
	public final IntegerExpression movieId;
	public final AssociationExpression movie;
	public final IntegerExpression theaterId;
	public final AssociationExpression theater;
	public final DateExpression date;
	
	public ShowtimeDetachedCriteria() {
		super(data.movieverse.Showtime.class, data.movieverse.ShowtimeCriteria.class);
		id = new IntegerExpression("id", this.getDetachedCriteria());
		movieId = new IntegerExpression("movie.id", this.getDetachedCriteria());
		movie = new AssociationExpression("movie", this.getDetachedCriteria());
		theaterId = new IntegerExpression("theater.id", this.getDetachedCriteria());
		theater = new AssociationExpression("theater", this.getDetachedCriteria());
		date = new DateExpression("date", this.getDetachedCriteria());
	}
	
	public ShowtimeDetachedCriteria(DetachedCriteria aDetachedCriteria) {
		super(aDetachedCriteria, data.movieverse.ShowtimeCriteria.class);
		id = new IntegerExpression("id", this.getDetachedCriteria());
		movieId = new IntegerExpression("movie.id", this.getDetachedCriteria());
		movie = new AssociationExpression("movie", this.getDetachedCriteria());
		theaterId = new IntegerExpression("theater.id", this.getDetachedCriteria());
		theater = new AssociationExpression("theater", this.getDetachedCriteria());
		date = new DateExpression("date", this.getDetachedCriteria());
	}
	
	public MovieDetachedCriteria createMovieCriteria() {
		return new MovieDetachedCriteria(createCriteria("movie"));
	}
	
	public TheaterDetachedCriteria createTheaterCriteria() {
		return new TheaterDetachedCriteria(createCriteria("theater"));
	}
	
	public Showtime uniqueShowtime(PersistentSession session) {
		return (Showtime) super.createExecutableCriteria(session).uniqueResult();
	}
	
	public Showtime[] listShowtime(PersistentSession session) {
		List list = super.createExecutableCriteria(session).list();
		return (Showtime[]) list.toArray(new Showtime[list.size()]);
	}
}

