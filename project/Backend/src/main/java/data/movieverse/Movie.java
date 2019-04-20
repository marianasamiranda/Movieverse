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

public class Movie {
	public Movie() {
	}
	
	private java.util.Set this_getSet (int key) {
		if (key == ORMConstants.KEY_MOVIE_USERMOVIES) {
			return ORM_userMovies;
		}
		else if (key == ORMConstants.KEY_MOVIE_GENRES) {
			return ORM_genres;
		}
		else if (key == ORMConstants.KEY_MOVIE_COMPANIES) {
			return ORM_companies;
		}
		else if (key == ORMConstants.KEY_MOVIE_COMMENTS) {
			return ORM_comments;
		}
		else if (key == ORMConstants.KEY_MOVIE_MOVIEMEMBERS) {
			return ORM_movieMembers;
		}
		else if (key == ORMConstants.KEY_MOVIE_SHOWTIME) {
			return ORM_showtime;
		}
		else if (key == ORMConstants.KEY_MOVIE_RELATEDMOVIES) {
			return ORM_relatedMovies;
		}
		else if (key == ORMConstants.KEY_MOVIE_MEDIA) {
			return ORM_media;
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
	
	private String poster;
	
	private String backdrop;
	
	private String plot;
	
	private String imdb;
	
	private java.util.Date release;
	
	private int runtime;
	
	private int ratingSum;
	
	private int ratingCount;
	
	private java.util.Set ORM_userMovies = new java.util.HashSet();
	
	private java.util.Set ORM_genres = new java.util.HashSet();
	
	private java.util.Set ORM_companies = new java.util.HashSet();
	
	private java.util.Set ORM_comments = new java.util.HashSet();
	
	private java.util.Set ORM_movieMembers = new java.util.HashSet();
	
	private java.util.Set ORM_showtime = new java.util.HashSet();
	
	private java.util.Set ORM_relatedMovies = new java.util.HashSet();
	
	private java.util.Set ORM_media = new java.util.HashSet();
	
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
	
	public void setRuntime(int value) {
		this.runtime = value;
	}
	
	public int getRuntime() {
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
	
	public data.movieverse.MUser[] getmUsers() {
		java.util.ArrayList lValues = new java.util.ArrayList(5);
		for(java.util.Iterator lIter = userMovies.getIterator();lIter.hasNext();) {
			lValues.add(((data.movieverse.UserMovie)lIter.next()).getmUser());
		}
		return (data.movieverse.MUser[])lValues.toArray(new data.movieverse.MUser[lValues.size()]);
	}
	
	public void removemUser(data.movieverse.MUser amUser) {
		data.movieverse.UserMovie[] lUserMovies = userMovies.toArray();
		for(int i = 0; i < lUserMovies.length; i++) {
			if(lUserMovies[i].getmUser().equals(amUser)) {
				userMovies.remove(lUserMovies[i]);
			}
		}
	}
	
	public void addmUser(data.movieverse.UserMovie aUserMovie, data.movieverse.MUser amUser) {
		aUserMovie.setmUser(amUser);
		userMovies.add(aUserMovie);
	}
	
	public data.movieverse.UserMovie getUserMovieBymUser(data.movieverse.MUser amUser) {
		data.movieverse.UserMovie[] lUserMovies = userMovies.toArray();
		for(int i = 0; i < lUserMovies.length; i++) {
			if(lUserMovies[i].getmUser().equals(amUser)) {
				return lUserMovies[i];
			}
		}
		return null;
	}
	
	private void setORM_UserMovies(java.util.Set value) {
		this.ORM_userMovies = value;
	}
	
	private java.util.Set getORM_UserMovies() {
		return ORM_userMovies;
	}
	
	public final data.movieverse.UserMovieSetCollection userMovies = new data.movieverse.UserMovieSetCollection(this, _ormAdapter, ORMConstants.KEY_MOVIE_USERMOVIES, ORMConstants.KEY_USERMOVIE_MOVIE, ORMConstants.KEY_MUL_ONE_TO_MANY);
	
	private void setORM_Genres(java.util.Set value) {
		this.ORM_genres = value;
	}
	
	private java.util.Set getORM_Genres() {
		return ORM_genres;
	}
	
	public final data.movieverse.GenreSetCollection genres = new data.movieverse.GenreSetCollection(this, _ormAdapter, ORMConstants.KEY_MOVIE_GENRES, ORMConstants.KEY_GENRE_MOVIES, ORMConstants.KEY_MUL_MANY_TO_MANY);
	
	private void setORM_Companies(java.util.Set value) {
		this.ORM_companies = value;
	}
	
	private java.util.Set getORM_Companies() {
		return ORM_companies;
	}
	
	public final data.movieverse.CompanySetCollection companies = new data.movieverse.CompanySetCollection(this, _ormAdapter, ORMConstants.KEY_MOVIE_COMPANIES, ORMConstants.KEY_COMPANY_MOVIES, ORMConstants.KEY_MUL_MANY_TO_MANY);
	
	private void setORM_Comments(java.util.Set value) {
		this.ORM_comments = value;
	}
	
	private java.util.Set getORM_Comments() {
		return ORM_comments;
	}
	
	public final data.movieverse.CommentSetCollection comments = new data.movieverse.CommentSetCollection(this, _ormAdapter, ORMConstants.KEY_MOVIE_COMMENTS, ORMConstants.KEY_COMMENT_MOVIE, ORMConstants.KEY_MUL_ONE_TO_MANY);
	
	public data.movieverse.Member[] getMembers() {
		java.util.ArrayList lValues = new java.util.ArrayList(5);
		for(java.util.Iterator lIter = movieMembers.getIterator();lIter.hasNext();) {
			lValues.add(((data.movieverse.MovieMember)lIter.next()).getMember());
		}
		return (data.movieverse.Member[])lValues.toArray(new data.movieverse.Member[lValues.size()]);
	}
	
	public void removeMember(data.movieverse.Member aMember) {
		data.movieverse.MovieMember[] lMovieMembers = movieMembers.toArray();
		for(int i = 0; i < lMovieMembers.length; i++) {
			if(lMovieMembers[i].getMember().equals(aMember)) {
				movieMembers.remove(lMovieMembers[i]);
			}
		}
	}
	
	public void addMember(data.movieverse.MovieMember aMovieMember, data.movieverse.Member aMember) {
		aMovieMember.setMember(aMember);
		movieMembers.add(aMovieMember);
	}
	
	public data.movieverse.MovieMember getMovieMemberByMember(data.movieverse.Member aMember) {
		data.movieverse.MovieMember[] lMovieMembers = movieMembers.toArray();
		for(int i = 0; i < lMovieMembers.length; i++) {
			if(lMovieMembers[i].getMember().equals(aMember)) {
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
	
	public final data.movieverse.MovieMemberSetCollection movieMembers = new data.movieverse.MovieMemberSetCollection(this, _ormAdapter, ORMConstants.KEY_MOVIE_MOVIEMEMBERS, ORMConstants.KEY_MOVIEMEMBER_MOVIE, ORMConstants.KEY_MUL_ONE_TO_MANY);
	
	private void setORM_Showtime(java.util.Set value) {
		this.ORM_showtime = value;
	}
	
	private java.util.Set getORM_Showtime() {
		return ORM_showtime;
	}
	
	public final data.movieverse.ShowtimeSetCollection showtime = new data.movieverse.ShowtimeSetCollection(this, _ormAdapter, ORMConstants.KEY_MOVIE_SHOWTIME, ORMConstants.KEY_SHOWTIME_MOVIE, ORMConstants.KEY_MUL_ONE_TO_MANY);
	
	private void setORM_RelatedMovies(java.util.Set value) {
		this.ORM_relatedMovies = value;
	}
	
	private java.util.Set getORM_RelatedMovies() {
		return ORM_relatedMovies;
	}
	
	public final data.movieverse.MovieSetCollection relatedMovies = new data.movieverse.MovieSetCollection(this, _ormAdapter, ORMConstants.KEY_MOVIE_RELATEDMOVIES, ORMConstants.KEY_MUL_ONE_TO_MANY);
	
	private void setORM_Media(java.util.Set value) {
		this.ORM_media = value;
	}
	
	private java.util.Set getORM_Media() {
		return ORM_media;
	}
	
	public final data.movieverse.MediaSetCollection media = new data.movieverse.MediaSetCollection(this, _ormAdapter, ORMConstants.KEY_MOVIE_MEDIA, ORMConstants.KEY_MUL_ONE_TO_MANY);
	
	public void getId_int() {
		//TODO: Implement Method
		throw new UnsupportedOperationException();
	}
	
	public void setId_int(int id_int) {
		//TODO: Implement Method
		throw new UnsupportedOperationException();
	}
	
	public String toString() {
		return String.valueOf(getId());
	}
	
}
