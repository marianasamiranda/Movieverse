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

public class BadgeDAO {
	public static Badge loadBadgeByORMID(int id) throws PersistentException {
		try {
			PersistentSession session = MovieversePersistentManager.instance().getSession();
			return loadBadgeByORMID(session, id);
		}
		catch (Exception e) {
			e.printStackTrace();
			throw new PersistentException(e);
		}
	}
	
	public static Badge getBadgeByORMID(int id) throws PersistentException {
		try {
			PersistentSession session = MovieversePersistentManager.instance().getSession();
			return getBadgeByORMID(session, id);
		}
		catch (Exception e) {
			e.printStackTrace();
			throw new PersistentException(e);
		}
	}
	
	public static Badge loadBadgeByORMID(int id, org.hibernate.LockMode lockMode) throws PersistentException {
		try {
			PersistentSession session = MovieversePersistentManager.instance().getSession();
			return loadBadgeByORMID(session, id, lockMode);
		}
		catch (Exception e) {
			e.printStackTrace();
			throw new PersistentException(e);
		}
	}
	
	public static Badge getBadgeByORMID(int id, org.hibernate.LockMode lockMode) throws PersistentException {
		try {
			PersistentSession session = MovieversePersistentManager.instance().getSession();
			return getBadgeByORMID(session, id, lockMode);
		}
		catch (Exception e) {
			e.printStackTrace();
			throw new PersistentException(e);
		}
	}
	
	public static Badge loadBadgeByORMID(PersistentSession session, int id) throws PersistentException {
		try {
			return (Badge) session.load(data.movieverse.Badge.class, new Integer(id));
		}
		catch (Exception e) {
			e.printStackTrace();
			throw new PersistentException(e);
		}
	}
	
	public static Badge getBadgeByORMID(PersistentSession session, int id) throws PersistentException {
		try {
			return (Badge) session.get(data.movieverse.Badge.class, new Integer(id));
		}
		catch (Exception e) {
			e.printStackTrace();
			throw new PersistentException(e);
		}
	}
	
	public static Badge loadBadgeByORMID(PersistentSession session, int id, org.hibernate.LockMode lockMode) throws PersistentException {
		try {
			return (Badge) session.load(data.movieverse.Badge.class, new Integer(id), lockMode);
		}
		catch (Exception e) {
			e.printStackTrace();
			throw new PersistentException(e);
		}
	}
	
	public static Badge getBadgeByORMID(PersistentSession session, int id, org.hibernate.LockMode lockMode) throws PersistentException {
		try {
			return (Badge) session.get(data.movieverse.Badge.class, new Integer(id), lockMode);
		}
		catch (Exception e) {
			e.printStackTrace();
			throw new PersistentException(e);
		}
	}
	
	public static List queryBadge(String condition, String orderBy) throws PersistentException {
		try {
			PersistentSession session = MovieversePersistentManager.instance().getSession();
			return queryBadge(session, condition, orderBy);
		}
		catch (Exception e) {
			e.printStackTrace();
			throw new PersistentException(e);
		}
	}
	
	public static List queryBadge(String condition, String orderBy, org.hibernate.LockMode lockMode) throws PersistentException {
		try {
			PersistentSession session = MovieversePersistentManager.instance().getSession();
			return queryBadge(session, condition, orderBy, lockMode);
		}
		catch (Exception e) {
			e.printStackTrace();
			throw new PersistentException(e);
		}
	}
	
	public static Badge[] listBadgeByQuery(String condition, String orderBy) throws PersistentException {
		try {
			PersistentSession session = MovieversePersistentManager.instance().getSession();
			return listBadgeByQuery(session, condition, orderBy);
		}
		catch (Exception e) {
			e.printStackTrace();
			throw new PersistentException(e);
		}
	}
	
	public static Badge[] listBadgeByQuery(String condition, String orderBy, org.hibernate.LockMode lockMode) throws PersistentException {
		try {
			PersistentSession session = MovieversePersistentManager.instance().getSession();
			return listBadgeByQuery(session, condition, orderBy, lockMode);
		}
		catch (Exception e) {
			e.printStackTrace();
			throw new PersistentException(e);
		}
	}
	
	public static List queryBadge(PersistentSession session, String condition, String orderBy) throws PersistentException {
		StringBuffer sb = new StringBuffer("From data.movieverse.Badge as Badge");
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
	
	public static List queryBadge(PersistentSession session, String condition, String orderBy, org.hibernate.LockMode lockMode) throws PersistentException {
		StringBuffer sb = new StringBuffer("From data.movieverse.Badge as Badge");
		if (condition != null)
			sb.append(" Where ").append(condition);
		if (orderBy != null)
			sb.append(" Order By ").append(orderBy);
		try {
			Query query = session.createQuery(sb.toString());
			query.setLockMode("Badge", lockMode);
			return query.list();
		}
		catch (Exception e) {
			e.printStackTrace();
			throw new PersistentException(e);
		}
	}
	
	public static Badge[] listBadgeByQuery(PersistentSession session, String condition, String orderBy) throws PersistentException {
		try {
			List list = queryBadge(session, condition, orderBy);
			return (Badge[]) list.toArray(new Badge[list.size()]);
		}
		catch (Exception e) {
			e.printStackTrace();
			throw new PersistentException(e);
		}
	}
	
	public static Badge[] listBadgeByQuery(PersistentSession session, String condition, String orderBy, org.hibernate.LockMode lockMode) throws PersistentException {
		try {
			List list = queryBadge(session, condition, orderBy, lockMode);
			return (Badge[]) list.toArray(new Badge[list.size()]);
		}
		catch (Exception e) {
			e.printStackTrace();
			throw new PersistentException(e);
		}
	}
	
	public static Badge loadBadgeByQuery(String condition, String orderBy) throws PersistentException {
		try {
			PersistentSession session = MovieversePersistentManager.instance().getSession();
			return loadBadgeByQuery(session, condition, orderBy);
		}
		catch (Exception e) {
			e.printStackTrace();
			throw new PersistentException(e);
		}
	}
	
	public static Badge loadBadgeByQuery(String condition, String orderBy, org.hibernate.LockMode lockMode) throws PersistentException {
		try {
			PersistentSession session = MovieversePersistentManager.instance().getSession();
			return loadBadgeByQuery(session, condition, orderBy, lockMode);
		}
		catch (Exception e) {
			e.printStackTrace();
			throw new PersistentException(e);
		}
	}
	
	public static Badge loadBadgeByQuery(PersistentSession session, String condition, String orderBy) throws PersistentException {
		Badge[] badges = listBadgeByQuery(session, condition, orderBy);
		if (badges != null && badges.length > 0)
			return badges[0];
		else
			return null;
	}
	
	public static Badge loadBadgeByQuery(PersistentSession session, String condition, String orderBy, org.hibernate.LockMode lockMode) throws PersistentException {
		Badge[] badges = listBadgeByQuery(session, condition, orderBy, lockMode);
		if (badges != null && badges.length > 0)
			return badges[0];
		else
			return null;
	}
	
	public static java.util.Iterator iterateBadgeByQuery(String condition, String orderBy) throws PersistentException {
		try {
			PersistentSession session = MovieversePersistentManager.instance().getSession();
			return iterateBadgeByQuery(session, condition, orderBy);
		}
		catch (Exception e) {
			e.printStackTrace();
			throw new PersistentException(e);
		}
	}
	
	public static java.util.Iterator iterateBadgeByQuery(String condition, String orderBy, org.hibernate.LockMode lockMode) throws PersistentException {
		try {
			PersistentSession session = MovieversePersistentManager.instance().getSession();
			return iterateBadgeByQuery(session, condition, orderBy, lockMode);
		}
		catch (Exception e) {
			e.printStackTrace();
			throw new PersistentException(e);
		}
	}
	
	public static java.util.Iterator iterateBadgeByQuery(PersistentSession session, String condition, String orderBy) throws PersistentException {
		StringBuffer sb = new StringBuffer("From data.movieverse.Badge as Badge");
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
	
	public static java.util.Iterator iterateBadgeByQuery(PersistentSession session, String condition, String orderBy, org.hibernate.LockMode lockMode) throws PersistentException {
		StringBuffer sb = new StringBuffer("From data.movieverse.Badge as Badge");
		if (condition != null)
			sb.append(" Where ").append(condition);
		if (orderBy != null)
			sb.append(" Order By ").append(orderBy);
		try {
			Query query = session.createQuery(sb.toString());
			query.setLockMode("Badge", lockMode);
			return query.iterate();
		}
		catch (Exception e) {
			e.printStackTrace();
			throw new PersistentException(e);
		}
	}
	
	public static Badge createBadge() {
		return new data.movieverse.Badge();
	}
	
	public static boolean save(data.movieverse.Badge badge) throws PersistentException {
		try {
			MovieversePersistentManager.instance().saveObject(badge);
			return true;
		}
		catch (Exception e) {
			e.printStackTrace();
			throw new PersistentException(e);
		}
	}
	
	public static boolean delete(data.movieverse.Badge badge) throws PersistentException {
		try {
			MovieversePersistentManager.instance().deleteObject(badge);
			return true;
		}
		catch (Exception e) {
			e.printStackTrace();
			throw new PersistentException(e);
		}
	}
	
	public static boolean deleteAndDissociate(data.movieverse.Badge badge)throws PersistentException {
		try {
			data.movieverse.Achievement[] lAchievementss = badge.achievements.toArray();
			for(int i = 0; i < lAchievementss.length; i++) {
				lAchievementss[i].setBadge(null);
			}
			return delete(badge);
		}
		catch(Exception e) {
			e.printStackTrace();
			throw new PersistentException(e);
		}
	}
	
	public static boolean deleteAndDissociate(data.movieverse.Badge badge, org.orm.PersistentSession session)throws PersistentException {
		try {
			data.movieverse.Achievement[] lAchievementss = badge.achievements.toArray();
			for(int i = 0; i < lAchievementss.length; i++) {
				lAchievementss[i].setBadge(null);
			}
			try {
				session.delete(badge);
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
	
	public static boolean refresh(data.movieverse.Badge badge) throws PersistentException {
		try {
			MovieversePersistentManager.instance().getSession().refresh(badge);
			return true;
		}
		catch (Exception e) {
			e.printStackTrace();
			throw new PersistentException(e);
		}
	}
	
	public static boolean evict(data.movieverse.Badge badge) throws PersistentException {
		try {
			MovieversePersistentManager.instance().getSession().evict(badge);
			return true;
		}
		catch (Exception e) {
			e.printStackTrace();
			throw new PersistentException(e);
		}
	}
	
	public static Badge loadBadgeByCriteria(BadgeCriteria badgeCriteria) {
		Badge[] badges = listBadgeByCriteria(badgeCriteria);
		if(badges == null || badges.length == 0) {
			return null;
		}
		return badges[0];
	}
	
	public static Badge[] listBadgeByCriteria(BadgeCriteria badgeCriteria) {
		return badgeCriteria.listBadge();
	}
}
