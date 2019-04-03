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

public class MUserDAO {
	public static MUser loadMUserByORMID(int id) throws PersistentException {
		try {
			PersistentSession session = MovieversePersistentManager.instance().getSession();
			return loadMUserByORMID(session, id);
		}
		catch (Exception e) {
			e.printStackTrace();
			throw new PersistentException(e);
		}
	}
	
	public static MUser getMUserByORMID(int id) throws PersistentException {
		try {
			PersistentSession session = MovieversePersistentManager.instance().getSession();
			return getMUserByORMID(session, id);
		}
		catch (Exception e) {
			e.printStackTrace();
			throw new PersistentException(e);
		}
	}
	
	public static MUser loadMUserByORMID(int id, org.hibernate.LockMode lockMode) throws PersistentException {
		try {
			PersistentSession session = MovieversePersistentManager.instance().getSession();
			return loadMUserByORMID(session, id, lockMode);
		}
		catch (Exception e) {
			e.printStackTrace();
			throw new PersistentException(e);
		}
	}
	
	public static MUser getMUserByORMID(int id, org.hibernate.LockMode lockMode) throws PersistentException {
		try {
			PersistentSession session = MovieversePersistentManager.instance().getSession();
			return getMUserByORMID(session, id, lockMode);
		}
		catch (Exception e) {
			e.printStackTrace();
			throw new PersistentException(e);
		}
	}
	
	public static MUser loadMUserByORMID(PersistentSession session, int id) throws PersistentException {
		try {
			return (MUser) session.load(movieverse.MUser.class, new Integer(id));
		}
		catch (Exception e) {
			e.printStackTrace();
			throw new PersistentException(e);
		}
	}
	
	public static MUser getMUserByORMID(PersistentSession session, int id) throws PersistentException {
		try {
			return (MUser) session.get(movieverse.MUser.class, new Integer(id));
		}
		catch (Exception e) {
			e.printStackTrace();
			throw new PersistentException(e);
		}
	}
	
	public static MUser loadMUserByORMID(PersistentSession session, int id, org.hibernate.LockMode lockMode) throws PersistentException {
		try {
			return (MUser) session.load(movieverse.MUser.class, new Integer(id), lockMode);
		}
		catch (Exception e) {
			e.printStackTrace();
			throw new PersistentException(e);
		}
	}
	
	public static MUser getMUserByORMID(PersistentSession session, int id, org.hibernate.LockMode lockMode) throws PersistentException {
		try {
			return (MUser) session.get(movieverse.MUser.class, new Integer(id), lockMode);
		}
		catch (Exception e) {
			e.printStackTrace();
			throw new PersistentException(e);
		}
	}
	
	public static List queryMUser(String condition, String orderBy) throws PersistentException {
		try {
			PersistentSession session = MovieversePersistentManager.instance().getSession();
			return queryMUser(session, condition, orderBy);
		}
		catch (Exception e) {
			e.printStackTrace();
			throw new PersistentException(e);
		}
	}
	
	public static List queryMUser(String condition, String orderBy, org.hibernate.LockMode lockMode) throws PersistentException {
		try {
			PersistentSession session = MovieversePersistentManager.instance().getSession();
			return queryMUser(session, condition, orderBy, lockMode);
		}
		catch (Exception e) {
			e.printStackTrace();
			throw new PersistentException(e);
		}
	}
	
	public static MUser[] listMUserByQuery(String condition, String orderBy) throws PersistentException {
		try {
			PersistentSession session = MovieversePersistentManager.instance().getSession();
			return listMUserByQuery(session, condition, orderBy);
		}
		catch (Exception e) {
			e.printStackTrace();
			throw new PersistentException(e);
		}
	}
	
	public static MUser[] listMUserByQuery(String condition, String orderBy, org.hibernate.LockMode lockMode) throws PersistentException {
		try {
			PersistentSession session = MovieversePersistentManager.instance().getSession();
			return listMUserByQuery(session, condition, orderBy, lockMode);
		}
		catch (Exception e) {
			e.printStackTrace();
			throw new PersistentException(e);
		}
	}
	
	public static List queryMUser(PersistentSession session, String condition, String orderBy) throws PersistentException {
		StringBuffer sb = new StringBuffer("From movieverse.MUser as MUser");
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
	
	public static List queryMUser(PersistentSession session, String condition, String orderBy, org.hibernate.LockMode lockMode) throws PersistentException {
		StringBuffer sb = new StringBuffer("From movieverse.MUser as MUser");
		if (condition != null)
			sb.append(" Where ").append(condition);
		if (orderBy != null)
			sb.append(" Order By ").append(orderBy);
		try {
			Query query = session.createQuery(sb.toString());
			query.setLockMode("MUser", lockMode);
			return query.list();
		}
		catch (Exception e) {
			e.printStackTrace();
			throw new PersistentException(e);
		}
	}
	
	public static MUser[] listMUserByQuery(PersistentSession session, String condition, String orderBy) throws PersistentException {
		try {
			List list = queryMUser(session, condition, orderBy);
			return (MUser[]) list.toArray(new MUser[list.size()]);
		}
		catch (Exception e) {
			e.printStackTrace();
			throw new PersistentException(e);
		}
	}
	
	public static MUser[] listMUserByQuery(PersistentSession session, String condition, String orderBy, org.hibernate.LockMode lockMode) throws PersistentException {
		try {
			List list = queryMUser(session, condition, orderBy, lockMode);
			return (MUser[]) list.toArray(new MUser[list.size()]);
		}
		catch (Exception e) {
			e.printStackTrace();
			throw new PersistentException(e);
		}
	}
	
	public static MUser loadMUserByQuery(String condition, String orderBy) throws PersistentException {
		try {
			PersistentSession session = MovieversePersistentManager.instance().getSession();
			return loadMUserByQuery(session, condition, orderBy);
		}
		catch (Exception e) {
			e.printStackTrace();
			throw new PersistentException(e);
		}
	}
	
	public static MUser loadMUserByQuery(String condition, String orderBy, org.hibernate.LockMode lockMode) throws PersistentException {
		try {
			PersistentSession session = MovieversePersistentManager.instance().getSession();
			return loadMUserByQuery(session, condition, orderBy, lockMode);
		}
		catch (Exception e) {
			e.printStackTrace();
			throw new PersistentException(e);
		}
	}
	
	public static MUser loadMUserByQuery(PersistentSession session, String condition, String orderBy) throws PersistentException {
		MUser[] mUsers = listMUserByQuery(session, condition, orderBy);
		if (mUsers != null && mUsers.length > 0)
			return mUsers[0];
		else
			return null;
	}
	
	public static MUser loadMUserByQuery(PersistentSession session, String condition, String orderBy, org.hibernate.LockMode lockMode) throws PersistentException {
		MUser[] mUsers = listMUserByQuery(session, condition, orderBy, lockMode);
		if (mUsers != null && mUsers.length > 0)
			return mUsers[0];
		else
			return null;
	}
	
	public static java.util.Iterator iterateMUserByQuery(String condition, String orderBy) throws PersistentException {
		try {
			PersistentSession session = MovieversePersistentManager.instance().getSession();
			return iterateMUserByQuery(session, condition, orderBy);
		}
		catch (Exception e) {
			e.printStackTrace();
			throw new PersistentException(e);
		}
	}
	
	public static java.util.Iterator iterateMUserByQuery(String condition, String orderBy, org.hibernate.LockMode lockMode) throws PersistentException {
		try {
			PersistentSession session = MovieversePersistentManager.instance().getSession();
			return iterateMUserByQuery(session, condition, orderBy, lockMode);
		}
		catch (Exception e) {
			e.printStackTrace();
			throw new PersistentException(e);
		}
	}
	
	public static java.util.Iterator iterateMUserByQuery(PersistentSession session, String condition, String orderBy) throws PersistentException {
		StringBuffer sb = new StringBuffer("From movieverse.MUser as MUser");
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
	
	public static java.util.Iterator iterateMUserByQuery(PersistentSession session, String condition, String orderBy, org.hibernate.LockMode lockMode) throws PersistentException {
		StringBuffer sb = new StringBuffer("From movieverse.MUser as MUser");
		if (condition != null)
			sb.append(" Where ").append(condition);
		if (orderBy != null)
			sb.append(" Order By ").append(orderBy);
		try {
			Query query = session.createQuery(sb.toString());
			query.setLockMode("MUser", lockMode);
			return query.iterate();
		}
		catch (Exception e) {
			e.printStackTrace();
			throw new PersistentException(e);
		}
	}
	
	public static MUser createMUser() {
		return new movieverse.MUser();
	}
	
	public static boolean save(movieverse.MUser mUser) throws PersistentException {
		try {
			MovieversePersistentManager.instance().saveObject(mUser);
			return true;
		}
		catch (Exception e) {
			e.printStackTrace();
			throw new PersistentException(e);
		}
	}
	
	public static boolean delete(movieverse.MUser mUser) throws PersistentException {
		try {
			MovieversePersistentManager.instance().deleteObject(mUser);
			return true;
		}
		catch (Exception e) {
			e.printStackTrace();
			throw new PersistentException(e);
		}
	}
	
	public static boolean deleteAndDissociate(movieverse.MUser mUser)throws PersistentException {
		try {
			movieverse.Friendship[] lRequestedFriendshipss = mUser.requestedFriendships.toArray();
			for(int i = 0; i < lRequestedFriendshipss.length; i++) {
				lRequestedFriendshipss[i].setReceivedMuser(null);
			}
			movieverse.Comment[] lCommentss = mUser.comments.toArray();
			for(int i = 0; i < lCommentss.length; i++) {
				lCommentss[i].setCommenter(null);
			}
			movieverse.Feed[] lFeeds = mUser.feed.toArray();
			for(int i = 0; i < lFeeds.length; i++) {
				lFeeds[i].setUser(null);
			}
			movieverse.Achievement[] lAchievementss = mUser.achievements.toArray();
			for(int i = 0; i < lAchievementss.length; i++) {
				lAchievementss[i].setmUser(null);
			}
			movieverse.MUserMovie[] lmUserMoviess = mUser.mUserMovies.toArray();
			for(int i = 0; i < lmUserMoviess.length; i++) {
				lmUserMoviess[i].setmUser(null);
			}
			movieverse.Friendship[] lReceivedFriendshipss = mUser.receivedFriendships.toArray();
			for(int i = 0; i < lReceivedFriendshipss.length; i++) {
				lReceivedFriendshipss[i].setRequestedMuser(null);
			}
			return delete(mUser);
		}
		catch(Exception e) {
			e.printStackTrace();
			throw new PersistentException(e);
		}
	}
	
	public static boolean deleteAndDissociate(movieverse.MUser mUser, org.orm.PersistentSession session)throws PersistentException {
		try {
			movieverse.Friendship[] lRequestedFriendshipss = mUser.requestedFriendships.toArray();
			for(int i = 0; i < lRequestedFriendshipss.length; i++) {
				lRequestedFriendshipss[i].setReceivedMuser(null);
			}
			movieverse.Comment[] lCommentss = mUser.comments.toArray();
			for(int i = 0; i < lCommentss.length; i++) {
				lCommentss[i].setCommenter(null);
			}
			movieverse.Feed[] lFeeds = mUser.feed.toArray();
			for(int i = 0; i < lFeeds.length; i++) {
				lFeeds[i].setUser(null);
			}
			movieverse.Achievement[] lAchievementss = mUser.achievements.toArray();
			for(int i = 0; i < lAchievementss.length; i++) {
				lAchievementss[i].setmUser(null);
			}
			movieverse.MUserMovie[] lmUserMoviess = mUser.mUserMovies.toArray();
			for(int i = 0; i < lmUserMoviess.length; i++) {
				lmUserMoviess[i].setmUser(null);
			}
			movieverse.Friendship[] lReceivedFriendshipss = mUser.receivedFriendships.toArray();
			for(int i = 0; i < lReceivedFriendshipss.length; i++) {
				lReceivedFriendshipss[i].setRequestedMuser(null);
			}
			try {
				session.delete(mUser);
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
	
	public static boolean refresh(movieverse.MUser mUser) throws PersistentException {
		try {
			MovieversePersistentManager.instance().getSession().refresh(mUser);
			return true;
		}
		catch (Exception e) {
			e.printStackTrace();
			throw new PersistentException(e);
		}
	}
	
	public static boolean evict(movieverse.MUser mUser) throws PersistentException {
		try {
			MovieversePersistentManager.instance().getSession().evict(mUser);
			return true;
		}
		catch (Exception e) {
			e.printStackTrace();
			throw new PersistentException(e);
		}
	}
	
	public static MUser loadMUserByCriteria(MUserCriteria mUserCriteria) {
		MUser[] mUsers = listMUserByCriteria(mUserCriteria);
		if(mUsers == null || mUsers.length == 0) {
			return null;
		}
		return mUsers[0];
	}
	
	public static MUser[] listMUserByCriteria(MUserCriteria mUserCriteria) {
		return mUserCriteria.listMUser();
	}
}
