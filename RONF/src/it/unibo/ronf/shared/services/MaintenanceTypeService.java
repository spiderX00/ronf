package it.unibo.ronf.shared.services;

import java.util.List;

import it.unibo.ronf.shared.entities.MaintenanceType;

import com.google.gwt.user.client.rpc.RemoteService;
import com.google.gwt.user.client.rpc.RemoteServiceRelativePath;

@RemoteServiceRelativePath("ronfServices/maintenanceTypeService")
public interface MaintenanceTypeService extends RemoteService {
	
	public MaintenanceType findByName(String name);
	
	public void createMaintenanceType(MaintenanceType mt);
	
	public void removeById(long id);
	
	public List<MaintenanceType> findAll();
	

}
