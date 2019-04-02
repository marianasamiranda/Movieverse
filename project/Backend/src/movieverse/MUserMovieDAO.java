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

import org.orm.*;
import org.hibernate.Query;
import org.hibernate.LockMode;
import java.util.List;

public class MUserMovieDAO {
	public static MUserMovie loadMUserMovieByORMID(int id) throws PersistentException {
		try {
			PersistentSession session = MovieversePersistentManager.instance().getSession();
			return loadMUserMovieByORMID(session, id);
		}
		catch (Exception e) {
			e.printStackTrace();
			throw new PersistentException(e);
		}
	}
	
	public static MUserMovie getMUserMovieByORMID(int id) throws PersistentException {
		try {
			PersistentSession session = MovieversePersistentManager.instance().getSession();
			return getMUserMovieByORMID(session, id);
		}
		catch (Exception e) {
			e.printStackTrace();
			throw new PersistentException(e);
		}
	}
	
	public static MUserMovie loadMUserMovieByORMID(int id, org.hibernate.LockMode lockMode) throws PersistentException {
		try {
			PersistentSession session = MovieversePersistentManager.instance().getSession();
			return loadMUserMovieByORMID(session, id, lockMode);
		}
		catch (Exception e) {
			e.printStackTrace();
			throw new PersistentException(e);
		}
	}
	
	public static MUserMovie getMUserMovieByORMID(int id, org.hibernate.LockMode lockMode) throws PersistentException {
		try {
			PersistentSession session = MovieversePersistentManager.instance().getSession();
			return getMUserMovieByORMID(session, id, lockMode);
		}
		catch (Exception e) {
			e.printStackTrace();
			throw new PersistentException(e);
		}
	}
	
	public static MUserMovie loadMUserMovieByORMID(PersistentSession session, int id) throws PersistentException {
		try {
			return (MUserMovie) session.load(movieverse.MUserMovie.class, new Integer(id));
		}
		catch (Exception e) {
			e.printStackTrace();
			throw new PersistentException(e);
		}
	}
	
	public static MUserMovie getMUserMovieByORMID(PersistentSession session, int id) throws PersistentException {
		try {
			return (MUserMovie) session.get(movieverse.MUserMovie.class, new Integer(id));
		}
		catch (Exception e) {
			e.printStackTrace();
			throw new PersistentException(e);
		}
	}
	
	public static MUserMovie loadMUserMovieByORMID(PersistentSession session, int id, org.hibernate.LockMode lockMode) throws PersistentException {
		try {
			return (MUserMovie) session.load(movieverse.MUserMovie.class, new Integer(id), lockMode);
		}
		catch (Exception e) {
			e.printStackTrace();
			throw new PersistentException(e);
		}
	}
	
	public static MUserMovie getMUserMovieByORMID(PersistentSession session, int id, org.hibernate.LockMode lockMode) throws PersistentException {
		try {
			return (MUserMovie) session.get(movieverse.MUserMovie.class, new Integer(id), lockMode);
		}
		catch (Exception e) {
			e.printStackTrace();
			throw new PersistentException(e);
		}
	}
	
	public static List queryMUserMovie(String condition, String orderBy) throws PersistentException {
		try {
			PersistentSession session = MovieversePersistentManager.instance().getSession();
			return queryMUserMovie(session, condition, orderBy);
		}
		catch (Exception e) {
			e.printStackTrace();
			throw new PersistentException(e);
		}
	}
	
	public static List queryMUserMovie(String condition, String orderBy, org.hibernate.LockMode lockMode) throws PersistentException {
		try {
			PersistentSession session = MovieversePersistentManager.instance().getSession();
			return queryMUserMovie(session, condition, orderBy, lockMode);
		}
		catch (Exception e) {
			e.printStackTrace();
			throw new PersistentException(e);
		}
	}
	
	public static MUserMovie[] listMUserMovieByQuery(String condition, String orderBy) throws PersistentException {
		try {
			PersistentSession session = MovieversePersistentManager.instance().getSession();
			return listMUserMovieByQuery(session, condition, orderBy);
		}
		catch (Exception e) {
			e.printStackTrace();
			throw new PersistentException(e);
		}
	}
	
	public static MUserMovie[] listMUserMovieByQuery(String condition, String orderBy, org.hibernate.LockMode lockMode) throws PersistentException {
		try {
			PersistentSession session = MovieversePersistentManager.instance().getSession();
			return listMUserMovieByQuery(session, condition, orderBy, lockMode);
		}
		catch (Exception e) {
			e.printStackTrace();
			throw new PersistentException(e);
		}
	}
	
	public static List queryMUserMovie(PersistentSession session, String condition, String orderBy) throws PersistentException {
		StringBuffer sb = new StringBuffer("From movieverse.MUserMovie as MUserMovie");
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
	
	public static List queryMUserMovie(PersistentSession session, String condition, String orderBy, org.hibernate.LockMode lockMode) throws PersistentException {
		StringBuffer sb = new StringBuffer("From movieverse.MUserMovie as MUserMovie");
		if (condition != null)
			sb.append(" Where ").append(condition);
		if (orderBy != null)
			sb.append(" Order By ").append(orderBy);
		try {
			Query query = session.createQuery(sb.toString());
			query.setLockMode("MUserMovie", lockMode);
			return query.list();
		}
		catch (Exception e) {
			e.printStackTrace();
			throw new PersistentException(e);
		}
	}
	
	public static MUserMovie[] listMUserMovieByQuery(PersistentSession session, String condition, String orderBy) throws PersistentException {
		try {
			List list = queryMUserMovie(session, condition, orderBy);
			return (MUserMovie[]) list.toArray(new MUserMovie[list.size()]);
		}
		catch (Exception e) {
			e.printStackTrace();
			throw new PersistentException(e);
		}
	}
	
	public static MUserMovie[] listMUserMovieByQuery(PersistentSession session, String condition, String orderBy, org.hibernate.LockMode lockMode) throws PersistentException {
		try {
			List list = queryMUserMovie(session, condition, orderBy, lockMode);
			return (MUserMovie[]) list.toArray(new MUserMovie[list.size()]);
		}
		catch (Exception e) {
			e.printStackTrace();
			throw new PersistentException(e);
		}
	}
	
	public static MUserMovie loadMUserMovieByQuery(String condition, String orderBy) throws PersistentException {
		try {
			PersistentSession session = MovieversePersistentManager.instance().getSession();
			return loadMUserMovieByQuery(session, condition, orderBy);
		}
		catch (Exception e) {
			e.printStackTrace();
			throw new PersistentException(e);
		}
	}
	
	public static MUserMovie loadMUserMovieByQuery(String condition, String orderBy, org.hibernate.LockMode lockMode) throws PersistentException {
		try {
			PersistentSession session = MovieversePersistentManager.instance().getSession();
			return loadMUserMovieByQuery(session, condition, orderBy, lockMode);
		}
		catch (Exception e) {
			e.printStackTrace();
			throw new PersistentException(e);
		}
	}
	
	public static MUserMovie loadMUserMovieByQuery(PersistentSession session, String condition, String orderBy) throws PersistentException {
		MUserMovie[] mUserMovies = listMUserMovieByQuery(session, condition, orderBy);
		if (mUserMovies != null && mUserMovies.length > 0)
			return mUserMovies[0];
		else
			return null;
	}
	
	public static MUserMovie loadMUserMovieByQuery(PersistentSession session, String condition, String orderBy, org.hibernate.LockMode lockMode) throws PersistentException {
		MUserMovie[] mUserMovies = listMUserMovieByQuery(session, condition, orderBy, lockMode);
		if (mUserMovies != null && mUserMovies.length > 0)
			return mUserMovies[0];
		else
			return null;
	}
	
	public static java.util.Iterator iterateMUserMovieByQuery(String condition, String orderBy) throws PersistentException {
		try {
			PersistentSession session = MovieversePersistentManager.instance().getSession();
			return iterateMUserMovieByQuery(session, condition, orderBy);
		}
		catch (Exception e) {
			e.printStackTrace();
			throw new PersistentException(e);
		}
	}
	
	public static java.util.Iterator iterateMUserMovieByQuery(String condition, String orderBy, org.hibernate.LockMode lockMode) throws PersistentException {
		try {
			PersistentSession session = MovieversePersistentManager.instance().getSession();
			return iterateMUserMovieByQuery(session, condition, orderBy, lockMode);
		}
		catch (Exception e) {
			e.printStackTrace();
			throw new PersistentException(e);
		}
	}
	
	public static java.util.Iterator iterateMUserMovieByQuery(PersistentSession session, String condition, String orderBy) throws PersistentException {
		StringBuffer sb = new StringBuffer("From movieverse.MUserMovie as MUserMovie");
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
	
	public static java.util.Iterator iterateMUserMovieByQuery(PersistentSession session, String condition, String orderBy, org.hibernate.LockMode lockMode) throws PersistentException {
		StringBuffer sb = new StringBuffer("From movieverse.MUserMovie as MUserMovie");
		if (condition != null)
			sb.append(" Where ").append(condition);
		if (orderBy != null)
			sb.append(" Order By ").append(orderBy);
		try {
			Query query = session.createQuery(sb.toString());
			query.setLockMode("MUserMovie", lockMode);
			return query.iterate();
		}
		catch (Exception e) {
			e.printStackTrace();
			throw new PersistentException(e);
		}
	}
	
	public static MUserMovie createMUserMovie() {
		return new movieverse.MUserMovie();
	}
	
	public static boolean save(movieverse.MUserMovie mUserMovie) throws PersistentException {
		try {
			MovieversePersistentManager.instance().saveObject(mUserMovie);
			return true;
		}
		catch (Exception e) {
			e.printStackTrace();
			throw new PersistentException(e);
		}
	}
	
	public static boolean delete(movieverse.MUserMovie mUserMovie) throws PersistentException {
		try {
			MovieversePersistentManager.instance().deleteObject(mUserMovie);
			return true;
		}
		catch (Exception e) {
			e.printStackTrace();
			throw new PersistentException(e);
		}
	}
	
	public static boolean deleteAndDissociate(movieverse.MUserMovie mUserMovie)throws PersistentException {
		try {
			if (mUserMovie.getMovie() != null) {
				mUserMovie.getMovie().mUserMovies.remove(mUserMovie);
			}
			
			if (mUserMovie.getmUser() != null) {
				mUserMovie.getmUser().mUserMovies.remove(mUserMovie);
			}
			
			return delete(mUserMovie);
		}
		catch(Exception e) {
			e.printStackTrace();
			throw new PersistentException(e);
		}
	}
	
	public static boolean deleteAndDissociate(movieverse.MUserMovie mUserMovie, org.orm.PersistentSession session)throws PersistentException {
		try {
			if (mUserMovie.getMovie() != null) {
				mUserMovie.getMovie().mUserMovies.remove(mUserMovie);
			}
			
			if (mUserMovie.getmUser() != null) {
				mUserMovie.getmUser().mUserMovies.remove(mUserMovie);
			}
			
			try {
				session.delete(mUserMovie);
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
	
	public static boolean refresh(movieverse.MUserMovie mUserMovie) throws PersistentException {
		try {
			MovieversePersistentManager.instance().getSession().refresh(mUserMovie);
			return true;
		}
		catch (Exception e) {
			e.printStackTrace();
			throw new PersistentException(e);
		}
	}
	
	public static boolean evict(movieverse.MUserMovie mUserMovie) throws PersistentException {
		try {
			MovieversePersistentManager.instance().getSession().evict(mUserMovie);
			return true;
		}
		catch (Exception e) {
			e.printStackTrace();
			throw new PersistentException(e);
		}
	}
	
	public static MUserMovie loadMUserMovieByCriteria(MUserMovieCriteria mUserMovieCriteria) {
		MUserMovie[] mUserMovies = listMUserMovieByCriteria(mUserMovieCriteria);
		if(mUserMovies == null || mUserMovies.length == 0) {
			return null;
		}
		return mUserMovies[0];
	}
	
	public static MUserMovie[] listMUserMovieByCriteria(MUserMovieCriteria mUserMovieCriteria) {
		return mUserMovieCriteria.listMUserMovie();
	}
}
