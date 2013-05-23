package it.unibo.ronf.server.services;

import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Propagation;
import org.springframework.transaction.annotation.Transactional;

import it.unibo.ronf.server.dao.OptionalDAO;
import it.unibo.ronf.shared.entities.Optional;
import it.unibo.ronf.shared.services.OptionalService;

@Service("optionalService")
public class OptionalServiceImpl implements OptionalService {

	@Autowired
	OptionalDAO optionalDAO;

	@Override
	public Optional findByName(String name) {
		return optionalDAO.findByName(name);
	}

	@Override
	@Transactional(propagation = Propagation.REQUIRED, rollbackFor = Exception.class)
	public void createOptional(Optional optional) {
		optionalDAO.persist(optional);

	}

	@Override
	@Transactional(propagation=Propagation.REQUIRED,rollbackFor=Exception.class)
	public void removeById(long id) {
		optionalDAO.remove(optionalDAO.findById(id));

	}

	@Override
	public List<Optional> findAll() {
		return optionalDAO.findAll();
	}

}
