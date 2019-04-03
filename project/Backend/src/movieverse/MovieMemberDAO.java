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

public class MovieMemberDAO {
	public static MovieMember loadMovieMemberByORMID(int id) throws PersistentException {
		try {
			PersistentSession session = MovieversePersistentManager.instance().getSession();
			return loadMovieMemberByORMID(session, id);
		}
		catch (Exception e) {
			e.printStackTrace();
			throw new PersistentException(e);
		}
	}
	
	public static MovieMember getMovieMemberByORMID(int id) throws PersistentException {
		try {
			PersistentSession session = MovieversePersistentManager.instance().getSession();
			return getMovieMemberByORMID(session, id);
		}
		catch (Exception e) {
			e.printStackTrace();
			throw new PersistentException(e);
		}
	}
	
	public static MovieMember loadMovieMemberByORMID(int id, org.hibernate.LockMode lockMode) throws PersistentException {
		try {
			PersistentSession session = MovieversePersistentManager.instance().getSession();
			return loadMovieMemberByORMID(session, id, lockMode);
		}
		catch (Exception e) {
			e.printStackTrace();
			throw new PersistentException(e);
		}
	}
	
	public static MovieMember getMovieMemberByORMID(int id, org.hibernate.LockMode lockMode) throws PersistentException {
		try {
			PersistentSession session = MovieversePersistentManager.instance().getSession();
			return getMovieMemberByORMID(session, id, lockMode);
		}
		catch (Exception e) {
			e.printStackTrace();
			throw new PersistentException(e);
		}
	}
	
	public static MovieMember loadMovieMemberByORMID(PersistentSession session, int id) throws PersistentException {
		try {
			return (MovieMember) session.load(movieverse.MovieMember.class, new Integer(id));
		}
		catch (Exception e) {
			e.printStackTrace();
			throw new PersistentException(e);
		}
	}
	
	public static MovieMember getMovieMemberByORMID(PersistentSession session, int id) throws PersistentException {
		try {
			return (MovieMember) session.get(movieverse.MovieMember.class, new Integer(id));
		}
		catch (Exception e) {
			e.printStackTrace();
			throw new PersistentException(e);
		}
	}
	
	public static MovieMember loadMovieMemberByORMID(PersistentSession session, int id, org.hibernate.LockMode lockMode) throws PersistentException {
		try {
			return (MovieMember) session.load(movieverse.MovieMember.class, new Integer(id), lockMode);
		}
		catch (Exception e) {
			e.printStackTrace();
			throw new PersistentException(e);
		}
	}
	
	public static MovieMember getMovieMemberByORMID(PersistentSession session, int id, org.hibernate.LockMode lockMode) throws PersistentException {
		try {
			return (MovieMember) session.get(movieverse.MovieMember.class, new Integer(id), lockMode);
		}
		catch (Exception e) {
			e.printStackTrace();
			throw new PersistentException(e);
		}
	}
	
	public static List queryMovieMember(String condition, String orderBy) throws PersistentException {
		try {
			PersistentSession session = MovieversePersistentManager.instance().getSession();
			return queryMovieMember(session, condition, orderBy);
		}
		catch (Exception e) {
			e.printStackTrace();
			throw new PersistentException(e);
		}
	}
	
	public static List queryMovieMember(String condition, String orderBy, org.hibernate.LockMode lockMode) throws PersistentException {
		try {
			PersistentSession session = MovieversePersistentManager.instance().getSession();
			return queryMovieMember(session, condition, orderBy, lockMode);
		}
		catch (Exception e) {
			e.printStackTrace();
			throw new PersistentException(e);
		}
	}
	
	public static MovieMember[] listMovieMemberByQuery(String condition, String orderBy) throws PersistentException {
		try {
			PersistentSession session = MovieversePersistentManager.instance().getSession();
			return listMovieMemberByQuery(session, condition, orderBy);
		}
		catch (Exception e) {
			e.printStackTrace();
			throw new PersistentException(e);
		}
	}
	
	public static MovieMember[] listMovieMemberByQuery(String condition, String orderBy, org.hibernate.LockMode lockMode) throws PersistentException {
		try {
			PersistentSession session = MovieversePersistentManager.instance().getSession();
			return listMovieMemberByQuery(session, condition, orderBy, lockMode);
		}
		catch (Exception e) {
			e.printStackTrace();
			throw new PersistentException(e);
		}
	}
	
	public static List queryMovieMember(PersistentSession session, String condition, String orderBy) throws PersistentException {
		StringBuffer sb = new StringBuffer("From movieverse.MovieMember as MovieMember");
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
	
	public static List queryMovieMember(PersistentSession session, String condition, String orderBy, org.hibernate.LockMode lockMode) throws PersistentException {
		StringBuffer sb = new StringBuffer("From movieverse.MovieMember as MovieMember");
		if (condition != null)
			sb.append(" Where ").append(condition);
		if (orderBy != null)
			sb.append(" Order By ").append(orderBy);
		try {
			Query query = session.createQuery(sb.toString());
			query.setLockMode("MovieMember", lockMode);
			return query.list();
		}
		catch (Exception e) {
			e.printStackTrace();
			throw new PersistentException(e);
		}
	}
	
	public static MovieMember[] listMovieMemberByQuery(PersistentSession session, String condition, String orderBy) throws PersistentException {
		try {
			List list = queryMovieMember(session, condition, orderBy);
			return (MovieMember[]) list.toArray(new MovieMember[list.size()]);
		}
		catch (Exception e) {
			e.printStackTrace();
			throw new PersistentException(e);
		}
	}
	
	public static MovieMember[] listMovieMemberByQuery(PersistentSession session, String condition, String orderBy, org.hibernate.LockMode lockMode) throws PersistentException {
		try {
			List list = queryMovieMember(session, condition, orderBy, lockMode);
			return (MovieMember[]) list.toArray(new MovieMember[list.size()]);
		}
		catch (Exception e) {
			e.printStackTrace();
			throw new PersistentException(e);
		}
	}
	
	public static MovieMember loadMovieMemberByQuery(String condition, String orderBy) throws PersistentException {
		try {
			PersistentSession session = MovieversePersistentManager.instance().getSession();
			return loadMovieMemberByQuery(session, condition, orderBy);
		}
		catch (Exception e) {
			e.printStackTrace();
			throw new PersistentException(e);
		}
	}
	
	public static MovieMember loadMovieMemberByQuery(String condition, String orderBy, org.hibernate.LockMode lockMode) throws PersistentException {
		try {
			PersistentSession session = MovieversePersistentManager.instance().getSession();
			return loadMovieMemberByQuery(session, condition, orderBy, lockMode);
		}
		catch (Exception e) {
			e.printStackTrace();
			throw new PersistentException(e);
		}
	}
	
	public static MovieMember loadMovieMemberByQuery(PersistentSession session, String condition, String orderBy) throws PersistentException {
		MovieMember[] movieMembers = listMovieMemberByQuery(session, condition, orderBy);
		if (movieMembers != null && movieMembers.length > 0)
			return movieMembers[0];
		else
			return null;
	}
	
	public static MovieMember loadMovieMemberByQuery(PersistentSession session, String condition, String orderBy, org.hibernate.LockMode lockMode) throws PersistentException {
		MovieMember[] movieMembers = listMovieMemberByQuery(session, condition, orderBy, lockMode);
		if (movieMembers != null && movieMembers.length > 0)
			return movieMembers[0];
		else
			return null;
	}
	
	public static java.util.Iterator iterateMovieMemberByQuery(String condition, String orderBy) throws PersistentException {
		try {
			PersistentSession session = MovieversePersistentManager.instance().getSession();
			return iterateMovieMemberByQuery(session, condition, orderBy);
		}
		catch (Exception e) {
			e.printStackTrace();
			throw new PersistentException(e);
		}
	}
	
	public static java.util.Iterator iterateMovieMemberByQuery(String condition, String orderBy, org.hibernate.LockMode lockMode) throws PersistentException {
		try {
			PersistentSession session = MovieversePersistentManager.instance().getSession();
			return iterateMovieMemberByQuery(session, condition, orderBy, lockMode);
		}
		catch (Exception e) {
			e.printStackTrace();
			throw new PersistentException(e);
		}
	}
	
	public static java.util.Iterator iterateMovieMemberByQuery(PersistentSession session, String condition, String orderBy) throws PersistentException {
		StringBuffer sb = new StringBuffer("From movieverse.MovieMember as MovieMember");
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
	
	public static java.util.Iterator iterateMovieMemberByQuery(PersistentSession session, String condition, String orderBy, org.hibernate.LockMode lockMode) throws PersistentException {
		StringBuffer sb = new StringBuffer("From movieverse.MovieMember as MovieMember");
		if (condition != null)
			sb.append(" Where ").append(condition);
		if (orderBy != null)
			sb.append(" Order By ").append(orderBy);
		try {
			Query query = session.createQuery(sb.toString());
			query.setLockMode("MovieMember", lockMode);
			return query.iterate();
		}
		catch (Exception e) {
			e.printStackTrace();
			throw new PersistentException(e);
		}
	}
	
	public static MovieMember createMovieMember() {
		return new movieverse.MovieMember();
	}
	
	public static boolean save(movieverse.MovieMember movieMember) throws PersistentException {
		try {
			MovieversePersistentManager.instance().saveObject(movieMember);
			return true;
		}
		catch (Exception e) {
			e.printStackTrace();
			throw new PersistentException(e);
		}
	}
	
	public static boolean delete(movieverse.MovieMember movieMember) throws PersistentException {
		try {
			MovieversePersistentManager.instance().deleteObject(movieMember);
			return true;
		}
		catch (Exception e) {
			e.printStackTrace();
			throw new PersistentException(e);
		}
	}
	
	public static boolean deleteAndDissociate(movieverse.MovieMember movieMember)throws PersistentException {
		try {
			if (movieMember.getMember() != null) {
				movieMember.getMember().movieMembers.remove(movieMember);
			}
			
			if (movieMember.getMovie() != null) {
				movieMember.getMovie().movieMembers.remove(movieMember);
			}
			
			return delete(movieMember);
		}
		catch(Exception e) {
			e.printStackTrace();
			throw new PersistentException(e);
		}
	}
	
	public static boolean deleteAndDissociate(movieverse.MovieMember movieMember, org.orm.PersistentSession session)throws PersistentException {
		try {
			if (movieMember.getMember() != null) {
				movieMember.getMember().movieMembers.remove(movieMember);
			}
			
			if (movieMember.getMovie() != null) {
				movieMember.getMovie().movieMembers.remove(movieMember);
			}
			
			try {
				session.delete(movieMember);
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
	
	public static boolean refresh(movieverse.MovieMember movieMember) throws PersistentException {
		try {
			MovieversePersistentManager.instance().getSession().refresh(movieMember);
			return true;
		}
		catch (Exception e) {
			e.printStackTrace();
			throw new PersistentException(e);
		}
	}
	
	public static boolean evict(movieverse.MovieMember movieMember) throws PersistentException {
		try {
			MovieversePersistentManager.instance().getSession().evict(movieMember);
			return true;
		}
		catch (Exception e) {
			e.printStackTrace();
			throw new PersistentException(e);
		}
	}
	
	public static MovieMember loadMovieMemberByCriteria(MovieMemberCriteria movieMemberCriteria) {
		MovieMember[] movieMembers = listMovieMemberByCriteria(movieMemberCriteria);
		if(movieMembers == null || movieMembers.length == 0) {
			return null;
		}
		return movieMembers[0];
	}
	
	public static MovieMember[] listMovieMemberByCriteria(MovieMemberCriteria movieMemberCriteria) {
		return movieMemberCriteria.listMovieMember();
	}
}
