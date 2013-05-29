package it.unibo.ronf.server.dao;

import it.unibo.ronf.shared.entities.Agency;

import org.springframework.stereotype.Repository;

import javax.persistence.NoResultException;
import javax.persistence.TypedQuery;

@Repository("agencyDAO")
public class AgencyDAO extends JpaDAO<Agency> {

	public Agency findByCode(String code) {

		try {

			TypedQuery<Agency> query = em.createQuery(
					"SELECT a FROM Agency a WHERE a.code = :code", entityClass);

			query.setParameter("code", code);

			return query.getSingleResult();
		} catch (NoResultException ex) {
			return null;
		}

	}

	public Agency findByName(String name) {

		try {

			TypedQuery<Agency> query = em.createQuery(
					"SELECT a FROM Agency a WHERE a.name = :name", entityClass);

			query.setParameter("name", name);

			return query.getSingleResult();

		} catch (NoResultException ex) {
			return null;
		}

	}

}
