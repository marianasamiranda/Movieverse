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

public class Member {
	public Member() {
	}
	
	private int tmdb;
	
	private String name;
	
	private String image;
	
	private java.util.Date birthDate;
	
	private char gender;
	
	private String imdb;
	
	private String biography;
	
	private String BirthPlace;
	
	private java.util.Set movieMembers = new java.util.HashSet();
	
	private java.util.Set media = new java.util.HashSet();
	
	private void setTmdb(int value) {
		this.tmdb = value;
	}
	
	public int getTmdb() {
		return tmdb;
	}
	
	public int getORMID() {
		return getTmdb();
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
	
	public void setGender(char value) {
		this.gender = value;
	}
	
	public char getGender() {
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
	
	public Movie[] getMovies() {
		java.util.ArrayList lValues = new java.util.ArrayList(5);
		for(java.util.Iterator lIter = movieMembers.iterator();lIter.hasNext();) {
			lValues.add(((MovieMember)lIter.next()).getMovie());
		}
		return (Movie[])lValues.toArray(new Movie[lValues.size()]);
	}
	
	public void removeMovie(Movie aMovie) {
		MovieMember[] lMovieMembers = (MovieMember[])movieMembers.toArray(new MovieMember[movieMembers.size()]);
		for(int i = 0; i < lMovieMembers.length; i++) {
			if(lMovieMembers[i].getMovie().equals(aMovie)) {
				movieMembers.remove(lMovieMembers[i]);
			}
		}
	}
	
	public void addMovie(MovieMember aMovieMember, Movie aMovie) {
		aMovieMember.setMovie(aMovie);
		movieMembers.add(aMovieMember);
	}
	
	public MovieMember getMovieMemberByMovie(Movie aMovie) {
		MovieMember[] lMovieMembers = (MovieMember[])movieMembers.toArray(new MovieMember[movieMembers.size()]);
		for(int i = 0; i < lMovieMembers.length; i++) {
			if(lMovieMembers[i].getMovie().equals(aMovie)) {
				return lMovieMembers[i];
			}
		}
		return null;
	}
	
	public void setMovieMembers(java.util.Set value) {
		this.movieMembers = value;
	}
	
	public java.util.Set getMovieMembers() {
		return movieMembers;
	}
	
	
	public void setMedia(java.util.Set value) {
		this.media = value;
	}
	
	public java.util.Set getMedia() {
		return media;
	}
	
	
	public void setGender(boolean gender) {
		//TODO: Implement Method
		throw new UnsupportedOperationException();
	}
	
	public String toString() {
		return String.valueOf(getTmdb());
	}
	
}
