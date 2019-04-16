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

public class TheaterDetachedCriteria extends AbstractORMDetachedCriteria {
	public final IntegerExpression id;
	public final IntegerExpression countryId;
	public final AssociationExpression country;
	public final StringExpression name;
	public final StringExpression city;
	public final StringExpression site;
	public final CollectionExpression showtime;
	
	public TheaterDetachedCriteria() {
		super(data.movieverse.Theater.class, data.movieverse.TheaterCriteria.class);
		id = new IntegerExpression("id", this.getDetachedCriteria());
		countryId = new IntegerExpression("country.id", this.getDetachedCriteria());
		country = new AssociationExpression("country", this.getDetachedCriteria());
		name = new StringExpression("name", this.getDetachedCriteria());
		city = new StringExpression("city", this.getDetachedCriteria());
		site = new StringExpression("site", this.getDetachedCriteria());
		showtime = new CollectionExpression("ORM_Showtime", this.getDetachedCriteria());
	}
	
	public TheaterDetachedCriteria(DetachedCriteria aDetachedCriteria) {
		super(aDetachedCriteria, data.movieverse.TheaterCriteria.class);
		id = new IntegerExpression("id", this.getDetachedCriteria());
		countryId = new IntegerExpression("country.id", this.getDetachedCriteria());
		country = new AssociationExpression("country", this.getDetachedCriteria());
		name = new StringExpression("name", this.getDetachedCriteria());
		city = new StringExpression("city", this.getDetachedCriteria());
		site = new StringExpression("site", this.getDetachedCriteria());
		showtime = new CollectionExpression("ORM_Showtime", this.getDetachedCriteria());
	}
	
	public CountryDetachedCriteria createCountryCriteria() {
		return new CountryDetachedCriteria(createCriteria("country"));
	}
	
	public ShowtimeDetachedCriteria createShowtimeCriteria() {
		return new ShowtimeDetachedCriteria(createCriteria("ORM_Showtime"));
	}
	
	public Theater uniqueTheater(PersistentSession session) {
		return (Theater) super.createExecutableCriteria(session).uniqueResult();
	}
	
	public Theater[] listTheater(PersistentSession session) {
		List list = super.createExecutableCriteria(session).list();
		return (Theater[]) list.toArray(new Theater[list.size()]);
	}
}

