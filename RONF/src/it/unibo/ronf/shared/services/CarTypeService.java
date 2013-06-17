package it.unibo.ronf.shared.services;

import it.unibo.ronf.shared.entities.CarType;

import java.util.List;

import com.google.gwt.user.client.rpc.RemoteService;
import com.google.gwt.user.client.rpc.RemoteServiceRelativePath;

@RemoteServiceRelativePath("ronfServices/carTypeService")
public interface CarTypeService extends RemoteService {

	public List<CarType> findAll();

	public CarType findBytype(String type);

	public void insertCarType(CarType ct);

	public void removeById(long id);

}
