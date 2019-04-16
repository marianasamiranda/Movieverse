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

public class CountryCriteria extends AbstractORMCriteria {
	public final IntegerExpression id;
	public final StringExpression name;
	public final StringExpression alphaCode;
	
	public CountryCriteria(Criteria criteria) {
		super(criteria);
		id = new IntegerExpression("id", this);
		name = new StringExpression("name", this);
		alphaCode = new StringExpression("alphaCode", this);
	}
	
	public CountryCriteria(PersistentSession session) {
		this(session.createCriteria(Country.class));
	}
	
	public CountryCriteria() throws PersistentException {
		this(MovieversePersistentManager.instance().getSession());
	}
	
	public Country uniqueCountry() {
		return (Country) super.uniqueResult();
	}
	
	public Country[] listCountry() {
		java.util.List list = super.list();
		return (Country[]) list.toArray(new Country[list.size()]);
	}
}

