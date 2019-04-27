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
package data.entities;

public class Company {
	public Company() {
	}
	
	private int id;
	
	private Company parentCompany;
	
	private Country companyCountry;
	
	private String homepage;
	
	private String name;
	
	private String image;
	
	private String headquarters;
	
	private String description;
	
	private java.util.Set movies = new java.util.HashSet();
	
	private java.util.Set childrenCompanies = new java.util.HashSet();
	
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
	
	public void setMovies(java.util.Set value) {
		this.movies = value;
	}
	
	public java.util.Set getMovies() {
		return movies;
	}
	
	
	public void setChildrenCompanies(java.util.Set value) {
		this.childrenCompanies = value;
	}
	
	public java.util.Set getChildrenCompanies() {
		return childrenCompanies;
	}
	
	
	public void setCompanyCountry(Country value) {
		this.companyCountry = value;
	}
	
	public Country getCompanyCountry() {
		return companyCountry;
	}
	
	public void setParentCompany(Company value) {
		this.parentCompany = value;
	}
	
	public Company getParentCompany() {
		return parentCompany;
	}
	
	public String toString() {
		return String.valueOf(getId());
	}
	
}
