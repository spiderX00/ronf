package it.unibo.ronf.shared.services;

import java.util.List;

import it.unibo.ronf.shared.entities.MaintenanceType;

import com.google.gwt.user.client.rpc.AsyncCallback;

public interface MaintenanceTypeServiceAsync {

	public void findByName(String name, AsyncCallback<MaintenanceType> callback);

	public void createMaintenanceType(MaintenanceType mt, AsyncCallback<Void> callback);

	public void removeById(long id, AsyncCallback<Void> callback);

	public void findAll(AsyncCallback<List<MaintenanceType>> callback);

}
