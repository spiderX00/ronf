package it.unibo.ronf.server.rest;

import it.unibo.ronf.shared.dto.GetRentalByUserDTO;
import it.unibo.ronf.shared.entities.Rental;

import java.util.List;

/**
 * Interfaccia del servizio REST relativo ai noleggi.
 */
public interface RentalRestService {

	public abstract void closeRental(Rental r);

	public abstract List<Rental> findRentalForUser(GetRentalByUserDTO closeDto);

}