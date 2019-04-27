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

public class Theater {
	public Theater() {
	}
	
	private int id;
	
	private String name;
	
	private String city;
	
	private String site;
	
	private Country country;
	
	private java.util.Set showtime = new java.util.HashSet();
	
	private void setId(int value) {
		this.id = value;
	}
	
	public int getId() {
		return id;
	}
	
	public int getORMID() {
		return getId();
	}
	
	public void setName(String value) {
		this.name = value;
	}
	
	public String getName() {
		return name;
	}
	
	public void setCity(String value) {
		this.city = value;
	}
	
	public String getCity() {
		return city;
	}
	
	public void setSite(String value) {
		this.site = value;
	}
	
	public String getSite() {
		return site;
	}
	
	public void setShowtime(java.util.Set value) {
		this.showtime = value;
	}
	
	public java.util.Set getShowtime() {
		return showtime;
	}
	
	
	public void setCountry(Country value) {
		this.country = value;
	}
	
	public Country getCountry() {
		return country;
	}
	
	public String toString() {
		return String.valueOf(getId());
	}
	
}
