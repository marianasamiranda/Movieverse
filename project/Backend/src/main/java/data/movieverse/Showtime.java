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

public class Showtime {
	public Showtime() {
	}
	
	private void this_setOwner(Object owner, int key) {
		if (key == ORMConstants.KEY_SHOWTIME_THEATER) {
			this.theater = (data.movieverse.Theater) owner;
		}
		
		else if (key == ORMConstants.KEY_SHOWTIME_MOVIE) {
			this.movie = (data.movieverse.Movie) owner;
		}
	}
	
	org.orm.util.ORMAdapter _ormAdapter = new org.orm.util.AbstractORMAdapter() {
		public void setOwner(Object owner, int key) {
			this_setOwner(owner, key);
		}
		
	};
	
	private int id;
	
	private data.movieverse.Movie movie;
	
	private data.movieverse.Theater theater;
	
	private java.util.Date date;
	
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
	
	public void setTheater(data.movieverse.Theater value) {
		if (theater != null) {
			theater.showtime.remove(this);
		}
		if (value != null) {
			value.showtime.add(this);
		}
	}
	
	public data.movieverse.Theater getTheater() {
		return theater;
	}
	
	/**
	 * This method is for internal use only.
	 */
	public void setORM_Theater(data.movieverse.Theater value) {
		this.theater = value;
	}
	
	private data.movieverse.Theater getORM_Theater() {
		return theater;
	}
	
	public void setMovie(data.movieverse.Movie value) {
		if (movie != null) {
			movie.showtime.remove(this);
		}
		if (value != null) {
			value.showtime.add(this);
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
