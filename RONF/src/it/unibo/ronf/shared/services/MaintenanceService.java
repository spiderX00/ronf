package it.unibo.ronf.shared.services;

import it.unibo.ronf.shared.entities.Car;
import it.unibo.ronf.shared.entities.Maintenance;
import it.unibo.ronf.shared.entities.MaintenanceEmployee;

import java.util.Date;
import java.util.List;

import com.google.gwt.user.client.rpc.RemoteService;
import com.google.gwt.user.client.rpc.RemoteServiceRelativePath;

@RemoteServiceRelativePath("ronfServices/maintenanceService")
public interface MaintenanceService extends RemoteService {

	public void createMaintenance(Maintenance mt);

	public List<Maintenance> findAll();

	public Maintenance findByCar(Car car);

	public List<Maintenance> findByDate(Date date);

	public List<Maintenance> findByMaintenanceEmployee(MaintenanceEmployee maintenanceEmployee);

	public void removeById(long id);

}
