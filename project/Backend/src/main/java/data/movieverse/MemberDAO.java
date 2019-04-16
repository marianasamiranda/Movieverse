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

public class MemberDAO {
	public static Member loadMemberByORMID(int id) throws PersistentException {
		try {
			PersistentSession session = MovieversePersistentManager.instance().getSession();
			return loadMemberByORMID(session, id);
		}
		catch (Exception e) {
			e.printStackTrace();
			throw new PersistentException(e);
		}
	}
	
	public static Member getMemberByORMID(int id) throws PersistentException {
		try {
			PersistentSession session = MovieversePersistentManager.instance().getSession();
			return getMemberByORMID(session, id);
		}
		catch (Exception e) {
			e.printStackTrace();
			throw new PersistentException(e);
		}
	}
	
	public static Member loadMemberByORMID(int id, org.hibernate.LockMode lockMode) throws PersistentException {
		try {
			PersistentSession session = MovieversePersistentManager.instance().getSession();
			return loadMemberByORMID(session, id, lockMode);
		}
		catch (Exception e) {
			e.printStackTrace();
			throw new PersistentException(e);
		}
	}
	
	public static Member getMemberByORMID(int id, org.hibernate.LockMode lockMode) throws PersistentException {
		try {
			PersistentSession session = MovieversePersistentManager.instance().getSession();
			return getMemberByORMID(session, id, lockMode);
		}
		catch (Exception e) {
			e.printStackTrace();
			throw new PersistentException(e);
		}
	}
	
	public static Member loadMemberByORMID(PersistentSession session, int id) throws PersistentException {
		try {
			return (Member) session.load(data.movieverse.Member.class, new Integer(id));
		}
		catch (Exception e) {
			e.printStackTrace();
			throw new PersistentException(e);
		}
	}
	
	public static Member getMemberByORMID(PersistentSession session, int id) throws PersistentException {
		try {
			return (Member) session.get(data.movieverse.Member.class, new Integer(id));
		}
		catch (Exception e) {
			e.printStackTrace();
			throw new PersistentException(e);
		}
	}
	
	public static Member loadMemberByORMID(PersistentSession session, int id, org.hibernate.LockMode lockMode) throws PersistentException {
		try {
			return (Member) session.load(data.movieverse.Member.class, new Integer(id), lockMode);
		}
		catch (Exception e) {
			e.printStackTrace();
			throw new PersistentException(e);
		}
	}
	
	public static Member getMemberByORMID(PersistentSession session, int id, org.hibernate.LockMode lockMode) throws PersistentException {
		try {
			return (Member) session.get(data.movieverse.Member.class, new Integer(id), lockMode);
		}
		catch (Exception e) {
			e.printStackTrace();
			throw new PersistentException(e);
		}
	}
	
	public static List queryMember(String condition, String orderBy) throws PersistentException {
		try {
			PersistentSession session = MovieversePersistentManager.instance().getSession();
			return queryMember(session, condition, orderBy);
		}
		catch (Exception e) {
			e.printStackTrace();
			throw new PersistentException(e);
		}
	}
	
	public static List queryMember(String condition, String orderBy, org.hibernate.LockMode lockMode) throws PersistentException {
		try {
			PersistentSession session = MovieversePersistentManager.instance().getSession();
			return queryMember(session, condition, orderBy, lockMode);
		}
		catch (Exception e) {
			e.printStackTrace();
			throw new PersistentException(e);
		}
	}
	
	public static Member[] listMemberByQuery(String condition, String orderBy) throws PersistentException {
		try {
			PersistentSession session = MovieversePersistentManager.instance().getSession();
			return listMemberByQuery(session, condition, orderBy);
		}
		catch (Exception e) {
			e.printStackTrace();
			throw new PersistentException(e);
		}
	}
	
	public static Member[] listMemberByQuery(String condition, String orderBy, org.hibernate.LockMode lockMode) throws PersistentException {
		try {
			PersistentSession session = MovieversePersistentManager.instance().getSession();
			return listMemberByQuery(session, condition, orderBy, lockMode);
		}
		catch (Exception e) {
			e.printStackTrace();
			throw new PersistentException(e);
		}
	}
	
	public static List queryMember(PersistentSession session, String condition, String orderBy) throws PersistentException {
		StringBuffer sb = new StringBuffer("From data.movieverse.Member as Member");
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
	
	public static List queryMember(PersistentSession session, String condition, String orderBy, org.hibernate.LockMode lockMode) throws PersistentException {
		StringBuffer sb = new StringBuffer("From data.movieverse.Member as Member");
		if (condition != null)
			sb.append(" Where ").append(condition);
		if (orderBy != null)
			sb.append(" Order By ").append(orderBy);
		try {
			Query query = session.createQuery(sb.toString());
			query.setLockMode("Member", lockMode);
			return query.list();
		}
		catch (Exception e) {
			e.printStackTrace();
			throw new PersistentException(e);
		}
	}
	
	public static Member[] listMemberByQuery(PersistentSession session, String condition, String orderBy) throws PersistentException {
		try {
			List list = queryMember(session, condition, orderBy);
			return (Member[]) list.toArray(new Member[list.size()]);
		}
		catch (Exception e) {
			e.printStackTrace();
			throw new PersistentException(e);
		}
	}
	
	public static Member[] listMemberByQuery(PersistentSession session, String condition, String orderBy, org.hibernate.LockMode lockMode) throws PersistentException {
		try {
			List list = queryMember(session, condition, orderBy, lockMode);
			return (Member[]) list.toArray(new Member[list.size()]);
		}
		catch (Exception e) {
			e.printStackTrace();
			throw new PersistentException(e);
		}
	}
	
	public static Member loadMemberByQuery(String condition, String orderBy) throws PersistentException {
		try {
			PersistentSession session = MovieversePersistentManager.instance().getSession();
			return loadMemberByQuery(session, condition, orderBy);
		}
		catch (Exception e) {
			e.printStackTrace();
			throw new PersistentException(e);
		}
	}
	
	public static Member loadMemberByQuery(String condition, String orderBy, org.hibernate.LockMode lockMode) throws PersistentException {
		try {
			PersistentSession session = MovieversePersistentManager.instance().getSession();
			return loadMemberByQuery(session, condition, orderBy, lockMode);
		}
		catch (Exception e) {
			e.printStackTrace();
			throw new PersistentException(e);
		}
	}
	
	public static Member loadMemberByQuery(PersistentSession session, String condition, String orderBy) throws PersistentException {
		Member[] members = listMemberByQuery(session, condition, orderBy);
		if (members != null && members.length > 0)
			return members[0];
		else
			return null;
	}
	
	public static Member loadMemberByQuery(PersistentSession session, String condition, String orderBy, org.hibernate.LockMode lockMode) throws PersistentException {
		Member[] members = listMemberByQuery(session, condition, orderBy, lockMode);
		if (members != null && members.length > 0)
			return members[0];
		else
			return null;
	}
	
	public static java.util.Iterator iterateMemberByQuery(String condition, String orderBy) throws PersistentException {
		try {
			PersistentSession session = MovieversePersistentManager.instance().getSession();
			return iterateMemberByQuery(session, condition, orderBy);
		}
		catch (Exception e) {
			e.printStackTrace();
			throw new PersistentException(e);
		}
	}
	
	public static java.util.Iterator iterateMemberByQuery(String condition, String orderBy, org.hibernate.LockMode lockMode) throws PersistentException {
		try {
			PersistentSession session = MovieversePersistentManager.instance().getSession();
			return iterateMemberByQuery(session, condition, orderBy, lockMode);
		}
		catch (Exception e) {
			e.printStackTrace();
			throw new PersistentException(e);
		}
	}
	
	public static java.util.Iterator iterateMemberByQuery(PersistentSession session, String condition, String orderBy) throws PersistentException {
		StringBuffer sb = new StringBuffer("From data.movieverse.Member as Member");
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
	
	public static java.util.Iterator iterateMemberByQuery(PersistentSession session, String condition, String orderBy, org.hibernate.LockMode lockMode) throws PersistentException {
		StringBuffer sb = new StringBuffer("From data.movieverse.Member as Member");
		if (condition != null)
			sb.append(" Where ").append(condition);
		if (orderBy != null)
			sb.append(" Order By ").append(orderBy);
		try {
			Query query = session.createQuery(sb.toString());
			query.setLockMode("Member", lockMode);
			return query.iterate();
		}
		catch (Exception e) {
			e.printStackTrace();
			throw new PersistentException(e);
		}
	}
	
	public static Member createMember() {
		return new data.movieverse.Member();
	}
	
	public static boolean save(data.movieverse.Member member) throws PersistentException {
		try {
			MovieversePersistentManager.instance().saveObject(member);
			return true;
		}
		catch (Exception e) {
			e.printStackTrace();
			throw new PersistentException(e);
		}
	}
	
	public static boolean delete(data.movieverse.Member member) throws PersistentException {
		try {
			MovieversePersistentManager.instance().deleteObject(member);
			return true;
		}
		catch (Exception e) {
			e.printStackTrace();
			throw new PersistentException(e);
		}
	}
	
	public static boolean deleteAndDissociate(data.movieverse.Member member)throws PersistentException {
		try {
			data.movieverse.MovieMember[] lMovieMemberss = member.movieMembers.toArray();
			for(int i = 0; i < lMovieMemberss.length; i++) {
				lMovieMemberss[i].setMember(null);
			}
			return delete(member);
		}
		catch(Exception e) {
			e.printStackTrace();
			throw new PersistentException(e);
		}
	}
	
	public static boolean deleteAndDissociate(data.movieverse.Member member, org.orm.PersistentSession session)throws PersistentException {
		try {
			data.movieverse.MovieMember[] lMovieMemberss = member.movieMembers.toArray();
			for(int i = 0; i < lMovieMemberss.length; i++) {
				lMovieMemberss[i].setMember(null);
			}
			try {
				session.delete(member);
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
	
	public static boolean refresh(data.movieverse.Member member) throws PersistentException {
		try {
			MovieversePersistentManager.instance().getSession().refresh(member);
			return true;
		}
		catch (Exception e) {
			e.printStackTrace();
			throw new PersistentException(e);
		}
	}
	
	public static boolean evict(data.movieverse.Member member) throws PersistentException {
		try {
			MovieversePersistentManager.instance().getSession().evict(member);
			return true;
		}
		catch (Exception e) {
			e.printStackTrace();
			throw new PersistentException(e);
		}
	}
	
	public static Member loadMemberByCriteria(MemberCriteria memberCriteria) {
		Member[] members = listMemberByCriteria(memberCriteria);
		if(members == null || members.length == 0) {
			return null;
		}
		return members[0];
	}
	
	public static Member[] listMemberByCriteria(MemberCriteria memberCriteria) {
		return memberCriteria.listMember();
	}
}
