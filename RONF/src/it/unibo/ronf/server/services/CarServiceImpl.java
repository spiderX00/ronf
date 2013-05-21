package it.unibo.ronf.server.services;

import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.transaction.annotation.Propagation;
import org.springframework.transaction.annotation.Transactional;

import it.unibo.ronf.server.dao.CarDAO;
import it.unibo.ronf.shared.entities.Car;
import it.unibo.ronf.shared.services.CarService;
import it.unibo.ronf.shared.entities.CarType;

public class CarServiceImpl implements CarService {

	@Autowired
	private CarDAO carDAO;
	
	@Override
	@Transactional(propagation=Propagation.REQUIRED,rollbackFor=Exception.class)
	public void createCar(Car car) {
		carDAO.persist(car);
	}

	@Override
	public List<Car> findByModel(String model) {
		return carDAO.findByModel(model);
	}

	@Override
	public Car findByPlate(String plate) {
		return carDAO.findByPlate(plate);
	}

	@Override
	public List<Car> findByGasolineType(String gasolineType) {
		return carDAO.findByGasolineType(gasolineType);
	}

	@Override
	public List<Car> findByType(CarType cartype) {
		return carDAO.findByType(cartype);
	}

}
