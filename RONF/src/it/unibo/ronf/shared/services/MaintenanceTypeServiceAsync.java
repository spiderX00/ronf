package it.unibo.ronf.shared.services;

import it.unibo.ronf.shared.entities.MaintenanceType;

import java.util.List;

import com.google.gwt.user.client.rpc.AsyncCallback;

public interface MaintenanceTypeServiceAsync {

	public void createMaintenanceType(MaintenanceType mt, AsyncCallback<Void> callback);

	public void findAll(AsyncCallback<List<MaintenanceType>> callback);

	public void findByName(String name, AsyncCallback<MaintenanceType> callback);

	public void removeById(long id, AsyncCallback<Void> callback);

}
