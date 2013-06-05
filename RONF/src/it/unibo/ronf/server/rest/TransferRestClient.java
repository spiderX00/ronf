package it.unibo.ronf.server.rest;

import javax.ws.rs.core.MediaType;

import it.unibo.ronf.server.dao.TransferDAO;
import it.unibo.ronf.server.services.RentalServiceImpl;
import it.unibo.ronf.shared.entities.Agency;
import it.unibo.ronf.shared.entities.Transfer;

import org.apache.log4j.Logger;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import com.sun.jersey.api.client.Client;
import com.sun.jersey.api.client.ClientResponse;
import com.sun.jersey.api.client.WebResource;

@Service("clientRestTransferService")
public class TransferRestClient {

	@Autowired
	private TransferDAO transferDAO;
	
	private static final Logger logger = Logger.getLogger(RentalServiceImpl.class);


	public void sendRequestTransfer(Transfer t) {
		Client client = Client.create();
		client.setConnectTimeout(10000);
		WebResource webResource = client.resource(getURI(t.getStartAgency()));
		ClientResponse response = webResource.type("application/xml").post(ClientResponse.class, t);
		//webResource.accept(MediaType.APPLICATION_XML).post(t);
		logger.debug("Post eseguita dal client");
	}

	public String getURI(Agency a) {
		return "http://" + a.getIpAddress() + ":" + a.getPort() + "/RONF/rest/transfer/";
	}

}
