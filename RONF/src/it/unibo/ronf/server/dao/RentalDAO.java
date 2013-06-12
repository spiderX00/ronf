package it.unibo.ronf.server.dao;

import it.unibo.ronf.shared.entities.Rental;
import it.unibo.ronf.shared.entities.Agency;

import java.util.Date;

import org.springframework.stereotype.Repository;

import java.util.List;

import javax.persistence.TypedQuery;

@Repository("rentalDAO")
public class RentalDAO extends JpaDAO<Rental> {

	public List<Rental> findByStart(Date start) {

		TypedQuery<Rental> query = em.createQuery("SELECT r FROM Rental r WHERE r.start = :start", entityClass);

		query.setParameter("start", start);

		List<Rental> rentalListStart = query.getResultList();

		return rentalListStart;

	}

	public List<Rental> findByEnd(Date end) {

		TypedQuery<Rental> query = em.createQuery("SELECT r FROM Rental r WHERE r.end = :end", entityClass);

		query.setParameter("end", end);

		List<Rental> rentalListEnd = query.getResultList();

		return rentalListEnd;

	}

	public List<Rental> findByStartingAgency(Agency startingAgency) {

		TypedQuery<Rental> query = em.createQuery("SELECT r FROM Rental r WHERE r.startingAgency = :startingAgency", entityClass);

		query.setParameter("startingAgency", startingAgency);

		List<Rental> rentalListStartAgency = query.getResultList();

		return rentalListStartAgency;

	}

	public List<Rental> findByArrivalAgency(Agency arrivalAgency) {

		TypedQuery<Rental> query = em.createQuery("SELECT r FROM Rental r WHERE r.arrivalAgency = :arrivalAgency", entityClass);

		query.setParameter("arrivalAgency", arrivalAgency);

		List<Rental> rentalListArrivalAgency = query.getResultList();

		return rentalListArrivalAgency;

	}

	public List<Rental> findByUserId(long userID) {
		TypedQuery<Rental> query = em.createQuery("SELECT r FROM Rental r WHERE r.customer.id = :userID", entityClass);

		query.setParameter("userID", userID);
		List<Rental> rentalListArrivalAgency = query.getResultList();
		return rentalListArrivalAgency;
	}

}
