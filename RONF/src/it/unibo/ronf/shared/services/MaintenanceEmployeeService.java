package it.unibo.ronf.shared.services;

import it.unibo.ronf.shared.entities.MaintenanceEmployee;

import java.util.List;

import com.google.gwt.user.client.rpc.RemoteService;
import com.google.gwt.user.client.rpc.RemoteServiceRelativePath;

@RemoteServiceRelativePath("ronfServices/maintenanceEmployeeService")
public interface MaintenanceEmployeeService extends RemoteService {

	public List<MaintenanceEmployee> findByBusy(boolean busy);

	public void createMaintenanceEmployee(MaintenanceEmployee me);

	public void removeById(long id);

	public void updateMaintenanceEmployee(MaintenanceEmployee me);

}
