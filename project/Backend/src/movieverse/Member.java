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

public class Member {
	public Member() {
	}
	
	private java.util.Set this_getSet (int key) {
		if (key == ORMConstants.KEY_MEMBER_MOVIEMEMBERS) {
			return ORM_movieMembers;
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
	
	private String image;
	
	private java.util.Date birthDate;
	
	private boolean gender;
	
	private String imdb;
	
	private String biography;
	
	private String BirthPlace;
	
	private java.util.Set ORM_movieMembers = new java.util.HashSet();
	
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
	
	public void setImage(String value) {
		this.image = value;
	}
	
	public String getImage() {
		return image;
	}
	
	public void setBirthDate(java.util.Date value) {
		this.birthDate = value;
	}
	
	public java.util.Date getBirthDate() {
		return birthDate;
	}
	
	public void setGender(boolean value) {
		this.gender = value;
	}
	
	public boolean getGender() {
		return gender;
	}
	
	public void setImdb(String value) {
		this.imdb = value;
	}
	
	public String getImdb() {
		return imdb;
	}
	
	public void setBiography(String value) {
		this.biography = value;
	}
	
	public String getBiography() {
		return biography;
	}
	
	public void setBirthPlace(String value) {
		this.BirthPlace = value;
	}
	
	public String getBirthPlace() {
		return BirthPlace;
	}
	
	public movieverse.Movie[] getMovies() {
		java.util.ArrayList lValues = new java.util.ArrayList(5);
		for(java.util.Iterator lIter = movieMembers.getIterator();lIter.hasNext();) {
			lValues.add(((movieverse.MovieMember)lIter.next()).getMovie());
		}
		return (movieverse.Movie[])lValues.toArray(new movieverse.Movie[lValues.size()]);
	}
	
	public void removeMovie(movieverse.Movie aMovie) {
		movieverse.MovieMember[] lMovieMembers = movieMembers.toArray();
		for(int i = 0; i < lMovieMembers.length; i++) {
			if(lMovieMembers[i].getMovie().equals(aMovie)) {
				movieMembers.remove(lMovieMembers[i]);
			}
		}
	}
	
	public void addMovie(movieverse.MovieMember aMovieMember, movieverse.Movie aMovie) {
		aMovieMember.setMovie(aMovie);
		movieMembers.add(aMovieMember);
	}
	
	public movieverse.MovieMember getMovieMemberByMovie(movieverse.Movie aMovie) {
		movieverse.MovieMember[] lMovieMembers = movieMembers.toArray();
		for(int i = 0; i < lMovieMembers.length; i++) {
			if(lMovieMembers[i].getMovie().equals(aMovie)) {
				return lMovieMembers[i];
			}
		}
		return null;
	}
	
	private void setORM_MovieMembers(java.util.Set value) {
		this.ORM_movieMembers = value;
	}
	
	private java.util.Set getORM_MovieMembers() {
		return ORM_movieMembers;
	}
	
	public final movieverse.MovieMemberSetCollection movieMembers = new movieverse.MovieMemberSetCollection(this, _ormAdapter, ORMConstants.KEY_MEMBER_MOVIEMEMBERS, ORMConstants.KEY_MOVIEMEMBER_MEMBER, ORMConstants.KEY_MUL_ONE_TO_MANY);
	
	public String toString() {
		return String.valueOf(getId());
	}
	
}
