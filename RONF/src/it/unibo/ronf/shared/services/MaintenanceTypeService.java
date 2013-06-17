package it.unibo.ronf.shared.services;

import it.unibo.ronf.shared.entities.MaintenanceType;

import java.util.List;

import com.google.gwt.user.client.rpc.RemoteService;
import com.google.gwt.user.client.rpc.RemoteServiceRelativePath;

@RemoteServiceRelativePath("ronfServices/maintenanceTypeService")
public interface MaintenanceTypeService extends RemoteService {

	public void createMaintenanceType(MaintenanceType mt);

	public List<MaintenanceType> findAll();

	public MaintenanceType findByName(String name);

	public void removeById(long id);

}
