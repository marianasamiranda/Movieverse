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
		
		return null;
	}
	
	private void this_setOwner(Object owner, int key) {
		if (key == ORMConstants.KEY_COMPANY_COMPANYCOUNTRY) {
			this.companyCountry = (data.movieverse.Country) owner;
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
	
	private data.movieverse.Country companyCountry;
	
	private String homepage;
	
	private String name;
	
	private String image;
	
	private String country;
	
	private String headquarters;
	
	private java.util.Set ORM_movies = new java.util.HashSet();
	
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
	
	private void setORM_Movies(java.util.Set value) {
		this.ORM_movies = value;
	}
	
	private java.util.Set getORM_Movies() {
		return ORM_movies;
	}
	
	public final data.movieverse.MovieSetCollection movies = new data.movieverse.MovieSetCollection(this, _ormAdapter, ORMConstants.KEY_COMPANY_MOVIES, ORMConstants.KEY_MOVIE_COMPANIES, ORMConstants.KEY_MUL_MANY_TO_MANY);
	
	public void setCompanyCountry(data.movieverse.Country value) {
		this.companyCountry = value;
	}
	
	public data.movieverse.Country getCompanyCountry() {
		return companyCountry;
	}
	
	public String toString() {
		return String.valueOf(getId());
	}
	
}
