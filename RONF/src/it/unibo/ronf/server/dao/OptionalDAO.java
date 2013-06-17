package it.unibo.ronf.server.dao;

import it.unibo.ronf.shared.entities.Optional;

import javax.persistence.NoResultException;
import javax.persistence.TypedQuery;

import org.springframework.stereotype.Repository;

@Repository("optionalDAO")
public class OptionalDAO extends JpaDAO<Optional> {

	public Optional findByName(String name) {
		try {

			TypedQuery<Optional> query = em.createQuery("SELECT o FROM Optional o WHERE o.name = :name", entityClass);

			query.setParameter("name", name);

			return query.getSingleResult();
		} catch (NoResultException ex) {
			return null;
		}

	}

}
