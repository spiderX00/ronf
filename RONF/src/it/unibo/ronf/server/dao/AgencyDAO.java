package it.unibo.ronf.server.dao;

import it.unibo.ronf.shared.entities.Agency;

import java.util.List;

import javax.persistence.NoResultException;
import javax.persistence.TypedQuery;

import org.springframework.stereotype.Repository;

@Repository("agencyDAO")
public class AgencyDAO extends JpaDAO<Agency> {

	private Agency currentAgency;

	public Agency findByCode(String code) {
		try {
			TypedQuery<Agency> query = em.createQuery("SELECT a FROM Agency a WHERE a.code = :code", entityClass);

			query.setParameter("code", code);

			return query.getSingleResult();
		} catch (NoResultException ex) {
			return null;
		}

	}

	public Agency findByName(String name) {

		try {

			TypedQuery<Agency> query = em.createQuery("SELECT a FROM Agency a WHERE a.name = :name", entityClass);

			query.setParameter("name", name);

			return query.getSingleResult();

		} catch (NoResultException ex) {
			return null;
		}
	}

	public Agency getCurrentAgency() {
		return currentAgency;
	}

	public List<Agency> getOthers() {

		TypedQuery<Agency> query = em.createQuery("SELECT a FROM Agency a WHERE a.name != :current", entityClass);

		query.setParameter("current", getCurrentAgency().getName());

		return query.getResultList();

	}

	public void setCurrentAgency(Agency currentAgency) {
		this.currentAgency = currentAgency;
	}

}
