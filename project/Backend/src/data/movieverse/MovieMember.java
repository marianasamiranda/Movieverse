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

public class MovieMember {
	public MovieMember() {
	}
	
	private void this_setOwner(Object owner, int key) {
		if (key == ORMConstants.KEY_MOVIEMEMBER_MOVIE) {
			this.movie = (data.movieverse.Movie) owner;
		}
		
		else if (key == ORMConstants.KEY_MOVIEMEMBER_MEMBER) {
			this.member = (data.movieverse.Member) owner;
		}
	}
	
	org.orm.util.ORMAdapter _ormAdapter = new org.orm.util.AbstractORMAdapter() {
		public void setOwner(Object owner, int key) {
			this_setOwner(owner, key);
		}
		
	};
	
	private int id;
	
	private String role;
	
	private data.movieverse.Member member;
	
	private data.movieverse.Movie movie;
	
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
	
	public void setMovie(data.movieverse.Movie value) {
		if (movie != null) {
			movie.movieMembers.remove(this);
		}
		if (value != null) {
			value.movieMembers.add(this);
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
	
	public void setMember(data.movieverse.Member value) {
		if (member != null) {
			member.movieMembers.remove(this);
		}
		if (value != null) {
			value.movieMembers.add(this);
		}
	}
	
	public data.movieverse.Member getMember() {
		return member;
	}
	
	/**
	 * This method is for internal use only.
	 */
	public void setORM_Member(data.movieverse.Member value) {
		this.member = value;
	}
	
	private data.movieverse.Member getORM_Member() {
		return member;
	}
	
	public String toString() {
		return String.valueOf(getId());
	}
	
}
