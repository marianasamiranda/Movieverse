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
public class UserMovie implements Serializable {
	public UserMovie() {
	}


	private boolean status;
	
	private Integer rating;
	
	private boolean favourite;
	
	private java.util.Date dateWatched;
	
	private java.util.Date dateFavourite;
	
	private Movie movie;

	private int movieId;

	private void setMovieId(int value) {
		this.movieId = value;
	}

	public int getMovieId() {
		return movieId;
	}

	private MUser mUser;

	private int mUserId;

	private void setmUserId(int value) {
		this.mUserId = value;
	}

	public int getmUserId() {
		return mUserId;
	}
	
	public void setStatus(boolean value) {
		this.status = value;
	}
	
	public boolean getStatus() {
		return status;
	}
	
	public void setRating(Integer value) {
		this.rating = value;
	}
	
	public Integer getRating() {
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

	public void setmUser(MUser value) {
		this.mUser = value;
		this.mUserId = value.getId();
	}
	
	public MUser getmUser() {
		return mUser;
	}
	
	public void setMovie(Movie value) {
		this.movie = value;
		this.movieId = movie.getTmdb();
	}
	
	public Movie getMovie() {
		return movie;
	}
	
	public String toString() {
		return String.valueOf(((getMovie() == null) ? "" : String.valueOf(getMovie().getORMID())) + " " + ((getmUser() == null) ? "" : String.valueOf(getmUser().getORMID())));
	}
}
