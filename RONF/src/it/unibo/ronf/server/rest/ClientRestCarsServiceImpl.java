package it.unibo.ronf.server.rest;

import java.util.ArrayList;
import java.util.Date;
import java.util.GregorianCalendar;
import java.util.List;

import javax.ws.rs.core.MediaType;

import org.apache.log4j.Logger;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Propagation;
import org.springframework.transaction.annotation.Transactional;

import com.sun.jersey.api.client.Client;
import com.sun.jersey.api.client.GenericType;
import com.sun.jersey.api.client.WebResource;


import it.unibo.ronf.server.dao.CarDAO;
import it.unibo.ronf.server.dao.CarTypeDAO;
import it.unibo.ronf.server.dao.RentalDAO;
import it.unibo.ronf.shared.dto.AvailableCarRequestDTO;
import it.unibo.ronf.shared.entities.Car;
import it.unibo.ronf.shared.entities.CarType;
import it.unibo.ronf.shared.entities.Rental;
import it.unibo.ronf.shared.services.ClientRestCarsService;
import it.unibo.ronf.shared.util.Utils;

@Service("clientRestCarsService")
@Transactional(propagation=Propagation.REQUIRED,rollbackFor=Exception.class)
public class ClientRestCarsServiceImpl implements ClientRestCarsService {
	
	private static final Logger logger = Logger
			.getLogger(ClientRestCarsServiceImpl.class);
	
	@Autowired
	private CarDAO carDAO;
	@Autowired
	private CarTypeDAO typeDAO;
	@Autowired
	private RentalDAO rentalDAO;
		
	public List<Car> findAvailableCar(CarType type, Date start, Date end) {
		
		try {
			Client client = Client.create();

			WebResource webResource = client.resource(getBaseUrl());
			List<Car> cars = webResource.accept(MediaType.APPLICATION_XML)
					.post(new GenericType<List<Car>>() {
					}, new AvailableCarRequestDTO(type, start, end));
			return cars;
		} catch (Exception ex) {
			logger.error(ex.getMessage(), ex);
			return new ArrayList<Car>();
		}
	}

	public String getBaseUrl() {
		if (Utils.isProductionMode()) {
			return "http://127.0.0.1:8888/rest/cars/";
		} else {
			return "http://127.0.0.1:8888/rest/cars/";
		}
	}
	
}

	