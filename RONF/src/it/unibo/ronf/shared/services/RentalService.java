package it.unibo.ronf.shared.services;

import it.unibo.ronf.shared.entities.Agency;
import it.unibo.ronf.shared.entities.Rental;

import java.util.Date;
import java.util.List;

import com.google.gwt.user.client.rpc.RemoteService;
import com.google.gwt.user.client.rpc.RemoteServiceRelativePath;

@RemoteServiceRelativePath("ronfServices/rentalService")
public interface RentalService extends RemoteService {

	public void createRental(Rental rental);

	public List<Rental> findAll();

	public List<Rental> findByArrivalAgency(Agency arrivalAgency);

	public List<Rental> findByEnd(Date end);

	public List<Rental> findByStart(Date start);

	public List<Rental> findByStartingAgency(Agency startingAgency);

	public void removeById(long id);

	public void updateRental(Rental r);
}
