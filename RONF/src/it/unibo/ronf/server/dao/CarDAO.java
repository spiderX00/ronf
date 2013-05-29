package it.unibo.ronf.server.dao;

import it.unibo.ronf.shared.entities.Car;
import it.unibo.ronf.shared.entities.CarType;

import java.util.Date;
import java.util.List;

import javax.persistence.NoResultException;
import javax.persistence.TypedQuery;

import org.springframework.stereotype.Repository;

@Repository("carDAO")
public class CarDAO extends JpaDAO<Car> {

	public List<Car> findByModel(String model) {

		TypedQuery<Car> query = em.createQuery(
				"SELECT c FROM Car c WHERE c.model = :model", entityClass);

		query.setParameter("model", model);
		List<Car> carList = query.getResultList();
		return carList;
	}

	public Car findByPlate(String plate) {
		try {
			TypedQuery<Car> query = em.createQuery(
					"SELECT c FROM Car c WHERE c.plate = :plate", entityClass);

			query.setParameter("plate", plate);
			return query.getSingleResult();
		} catch (NoResultException ex) {
			return null;
		}
	}

	public List<Car> findByGasolineType(String gasolineType) {

		TypedQuery<Car> query = em.createQuery(
				"SELECT c FROM Car c WHERE c.gasolineType = :gasolineType",
				entityClass);

		query.setParameter("gasolineType", gasolineType);
		List<Car> carList = query.getResultList();
		return carList;
	}

	public List<Car> findBySeatsNumber(int seatsNumber) {

		TypedQuery<Car> query = em.createQuery(
				"SELECT c FROM Car c WHERE c.seatsNumber = :seatsNumber",
				entityClass);

		query.setParameter("seatsNumber", seatsNumber);

		List<Car> carList = query.getResultList();

		return carList;

	}

	/**
	 * Supercazzola
	 * 
	 * @param type
	 * @param start
	 * @param end
	 * @return
	 */
	public List<Car> findAvailableCar(CarType type, Date start, Date end) {

		// Macchine di un determinato tipo che sono state noleggiate
		TypedQuery<Car> queryFree = em.createQuery("SELECT r.rentedCar "
				+ "FROM Rental r " + "WHERE r.rentedCar.type = :type",
				Car.class);
		queryFree.setParameter("type", type);
		List<Car> rentedCars = queryFree.getResultList();

		// Macchine di un determinato tipo meno quelle noleggiate
		queryFree = em.createQuery("SELECT c FROM Car c "
				+ "WHERE c.type = :type AND c NOT IN :rentedCars", Car.class);
		queryFree.setParameter("type", type);
		queryFree.setParameter("rentedCars", rentedCars);
		List<Car> freeCars = queryFree.getResultList();

		// Macchine di un determinato tipo noleggiate ma disponibili nel periodo
		// richiesto
		queryFree = em.createQuery("SELECT c.rentedCar FROM Rental c "
				+ "WHERE c.rentedCar.type = :type AND "
				+ "((c.start > :start AND c.start > :end) OR "
				+ "(c.end < :start AND c.end < :end)) ", Car.class);
		queryFree.setParameter("type", type);
		queryFree.setParameter("start", start);
		queryFree.setParameter("end", end);

		// unione delle macchine non noleggiate con quelle
		// noleggiate ma disponibili nel periodo richiesto
		List<Car> availableCars = queryFree.getResultList();
		availableCars.addAll(freeCars);

		return availableCars;

	}

	public List<Car> findByType(CarType cartype) {

		TypedQuery<Car> query = em.createQuery(
				"SELECT c FROM Car c WHERE c.type = :cartype", entityClass);

		query.setParameter("cartype", cartype);

		List<Car> carListType = query.getResultList();

		return carListType;
	}

}
