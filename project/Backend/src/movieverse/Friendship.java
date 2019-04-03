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
package movieverse;

public class Friendship {
	public Friendship() {
	}
	
	private void this_setOwner(Object owner, int key) {
		if (key == ORMConstants.KEY_FRIENDSHIP_REQUESTEDMUSER) {
			this.requestedMuser = (movieverse.MUser) owner;
		}
		
		else if (key == ORMConstants.KEY_FRIENDSHIP_RECEIVEDMUSER) {
			this.receivedMuser = (movieverse.MUser) owner;
		}
	}
	
	org.orm.util.ORMAdapter _ormAdapter = new org.orm.util.AbstractORMAdapter() {
		public void setOwner(Object owner, int key) {
			this_setOwner(owner, key);
		}
		
	};
	
	private int id;
	
	private java.util.Date date;
	
	private movieverse.MUser receivedMuser;
	
	private movieverse.MUser requestedMuser;
	
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
	
	public void setRequestedMuser(movieverse.MUser value) {
		if (requestedMuser != null) {
			requestedMuser.receivedFriendships.remove(this);
		}
		if (value != null) {
			value.receivedFriendships.add(this);
		}
	}
	
	public movieverse.MUser getRequestedMuser() {
		return requestedMuser;
	}
	
	/**
	 * This method is for internal use only.
	 */
	public void setORM_RequestedMuser(movieverse.MUser value) {
		this.requestedMuser = value;
	}
	
	private movieverse.MUser getORM_RequestedMuser() {
		return requestedMuser;
	}
	
	public void setReceivedMuser(movieverse.MUser value) {
		if (receivedMuser != null) {
			receivedMuser.requestedFriendships.remove(this);
		}
		if (value != null) {
			value.requestedFriendships.add(this);
		}
	}
	
	public movieverse.MUser getReceivedMuser() {
		return receivedMuser;
	}
	
	/**
	 * This method is for internal use only.
	 */
	public void setORM_ReceivedMuser(movieverse.MUser value) {
		this.receivedMuser = value;
	}
	
	private movieverse.MUser getORM_ReceivedMuser() {
		return receivedMuser;
	}
	
	public String toString() {
		return String.valueOf(getId());
	}
	
}
