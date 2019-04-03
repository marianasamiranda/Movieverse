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

import org.hibernate.Criteria;
import org.orm.PersistentException;
import org.orm.PersistentSession;
import org.orm.criteria.*;

public class GenreCriteria extends AbstractORMCriteria {
	public final IntegerExpression id;
	public final StringExpression name;
	public final CollectionExpression movies;
	
	public GenreCriteria(Criteria criteria) {
		super(criteria);
		id = new IntegerExpression("id", this);
		name = new StringExpression("name", this);
		movies = new CollectionExpression("ORM_Movies", this);
	}
	
	public GenreCriteria(PersistentSession session) {
		this(session.createCriteria(Genre.class));
	}
	
	public GenreCriteria() throws PersistentException {
		this(MovieversePersistentManager.instance().getSession());
	}
	
	public MovieCriteria createMoviesCriteria() {
		return new MovieCriteria(createCriteria("ORM_Movies"));
	}
	
	public Genre uniqueGenre() {
		return (Genre) super.uniqueResult();
	}
	
	public Genre[] listGenre() {
		java.util.List list = super.list();
		return (Genre[]) list.toArray(new Genre[list.size()]);
	}
}

