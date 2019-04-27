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

public class Showtime {
	public Showtime() {
	}
	
	private int id;
	
	private Movie movie;
	
	private Theater theater;
	
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
	
	public void setTheater(Theater value) {
		this.theater = value;
	}
	
	public Theater getTheater() {
		return theater;
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
