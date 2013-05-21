package it.unibo.ronf.shared.services;

import it.unibo.ronf.shared.entities.CarType;

import com.google.gwt.user.client.rpc.AsyncCallback;

public interface CarTypeServiceAsync {

	void insertCarType(CarType ct, AsyncCallback<Void> callback);

	void findBytype(String type, AsyncCallback<CarType> callback);

}
