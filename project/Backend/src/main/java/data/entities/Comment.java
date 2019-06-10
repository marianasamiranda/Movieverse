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

import org.springframework.transaction.annotation.Transactional;

public class Comment {
	public Comment() {
	}
	
	private int id;
	
	private Movie movie;
	
	private MUser commenter;
	
	private java.util.Date date;
	
	private String content;
	
	private int parent;
	
	private int likes;
	
	private java.util.Set upvoter = new java.util.HashSet();
	
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
	
	public void setContent(String value) {
		this.content = value;
	}
	
	public String getContent() {
		return content;
	}
	
	public void setParent(int value) {
		this.parent = value;
	}
	
	public int getParent() {
		return parent;
	}
	
	public void setLikes(int value) {
		this.likes = value;
	}
	
	public int getLikes() {
		return likes;
	}
	
	public void setCommenter(MUser value) {
		this.commenter = value;
	}
	
	public MUser getCommenter() {
		return commenter;
	}
	
	public void setUpvoter(java.util.Set value) {
		this.upvoter = value;
	}
	
	public java.util.Set getUpvoter() {
		return upvoter;
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

	public void addUpvoter(MUser user) {
		upvoter.add(user);
	}

	public void removeUpvoter(MUser user) {
		upvoter.remove(user);
	}
	
}
