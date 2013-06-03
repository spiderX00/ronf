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

	public List<Car> findRemoteAvailableCar(AvailableCarRequestDTO request)
			throws Exception {
		List<Car> allAvailableCars = new ArrayList<>();
		try {
			for (Agency a : agencyDAO.findAll()) {
				Client client = Client.create();
				WebResource webResource = client.resource(getBaseUrl(
						a.getIpAddress(), a.getPort()));
				List<Car> cars = webResource.accept(MediaType.APPLICATION_XML)
						.post(new GenericType<List<Car>>() {
						}, request);
				allAvailableCars.addAll(cars);
			}
			return allAvailableCars;
		} catch (Exception ex) {
			logger.error(ex.getMessage(), ex);
			throw new Exception(ex);
		}
	}

	public String getBaseUrl(String ip, int port) {
		if (Utils.isProductionMode()) {
			return "http://" + ip + ":" + port + "/rest/cars/";
		} else {
			return "http://127.0.0.1:8081/rest/cars/";
		}
	}

}
