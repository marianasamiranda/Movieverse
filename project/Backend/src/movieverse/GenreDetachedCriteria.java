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

import java.util.List;
import org.hibernate.criterion.DetachedCriteria;
import org.orm.PersistentSession;
import org.orm.criteria.*;

public class GenreDetachedCriteria extends AbstractORMDetachedCriteria {
	public final IntegerExpression id;
	public final StringExpression name;
	public final CollectionExpression movies;
	
	public GenreDetachedCriteria() {
		super(movieverse.Genre.class, movieverse.GenreCriteria.class);
		id = new IntegerExpression("id", this.getDetachedCriteria());
		name = new StringExpression("name", this.getDetachedCriteria());
		movies = new CollectionExpression("ORM_Movies", this.getDetachedCriteria());
	}
	
	public GenreDetachedCriteria(DetachedCriteria aDetachedCriteria) {
		super(aDetachedCriteria, movieverse.GenreCriteria.class);
		id = new IntegerExpression("id", this.getDetachedCriteria());
		name = new StringExpression("name", this.getDetachedCriteria());
		movies = new CollectionExpression("ORM_Movies", this.getDetachedCriteria());
	}
	
	public MovieDetachedCriteria createMoviesCriteria() {
		return new MovieDetachedCriteria(createCriteria("ORM_Movies"));
	}
	
	public Genre uniqueGenre(PersistentSession session) {
		return (Genre) super.createExecutableCriteria(session).uniqueResult();
	}
	
	public Genre[] listGenre(PersistentSession session) {
		List list = super.createExecutableCriteria(session).list();
		return (Genre[]) list.toArray(new Genre[list.size()]);
	}
}

