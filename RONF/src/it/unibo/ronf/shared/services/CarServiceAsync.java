package it.unibo.ronf.shared.services;

import it.unibo.ronf.shared.dto.AvailableCarRequestDTO;
import it.unibo.ronf.shared.entities.Car;
import it.unibo.ronf.shared.entities.CarType;

import java.util.Date;
import java.util.List;

import com.google.gwt.user.client.rpc.AsyncCallback;

public interface CarServiceAsync {

	public void findByModel(String model, AsyncCallback<List<Car>> callback);

	public void findByPlate(String plate, AsyncCallback<Car> callback);

	public void findByGasolineType(String gasolineType,AsyncCallback<List<Car>> callback);

	public void createCar(Car car, AsyncCallback<Void> callback);

	void removeById(long id, AsyncCallback<Void> callback);

	public void findAll(AsyncCallback<List<Car>> callback);

	public void findAvailableCar(AvailableCarRequestDTO request,
			AsyncCallback<List<Car>> callback);

	public void findByType(CarType cartype, AsyncCallback<List<Car>> callback);

}