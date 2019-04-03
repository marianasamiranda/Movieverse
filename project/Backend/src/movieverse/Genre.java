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
package movieverse;

public class Genre {
	public Genre() {
	}
	
	private java.util.Set this_getSet (int key) {
		if (key == ORMConstants.KEY_GENRE_MOVIES) {
			return ORM_movies;
		}
		
		return null;
	}
	
	org.orm.util.ORMAdapter _ormAdapter = new org.orm.util.AbstractORMAdapter() {
		public java.util.Set getSet(int key) {
			return this_getSet(key);
		}
		
	};
	
	private int id;
	
	private String name;
	
	private java.util.Set ORM_movies = new java.util.HashSet();
	
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
	
	private void setORM_Movies(java.util.Set value) {
		this.ORM_movies = value;
	}
	
	private java.util.Set getORM_Movies() {
		return ORM_movies;
	}
	
	public final movieverse.MovieSetCollection movies = new movieverse.MovieSetCollection(this, _ormAdapter, ORMConstants.KEY_GENRE_MOVIES, ORMConstants.KEY_MOVIE_GENRES, ORMConstants.KEY_MUL_MANY_TO_MANY);
	
	public String toString() {
		return String.valueOf(getId());
	}
	
}
