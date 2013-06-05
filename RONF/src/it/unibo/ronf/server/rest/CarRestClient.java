package it.unibo.ronf.server.rest;

import it.unibo.ronf.server.dao.AgencyDAO;
import it.unibo.ronf.shared.dto.AvailableCarRequestDTO;
import it.unibo.ronf.shared.entities.Agency;
import it.unibo.ronf.shared.entities.Car;
import it.unibo.ronf.shared.util.Utils;

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
public class CarRestClient {

	private static final Logger logger = Logger.getLogger(CarRestClient.class);

	@Autowired
	private AgencyDAO agencyDAO;

	public List<Car> findRemoteAvailableCar(AvailableCarRequestDTO request) {
		List<Car> allAvailableCars = new ArrayList<>();

		for (Agency a : agencyDAO.findAll()) {
			logger.debug("Richiesta inoltrata a " + a.getName());
			Client client = Client.create();
			client.setConnectTimeout(10000);
			WebResource webResource = client.resource(getBaseUrl(a.getIpAddress(), a.getPort()));
			List<Car> cars = webResource.accept(MediaType.APPLICATION_XML).post(new GenericType<List<Car>>() {}, request);
			
			if (logger.isDebugEnabled()) {
				for (Car c : cars) {
					logger.debug("Traovata macchina: " + c.getModel() + " at " + c.getAgency().getName()+ " in "+a.getName());
				}
			}
			
			allAvailableCars.addAll(cars);
		}
		return allAvailableCars;

	}

	public String getBaseUrl(String ip, int port) {
		// if (Utils.isProductionMode()) {
		return "http://" + ip + ":" + port + "/RONF/rest/cars/";
		// } else {
		// return "http://127.0.0.1:8081/rest/cars/";
		// }
	}

}
