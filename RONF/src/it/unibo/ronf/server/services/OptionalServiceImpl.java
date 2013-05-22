package it.unibo.ronf.server.services;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.transaction.annotation.Propagation;
import org.springframework.transaction.annotation.Transactional;

import it.unibo.ronf.server.dao.OptionalDAO;
import it.unibo.ronf.shared.entities.Optional;
import it.unibo.ronf.shared.services.OptionalService;

public class OptionalServiceImpl implements OptionalService {
	
	@Autowired
	OptionalDAO optionalDAO;

	@Override
	@Transactional(propagation = Propagation.REQUIRED, rollbackFor = Exception.class)
	public Optional findByName(String name) {
		return optionalDAO.findByName(name);
	}

	@Override
	public void createOptional(Optional optional) {
		optionalDAO.persist(optional);
		
	}

	@Override
	public void removeById(long id) {
		optionalDAO.remove(optionalDAO.findById(id));
		
	}

}
