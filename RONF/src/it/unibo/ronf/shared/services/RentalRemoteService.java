package it.unibo.ronf.shared.services;

import it.unibo.ronf.shared.entities.Agency;
import it.unibo.ronf.shared.entities.Rental;

import java.util.List;

import com.google.gwt.user.client.rpc.RemoteService;
import com.google.gwt.user.client.rpc.RemoteServiceRelativePath;

@RemoteServiceRelativePath("ronfServices/rentalRemoteService")
public interface RentalRemoteService extends RemoteService {

	public List<Rental> getUserRemoteRental(long id, Agency a);

	public void closeRemoteRental(Rental r);

}
