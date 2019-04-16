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

import org.orm.*;
import org.hibernate.Query;
import org.hibernate.LockMode;
import java.util.List;

public class GenreDAO {
	public static Genre loadGenreByORMID(int id) throws PersistentException {
		try {
			PersistentSession session = MovieversePersistentManager.instance().getSession();
			return loadGenreByORMID(session, id);
		}
		catch (Exception e) {
			e.printStackTrace();
			throw new PersistentException(e);
		}
	}
	
	public static Genre getGenreByORMID(int id) throws PersistentException {
		try {
			PersistentSession session = MovieversePersistentManager.instance().getSession();
			return getGenreByORMID(session, id);
		}
		catch (Exception e) {
			e.printStackTrace();
			throw new PersistentException(e);
		}
	}
	
	public static Genre loadGenreByORMID(int id, org.hibernate.LockMode lockMode) throws PersistentException {
		try {
			PersistentSession session = MovieversePersistentManager.instance().getSession();
			return loadGenreByORMID(session, id, lockMode);
		}
		catch (Exception e) {
			e.printStackTrace();
			throw new PersistentException(e);
		}
	}
	
	public static Genre getGenreByORMID(int id, org.hibernate.LockMode lockMode) throws PersistentException {
		try {
			PersistentSession session = MovieversePersistentManager.instance().getSession();
			return getGenreByORMID(session, id, lockMode);
		}
		catch (Exception e) {
			e.printStackTrace();
			throw new PersistentException(e);
		}
	}
	
	public static Genre loadGenreByORMID(PersistentSession session, int id) throws PersistentException {
		try {
			return (Genre) session.load(data.movieverse.Genre.class, new Integer(id));
		}
		catch (Exception e) {
			e.printStackTrace();
			throw new PersistentException(e);
		}
	}
	
	public static Genre getGenreByORMID(PersistentSession session, int id) throws PersistentException {
		try {
			return (Genre) session.get(data.movieverse.Genre.class, new Integer(id));
		}
		catch (Exception e) {
			e.printStackTrace();
			throw new PersistentException(e);
		}
	}
	
	public static Genre loadGenreByORMID(PersistentSession session, int id, org.hibernate.LockMode lockMode) throws PersistentException {
		try {
			return (Genre) session.load(data.movieverse.Genre.class, new Integer(id), lockMode);
		}
		catch (Exception e) {
			e.printStackTrace();
			throw new PersistentException(e);
		}
	}
	
	public static Genre getGenreByORMID(PersistentSession session, int id, org.hibernate.LockMode lockMode) throws PersistentException {
		try {
			return (Genre) session.get(data.movieverse.Genre.class, new Integer(id), lockMode);
		}
		catch (Exception e) {
			e.printStackTrace();
			throw new PersistentException(e);
		}
	}
	
	public static List queryGenre(String condition, String orderBy) throws PersistentException {
		try {
			PersistentSession session = MovieversePersistentManager.instance().getSession();
			return queryGenre(session, condition, orderBy);
		}
		catch (Exception e) {
			e.printStackTrace();
			throw new PersistentException(e);
		}
	}
	
	public static List queryGenre(String condition, String orderBy, org.hibernate.LockMode lockMode) throws PersistentException {
		try {
			PersistentSession session = MovieversePersistentManager.instance().getSession();
			return queryGenre(session, condition, orderBy, lockMode);
		}
		catch (Exception e) {
			e.printStackTrace();
			throw new PersistentException(e);
		}
	}
	
	public static Genre[] listGenreByQuery(String condition, String orderBy) throws PersistentException {
		try {
			PersistentSession session = MovieversePersistentManager.instance().getSession();
			return listGenreByQuery(session, condition, orderBy);
		}
		catch (Exception e) {
			e.printStackTrace();
			throw new PersistentException(e);
		}
	}
	
	public static Genre[] listGenreByQuery(String condition, String orderBy, org.hibernate.LockMode lockMode) throws PersistentException {
		try {
			PersistentSession session = MovieversePersistentManager.instance().getSession();
			return listGenreByQuery(session, condition, orderBy, lockMode);
		}
		catch (Exception e) {
			e.printStackTrace();
			throw new PersistentException(e);
		}
	}
	
	public static List queryGenre(PersistentSession session, String condition, String orderBy) throws PersistentException {
		StringBuffer sb = new StringBuffer("From data.movieverse.Genre as Genre");
		if (condition != null)
			sb.append(" Where ").append(condition);
		if (orderBy != null)
			sb.append(" Order By ").append(orderBy);
		try {
			Query query = session.createQuery(sb.toString());
			return query.list();
		}
		catch (Exception e) {
			e.printStackTrace();
			throw new PersistentException(e);
		}
	}
	
	public static List queryGenre(PersistentSession session, String condition, String orderBy, org.hibernate.LockMode lockMode) throws PersistentException {
		StringBuffer sb = new StringBuffer("From data.movieverse.Genre as Genre");
		if (condition != null)
			sb.append(" Where ").append(condition);
		if (orderBy != null)
			sb.append(" Order By ").append(orderBy);
		try {
			Query query = session.createQuery(sb.toString());
			query.setLockMode("Genre", lockMode);
			return query.list();
		}
		catch (Exception e) {
			e.printStackTrace();
			throw new PersistentException(e);
		}
	}
	
	public static Genre[] listGenreByQuery(PersistentSession session, String condition, String orderBy) throws PersistentException {
		try {
			List list = queryGenre(session, condition, orderBy);
			return (Genre[]) list.toArray(new Genre[list.size()]);
		}
		catch (Exception e) {
			e.printStackTrace();
			throw new PersistentException(e);
		}
	}
	
	public static Genre[] listGenreByQuery(PersistentSession session, String condition, String orderBy, org.hibernate.LockMode lockMode) throws PersistentException {
		try {
			List list = queryGenre(session, condition, orderBy, lockMode);
			return (Genre[]) list.toArray(new Genre[list.size()]);
		}
		catch (Exception e) {
			e.printStackTrace();
			throw new PersistentException(e);
		}
	}
	
	public static Genre loadGenreByQuery(String condition, String orderBy) throws PersistentException {
		try {
			PersistentSession session = MovieversePersistentManager.instance().getSession();
			return loadGenreByQuery(session, condition, orderBy);
		}
		catch (Exception e) {
			e.printStackTrace();
			throw new PersistentException(e);
		}
	}
	
	public static Genre loadGenreByQuery(String condition, String orderBy, org.hibernate.LockMode lockMode) throws PersistentException {
		try {
			PersistentSession session = MovieversePersistentManager.instance().getSession();
			return loadGenreByQuery(session, condition, orderBy, lockMode);
		}
		catch (Exception e) {
			e.printStackTrace();
			throw new PersistentException(e);
		}
	}
	
	public static Genre loadGenreByQuery(PersistentSession session, String condition, String orderBy) throws PersistentException {
		Genre[] genres = listGenreByQuery(session, condition, orderBy);
		if (genres != null && genres.length > 0)
			return genres[0];
		else
			return null;
	}
	
	public static Genre loadGenreByQuery(PersistentSession session, String condition, String orderBy, org.hibernate.LockMode lockMode) throws PersistentException {
		Genre[] genres = listGenreByQuery(session, condition, orderBy, lockMode);
		if (genres != null && genres.length > 0)
			return genres[0];
		else
			return null;
	}
	
	public static java.util.Iterator iterateGenreByQuery(String condition, String orderBy) throws PersistentException {
		try {
			PersistentSession session = MovieversePersistentManager.instance().getSession();
			return iterateGenreByQuery(session, condition, orderBy);
		}
		catch (Exception e) {
			e.printStackTrace();
			throw new PersistentException(e);
		}
	}
	
	public static java.util.Iterator iterateGenreByQuery(String condition, String orderBy, org.hibernate.LockMode lockMode) throws PersistentException {
		try {
			PersistentSession session = MovieversePersistentManager.instance().getSession();
			return iterateGenreByQuery(session, condition, orderBy, lockMode);
		}
		catch (Exception e) {
			e.printStackTrace();
			throw new PersistentException(e);
		}
	}
	
	public static java.util.Iterator iterateGenreByQuery(PersistentSession session, String condition, String orderBy) throws PersistentException {
		StringBuffer sb = new StringBuffer("From data.movieverse.Genre as Genre");
		if (condition != null)
			sb.append(" Where ").append(condition);
		if (orderBy != null)
			sb.append(" Order By ").append(orderBy);
		try {
			Query query = session.createQuery(sb.toString());
			return query.iterate();
		}
		catch (Exception e) {
			e.printStackTrace();
			throw new PersistentException(e);
		}
	}
	
	public static java.util.Iterator iterateGenreByQuery(PersistentSession session, String condition, String orderBy, org.hibernate.LockMode lockMode) throws PersistentException {
		StringBuffer sb = new StringBuffer("From data.movieverse.Genre as Genre");
		if (condition != null)
			sb.append(" Where ").append(condition);
		if (orderBy != null)
			sb.append(" Order By ").append(orderBy);
		try {
			Query query = session.createQuery(sb.toString());
			query.setLockMode("Genre", lockMode);
			return query.iterate();
		}
		catch (Exception e) {
			e.printStackTrace();
			throw new PersistentException(e);
		}
	}
	
	public static Genre createGenre() {
		return new data.movieverse.Genre();
	}
	
	public static boolean save(data.movieverse.Genre genre) throws PersistentException {
		try {
			MovieversePersistentManager.instance().saveObject(genre);
			return true;
		}
		catch (Exception e) {
			e.printStackTrace();
			throw new PersistentException(e);
		}
	}
	
	public static boolean delete(data.movieverse.Genre genre) throws PersistentException {
		try {
			MovieversePersistentManager.instance().deleteObject(genre);
			return true;
		}
		catch (Exception e) {
			e.printStackTrace();
			throw new PersistentException(e);
		}
	}
	
	public static boolean deleteAndDissociate(data.movieverse.Genre genre)throws PersistentException {
		try {
			data.movieverse.Movie[] lMoviess = genre.movies.toArray();
			for(int i = 0; i < lMoviess.length; i++) {
				lMoviess[i].genres.remove(genre);
			}
			return delete(genre);
		}
		catch(Exception e) {
			e.printStackTrace();
			throw new PersistentException(e);
		}
	}
	
	public static boolean deleteAndDissociate(data.movieverse.Genre genre, org.orm.PersistentSession session)throws PersistentException {
		try {
			data.movieverse.Movie[] lMoviess = genre.movies.toArray();
			for(int i = 0; i < lMoviess.length; i++) {
				lMoviess[i].genres.remove(genre);
			}
			try {
				session.delete(genre);
				return true;
			} catch (Exception e) {
				return false;
			}
		}
		catch(Exception e) {
			e.printStackTrace();
			throw new PersistentException(e);
		}
	}
	
	public static boolean refresh(data.movieverse.Genre genre) throws PersistentException {
		try {
			MovieversePersistentManager.instance().getSession().refresh(genre);
			return true;
		}
		catch (Exception e) {
			e.printStackTrace();
			throw new PersistentException(e);
		}
	}
	
	public static boolean evict(data.movieverse.Genre genre) throws PersistentException {
		try {
			MovieversePersistentManager.instance().getSession().evict(genre);
			return true;
		}
		catch (Exception e) {
			e.printStackTrace();
			throw new PersistentException(e);
		}
	}
	
	public static Genre loadGenreByCriteria(GenreCriteria genreCriteria) {
		Genre[] genres = listGenreByCriteria(genreCriteria);
		if(genres == null || genres.length == 0) {
			return null;
		}
		return genres[0];
	}
	
	public static Genre[] listGenreByCriteria(GenreCriteria genreCriteria) {
		return genreCriteria.listGenre();
	}
}
