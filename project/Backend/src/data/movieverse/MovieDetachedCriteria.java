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

import java.util.List;
import org.hibernate.criterion.DetachedCriteria;
import org.orm.PersistentSession;
import org.orm.criteria.*;

public class MovieDetachedCriteria extends AbstractORMDetachedCriteria {
	public final IntegerExpression id;
	public final StringExpression name;
	public final StringExpression poster;
	public final StringExpression backdrop;
	public final StringExpression plot;
	public final StringExpression imdb;
	public final DateExpression release;
	public final IntegerExpression runtime;
	public final IntegerExpression ratingSum;
	public final IntegerExpression ratingCount;
	public final CollectionExpression mUserMovies;
	public final CollectionExpression genres;
	public final CollectionExpression companies;
	public final CollectionExpression comments;
	public final CollectionExpression movieMembers;
	
	public MovieDetachedCriteria() {
		super(data.movieverse.Movie.class, data.movieverse.MovieCriteria.class);
		id = new IntegerExpression("id", this.getDetachedCriteria());
		name = new StringExpression("name", this.getDetachedCriteria());
		poster = new StringExpression("poster", this.getDetachedCriteria());
		backdrop = new StringExpression("backdrop", this.getDetachedCriteria());
		plot = new StringExpression("plot", this.getDetachedCriteria());
		imdb = new StringExpression("imdb", this.getDetachedCriteria());
		release = new DateExpression("release", this.getDetachedCriteria());
		runtime = new IntegerExpression("runtime", this.getDetachedCriteria());
		ratingSum = new IntegerExpression("ratingSum", this.getDetachedCriteria());
		ratingCount = new IntegerExpression("ratingCount", this.getDetachedCriteria());
		mUserMovies = new CollectionExpression("ORM_mUserMovies", this.getDetachedCriteria());
		genres = new CollectionExpression("ORM_Genres", this.getDetachedCriteria());
		companies = new CollectionExpression("ORM_Companies", this.getDetachedCriteria());
		comments = new CollectionExpression("ORM_Comments", this.getDetachedCriteria());
		movieMembers = new CollectionExpression("ORM_MovieMembers", this.getDetachedCriteria());
	}
	
	public MovieDetachedCriteria(DetachedCriteria aDetachedCriteria) {
		super(aDetachedCriteria, data.movieverse.MovieCriteria.class);
		id = new IntegerExpression("id", this.getDetachedCriteria());
		name = new StringExpression("name", this.getDetachedCriteria());
		poster = new StringExpression("poster", this.getDetachedCriteria());
		backdrop = new StringExpression("backdrop", this.getDetachedCriteria());
		plot = new StringExpression("plot", this.getDetachedCriteria());
		imdb = new StringExpression("imdb", this.getDetachedCriteria());
		release = new DateExpression("release", this.getDetachedCriteria());
		runtime = new IntegerExpression("runtime", this.getDetachedCriteria());
		ratingSum = new IntegerExpression("ratingSum", this.getDetachedCriteria());
		ratingCount = new IntegerExpression("ratingCount", this.getDetachedCriteria());
		mUserMovies = new CollectionExpression("ORM_mUserMovies", this.getDetachedCriteria());
		genres = new CollectionExpression("ORM_Genres", this.getDetachedCriteria());
		companies = new CollectionExpression("ORM_Companies", this.getDetachedCriteria());
		comments = new CollectionExpression("ORM_Comments", this.getDetachedCriteria());
		movieMembers = new CollectionExpression("ORM_MovieMembers", this.getDetachedCriteria());
	}
	
	public MUserMovieDetachedCriteria createMUserMoviesCriteria() {
		return new MUserMovieDetachedCriteria(createCriteria("ORM_mUserMovies"));
	}
	
	public GenreDetachedCriteria createGenresCriteria() {
		return new GenreDetachedCriteria(createCriteria("ORM_Genres"));
	}
	
	public CompanyDetachedCriteria createCompaniesCriteria() {
		return new CompanyDetachedCriteria(createCriteria("ORM_Companies"));
	}
	
	public CommentDetachedCriteria createCommentsCriteria() {
		return new CommentDetachedCriteria(createCriteria("ORM_Comments"));
	}
	
	public MovieMemberDetachedCriteria createMovieMembersCriteria() {
		return new MovieMemberDetachedCriteria(createCriteria("ORM_MovieMembers"));
	}
	
	public Movie uniqueMovie(PersistentSession session) {
		return (Movie) super.createExecutableCriteria(session).uniqueResult();
	}
	
	public Movie[] listMovie(PersistentSession session) {
		List list = super.createExecutableCriteria(session).list();
		return (Movie[]) list.toArray(new Movie[list.size()]);
	}
}

