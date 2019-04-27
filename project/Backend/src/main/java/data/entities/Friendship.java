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

public class Friendship {
	public Friendship() {
	}
	
	private int id;
	
	private java.util.Date date;
	
	private boolean pending;
	
	private MUser requestedMuser;
	
	private MUser receivedMuser;
	
	private void setId(int value) {
		this.id = value;
	}
	
	public int getId() {
		return id;
	}
	
	public int getORMID() {
		return getId();
	}
	
	public void setDate(java.util.Date value) {
		this.date = value;
	}
	
	public java.util.Date getDate() {
		return date;
	}
	
	public void setPending(boolean value) {
		this.pending = value;
	}
	
	public boolean getPending() {
		return pending;
	}
	
	public void setReceivedMuser(MUser value) {
		this.receivedMuser = value;
	}
	
	public MUser getReceivedMuser() {
		return receivedMuser;
	}
	
	public void setRequestedMuser(MUser value) {
		this.requestedMuser = value;
	}
	
	public MUser getRequestedMuser() {
		return requestedMuser;
	}
	
	public String toString() {
		return String.valueOf(getId());
	}
	
}
