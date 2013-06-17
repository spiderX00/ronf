package it.unibo.ronf.shared.services;

import it.unibo.ronf.shared.entities.CarType;

import java.util.List;

import com.google.gwt.user.client.rpc.AsyncCallback;

public interface CarTypeServiceAsync {

	void findAll(AsyncCallback<List<CarType>> callback);

	public void findBytype(String type, AsyncCallback<CarType> callback);

	public void insertCarType(CarType ct, AsyncCallback<Void> callback);

	public void removeById(long id, AsyncCallback<Void> callback);

}
