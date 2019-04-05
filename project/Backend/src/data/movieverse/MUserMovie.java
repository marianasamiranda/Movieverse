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

public class MUserMovie {
	public MUserMovie() {
	}
	
	private void this_setOwner(Object owner, int key) {
		if (key == ORMConstants.KEY_MUSERMOVIE_MUSER) {
			this.mUser = (data.movieverse.MUser) owner;
		}
		
		else if (key == ORMConstants.KEY_MUSERMOVIE_MOVIE) {
			this.movie = (data.movieverse.Movie) owner;
		}
	}
	
	org.orm.util.ORMAdapter _ormAdapter = new org.orm.util.AbstractORMAdapter() {
		public void setOwner(Object owner, int key) {
			this_setOwner(owner, key);
		}
		
	};
	
	private int id;
	
	private boolean status;
	
	private int rating;
	
	private boolean favourite;
	
	private java.util.Date dateWatched;
	
	private java.util.Date dateFavourite;
	
	private boolean visible;
	
	private data.movieverse.Movie movie;
	
	private data.movieverse.MUser mUser;
	
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
	
	public void setmUser(data.movieverse.MUser value) {
		if (mUser != null) {
			mUser.mUserMovies.remove(this);
		}
		if (value != null) {
			value.mUserMovies.add(this);
		}
	}
	
	public data.movieverse.MUser getmUser() {
		return mUser;
	}
	
	/**
	 * This method is for internal use only.
	 */
	public void setORM_mUser(data.movieverse.MUser value) {
		this.mUser = value;
	}
	
	private data.movieverse.MUser getORM_mUser() {
		return mUser;
	}
	
	public void setMovie(data.movieverse.Movie value) {
		if (movie != null) {
			movie.mUserMovies.remove(this);
		}
		if (value != null) {
			value.mUserMovies.add(this);
		}
	}
	
	public data.movieverse.Movie getMovie() {
		return movie;
	}
	
	/**
	 * This method is for internal use only.
	 */
	public void setORM_Movie(data.movieverse.Movie value) {
		this.movie = value;
	}
	
	private data.movieverse.Movie getORM_Movie() {
		return movie;
	}
	
	public String toString() {
		return String.valueOf(getId());
	}
	
}
