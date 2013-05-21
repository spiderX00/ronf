package it.unibo.ronf.shared.services;

import it.unibo.ronf.shared.entities.CarType;


import com.google.gwt.user.client.rpc.RemoteService;
import com.google.gwt.user.client.rpc.RemoteServiceRelativePath;

@RemoteServiceRelativePath("ronfServices/carTypeService")
public interface CarTypeService extends RemoteService{
	
	public void insertCarType(CarType ct);
	
	public CarType findBytype(String type);

}
