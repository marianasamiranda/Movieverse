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

public class Movie {
	public Movie() {
	}
	
	private int tmdb;
	
	private String name;
	
	private String poster;
	
	private String backdrop;
	
	private String plot;
	
	private String imdb;
	
	private java.util.Date release;
	
	private Integer runtime;
	
	private int ratingSum;
	
	private int ratingCount;
	
	private Integer budget;
	
	private String language;
	
	private String tagline;

	private int favouriteCount;

	private int watchCount;
	
	private java.util.Set userMovies = new java.util.HashSet();
	
	private java.util.Set genres = new java.util.HashSet();
	
	private java.util.Set companies = new java.util.HashSet();
	
	private java.util.Set comments = new java.util.HashSet();
	
	private java.util.Set movieMembers = new java.util.HashSet();
	
	private java.util.Set showtime = new java.util.HashSet();
	
	private java.util.Set relatedMovies = new java.util.HashSet();
	
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
	
	public void setPoster(String value) {
		this.poster = value;
	}
	
	public String getPoster() {
		return poster;
	}
	
	public void setBackdrop(String value) {
		this.backdrop = value;
	}
	
	public String getBackdrop() {
		return backdrop;
	}
	
	public void setPlot(String value) {
		this.plot = value;
	}
	
	public String getPlot() {
		return plot;
	}
	
	public void setImdb(String value) {
		this.imdb = value;
	}
	
	public String getImdb() {
		return imdb;
	}
	
	public void setRelease(java.util.Date value) {
		this.release = value;
	}
	
	public java.util.Date getRelease() {
		return release;
	}
	
	public void setRuntime(Integer value) {
		this.runtime = value;
	}
	
	public Integer getRuntime() {
		return runtime;
	}
	
	public void setRatingSum(int value) {
		this.ratingSum = value;
	}
	
	public int getRatingSum() {
		return ratingSum;
	}
	
	public void setRatingCount(int value) {
		this.ratingCount = value;
	}
	
	public int getRatingCount() {
		return ratingCount;
	}
	
	public void setBudget(Integer value) {
		this.budget = value;
	}
	
	public Integer getBudget() {
		return budget;
	}
	
	public void setLanguage(String value) {
		this.language = value;
	}
	
	public String getLanguage() {
		return language;
	}
	
	public void setTagline(String value) {
		this.tagline = value;
	}
	
	public String getTagline() {
		return tagline;
	}

	public void setFavouriteCount(int value) {
		this.favouriteCount = value;
	}

	public int getFavouriteCount() {
		return favouriteCount;
	}

	public void setWatchCount(int value) {
		this.watchCount = value;
	}

	public int getWatchCount() {
		return watchCount;
	}

	public MUser[] getmUsers() {
		java.util.ArrayList lValues = new java.util.ArrayList(5);
		for(java.util.Iterator lIter = userMovies.iterator();lIter.hasNext();) {
			lValues.add(((UserMovie)lIter.next()).getmUser());
		}
		return (MUser[])lValues.toArray(new MUser[lValues.size()]);
	}

	public void removemUser(MUser amUser) {
		UserMovie[] lUserMovies = (UserMovie[])userMovies.toArray(new UserMovie[userMovies.size()]);
		for(int i = 0; i < lUserMovies.length; i++) {
			if(lUserMovies[i].getmUser().equals(amUser)) {
				userMovies.remove(lUserMovies[i]);
			}
		}
	}

	public void addmUser(UserMovie aUserMovie, MUser amUser) {
		aUserMovie.setmUser(amUser);
		userMovies.add(aUserMovie);
	}

	public UserMovie getUserMovieBymUser(MUser amUser) {
		UserMovie[] lUserMovies = (UserMovie[])userMovies.toArray(new UserMovie[userMovies.size()]);
		for(int i = 0; i < lUserMovies.length; i++) {
			if(lUserMovies[i].getmUser().equals(amUser)) {
				return lUserMovies[i];
			}
		}
		return null;
	}

	public void setUserMovies(java.util.Set value) {
		this.userMovies = value;
	}

	public java.util.Set getUserMovies() {
		return userMovies;
	}


	public void setGenres(java.util.Set value) {
		this.genres = value;
	}

	public java.util.Set getGenres() {
		return genres;
	}


	public void setCompanies(java.util.Set value) {
		this.companies = value;
	}

	public java.util.Set getCompanies() {
		return companies;
	}


	public void setComments(java.util.Set value) {
		this.comments = value;
	}

	public java.util.Set getComments() {
		return comments;
	}


	public Member[] getMembers() {
		java.util.ArrayList lValues = new java.util.ArrayList(5);
		for(java.util.Iterator lIter = movieMembers.iterator();lIter.hasNext();) {
			lValues.add(((MovieMember)lIter.next()).getMember());
		}
		return (Member[])lValues.toArray(new Member[lValues.size()]);
	}

	public void removeMember(Member aMember) {
		MovieMember[] lMovieMembers = (MovieMember[])movieMembers.toArray(new MovieMember[movieMembers.size()]);
		for(int i = 0; i < lMovieMembers.length; i++) {
			if(lMovieMembers[i].getMember().equals(aMember)) {
				movieMembers.remove(lMovieMembers[i]);
			}
		}
	}

	public void addMember(MovieMember aMovieMember, Member aMember) {
		aMovieMember.setMember(aMember);
		movieMembers.add(aMovieMember);
	}

	public MovieMember getMovieMemberByMember(Member aMember) {
		MovieMember[] lMovieMembers = (MovieMember[])movieMembers.toArray(new MovieMember[movieMembers.size()]);
		for(int i = 0; i < lMovieMembers.length; i++) {
			if(lMovieMembers[i].getMember().equals(aMember)) {
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


	public void setShowtime(java.util.Set value) {
		this.showtime = value;
	}

	public java.util.Set getShowtime() {
		return showtime;
	}


	public void setRelatedMovies(java.util.Set value) {
		this.relatedMovies = value;
	}

	public java.util.Set getRelatedMovies() {
		return relatedMovies;
	}


	public void setMedia(java.util.Set value) {
		this.media = value;
	}

	public java.util.Set getMedia() {
		return media;
	}


	public String toString() {
		return String.valueOf(getTmdb());
	}

	
}
