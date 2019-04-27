package data.daos.impl;
import java.lang.reflect.ParameterizedType;
import java.util.List;

import javax.persistence.EntityManager;
import javax.persistence.PersistenceContext;
import javax.persistence.PersistenceContextType;
import javax.persistence.Query;

import data.daos.DAO;
import data.entities.Country;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Repository;
import org.springframework.transaction.annotation.Transactional;


@Repository
@Transactional
public abstract class DAOImpl<K, E> implements DAO<K, E> {

    protected Class<E> entityClass;

    @Autowired
	//@PersistenceContext
	EntityManager entityManager;
	
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
	public void save(E entity) {

		entityManager.getTransaction().begin();
		entityManager.persist(entity);
		entityManager.getTransaction().commit();
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
		Query q = entityManager.createQuery(query);
        return q.getResultList();
	}

	public E querySingle(String condition){
        Query query =  entityManager.createQuery("SELECT c FROM "+ entityClass.getName() + " c WHERE " + condition);
        return (E) query.getSingleResult();
    }

	public E loadEntity(String condition, String orderBy){
        Query query =  entityManager.createQuery("SELECT c FROM "+ entityClass.getName() + " c WHERE " + condition + " ORDER BY " + orderBy);
        return (E) query.getSingleResult();
    }

}