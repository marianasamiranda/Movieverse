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

public class Genre {
	public Genre() {
	}
	
	private int id;
	
	private String name;
	
	private java.util.Set movies = new java.util.HashSet();
	
	private void setId(int value) {
		this.id = value;
	}
	
	public int getId() {
		return id;
	}
	
	public int getORMID() {
		return getId();
	}
	
	public void setName(String value) {
		this.name = value;
	}
	
	public String getName() {
		return name;
	}
	
	public void setMovies(java.util.Set value) {
		this.movies = value;
	}
	
	public java.util.Set getMovies() {
		return movies;
	}
	
	
	public String toString() {
		return String.valueOf(getId());
	}
	
}
