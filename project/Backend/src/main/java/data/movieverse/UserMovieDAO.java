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

public class UserMovieDAO {
	public static UserMovie loadUserMovieByORMID(int id) throws PersistentException {
		try {
			PersistentSession session = MovieversePersistentManager.instance().getSession();
			return loadUserMovieByORMID(session, id);
		}
		catch (Exception e) {
			e.printStackTrace();
			throw new PersistentException(e);
		}
	}
	
	public static UserMovie getUserMovieByORMID(int id) throws PersistentException {
		try {
			PersistentSession session = MovieversePersistentManager.instance().getSession();
			return getUserMovieByORMID(session, id);
		}
		catch (Exception e) {
			e.printStackTrace();
			throw new PersistentException(e);
		}
	}
	
	public static UserMovie loadUserMovieByORMID(int id, org.hibernate.LockMode lockMode) throws PersistentException {
		try {
			PersistentSession session = MovieversePersistentManager.instance().getSession();
			return loadUserMovieByORMID(session, id, lockMode);
		}
		catch (Exception e) {
			e.printStackTrace();
			throw new PersistentException(e);
		}
	}
	
	public static UserMovie getUserMovieByORMID(int id, org.hibernate.LockMode lockMode) throws PersistentException {
		try {
			PersistentSession session = MovieversePersistentManager.instance().getSession();
			return getUserMovieByORMID(session, id, lockMode);
		}
		catch (Exception e) {
			e.printStackTrace();
			throw new PersistentException(e);
		}
	}
	
	public static UserMovie loadUserMovieByORMID(PersistentSession session, int id) throws PersistentException {
		try {
			return (UserMovie) session.load(data.movieverse.UserMovie.class, new Integer(id));
		}
		catch (Exception e) {
			e.printStackTrace();
			throw new PersistentException(e);
		}
	}
	
	public static UserMovie getUserMovieByORMID(PersistentSession session, int id) throws PersistentException {
		try {
			return (UserMovie) session.get(data.movieverse.UserMovie.class, new Integer(id));
		}
		catch (Exception e) {
			e.printStackTrace();
			throw new PersistentException(e);
		}
	}
	
	public static UserMovie loadUserMovieByORMID(PersistentSession session, int id, org.hibernate.LockMode lockMode) throws PersistentException {
		try {
			return (UserMovie) session.load(data.movieverse.UserMovie.class, new Integer(id), lockMode);
		}
		catch (Exception e) {
			e.printStackTrace();
			throw new PersistentException(e);
		}
	}
	
	public static UserMovie getUserMovieByORMID(PersistentSession session, int id, org.hibernate.LockMode lockMode) throws PersistentException {
		try {
			return (UserMovie) session.get(data.movieverse.UserMovie.class, new Integer(id), lockMode);
		}
		catch (Exception e) {
			e.printStackTrace();
			throw new PersistentException(e);
		}
	}
	
	public static List queryUserMovie(String condition, String orderBy) throws PersistentException {
		try {
			PersistentSession session = MovieversePersistentManager.instance().getSession();
			return queryUserMovie(session, condition, orderBy);
		}
		catch (Exception e) {
			e.printStackTrace();
			throw new PersistentException(e);
		}
	}
	
	public static List queryUserMovie(String condition, String orderBy, org.hibernate.LockMode lockMode) throws PersistentException {
		try {
			PersistentSession session = MovieversePersistentManager.instance().getSession();
			return queryUserMovie(session, condition, orderBy, lockMode);
		}
		catch (Exception e) {
			e.printStackTrace();
			throw new PersistentException(e);
		}
	}
	
	public static UserMovie[] listUserMovieByQuery(String condition, String orderBy) throws PersistentException {
		try {
			PersistentSession session = MovieversePersistentManager.instance().getSession();
			return listUserMovieByQuery(session, condition, orderBy);
		}
		catch (Exception e) {
			e.printStackTrace();
			throw new PersistentException(e);
		}
	}
	
	public static UserMovie[] listUserMovieByQuery(String condition, String orderBy, org.hibernate.LockMode lockMode) throws PersistentException {
		try {
			PersistentSession session = MovieversePersistentManager.instance().getSession();
			return listUserMovieByQuery(session, condition, orderBy, lockMode);
		}
		catch (Exception e) {
			e.printStackTrace();
			throw new PersistentException(e);
		}
	}
	
	public static List queryUserMovie(PersistentSession session, String condition, String orderBy) throws PersistentException {
		StringBuffer sb = new StringBuffer("From data.movieverse.UserMovie as UserMovie");
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
	
	public static List queryUserMovie(PersistentSession session, String condition, String orderBy, org.hibernate.LockMode lockMode) throws PersistentException {
		StringBuffer sb = new StringBuffer("From data.movieverse.UserMovie as UserMovie");
		if (condition != null)
			sb.append(" Where ").append(condition);
		if (orderBy != null)
			sb.append(" Order By ").append(orderBy);
		try {
			Query query = session.createQuery(sb.toString());
			query.setLockMode("UserMovie", lockMode);
			return query.list();
		}
		catch (Exception e) {
			e.printStackTrace();
			throw new PersistentException(e);
		}
	}
	
	public static UserMovie[] listUserMovieByQuery(PersistentSession session, String condition, String orderBy) throws PersistentException {
		try {
			List list = queryUserMovie(session, condition, orderBy);
			return (UserMovie[]) list.toArray(new UserMovie[list.size()]);
		}
		catch (Exception e) {
			e.printStackTrace();
			throw new PersistentException(e);
		}
	}
	
	public static UserMovie[] listUserMovieByQuery(PersistentSession session, String condition, String orderBy, org.hibernate.LockMode lockMode) throws PersistentException {
		try {
			List list = queryUserMovie(session, condition, orderBy, lockMode);
			return (UserMovie[]) list.toArray(new UserMovie[list.size()]);
		}
		catch (Exception e) {
			e.printStackTrace();
			throw new PersistentException(e);
		}
	}
	
	public static UserMovie loadUserMovieByQuery(String condition, String orderBy) throws PersistentException {
		try {
			PersistentSession session = MovieversePersistentManager.instance().getSession();
			return loadUserMovieByQuery(session, condition, orderBy);
		}
		catch (Exception e) {
			e.printStackTrace();
			throw new PersistentException(e);
		}
	}
	
	public static UserMovie loadUserMovieByQuery(String condition, String orderBy, org.hibernate.LockMode lockMode) throws PersistentException {
		try {
			PersistentSession session = MovieversePersistentManager.instance().getSession();
			return loadUserMovieByQuery(session, condition, orderBy, lockMode);
		}
		catch (Exception e) {
			e.printStackTrace();
			throw new PersistentException(e);
		}
	}
	
	public static UserMovie loadUserMovieByQuery(PersistentSession session, String condition, String orderBy) throws PersistentException {
		UserMovie[] userMovies = listUserMovieByQuery(session, condition, orderBy);
		if (userMovies != null && userMovies.length > 0)
			return userMovies[0];
		else
			return null;
	}
	
	public static UserMovie loadUserMovieByQuery(PersistentSession session, String condition, String orderBy, org.hibernate.LockMode lockMode) throws PersistentException {
		UserMovie[] userMovies = listUserMovieByQuery(session, condition, orderBy, lockMode);
		if (userMovies != null && userMovies.length > 0)
			return userMovies[0];
		else
			return null;
	}
	
	public static java.util.Iterator iterateUserMovieByQuery(String condition, String orderBy) throws PersistentException {
		try {
			PersistentSession session = MovieversePersistentManager.instance().getSession();
			return iterateUserMovieByQuery(session, condition, orderBy);
		}
		catch (Exception e) {
			e.printStackTrace();
			throw new PersistentException(e);
		}
	}
	
	public static java.util.Iterator iterateUserMovieByQuery(String condition, String orderBy, org.hibernate.LockMode lockMode) throws PersistentException {
		try {
			PersistentSession session = MovieversePersistentManager.instance().getSession();
			return iterateUserMovieByQuery(session, condition, orderBy, lockMode);
		}
		catch (Exception e) {
			e.printStackTrace();
			throw new PersistentException(e);
		}
	}
	
	public static java.util.Iterator iterateUserMovieByQuery(PersistentSession session, String condition, String orderBy) throws PersistentException {
		StringBuffer sb = new StringBuffer("From data.movieverse.UserMovie as UserMovie");
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
	
	public static java.util.Iterator iterateUserMovieByQuery(PersistentSession session, String condition, String orderBy, org.hibernate.LockMode lockMode) throws PersistentException {
		StringBuffer sb = new StringBuffer("From data.movieverse.UserMovie as UserMovie");
		if (condition != null)
			sb.append(" Where ").append(condition);
		if (orderBy != null)
			sb.append(" Order By ").append(orderBy);
		try {
			Query query = session.createQuery(sb.toString());
			query.setLockMode("UserMovie", lockMode);
			return query.iterate();
		}
		catch (Exception e) {
			e.printStackTrace();
			throw new PersistentException(e);
		}
	}
	
	public static UserMovie createUserMovie() {
		return new data.movieverse.UserMovie();
	}
	
	public static boolean save(data.movieverse.UserMovie userMovie) throws PersistentException {
		try {
			MovieversePersistentManager.instance().saveObject(userMovie);
			return true;
		}
		catch (Exception e) {
			e.printStackTrace();
			throw new PersistentException(e);
		}
	}
	
	public static boolean delete(data.movieverse.UserMovie userMovie) throws PersistentException {
		try {
			MovieversePersistentManager.instance().deleteObject(userMovie);
			return true;
		}
		catch (Exception e) {
			e.printStackTrace();
			throw new PersistentException(e);
		}
	}
	
	public static boolean deleteAndDissociate(data.movieverse.UserMovie userMovie)throws PersistentException {
		try {
			if (userMovie.getMovie() != null) {
				userMovie.getMovie().userMovies.remove(userMovie);
			}
			
			if (userMovie.getmUser() != null) {
				userMovie.getmUser().userMovies.remove(userMovie);
			}
			
			return delete(userMovie);
		}
		catch(Exception e) {
			e.printStackTrace();
			throw new PersistentException(e);
		}
	}
	
	public static boolean deleteAndDissociate(data.movieverse.UserMovie userMovie, org.orm.PersistentSession session)throws PersistentException {
		try {
			if (userMovie.getMovie() != null) {
				userMovie.getMovie().userMovies.remove(userMovie);
			}
			
			if (userMovie.getmUser() != null) {
				userMovie.getmUser().userMovies.remove(userMovie);
			}
			
			try {
				session.delete(userMovie);
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
	
	public static boolean refresh(data.movieverse.UserMovie userMovie) throws PersistentException {
		try {
			MovieversePersistentManager.instance().getSession().refresh(userMovie);
			return true;
		}
		catch (Exception e) {
			e.printStackTrace();
			throw new PersistentException(e);
		}
	}
	
	public static boolean evict(data.movieverse.UserMovie userMovie) throws PersistentException {
		try {
			MovieversePersistentManager.instance().getSession().evict(userMovie);
			return true;
		}
		catch (Exception e) {
			e.printStackTrace();
			throw new PersistentException(e);
		}
	}
	
	public static UserMovie loadUserMovieByCriteria(UserMovieCriteria userMovieCriteria) {
		UserMovie[] userMovies = listUserMovieByCriteria(userMovieCriteria);
		if(userMovies == null || userMovies.length == 0) {
			return null;
		}
		return userMovies[0];
	}
	
	public static UserMovie[] listUserMovieByCriteria(UserMovieCriteria userMovieCriteria) {
		return userMovieCriteria.listUserMovie();
	}
}
