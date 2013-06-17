package it.unibo.ronf.server.dao;

import java.lang.reflect.ParameterizedType;
import java.util.List;

import javax.persistence.EntityManager;
import javax.persistence.PersistenceContext;
import javax.persistence.Query;
import javax.persistence.TypedQuery;

import org.apache.log4j.Logger;

public abstract class JpaDAO<E> {

	public static final Logger logger = Logger.getLogger(JpaDAO.class);

	protected Class<E> entityClass;

	@PersistenceContext(unitName = "ronfPU")
	protected EntityManager em;

	@SuppressWarnings("unchecked")
	public JpaDAO() {
		ParameterizedType genericSuperclass = (ParameterizedType) getClass().getGenericSuperclass();
		this.entityClass = (Class<E>) genericSuperclass.getActualTypeArguments()[0];
	}

	public List<E> findAll() {
		TypedQuery<E> q = em.createQuery("SELECT h FROM " + entityClass.getName() + " h", entityClass);
		return q.getResultList();
	}

	public E findById(Long id) {
		return em.find(entityClass, id);
	}

	public E flush(E entity) {
		em.flush();
		return entity;
	}

	/* Aggiorna oggetto */
	public E merge(E entity) {
		return em.merge(entity);
	}

	public void persist(E entity) {
		em.persist(entity);
	}

	public void refresh(E entity) {
		em.refresh(entity);
	}

	public void remove(E entity) {
		em.remove(entity);
	}

	public Integer removeAll() {
		Query q = em.createQuery("DELETE FROM " + entityClass.getName() + " h");
		return q.executeUpdate();
	}

}