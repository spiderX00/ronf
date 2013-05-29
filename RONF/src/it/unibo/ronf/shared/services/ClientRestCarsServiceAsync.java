package it.unibo.ronf.shared.services;
import com.google.gwt.user.client.rpc.AsyncCallback;

import java.util.Date;
import java.util.List;

import it.unibo.ronf.shared.entities.Car;
import it.unibo.ronf.shared.entities.CarType;

public interface ClientRestCarsServiceAsync {
	
	public void findAvailableCar(CarType type, Date start, Date end, AsyncCallback<List<Car>> callback);

}
