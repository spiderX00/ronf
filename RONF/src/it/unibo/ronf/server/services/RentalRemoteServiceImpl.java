package it.unibo.ronf.server.services;

import java.util.List;

import it.unibo.ronf.server.rest.client.RentalRestClient;
import it.unibo.ronf.shared.entities.Agency;
import it.unibo.ronf.shared.entities.Rental;
import it.unibo.ronf.shared.services.RentalRemoteService;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

@Service("rentalRemoteService")
public class RentalRemoteServiceImpl implements RentalRemoteService {
	
	@Autowired
	private RentalRestClient rentalRestClient;

	@Override
	public List<Rental> getUserRemoteRental(long id, Agency a) {
		return rentalRestClient.getUserRemoteRental(id, a);
	}

	@Override
	public void closeRemoteRental(Rental r) {
		rentalRestClient.closeRemoteRental(r);
	}
	
}
