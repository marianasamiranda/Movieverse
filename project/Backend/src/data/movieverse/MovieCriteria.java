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

import org.hibernate.Criteria;
import org.orm.PersistentException;
import org.orm.PersistentSession;
import org.orm.criteria.*;

public class MovieCriteria extends AbstractORMCriteria {
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
	
	public MovieCriteria(Criteria criteria) {
		super(criteria);
		id = new IntegerExpression("id", this);
		name = new StringExpression("name", this);
		poster = new StringExpression("poster", this);
		backdrop = new StringExpression("backdrop", this);
		plot = new StringExpression("plot", this);
		imdb = new StringExpression("imdb", this);
		release = new DateExpression("release", this);
		runtime = new IntegerExpression("runtime", this);
		ratingSum = new IntegerExpression("ratingSum", this);
		ratingCount = new IntegerExpression("ratingCount", this);
		mUserMovies = new CollectionExpression("ORM_mUserMovies", this);
		genres = new CollectionExpression("ORM_Genres", this);
		companies = new CollectionExpression("ORM_Companies", this);
		comments = new CollectionExpression("ORM_Comments", this);
		movieMembers = new CollectionExpression("ORM_MovieMembers", this);
	}
	
	public MovieCriteria(PersistentSession session) {
		this(session.createCriteria(Movie.class));
	}
	
	public MovieCriteria() throws PersistentException {
		this(MovieversePersistentManager.instance().getSession());
	}
	
	public MUserMovieCriteria createMUserMoviesCriteria() {
		return new MUserMovieCriteria(createCriteria("ORM_mUserMovies"));
	}
	
	public GenreCriteria createGenresCriteria() {
		return new GenreCriteria(createCriteria("ORM_Genres"));
	}
	
	public CompanyCriteria createCompaniesCriteria() {
		return new CompanyCriteria(createCriteria("ORM_Companies"));
	}
	
	public CommentCriteria createCommentsCriteria() {
		return new CommentCriteria(createCriteria("ORM_Comments"));
	}
	
	public MovieMemberCriteria createMovieMembersCriteria() {
		return new MovieMemberCriteria(createCriteria("ORM_MovieMembers"));
	}
	
	public Movie uniqueMovie() {
		return (Movie) super.uniqueResult();
	}
	
	public Movie[] listMovie() {
		java.util.List list = super.list();
		return (Movie[]) list.toArray(new Movie[list.size()]);
	}
}

