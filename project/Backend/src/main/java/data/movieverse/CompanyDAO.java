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

public class CompanyDAO {
	public static Company loadCompanyByORMID(int id) throws PersistentException {
		try {
			PersistentSession session = MovieversePersistentManager.instance().getSession();
			return loadCompanyByORMID(session, id);
		}
		catch (Exception e) {
			e.printStackTrace();
			throw new PersistentException(e);
		}
	}
	
	public static Company getCompanyByORMID(int id) throws PersistentException {
		try {
			PersistentSession session = MovieversePersistentManager.instance().getSession();
			return getCompanyByORMID(session, id);
		}
		catch (Exception e) {
			e.printStackTrace();
			throw new PersistentException(e);
		}
	}
	
	public static Company loadCompanyByORMID(int id, org.hibernate.LockMode lockMode) throws PersistentException {
		try {
			PersistentSession session = MovieversePersistentManager.instance().getSession();
			return loadCompanyByORMID(session, id, lockMode);
		}
		catch (Exception e) {
			e.printStackTrace();
			throw new PersistentException(e);
		}
	}
	
	public static Company getCompanyByORMID(int id, org.hibernate.LockMode lockMode) throws PersistentException {
		try {
			PersistentSession session = MovieversePersistentManager.instance().getSession();
			return getCompanyByORMID(session, id, lockMode);
		}
		catch (Exception e) {
			e.printStackTrace();
			throw new PersistentException(e);
		}
	}
	
	public static Company loadCompanyByORMID(PersistentSession session, int id) throws PersistentException {
		try {
			return (Company) session.load(data.movieverse.Company.class, new Integer(id));
		}
		catch (Exception e) {
			e.printStackTrace();
			throw new PersistentException(e);
		}
	}
	
	public static Company getCompanyByORMID(PersistentSession session, int id) throws PersistentException {
		try {
			return (Company) session.get(data.movieverse.Company.class, new Integer(id));
		}
		catch (Exception e) {
			e.printStackTrace();
			throw new PersistentException(e);
		}
	}
	
	public static Company loadCompanyByORMID(PersistentSession session, int id, org.hibernate.LockMode lockMode) throws PersistentException {
		try {
			return (Company) session.load(data.movieverse.Company.class, new Integer(id), lockMode);
		}
		catch (Exception e) {
			e.printStackTrace();
			throw new PersistentException(e);
		}
	}
	
	public static Company getCompanyByORMID(PersistentSession session, int id, org.hibernate.LockMode lockMode) throws PersistentException {
		try {
			return (Company) session.get(data.movieverse.Company.class, new Integer(id), lockMode);
		}
		catch (Exception e) {
			e.printStackTrace();
			throw new PersistentException(e);
		}
	}
	
	public static List queryCompany(String condition, String orderBy) throws PersistentException {
		try {
			PersistentSession session = MovieversePersistentManager.instance().getSession();
			return queryCompany(session, condition, orderBy);
		}
		catch (Exception e) {
			e.printStackTrace();
			throw new PersistentException(e);
		}
	}
	
	public static List queryCompany(String condition, String orderBy, org.hibernate.LockMode lockMode) throws PersistentException {
		try {
			PersistentSession session = MovieversePersistentManager.instance().getSession();
			return queryCompany(session, condition, orderBy, lockMode);
		}
		catch (Exception e) {
			e.printStackTrace();
			throw new PersistentException(e);
		}
	}
	
	public static Company[] listCompanyByQuery(String condition, String orderBy) throws PersistentException {
		try {
			PersistentSession session = MovieversePersistentManager.instance().getSession();
			return listCompanyByQuery(session, condition, orderBy);
		}
		catch (Exception e) {
			e.printStackTrace();
			throw new PersistentException(e);
		}
	}
	
	public static Company[] listCompanyByQuery(String condition, String orderBy, org.hibernate.LockMode lockMode) throws PersistentException {
		try {
			PersistentSession session = MovieversePersistentManager.instance().getSession();
			return listCompanyByQuery(session, condition, orderBy, lockMode);
		}
		catch (Exception e) {
			e.printStackTrace();
			throw new PersistentException(e);
		}
	}
	
	public static List queryCompany(PersistentSession session, String condition, String orderBy) throws PersistentException {
		StringBuffer sb = new StringBuffer("From data.movieverse.Company as Company");
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
	
	public static List queryCompany(PersistentSession session, String condition, String orderBy, org.hibernate.LockMode lockMode) throws PersistentException {
		StringBuffer sb = new StringBuffer("From data.movieverse.Company as Company");
		if (condition != null)
			sb.append(" Where ").append(condition);
		if (orderBy != null)
			sb.append(" Order By ").append(orderBy);
		try {
			Query query = session.createQuery(sb.toString());
			query.setLockMode("Company", lockMode);
			return query.list();
		}
		catch (Exception e) {
			e.printStackTrace();
			throw new PersistentException(e);
		}
	}
	
	public static Company[] listCompanyByQuery(PersistentSession session, String condition, String orderBy) throws PersistentException {
		try {
			List list = queryCompany(session, condition, orderBy);
			return (Company[]) list.toArray(new Company[list.size()]);
		}
		catch (Exception e) {
			e.printStackTrace();
			throw new PersistentException(e);
		}
	}
	
	public static Company[] listCompanyByQuery(PersistentSession session, String condition, String orderBy, org.hibernate.LockMode lockMode) throws PersistentException {
		try {
			List list = queryCompany(session, condition, orderBy, lockMode);
			return (Company[]) list.toArray(new Company[list.size()]);
		}
		catch (Exception e) {
			e.printStackTrace();
			throw new PersistentException(e);
		}
	}
	
	public static Company loadCompanyByQuery(String condition, String orderBy) throws PersistentException {
		try {
			PersistentSession session = MovieversePersistentManager.instance().getSession();
			return loadCompanyByQuery(session, condition, orderBy);
		}
		catch (Exception e) {
			e.printStackTrace();
			throw new PersistentException(e);
		}
	}
	
	public static Company loadCompanyByQuery(String condition, String orderBy, org.hibernate.LockMode lockMode) throws PersistentException {
		try {
			PersistentSession session = MovieversePersistentManager.instance().getSession();
			return loadCompanyByQuery(session, condition, orderBy, lockMode);
		}
		catch (Exception e) {
			e.printStackTrace();
			throw new PersistentException(e);
		}
	}
	
	public static Company loadCompanyByQuery(PersistentSession session, String condition, String orderBy) throws PersistentException {
		Company[] companys = listCompanyByQuery(session, condition, orderBy);
		if (companys != null && companys.length > 0)
			return companys[0];
		else
			return null;
	}
	
	public static Company loadCompanyByQuery(PersistentSession session, String condition, String orderBy, org.hibernate.LockMode lockMode) throws PersistentException {
		Company[] companys = listCompanyByQuery(session, condition, orderBy, lockMode);
		if (companys != null && companys.length > 0)
			return companys[0];
		else
			return null;
	}
	
	public static java.util.Iterator iterateCompanyByQuery(String condition, String orderBy) throws PersistentException {
		try {
			PersistentSession session = MovieversePersistentManager.instance().getSession();
			return iterateCompanyByQuery(session, condition, orderBy);
		}
		catch (Exception e) {
			e.printStackTrace();
			throw new PersistentException(e);
		}
	}
	
	public static java.util.Iterator iterateCompanyByQuery(String condition, String orderBy, org.hibernate.LockMode lockMode) throws PersistentException {
		try {
			PersistentSession session = MovieversePersistentManager.instance().getSession();
			return iterateCompanyByQuery(session, condition, orderBy, lockMode);
		}
		catch (Exception e) {
			e.printStackTrace();
			throw new PersistentException(e);
		}
	}
	
	public static java.util.Iterator iterateCompanyByQuery(PersistentSession session, String condition, String orderBy) throws PersistentException {
		StringBuffer sb = new StringBuffer("From data.movieverse.Company as Company");
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
	
	public static java.util.Iterator iterateCompanyByQuery(PersistentSession session, String condition, String orderBy, org.hibernate.LockMode lockMode) throws PersistentException {
		StringBuffer sb = new StringBuffer("From data.movieverse.Company as Company");
		if (condition != null)
			sb.append(" Where ").append(condition);
		if (orderBy != null)
			sb.append(" Order By ").append(orderBy);
		try {
			Query query = session.createQuery(sb.toString());
			query.setLockMode("Company", lockMode);
			return query.iterate();
		}
		catch (Exception e) {
			e.printStackTrace();
			throw new PersistentException(e);
		}
	}
	
	public static Company createCompany() {
		return new data.movieverse.Company();
	}
	
	public static boolean save(data.movieverse.Company company) throws PersistentException {
		try {
			MovieversePersistentManager.instance().saveObject(company);
			return true;
		}
		catch (Exception e) {
			e.printStackTrace();
			throw new PersistentException(e);
		}
	}
	
	public static boolean delete(data.movieverse.Company company) throws PersistentException {
		try {
			MovieversePersistentManager.instance().deleteObject(company);
			return true;
		}
		catch (Exception e) {
			e.printStackTrace();
			throw new PersistentException(e);
		}
	}
	
	public static boolean deleteAndDissociate(data.movieverse.Company company)throws PersistentException {
		try {
			if (company.getParentCompany() != null) {
				company.getParentCompany().childrenCompanies.remove(company);
			}
			
			data.movieverse.Movie[] lMoviess = company.movies.toArray();
			for(int i = 0; i < lMoviess.length; i++) {
				lMoviess[i].companies.remove(company);
			}
			data.movieverse.Company[] lChildrenCompaniess = company.childrenCompanies.toArray();
			for(int i = 0; i < lChildrenCompaniess.length; i++) {
				lChildrenCompaniess[i].setParentCompany(null);
			}
			return delete(company);
		}
		catch(Exception e) {
			e.printStackTrace();
			throw new PersistentException(e);
		}
	}
	
	public static boolean deleteAndDissociate(data.movieverse.Company company, org.orm.PersistentSession session)throws PersistentException {
		try {
			if (company.getParentCompany() != null) {
				company.getParentCompany().childrenCompanies.remove(company);
			}
			
			data.movieverse.Movie[] lMoviess = company.movies.toArray();
			for(int i = 0; i < lMoviess.length; i++) {
				lMoviess[i].companies.remove(company);
			}
			data.movieverse.Company[] lChildrenCompaniess = company.childrenCompanies.toArray();
			for(int i = 0; i < lChildrenCompaniess.length; i++) {
				lChildrenCompaniess[i].setParentCompany(null);
			}
			try {
				session.delete(company);
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
	
	public static boolean refresh(data.movieverse.Company company) throws PersistentException {
		try {
			MovieversePersistentManager.instance().getSession().refresh(company);
			return true;
		}
		catch (Exception e) {
			e.printStackTrace();
			throw new PersistentException(e);
		}
	}
	
	public static boolean evict(data.movieverse.Company company) throws PersistentException {
		try {
			MovieversePersistentManager.instance().getSession().evict(company);
			return true;
		}
		catch (Exception e) {
			e.printStackTrace();
			throw new PersistentException(e);
		}
	}
	
	public static Company loadCompanyByCriteria(CompanyCriteria companyCriteria) {
		Company[] companys = listCompanyByCriteria(companyCriteria);
		if(companys == null || companys.length == 0) {
			return null;
		}
		return companys[0];
	}
	
	public static Company[] listCompanyByCriteria(CompanyCriteria companyCriteria) {
		return companyCriteria.listCompany();
	}
}
