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

public class CompanyDetachedCriteria extends AbstractORMDetachedCriteria {
	public final IntegerExpression id;
	public final IntegerExpression company_countryId;
	public final AssociationExpression company_country;
	public final StringExpression homepage;
	public final StringExpression name;
	public final StringExpression image;
	public final StringExpression headquarters;
	public final CollectionExpression movies;
	
	public CompanyDetachedCriteria() {
		super(data.movieverse.Company.class, data.movieverse.CompanyCriteria.class);
		id = new IntegerExpression("id", this.getDetachedCriteria());
		company_countryId = new IntegerExpression("company_country.id", this.getDetachedCriteria());
		company_country = new AssociationExpression("company_country", this.getDetachedCriteria());
		homepage = new StringExpression("homepage", this.getDetachedCriteria());
		name = new StringExpression("name", this.getDetachedCriteria());
		image = new StringExpression("image", this.getDetachedCriteria());
		headquarters = new StringExpression("headquarters", this.getDetachedCriteria());
		movies = new CollectionExpression("ORM_Movies", this.getDetachedCriteria());
	}
	
	public CompanyDetachedCriteria(DetachedCriteria aDetachedCriteria) {
		super(aDetachedCriteria, data.movieverse.CompanyCriteria.class);
		id = new IntegerExpression("id", this.getDetachedCriteria());
		company_countryId = new IntegerExpression("company_country.id", this.getDetachedCriteria());
		company_country = new AssociationExpression("company_country", this.getDetachedCriteria());
		homepage = new StringExpression("homepage", this.getDetachedCriteria());
		name = new StringExpression("name", this.getDetachedCriteria());
		image = new StringExpression("image", this.getDetachedCriteria());
		headquarters = new StringExpression("headquarters", this.getDetachedCriteria());
		movies = new CollectionExpression("ORM_Movies", this.getDetachedCriteria());
	}
	
	public CountryDetachedCriteria createCompany_countryCriteria() {
		return new CountryDetachedCriteria(createCriteria("company_country"));
	}
	
	public MovieDetachedCriteria createMoviesCriteria() {
		return new MovieDetachedCriteria(createCriteria("ORM_Movies"));
	}
	
	public Company uniqueCompany(PersistentSession session) {
		return (Company) super.createExecutableCriteria(session).uniqueResult();
	}
	
	public Company[] listCompany(PersistentSession session) {
		List list = super.createExecutableCriteria(session).list();
		return (Company[]) list.toArray(new Company[list.size()]);
	}
}

