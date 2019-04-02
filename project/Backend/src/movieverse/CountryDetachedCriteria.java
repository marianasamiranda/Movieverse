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
package movieverse;

import java.util.List;
import org.hibernate.criterion.DetachedCriteria;
import org.orm.PersistentSession;
import org.orm.criteria.*;

public class CountryDetachedCriteria extends AbstractORMDetachedCriteria {
	public final IntegerExpression id;
	public final StringExpression name;
	public final StringExpression alphaCode;
	public final StringExpression image;
	
	public CountryDetachedCriteria() {
		super(movieverse.Country.class, movieverse.CountryCriteria.class);
		id = new IntegerExpression("id", this.getDetachedCriteria());
		name = new StringExpression("name", this.getDetachedCriteria());
		alphaCode = new StringExpression("alphaCode", this.getDetachedCriteria());
		image = new StringExpression("image", this.getDetachedCriteria());
	}
	
	public CountryDetachedCriteria(DetachedCriteria aDetachedCriteria) {
		super(aDetachedCriteria, movieverse.CountryCriteria.class);
		id = new IntegerExpression("id", this.getDetachedCriteria());
		name = new StringExpression("name", this.getDetachedCriteria());
		alphaCode = new StringExpression("alphaCode", this.getDetachedCriteria());
		image = new StringExpression("image", this.getDetachedCriteria());
	}
	
	public Country uniqueCountry(PersistentSession session) {
		return (Country) super.createExecutableCriteria(session).uniqueResult();
	}
	
	public Country[] listCountry(PersistentSession session) {
		List list = super.createExecutableCriteria(session).list();
		return (Country[]) list.toArray(new Country[list.size()]);
	}
}

