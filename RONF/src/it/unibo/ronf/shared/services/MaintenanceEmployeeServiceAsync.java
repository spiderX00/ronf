package it.unibo.ronf.shared.services;

import it.unibo.ronf.shared.entities.MaintenanceEmployee;

import java.util.List;

import com.google.gwt.user.client.rpc.AsyncCallback;

public interface MaintenanceEmployeeServiceAsync {

	void createMaintenanceEmployee(MaintenanceEmployee me, AsyncCallback<Void> callback);

	void findByBusy(boolean busy, AsyncCallback<List<MaintenanceEmployee>> callback);

	void removeById(long id, AsyncCallback<Void> callback);

	void updateMaintenanceEmployee(MaintenanceEmployee me, AsyncCallback<Void> callback);

}
