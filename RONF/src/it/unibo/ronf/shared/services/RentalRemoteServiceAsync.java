package it.unibo.ronf.shared.services;

import it.unibo.ronf.shared.entities.Agency;
import it.unibo.ronf.shared.entities.Rental;

import java.util.List;

import com.google.gwt.user.client.rpc.AsyncCallback;

public interface RentalRemoteServiceAsync {

	void closeRemoteRental(Rental r, AsyncCallback<Void> callback);

	void getUserRemoteRental(long id, Agency a, AsyncCallback<List<Rental>> callback);

}
