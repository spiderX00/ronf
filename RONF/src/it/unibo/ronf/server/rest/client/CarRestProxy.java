package it.unibo.ronf.server.rest.client;

import it.unibo.ronf.server.dao.AgencyDAO;
import it.unibo.ronf.server.rest.RestClient;
import it.unibo.ronf.shared.dto.AvailableCarRequestDTO;
import it.unibo.ronf.shared.entities.Agency;
import it.unibo.ronf.shared.entities.Car;

import java.util.ArrayList;
import java.util.List;

import javax.ws.rs.core.MediaType;

import org.apache.log4j.Logger;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import com.sun.jersey.api.client.Client;
import com.sun.jersey.api.client.GenericType;
import com.sun.jersey.api.client.WebResource;

@Service("clientRestCarsService")
public class CarRestProxy implements RestClient {

	private static final Logger logger = Logger.getLogger(CarRestProxy.class);

	@Autowired
	private AgencyDAO agencyDAO;

	@Override
	public String getBaseUrl(Agency a) {
		return "http://" + a.getIpAddress() + ":" + a.getPort() + "/RONF/rest/cars";
	}

	public List<Car> findAvailableCar(AvailableCarRequestDTO request) {
		List<Car> allAvailableCars = new ArrayList<>();

		for (Agency a : agencyDAO.getOthers()) {

			logger.debug("Richiesta inoltrata a " + a.getName());

			Client client = Client.create();
			client.setConnectTimeout(10000);
			WebResource webResource = client.resource(getBaseUrl(a)).path("available");

			logger.debug("Request URI:" + webResource.getURI());

			List<Car> cars = webResource.accept(MediaType.APPLICATION_XML).post(new GenericType<List<Car>>() {
			}, request);

			if (logger.isDebugEnabled()) {
				for (Car c : cars) {
					logger.debug("Traovata macchina: " + c.getModel() + " at " + c.getOriginAgency().getName() + " in " + a.getName());
				}
			}

			allAvailableCars.addAll(cars);
		}
		return allAvailableCars;

	}

	public List<Car> findFreeCars(Agency a) {

		List<Car> freeRemote = new ArrayList<>();

		logger.debug("Richiesta macchine libere inoltrata a " + a.getName());

		Client client = Client.create();
		client.setConnectTimeout(1000);
		WebResource webResource = client.resource(getBaseUrl(a)).path("free");

		logger.debug("Request URI:" + webResource.getURI());

		freeRemote = webResource.accept(MediaType.APPLICATION_XML).post(new GenericType<List<Car>>() {
		});

		if (logger.isDebugEnabled()) {
			for (Car c : freeRemote) {
				logger.debug("Traovata macchina free per parco: " + c.getModel() + " at " + c.getOriginAgency().getName() + " in " + a.getName());
			}
		}
		return freeRemote;
	}

}