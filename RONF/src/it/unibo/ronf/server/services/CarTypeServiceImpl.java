package it.unibo.ronf.server.services;

import it.unibo.ronf.server.dao.CarTypeDAO;
import it.unibo.ronf.shared.entities.CarType;
import it.unibo.ronf.shared.services.CarTypeService;

import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Propagation;
import org.springframework.transaction.annotation.Transactional;

@Service("carTypeService")
public class CarTypeServiceImpl implements CarTypeService {

	@Autowired
	private CarTypeDAO carTypeDAO;

	@Override
	public List<CarType> findAll() {
		return carTypeDAO.findAll();
	}

	@Override
	public CarType findBytype(String type) {
		return carTypeDAO.findByType(type);
	}

	@Override
	@Transactional(propagation = Propagation.REQUIRED, rollbackFor = Exception.class)
	public void insertCarType(CarType ct) {
		carTypeDAO.persist(ct);
	}

	@Override
	public void removeById(long id) {
		carTypeDAO.remove(carTypeDAO.findById(id));
	}

}
