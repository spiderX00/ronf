package it.unibo.ronf.server.rest.client;

import it.unibo.ronf.server.rest.RestClient;
import it.unibo.ronf.shared.dto.GetRentalByUserDTO;
import it.unibo.ronf.shared.entities.Agency;
import it.unibo.ronf.shared.entities.Rental;

import java.util.List;

import javax.ws.rs.core.MediaType;

import org.apache.log4j.Logger;
import org.springframework.stereotype.Service;

import com.sun.jersey.api.client.Client;
import com.sun.jersey.api.client.GenericType;
import com.sun.jersey.api.client.WebResource;

@Service("clientRestRentalService")
public class RentalRestProxy implements RestClient {

	private final static Logger logger = Logger.getLogger(RentalRestProxy.class);

	public List<Rental> getRentalForUser(long id, Agency a) {

		GetRentalByUserDTO crDTO = new GetRentalByUserDTO();
		crDTO.setId(id);

		logger.debug("Eseguo insertRemoteRental");

		Client client = Client.create();
		client.setConnectTimeout(10000);
		WebResource webResource = client.resource(getBaseUrl(a)).path("getRentals");

		logger.debug("Getting remote rental for user " + id + " at " + webResource.getURI());
		List<Rental> rentals = webResource.accept(MediaType.APPLICATION_XML).post(new GenericType<List<Rental>>() {
		}, crDTO);

		logger.debug("Post Remote get user remote rental executed!");

		return rentals;
	}

	public void closeRental(Rental r) {
		logger.debug("closeRemoteRental -> start");
		Client client = Client.create();
		client.setConnectTimeout(10000);
		WebResource webResource = client.resource(getBaseUrl(r.getStartingAgency())).path("close");
		logger.debug("closeRemoteRental -> Sending Request..");
		webResource.accept(MediaType.APPLICATION_XML).post(r);
		logger.debug("closeRemoteRental -> Rental successfully closed");
	}

	@Override
	public String getBaseUrl(Agency a) {
		return "http://" + a.getIpAddress() + ":" + a.getPort() + "/RONF/rest/rental/";
	}

}
