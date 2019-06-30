package data.daos.impl;
import java.lang.reflect.ParameterizedType;
import java.util.List;


import data.daos.DAO;
import org.springframework.stereotype.Repository;
import org.springframework.transaction.annotation.Transactional;

import javax.persistence.EntityManager;
import javax.persistence.PersistenceContext;
import javax.persistence.Query;


@Repository
@Transactional
public abstract class DAOImpl<K, E> implements DAO<K, E> {

    protected Class<E> entityClass;

	@PersistenceContext
	private EntityManager entityManager;
	
	@Transactional(readOnly=false)
	public void flush() {
		entityManager.flush();
	}

	@SuppressWarnings("unchecked")
	public DAOImpl() {
		ParameterizedType genericSuperclass = (ParameterizedType) getClass()
				.getGenericSuperclass();
		this.entityClass = (Class<E>) genericSuperclass
				.getActualTypeArguments()[1];
	}

	@Transactional(readOnly=false)
	public void persist(E entity) {
		entityManager.persist(entity);
	}

	@Transactional(readOnly=false)
	public void merge(E entity) {
		entityManager.merge(entity);
	}

	@Transactional(readOnly=false)
	public void remove(E entity) {
		entityManager.remove(entity);
	}
	
	@Transactional(readOnly=true)
	public E findById(K id) {
		return entityManager.find(entityClass, id);
	}
	
	public void rollBack() {
		entityManager.getTransaction().rollback();
	}
	
	@Transactional(readOnly=true)
	@SuppressWarnings({ "unchecked", "rawtypes" })
	public List findAll() {		
		String query = "from " + entityClass.getName() + " c";
        return entityManager.createQuery(query).getResultList();
	}

	@Transactional(readOnly=true)
	public E loadEntity(String condition){
        Query query =  entityManager.createQuery("SELECT c FROM "+ entityClass.getName() + " c WHERE " + condition);
        return (E) query.getSingleResult();
    }

	@Transactional(readOnly=true)
	public E loadEntity(String condition, String orderBy){
        Query query =  entityManager.createQuery("FROM "+ entityClass.getName() + " c WHERE " + condition + " ORDER BY " + orderBy);
        E result = (E) query.getSingleResult();
        return result;
    }

	@Transactional(readOnly=false)
	public void removeEntity(String condition){
        Query query =  entityManager.createQuery("DELETE FROM "+ entityClass.getName() + " c WHERE " + condition);
		query.executeUpdate();
    }

	@Transactional(readOnly=true)
	public int estimatedSize() {
		Query query = entityManager.createNativeQuery(
				"SELECT reltuples AS estimate " +
				"FROM pg_class " +
				"WHERE relname='" + entityClass.getSimpleName().toLowerCase() + "'"
		);
		return Math.round((float) query.getSingleResult());
	}
}