package it.unibo.ronf.server.services;

import it.unibo.ronf.server.dao.OptionalDAO;
import it.unibo.ronf.shared.entities.Optional;
import it.unibo.ronf.shared.services.OptionalService;

import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Propagation;
import org.springframework.transaction.annotation.Transactional;

@Service("optionalService")
public class OptionalServiceImpl implements OptionalService {

	@Autowired
	OptionalDAO optionalDAO;

	@Override
	@Transactional(propagation = Propagation.REQUIRED, rollbackFor = Exception.class)
	public void createOptional(Optional optional) {
		optionalDAO.persist(optional);

	}

	@Override
	public List<Optional> findAll() {
		return optionalDAO.findAll();
	}

	@Override
	public Optional findByName(String name) {
		return optionalDAO.findByName(name);
	}

	@Override
	@Transactional(propagation = Propagation.REQUIRED, rollbackFor = Exception.class)
	public void removeById(long id) {
		optionalDAO.remove(optionalDAO.findById(id));

	}

}
