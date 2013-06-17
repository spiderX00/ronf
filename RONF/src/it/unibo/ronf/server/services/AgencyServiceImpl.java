package it.unibo.ronf.server.services;

import it.unibo.ronf.server.dao.AgencyDAO;
import it.unibo.ronf.shared.entities.Agency;
import it.unibo.ronf.shared.services.AgencyService;

import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Propagation;
import org.springframework.transaction.annotation.Transactional;

@Service("agencyService")
public class AgencyServiceImpl implements AgencyService {

	@Autowired
	private AgencyDAO agencyDAO;

	@Override
	@Transactional(propagation = Propagation.REQUIRED, rollbackFor = Exception.class)
	public void createAgency(Agency agency) {

		Agency res = agencyDAO.findByName(agency.getName());

		if (res == null) {
			agencyDAO.persist(agency);
		}

	}

	@Override
	public List<Agency> findAll() {
		return agencyDAO.findAll();
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
	public Agency getCurrentAgency() {
		return agencyDAO.getCurrentAgency();
	}

	@Override
	public List<Agency> getOthersAgencies() {
		return agencyDAO.getOthers();
	}

	@Override
	@Transactional(propagation = Propagation.REQUIRED, rollbackFor = Exception.class)
	public void removeById(long id) {
		agencyDAO.remove(agencyDAO.findById(id));

	}

	@Override
	public void setCurrentAgency(Agency a) {
		agencyDAO.setCurrentAgency(a);
	}

}
