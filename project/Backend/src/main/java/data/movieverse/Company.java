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

public class Company {
	public Company() {
	}
	
	private java.util.Set this_getSet (int key) {
		if (key == ORMConstants.KEY_COMPANY_MOVIES) {
			return ORM_movies;
		}
		else if (key == ORMConstants.KEY_COMPANY_CHILDRENCOMPANIES) {
			return ORM_childrenCompanies;
		}
		
		return null;
	}
	
	private void this_setOwner(Object owner, int key) {
		if (key == ORMConstants.KEY_COMPANY_COMPANYCOUNTRY) {
			this.companyCountry = (data.movieverse.Country) owner;
		}
		
		else if (key == ORMConstants.KEY_COMPANY_PARENTCOMPANY) {
			this.parentCompany = (data.movieverse.Company) owner;
		}
	}
	
	org.orm.util.ORMAdapter _ormAdapter = new org.orm.util.AbstractORMAdapter() {
		public java.util.Set getSet(int key) {
			return this_getSet(key);
		}
		
		public void setOwner(Object owner, int key) {
			this_setOwner(owner, key);
		}
		
	};
	
	private int id;
	
	private data.movieverse.Company parentCompany;
	
	private data.movieverse.Country companyCountry;
	
	private String homepage;
	
	private String name;
	
	private String image;
	
	private String country;
	
	private String headquarters;
	
	private String description;
	
	private java.util.Set ORM_movies = new java.util.HashSet();
	
	private java.util.Set ORM_childrenCompanies = new java.util.HashSet();
	
	private void setId(int value) {
		this.id = value;
	}
	
	public int getId() {
		return id;
	}
	
	public int getORMID() {
		return getId();
	}
	
	public void setHomepage(String value) {
		this.homepage = value;
	}
	
	public String getHomepage() {
		return homepage;
	}
	
	public void setName(String value) {
		this.name = value;
	}
	
	public String getName() {
		return name;
	}
	
	public void setImage(String value) {
		this.image = value;
	}
	
	public String getImage() {
		return image;
	}
	
	public void setCountry(String value) {
		this.country = value;
	}
	
	public String getCountry() {
		return country;
	}
	
	public void setHeadquarters(String value) {
		this.headquarters = value;
	}
	
	public String getHeadquarters() {
		return headquarters;
	}
	
	public void setDescription(String value) {
		this.description = value;
	}
	
	public String getDescription() {
		return description;
	}
	
	private void setORM_Movies(java.util.Set value) {
		this.ORM_movies = value;
	}
	
	private java.util.Set getORM_Movies() {
		return ORM_movies;
	}
	
	public final data.movieverse.MovieSetCollection movies = new data.movieverse.MovieSetCollection(this, _ormAdapter, ORMConstants.KEY_COMPANY_MOVIES, ORMConstants.KEY_MOVIE_COMPANIES, ORMConstants.KEY_MUL_MANY_TO_MANY);
	
	private void setORM_ChildrenCompanies(java.util.Set value) {
		this.ORM_childrenCompanies = value;
	}
	
	private java.util.Set getORM_ChildrenCompanies() {
		return ORM_childrenCompanies;
	}
	
	public final data.movieverse.CompanySetCollection childrenCompanies = new data.movieverse.CompanySetCollection(this, _ormAdapter, ORMConstants.KEY_COMPANY_CHILDRENCOMPANIES, ORMConstants.KEY_COMPANY_PARENTCOMPANY, ORMConstants.KEY_MUL_ONE_TO_MANY);
	
	public void setCompanyCountry(data.movieverse.Country value) {
		this.companyCountry = value;
	}
	
	public data.movieverse.Country getCompanyCountry() {
		return companyCountry;
	}
	
	public void setParentCompany(data.movieverse.Company value) {
		if (parentCompany != null) {
			parentCompany.childrenCompanies.remove(this);
		}
		if (value != null) {
			value.childrenCompanies.add(this);
		}
	}
	
	public data.movieverse.Company getParentCompany() {
		return parentCompany;
	}
	
	/**
	 * This method is for internal use only.
	 */
	public void setORM_ParentCompany(data.movieverse.Company value) {
		this.parentCompany = value;
	}
	
	private data.movieverse.Company getORM_ParentCompany() {
		return parentCompany;
	}
	
	public String toString() {
		return String.valueOf(getId());
	}
	
}
