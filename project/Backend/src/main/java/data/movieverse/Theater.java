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

public class Theater {
	public Theater() {
	}
	
	private java.util.Set this_getSet (int key) {
		if (key == ORMConstants.KEY_THEATER_SHOWTIME) {
			return ORM_showtime;
		}
		
		return null;
	}
	
	org.orm.util.ORMAdapter _ormAdapter = new org.orm.util.AbstractORMAdapter() {
		public java.util.Set getSet(int key) {
			return this_getSet(key);
		}
		
	};
	
	private int id;
	
	private String name;
	
	private String city;
	
	private String site;
	
	private data.movieverse.Country country;
	
	private java.util.Set ORM_showtime = new java.util.HashSet();
	
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
	
	private void setORM_Showtime(java.util.Set value) {
		this.ORM_showtime = value;
	}
	
	private java.util.Set getORM_Showtime() {
		return ORM_showtime;
	}
	
	public final data.movieverse.ShowtimeSetCollection showtime = new data.movieverse.ShowtimeSetCollection(this, _ormAdapter, ORMConstants.KEY_THEATER_SHOWTIME, ORMConstants.KEY_SHOWTIME_THEATER, ORMConstants.KEY_MUL_ONE_TO_MANY);
	
	public void setCountry(data.movieverse.Country value) {
		this.country = value;
	}
	
	public data.movieverse.Country getCountry() {
		return country;
	}
	
	public String toString() {
		return String.valueOf(getId());
	}
	
}
