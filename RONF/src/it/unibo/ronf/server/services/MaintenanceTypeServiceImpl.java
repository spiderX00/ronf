package it.unibo.ronf.server.services;

import java.util.List;

import it.unibo.ronf.server.dao.MaintenanceTypeDAO;
import it.unibo.ronf.shared.entities.MaintenanceType;
import it.unibo.ronf.shared.services.MaintenanceTypeService;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Propagation;
import org.springframework.transaction.annotation.Transactional;

@Service("maintenanceTypeService")
public class MaintenanceTypeServiceImpl implements MaintenanceTypeService {

	@Autowired
	MaintenanceTypeDAO maintenanceTypeDAO;

	@Override
	public MaintenanceType findByName(String name) {
		return maintenanceTypeDAO.findByName(name);
	}

	@Override
	@Transactional(propagation = Propagation.REQUIRED, rollbackFor = Exception.class)
	public void createMaintenanceType(MaintenanceType mt) {
		maintenanceTypeDAO.persist(mt);

	}

	@Override
	@Transactional(propagation = Propagation.REQUIRED, rollbackFor = Exception.class)
	public void removeById(long id) {
		maintenanceTypeDAO.remove(maintenanceTypeDAO.findById(id));

	}

	@Override
	public List<MaintenanceType> findAll() {
		return maintenanceTypeDAO.findAll();
	}

}
