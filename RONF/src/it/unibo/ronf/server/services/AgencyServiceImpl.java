package it.unibo.ronf.server.services;

import it.unibo.ronf.server.dao.AgencyDAO;
import it.unibo.ronf.shared.entities.Agency;
import it.unibo.ronf.shared.services.AgencyService;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.transaction.annotation.Propagation;
import org.springframework.transaction.annotation.Transactional;

public class AgencyServiceImpl implements AgencyService {

	@Autowired
	private AgencyDAO agencyDAO;

	@Override
	@Transactional(propagation = Propagation.REQUIRED, rollbackFor = Exception.class)
	public void createAgency(Agency agency) {
		agencyDAO.persist(agency);

	}

	@Override
	public Agency findByCode(String code) {
		return agencyDAO.findByCode(code);
	}

	@Override
	public Agency findByName(String name) {
		return agencyDAO.findByName(name);
	}

	@Override
	@Transactional(propagation = Propagation.REQUIRED, rollbackFor = Exception.class)
	public void removeById(long id) {
		agencyDAO.remove(agencyDAO.findById(id));

	}

}
