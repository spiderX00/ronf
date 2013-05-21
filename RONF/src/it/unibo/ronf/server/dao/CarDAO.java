package it.unibo.ronf.server.dao;

import it.unibo.ronf.shared.entities.Car;
import it.unibo.ronf.shared.entities.CarType;

import java.util.List;

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

		TypedQuery<Car> query = em.createQuery(
				"SELECT c FROM Car c WHERE c.plate = :plate", entityClass);

		query.setParameter("plate", plate);

		List<Car> carList = query.getResultList();

		return carList.get(0);

	}
	
	public List<Car> findByGasolineType(String gasolineType) {
		
		TypedQuery<Car> query = em.createQuery("SELECT c FROM Car c WHERE c.gasolineType = :gasolineType", entityClass);

		query.setParameter("gasolineType", gasolineType);

		List<Car> carList = query.getResultList();

		return carList;
		
	}
	
	
	public List<Car> findBySeatsNumber(int seatsNumber) {
		
		TypedQuery<Car> query = em.createQuery("SELECT c FROM Car c WHERE c.seatsNumber = :seatsNumber", entityClass);

		query.setParameter("seatsNumber", seatsNumber);

		List<Car> carList = query.getResultList();

		return carList;
		
	}
	
	public List<Car> findByType(CarType cartype) {
		
		TypedQuery<Car> query = em.createQuery("SELECT c FROM Car c WHERE c.type = :cartype", entityClass);

		query.setParameter("cartype", cartype);

		List<Car> carListType = query.getResultList();

		return carListType;
	}

}
