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

public class CompanyCriteria extends AbstractORMCriteria {
	public final IntegerExpression id;
	public final IntegerExpression parentCompanyId;
	public final AssociationExpression parentCompany;
	public final IntegerExpression companyCountryId;
	public final AssociationExpression companyCountry;
	public final StringExpression homepage;
	public final StringExpression name;
	public final StringExpression image;
	public final StringExpression country;
	public final StringExpression headquarters;
	public final StringExpression description;
	public final CollectionExpression movies;
	public final CollectionExpression childrenCompanies;
	
	public CompanyCriteria(Criteria criteria) {
		super(criteria);
		id = new IntegerExpression("id", this);
		parentCompanyId = new IntegerExpression("parentCompany.id", this);
		parentCompany = new AssociationExpression("parentCompany", this);
		companyCountryId = new IntegerExpression("companyCountry.id", this);
		companyCountry = new AssociationExpression("companyCountry", this);
		homepage = new StringExpression("homepage", this);
		name = new StringExpression("name", this);
		image = new StringExpression("image", this);
		country = new StringExpression("country", this);
		headquarters = new StringExpression("headquarters", this);
		description = new StringExpression("description", this);
		movies = new CollectionExpression("ORM_Movies", this);
		childrenCompanies = new CollectionExpression("ORM_ChildrenCompanies", this);
	}
	
	public CompanyCriteria(PersistentSession session) {
		this(session.createCriteria(Company.class));
	}
	
	public CompanyCriteria() throws PersistentException {
		this(MovieversePersistentManager.instance().getSession());
	}
	
	public CompanyCriteria createParentCompanyCriteria() {
		return new CompanyCriteria(createCriteria("parentCompany"));
	}
	
	public CountryCriteria createCompanyCountryCriteria() {
		return new CountryCriteria(createCriteria("companyCountry"));
	}
	
	public MovieCriteria createMoviesCriteria() {
		return new MovieCriteria(createCriteria("ORM_Movies"));
	}
	
	public CompanyCriteria createChildrenCompaniesCriteria() {
		return new CompanyCriteria(createCriteria("ORM_ChildrenCompanies"));
	}
	
	public Company uniqueCompany() {
		return (Company) super.uniqueResult();
	}
	
	public Company[] listCompany() {
		java.util.List list = super.list();
		return (Company[]) list.toArray(new Company[list.size()]);
	}
}

