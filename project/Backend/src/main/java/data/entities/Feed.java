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

public class Feed {
	public Feed() {
	}
	
	private int id;
	
	private MUser user;
	
	private int type;
	
	private int idContent;

	private java.sql.Timestamp timestamp;

	private void setId(int value) {
		this.id = value;
	}
	
	public int getId() {
		return id;
	}
	
	public int getORMID() {
		return getId();
	}
	
	public void setType(int value) {
		this.type = value;
	}
	
	public int getType() {
		return type;
	}
	
	public void setIdContent(int value) {
		this.idContent = value;
	}
	
	public int getIdContent() {
		return idContent;
	}

	public void setTimestamp(java.sql.Timestamp value) {
		this.timestamp = value;
	}

	public java.sql.Timestamp getTimestamp() {
		return timestamp;
	}
	
	public void setUser(MUser value) {
		this.user = value;
	}
	
	public MUser getUser() {
		return user;
	}
	
	public String toString() {
		return String.valueOf(getId());
	}
	
}
