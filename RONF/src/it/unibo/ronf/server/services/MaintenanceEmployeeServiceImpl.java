package it.unibo.ronf.server.services;

import it.unibo.ronf.server.dao.MaintenanceEmployeeDAO;
import it.unibo.ronf.shared.entities.MaintenanceEmployee;
import it.unibo.ronf.shared.services.MaintenanceEmployeeService;

import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Propagation;
import org.springframework.transaction.annotation.Transactional;

@Service("maintenanceEmployeeService")
public class MaintenanceEmployeeServiceImpl implements MaintenanceEmployeeService {

	@Autowired
	MaintenanceEmployeeDAO maintenanceEmployeeDao;

	@Override
	@Transactional(propagation = Propagation.REQUIRED, rollbackFor = Exception.class)
	public void createMaintenanceEmployee(MaintenanceEmployee me) {
		maintenanceEmployeeDao.persist(me);

	}

	@Override
	public List<MaintenanceEmployee> findByBusy(boolean busy) {
		return maintenanceEmployeeDao.findByBusy(busy);
	}

	@Override
	@Transactional(propagation = Propagation.REQUIRED, rollbackFor = Exception.class)
	public void removeById(long id) {
		maintenanceEmployeeDao.remove(maintenanceEmployeeDao.findById(id));

	}

	@Override
	@Transactional(propagation = Propagation.REQUIRED, rollbackFor = Exception.class)
	public void updateMaintenanceEmployee(MaintenanceEmployee me) {
		maintenanceEmployeeDao.merge(me);

	}

}
