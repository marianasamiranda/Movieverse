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

public class Comment {
	public Comment() {
	}
	
	private java.util.Set this_getSet (int key) {
		if (key == ORMConstants.KEY_COMMENT_UPVOTER) {
			return ORM_upvoter;
		}
		
		return null;
	}
	
	private void this_setOwner(Object owner, int key) {
		if (key == ORMConstants.KEY_COMMENT_COMMENTER) {
			this.commenter = (data.movieverse.MUser) owner;
		}
		
		else if (key == ORMConstants.KEY_COMMENT_MOVIE) {
			this.movie = (data.movieverse.Movie) owner;
		}
	}
	
	org.orm.util.ORMAdapter _ormAdapter = new org.orm.util.AbstractORMAdapter() {
		public java.util.Set getSet(int key) {
			return this_getSet(key);
		}
		
		public void setOwner(Object owner, int key) {
			this_setOwner(owner, key);
		}
		
	};
	
	private int id;
	
	private data.movieverse.Movie movie;
	
	private data.movieverse.MUser commenter;
	
	private java.util.Date date;
	
	private String content;
	
	private int parent;
	
	private int likes;
	
	private java.util.Set ORM_upvoter = new java.util.HashSet();
	
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
	
	public void setCommenter(data.movieverse.MUser value) {
		if (commenter != null) {
			commenter.comments.remove(this);
		}
		if (value != null) {
			value.comments.add(this);
		}
	}
	
	public data.movieverse.MUser getCommenter() {
		return commenter;
	}
	
	/**
	 * This method is for internal use only.
	 */
	public void setORM_Commenter(data.movieverse.MUser value) {
		this.commenter = value;
	}
	
	private data.movieverse.MUser getORM_Commenter() {
		return commenter;
	}
	
	private void setORM_Upvoter(java.util.Set value) {
		this.ORM_upvoter = value;
	}
	
	private java.util.Set getORM_Upvoter() {
		return ORM_upvoter;
	}
	
	public final data.movieverse.MUserSetCollection upvoter = new data.movieverse.MUserSetCollection(this, _ormAdapter, ORMConstants.KEY_COMMENT_UPVOTER, ORMConstants.KEY_MUL_MANY_TO_MANY);
	
	public void setMovie(data.movieverse.Movie value) {
		if (movie != null) {
			movie.comments.remove(this);
		}
		if (value != null) {
			value.comments.add(this);
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
