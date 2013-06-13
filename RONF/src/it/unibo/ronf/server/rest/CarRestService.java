package it.unibo.ronf.server.rest;

import it.unibo.ronf.shared.dto.AvailableCarRequestDTO;
import it.unibo.ronf.shared.entities.Car;

import java.util.List;

public interface CarRestService {

	public abstract List<Car> findAvailableCar(AvailableCarRequestDTO request);

	public abstract List<Car> findFreeCars();

}