package it.unibo.ronf.server.rest.client;

import java.util.List;

import javax.ws.rs.core.MediaType;


import it.unibo.ronf.shared.dto.CloseRentalDTO;
import it.unibo.ronf.shared.entities.Agency;
import it.unibo.ronf.shared.entities.Rental;

import org.apache.log4j.Logger;
import org.springframework.stereotype.Service;

import com.sun.jersey.api.client.Client;
import com.sun.jersey.api.client.GenericType;
import com.sun.jersey.api.client.WebResource;

@Service("clientRestRentalService")
public class RentalRestClient {

	private final static Logger logger = Logger
			.getLogger(RentalRestClient.class);

	public List<Rental> getUserRemoteRental(long id, Agency a) {
		
		CloseRentalDTO crDTO = new CloseRentalDTO();
		crDTO.setId(id);
		
		logger.debug("Eseguo insertRemoteRental");

		Client client = Client.create();
		client.setConnectTimeout(10000);
		WebResource webResource = client.resource(getBaseUrlRentals(a.getIpAddress(), a.getPort())).path("getRentals");
		
		logger.debug("Getting remote rental for user " + id + " at "+webResource.getURI());
		List<Rental> rentals = webResource.accept(MediaType.APPLICATION_XML).post(new GenericType<List<Rental>>() {}, crDTO);
		
		logger.debug("Post Remote get user remote rental executed!");
		
		return rentals;

	}
	
	public void closeRemoteRental(Rental r) {
		
		logger.debug("Inizio a chiudere rental remoto");
		
		Client client = Client.create();
		client.setConnectTimeout(10000);
		WebResource webResource = client.resource(getBaseUrlClose(r.getStartingAgency().getIpAddress(), r.getStartingAgency().getPort())).path("close");
		
		logger.debug("Chiamo RentalRestService servizio di chiusura");
		webResource.accept(MediaType.APPLICATION_XML).post(r);
		logger.debug("chiusura rental ha avuto successo");
		
		
	}

	public String getBaseUrlRentals(String ip, int port) {
		return "http://" + ip + ":" + port + "/RONF/rest/rental/getRentals";
	}
	
	public String getBaseUrlClose(String ip, int port) {
		return "http://" + ip + ":" + port + "/RONF/rest/rental/close";
	}

}
