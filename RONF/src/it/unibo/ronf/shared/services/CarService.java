package it.unibo.ronf.shared.services;

import it.unibo.ronf.shared.dto.AvailableCarRequestDTO;
import it.unibo.ronf.shared.entities.Agency;
import it.unibo.ronf.shared.entities.Car;
import it.unibo.ronf.shared.entities.CarType;

import java.util.List;

import com.google.gwt.user.client.rpc.RemoteService;
import com.google.gwt.user.client.rpc.RemoteServiceRelativePath;

@RemoteServiceRelativePath("ronfServices/carService")
public interface CarService extends RemoteService {

	public void createCar(Car car);

	public List<Car> findAll();

	public List<Car> findAvailableCarsInAllAgencies(AvailableCarRequestDTO request);

	public List<Car> findByGasolineType(String gasolineType);

	public Car findByPlate(String plate);

	public List<Car> findByType(CarType cartype);

	public List<Car> getAllFreeCars(Agency a);

	public void removeById(long id);

}