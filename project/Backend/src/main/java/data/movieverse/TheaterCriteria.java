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

public class TheaterCriteria extends AbstractORMCriteria {
	public final IntegerExpression id;
	public final StringExpression name;
	public final StringExpression city;
	public final StringExpression site;
	public final IntegerExpression countryId;
	public final AssociationExpression country;
	public final CollectionExpression showtime;
	
	public TheaterCriteria(Criteria criteria) {
		super(criteria);
		id = new IntegerExpression("id", this);
		name = new StringExpression("name", this);
		city = new StringExpression("city", this);
		site = new StringExpression("site", this);
		countryId = new IntegerExpression("country.id", this);
		country = new AssociationExpression("country", this);
		showtime = new CollectionExpression("ORM_Showtime", this);
	}
	
	public TheaterCriteria(PersistentSession session) {
		this(session.createCriteria(Theater.class));
	}
	
	public TheaterCriteria() throws PersistentException {
		this(MovieversePersistentManager.instance().getSession());
	}
	
	public CountryCriteria createCountryCriteria() {
		return new CountryCriteria(createCriteria("country"));
	}
	
	public ShowtimeCriteria createShowtimeCriteria() {
		return new ShowtimeCriteria(createCriteria("ORM_Showtime"));
	}
	
	public Theater uniqueTheater() {
		return (Theater) super.uniqueResult();
	}
	
	public Theater[] listTheater() {
		java.util.List list = super.list();
		return (Theater[]) list.toArray(new Theater[list.size()]);
	}
}

