package it.unibo.ronf.shared.services;

import java.util.List;
import java.util.Date;


import it.unibo.ronf.shared.entities.Rental;
import it.unibo.ronf.shared.entities.Agency;

import com.google.gwt.user.client.rpc.RemoteService;
import com.google.gwt.user.client.rpc.RemoteServiceRelativePath;

@RemoteServiceRelativePath("ronfServices/rentalService")
public interface RentalService extends RemoteService {

	public List<Rental> findByStart(Date start);

	public List<Rental> findByEnd(Date end);

	public List<Rental> findByStartingAgency(Agency startingAgency);

	public List<Rental> findByArrivalAgency(Agency arrivalAgency);

	public List<Rental> findAll();

	public void createRental(Rental rental);
	
	public void removeById(long id);
	
	public void updateRental(Rental r);
}
