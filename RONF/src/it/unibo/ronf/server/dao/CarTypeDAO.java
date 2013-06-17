package it.unibo.ronf.server.dao;

import it.unibo.ronf.shared.entities.CarType;

import javax.persistence.NoResultException;
import javax.persistence.TypedQuery;
import org.springframework.stereotype.Repository;

@Repository("carTypeDAO")
public class CarTypeDAO extends JpaDAO<CarType> {

	public CarType findByType(String type) {
		try {
			TypedQuery<CarType> query = em.createQuery("SELECT ct FROM CarType ct WHERE ct.type = :type", entityClass);

			query.setParameter("type", type);
			return query.getSingleResult();
		} catch (NoResultException ex) {
			return null;
		}
	}

}
