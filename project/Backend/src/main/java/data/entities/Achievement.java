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

public class Achievement {
	public Achievement() {
	}
	
	private int id;
	
	private java.util.Date date;
	
	private Badge badge;
	
	private MUser mUser;
	
	public void setDate(java.util.Date value) {
		this.date = value;
	}
	
	public java.util.Date getDate() {
		return date;
	}
	
	private void setId(int value) {
		this.id = value;
	}
	
	public int getId() {
		return id;
	}
	
	public int getORMID() {
		return getId();
	}
	
	public void setmUser(MUser value) {
		this.mUser = value;
	}
	
	public MUser getmUser() {
		return mUser;
	}
	
	public void setBadge(Badge value) {
		this.badge = value;
	}
	
	public Badge getBadge() {
		return badge;
	}
	
	public String toString() {
		return String.valueOf(getId());
	}
	
}
