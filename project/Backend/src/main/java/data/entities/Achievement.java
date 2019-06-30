/**
 * Licensee: mariana(Universidade do Minho)
 * License Type: Academic
 */
package data.entities;

import java.io.Serializable;

public class Achievement implements Serializable {
	public Achievement() {
	}


	private java.util.Date date;

	private Badge badge;

	private int badgeId;

	private void setBadgeId(int value) {
		this.badgeId = value;
	}

	public int getBadgeId() {
		return badgeId;
	}

	private MUser mUser;

	private int mUserId;

	private void setmUserId(int value) {
		this.mUserId = value;
	}

	public int getmUserId() {
		return mUserId;
	}

	public void setDate(java.util.Date value) {
		this.date = value;
	}

	public java.util.Date getDate() {
		return date;
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
		return String.valueOf(((getBadge() == null) ? "" : String.valueOf(getBadge().getORMID())) + " " + ((getmUser() == null) ? "" : String.valueOf(getmUser().getORMID())));
	}
}
