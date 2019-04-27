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

public class UserMovie {
	public UserMovie() {
	}
	
	private int id;
	
	private boolean status;
	
	private int rating;
	
	private boolean favourite;
	
	private java.util.Date dateWatched;
	
	private java.util.Date dateFavourite;
	
	private boolean visible;
	
	private Movie movie;
	
	private MUser mUser;
	
	private void setId(int value) {
		this.id = value;
	}
	
	public int getId() {
		return id;
	}
	
	public int getORMID() {
		return getId();
	}
	
	public void setStatus(boolean value) {
		this.status = value;
	}
	
	public boolean getStatus() {
		return status;
	}
	
	public void setRating(int value) {
		this.rating = value;
	}
	
	public int getRating() {
		return rating;
	}
	
	public void setFavourite(boolean value) {
		this.favourite = value;
	}
	
	public boolean getFavourite() {
		return favourite;
	}
	
	public void setDateWatched(java.util.Date value) {
		this.dateWatched = value;
	}
	
	public java.util.Date getDateWatched() {
		return dateWatched;
	}
	
	public void setDateFavourite(java.util.Date value) {
		this.dateFavourite = value;
	}
	
	public java.util.Date getDateFavourite() {
		return dateFavourite;
	}
	
	public void setVisible(boolean value) {
		this.visible = value;
	}
	
	public boolean getVisible() {
		return visible;
	}
	
	public void setmUser(MUser value) {
		this.mUser = value;
	}
	
	public MUser getmUser() {
		return mUser;
	}
	
	public void setMovie(Movie value) {
		this.movie = value;
	}
	
	public Movie getMovie() {
		return movie;
	}
	
	public String toString() {
		return String.valueOf(getId());
	}
	
}
