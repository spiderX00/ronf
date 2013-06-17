package it.unibo.ronf.shared.services;

import it.unibo.ronf.shared.entities.Car;
import it.unibo.ronf.shared.entities.Maintenance;
import it.unibo.ronf.shared.entities.MaintenanceEmployee;

import java.util.Date;
import java.util.List;

import com.google.gwt.user.client.rpc.AsyncCallback;

public interface MaintenanceServiceAsync {

	public void createMaintenance(Maintenance mt, AsyncCallback<Void> callback);

	public void findAll(AsyncCallback<List<Maintenance>> callback);

	public void findByCar(Car car, AsyncCallback<Maintenance> callback);

	public void findByDate(Date date, AsyncCallback<List<Maintenance>> callback);

	public void findByMaintenanceEmployee(MaintenanceEmployee maintenanceEmployee, AsyncCallback<List<Maintenance>> callback);

	public void removeById(long id, AsyncCallback<Void> callback);

}
