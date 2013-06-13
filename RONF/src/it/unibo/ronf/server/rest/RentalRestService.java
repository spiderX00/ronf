package it.unibo.ronf.server.rest;

import it.unibo.ronf.shared.dto.GetRentalByUserDTO;
import it.unibo.ronf.shared.entities.Rental;

import java.util.List;

public interface RentalRestService {

	public abstract List<Rental> findRentalForUser(GetRentalByUserDTO closeDto);

	public abstract void closeRental(Rental r);

}