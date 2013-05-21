package it.unibo.ronf.shared.services;

import java.util.Date;
import java.util.List;

import it.unibo.ronf.shared.entities.Rental;
import it.unibo.ronf.shared.entities.Agency;

import com.google.gwt.user.client.rpc.AsyncCallback;

public interface RentalServiceAsync {

	public void createRental(Rental rental, AsyncCallback<Void> callback);

	public void findAll(AsyncCallback<List<Rental>> callback);

	public void findByArrivalAgency(Agency arrivalAgency,
			AsyncCallback<List<Rental>> callback);

	public void findByEnd(Date end, AsyncCallback<List<Rental>> callback);

	public void findByStart(Date start, AsyncCallback<List<Rental>> callback);

	public void findByStartingAgency(Agency startingAgency,
			AsyncCallback<List<Rental>> callback);

	void removeById(long id, AsyncCallback<Void> callback);

}
