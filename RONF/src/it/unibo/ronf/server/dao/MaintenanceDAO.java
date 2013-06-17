package it.unibo.ronf.server.dao;

import it.unibo.ronf.shared.entities.Car;
import it.unibo.ronf.shared.entities.Maintenance;
import it.unibo.ronf.shared.entities.MaintenanceEmployee;

import org.springframework.stereotype.Repository;

import java.util.Date;
import java.util.List;

import javax.persistence.NoResultException;
import javax.persistence.TypedQuery;

@Repository("maintenanceDAO")
public class MaintenanceDAO extends JpaDAO<Maintenance> {

	public Maintenance findByCar(Car car) {
		try {

			TypedQuery<Maintenance> query = em.createQuery("SELECT m FROM Maintenance m WHERE m.car = :car", entityClass);

			query.setParameter("car", car);

			return query.getSingleResult();
		} catch (NoResultException ex) {
			return null;
		}

	}

	public List<Maintenance> findByDate(Date date) {

		TypedQuery<Maintenance> query = em.createQuery("SELECT m FROM Maintenance m WHERE m.date = :date", entityClass);

		query.setParameter("date", date);

		List<Maintenance> maintenanceResults = query.getResultList();

		return maintenanceResults;

	}

	public List<Maintenance> findByMaintenanceEmployee(MaintenanceEmployee maintenanceEmployee) {

		TypedQuery<Maintenance> query = em.createQuery("SELECT m FROM Maintenance m WHERE m.maintenanceEmployee = :maintenanceEmployee", entityClass);

		query.setParameter("maintenanceEmployee", maintenanceEmployee);

		List<Maintenance> maintenanceResults = query.getResultList();

		return maintenanceResults;

	}

}
