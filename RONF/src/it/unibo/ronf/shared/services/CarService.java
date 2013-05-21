package it.unibo.ronf.shared.services;

import java.util.List;

import it.unibo.ronf.shared.entities.Car;
import it.unibo.ronf.shared.entities.CarType;

import com.google.gwt.user.client.rpc.RemoteService;
import com.google.gwt.user.client.rpc.RemoteServiceRelativePath;

@RemoteServiceRelativePath("ronfServices/carService")
public interface CarService extends RemoteService {
	
	public List<Car> findByModel(String model);

	public Car findByPlate(String plate);
	
	public List<Car> findByGasolineType(String gasolineType);

	public void createCar(Car car);
	
	public List<Car> findByType(CarType cartype);
	
	public void removeById(long id);

}