package it.unibo.ronf.server.services;

import it.unibo.ronf.server.dao.CarDAO;
import it.unibo.ronf.server.rest.client.CarRestProxy;
import it.unibo.ronf.shared.dto.AvailableCarRequestDTO;
import it.unibo.ronf.shared.entities.Agency;
import it.unibo.ronf.shared.entities.Car;
import it.unibo.ronf.shared.entities.CarType;
import it.unibo.ronf.shared.services.CarService;

import java.util.ArrayList;
import java.util.List;

import org.apache.log4j.Logger;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Propagation;
import org.springframework.transaction.annotation.Transactional;

@Service("carService")
public class CarServiceImpl implements CarService {

	private static final Logger logger = Logger.getLogger(CarServiceImpl.class);

	@Autowired
	private CarDAO carDAO;

	@Autowired
	private CarRestProxy carRestClient;

	@Override
	@Transactional(propagation = Propagation.REQUIRED, rollbackFor = Exception.class)
	public void createCar(Car car) {
		carDAO.persist(car);
	}

	@Override
	public List<Car> findAll() {
		return carDAO.findAll();
	}

	@Override
	public List<Car> findAvailableCarsInAllAgencies(AvailableCarRequestDTO request) {
		List<Car> localCars = carDAO.findAvailableCar(request.getType(), request.getStart(), request.getEnd());
		List<Car> allFound = new ArrayList<>();
		allFound.addAll(localCars);
		try {
			List<Car> remoteCars = carRestClient.findAvailableCar(request);
			allFound.addAll(remoteCars);
		} catch (Exception ex) {
			logger.error("error while searching avaiable car in other agency: -->" + ex.getMessage());
		}
		return allFound;
	}

	@Override
	public List<Car> findByGasolineType(String gasolineType) {
		return carDAO.findByGasolineType(gasolineType);
	}

	@Override
	public Car findByPlate(String plate) {
		return carDAO.findByPlate(plate);
	}

	@Override
	public List<Car> findByType(CarType carType) {
		return carDAO.findByType(carType);
	}

	@Override
	public List<Car> getAllFreeCars(Agency a) {
		return carRestClient.findFreeCars(a);
	}

	@Override
	@Transactional(propagation = Propagation.REQUIRED, rollbackFor = Exception.class)
	public void removeById(long id) {
		carDAO.remove(carDAO.findById(id));
	}
}
