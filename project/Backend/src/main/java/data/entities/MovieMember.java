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

public class MovieMember {
	public MovieMember() {
	}
	
	private int id;
	
	private String role;
	
	private Member member;
	
	private Movie movie;
	
	private void setId(int value) {
		this.id = value;
	}
	
	public int getId() {
		return id;
	}
	
	public int getORMID() {
		return getId();
	}
	
	public void setRole(String value) {
		this.role = value;
	}
	
	public String getRole() {
		return role;
	}
	
	public void setMovie(Movie value) {
		this.movie = value;
	}
	
	public Movie getMovie() {
		return movie;
	}
	
	public void setMember(Member value) {
		this.member = value;
	}
	
	public Member getMember() {
		return member;
	}
	
	public String toString() {
		return String.valueOf(getId());
	}
	
}
