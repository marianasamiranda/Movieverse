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

public class Feed {
	public Feed() {
	}
	
	private void this_setOwner(Object owner, int key) {
		if (key == ORMConstants.KEY_FEED_USER) {
			this.user = (data.movieverse.MUser) owner;
		}
	}
	
	org.orm.util.ORMAdapter _ormAdapter = new org.orm.util.AbstractORMAdapter() {
		public void setOwner(Object owner, int key) {
			this_setOwner(owner, key);
		}
		
	};
	
	private int id;
	
	private data.movieverse.MUser user;
	
	private int type;
	
	private int idContent;
	
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
	
	public void setUser(data.movieverse.MUser value) {
		if (user != null) {
			user.feed.remove(this);
		}
		if (value != null) {
			value.feed.add(this);
		}
	}
	
	public data.movieverse.MUser getUser() {
		return user;
	}
	
	/**
	 * This method is for internal use only.
	 */
	public void setORM_User(data.movieverse.MUser value) {
		this.user = value;
	}
	
	private data.movieverse.MUser getORM_User() {
		return user;
	}
	
	public String toString() {
		return String.valueOf(getId());
	}
	
}
