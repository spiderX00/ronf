package it.unibo.ronf.shared.services;

import com.google.gwt.user.client.rpc.RemoteService;
import com.google.gwt.user.client.rpc.RemoteServiceRelativePath;

import java.util.List;
import java.util.Date;

import it.unibo.ronf.shared.entities.Car;
import it.unibo.ronf.shared.entities.Maintenance;
import it.unibo.ronf.shared.entities.MaintenanceEmployee;

@RemoteServiceRelativePath("ronfServices/maintenanceService")
public interface MaintenanceService extends RemoteService {

	public Maintenance findByCar(Car car);

	public List<Maintenance> findByDate(Date date);

	public List<Maintenance> findByMaintenanceEmployee(MaintenanceEmployee maintenanceEmployee);

	public void createMaintenance(Maintenance mt);

	public void removeById(long id);

	public List<Maintenance> findAll();

}
