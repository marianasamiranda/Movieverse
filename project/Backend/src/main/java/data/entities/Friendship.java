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

import java.io.Serializable;

public class Friendship implements Serializable {
	public Friendship() {
	}

	private java.util.Date date;
	
	private boolean pending;
	
	private MUser requestedMuser;

	private int requestedMuserId;

	private void setRequestedMuserId(int value) {
		this.requestedMuserId = value;
	}

	public int getRequestedMuserId() {
		return requestedMuserId;
	}

	private MUser receivedMuser;

	private int receivedMuserId;

	private void setReceivedMuserId(int value) {
		this.receivedMuserId = value;
	}

	public int getReceivedMuserId() {
		return receivedMuserId;
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
		return String.valueOf(((getRequestedMuser() == null) ? "" : String.valueOf(getRequestedMuser().getORMID())) + " " + ((getReceivedMuser() == null) ? "" : String.valueOf(getReceivedMuser().getORMID())));
	}
}
