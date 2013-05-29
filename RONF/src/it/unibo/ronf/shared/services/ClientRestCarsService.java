package it.unibo.ronf.shared.services;
import java.util.Date;
import java.util.List;

import it.unibo.ronf.server.rest.*;
import it.unibo.ronf.shared.entities.Car;
import it.unibo.ronf.shared.entities.CarType;

import com.google.gwt.user.client.rpc.RemoteService;
import com.google.gwt.user.client.rpc.RemoteServiceRelativePath;

@RemoteServiceRelativePath("ronfServices/clientRestCarsService")
public interface ClientRestCarsService extends RemoteService {
	
	public List<Car> findAvailableCar(CarType type, Date start, Date end);

}
