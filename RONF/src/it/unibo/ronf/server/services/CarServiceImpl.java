package it.unibo.ronf.server.services;

import it.unibo.ronf.server.dao.CarDAO;
import it.unibo.ronf.server.rest.CarRestClient;
import it.unibo.ronf.shared.dto.AvailableCarRequestDTO;
import it.unibo.ronf.shared.entities.Car;
import it.unibo.ronf.shared.entities.CarType;
import it.unibo.ronf.shared.services.CarService;

import java.util.ArrayList;
import java.util.List;

import javax.ws.rs.Consumes;
import javax.ws.rs.GET;
import javax.ws.rs.POST;
import javax.ws.rs.Path;
import javax.ws.rs.PathParam;
import javax.ws.rs.Produces;
import javax.ws.rs.core.MediaType;

import org.apache.log4j.Logger;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.context.annotation.Scope;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Propagation;
import org.springframework.transaction.annotation.Transactional;

@Service("carService")
@Path("/cars")
@Scope("request")
public class CarServiceImpl implements CarService {

	private static final Logger logger = Logger.getLogger(CarServiceImpl.class);

	@Autowired
	private CarDAO carDAO;

	@Autowired
	private CarRestClient carRestClient;

	@Override
	@Transactional(propagation = Propagation.REQUIRED, rollbackFor = Exception.class)
	public void createCar(Car car) {
		carDAO.persist(car);
	}

	@Override
	@GET
	@Path("/model/{model}")
	@Produces({ MediaType.APPLICATION_XML })
	public List<Car> findByModel(@PathParam("model") String model) {
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

	/**
	 * Questo metodo deve essere invocato soltanto attraverso chiamate REST
	 */
	@Override
	@POST
	@Produces({ MediaType.APPLICATION_XML })
	@Consumes({ MediaType.APPLICATION_XML })
	public List<Car> findAvailableCar(AvailableCarRequestDTO request) {
		return carDAO.findAvailableCar(request.getType(), request.getStart(), request.getEnd());
	}

	@Override
	@Transactional(propagation = Propagation.REQUIRED, rollbackFor = Exception.class)
	public void removeById(long id) {
		carDAO.remove(carDAO.findById(id));
	}

	@Override
	public List<Car> findAll() {
		return carDAO.findAll();
	}

	@Override
	public List<Car> findByType(CarType carType) {
		return carDAO.findByType(carType);
	}

	@Override
	public List<Car> findAvailableCarsInAllAgencies(AvailableCarRequestDTO request) {
		List<Car> localCars = findAvailableCar(request);
		List<Car> allFound = new ArrayList<>();
		allFound.addAll(localCars);
		try {
			List<Car> remoteCars = carRestClient.findRemoteAvailableCar(request);
			allFound.addAll(remoteCars);
		} catch (Exception ex) {
			logger.error("error while searching avaiable car in other agency: -->" + ex.getMessage());
		}
		return allFound;
	}

}
