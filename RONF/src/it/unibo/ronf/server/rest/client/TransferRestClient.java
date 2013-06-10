package it.unibo.ronf.server.rest.client;

import it.unibo.ronf.server.dao.TransferDAO;
import it.unibo.ronf.server.services.RentalServiceImpl;
import it.unibo.ronf.shared.entities.Agency;
import it.unibo.ronf.shared.entities.Transfer;

import javax.ws.rs.core.MediaType;

import org.apache.log4j.Logger;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import com.sun.jersey.api.client.Client;
import com.sun.jersey.api.client.WebResource;

@Service("clientRestTransferService")
public class TransferRestClient {

	@Autowired
	private TransferDAO transferDAO;
	
	private static final Logger logger = Logger.getLogger(RentalServiceImpl.class);


	public void sendTransferRequest(Transfer t) {
		Client client = Client.create();
		client.setConnectTimeout(10000);
		WebResource webResource = client.resource(getURI(t.getStartAgency()));
		logger.debug("Sending transfer request to: "+webResource.getURI());
		//ClientResponse response = webResource.type("application/xml").post(ClientResponse.class, t);
		webResource.accept(MediaType.APPLICATION_XML).post(t);
		logger.debug("Transfer successfully created on agency: "+t.getStartAgency().getName());
	}

	public String getURI(Agency a) {
		String res = "http://" + a.getIpAddress() + ":" + a.getPort() + "/RONF/rest/transfer/";
		return res;
	}

}
