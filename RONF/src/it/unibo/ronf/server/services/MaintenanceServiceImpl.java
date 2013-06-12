package it.unibo.ronf.server.services;

import java.util.Date;
import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Propagation;
import org.springframework.transaction.annotation.Transactional;

import it.unibo.ronf.server.dao.MaintenanceDAO;
import it.unibo.ronf.shared.entities.Car;
import it.unibo.ronf.shared.entities.Maintenance;
import it.unibo.ronf.shared.entities.MaintenanceEmployee;
import it.unibo.ronf.shared.services.MaintenanceService;

@Service("maintenanceService")
public class MaintenanceServiceImpl implements MaintenanceService {

	@Autowired
	MaintenanceDAO maintenanceDAO;

	@Override
	public Maintenance findByCar(Car car) {
		return maintenanceDAO.findByCar(car);
	}

	@Override
	public List<Maintenance> findByDate(Date date) {
		return maintenanceDAO.findByDate(date);
	}

	@Override
	public List<Maintenance> findByMaintenanceEmployee(MaintenanceEmployee maintenanceEmployee) {
		return maintenanceDAO.findByMaintenanceEmployee(maintenanceEmployee);
	}

	@Override
	@Transactional(propagation = Propagation.REQUIRED, rollbackFor = Exception.class)
	public void createMaintenance(Maintenance mt) {
		// TODO Auto-generated method stub

	}

	@Override
	@Transactional(propagation = Propagation.REQUIRED, rollbackFor = Exception.class)
	public void removeById(long id) {
		maintenanceDAO.remove(maintenanceDAO.findById(id));

	}

	@Override
	public List<Maintenance> findAll() {
		return maintenanceDAO.findAll();
	}

}
