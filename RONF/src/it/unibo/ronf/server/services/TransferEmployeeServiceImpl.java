package it.unibo.ronf.server.services;

import it.unibo.ronf.server.dao.TransferEmployeeDAO;
import it.unibo.ronf.shared.entities.TransferEmployee;
import it.unibo.ronf.shared.services.TransferEmployeeService;

import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Propagation;
import org.springframework.transaction.annotation.Transactional;

@Service("transferEmployeeService")
public class TransferEmployeeServiceImpl implements TransferEmployeeService {

	@Autowired
	private TransferEmployeeDAO teDAO;

	@Override
	@Transactional(propagation = Propagation.REQUIRED, rollbackFor = Exception.class)
	public void createTransferEmployee(TransferEmployee te) {
		teDAO.persist(te);

	}

	@Override
	public List<TransferEmployee> findAll() {
		return teDAO.findAll();
	}

	@Override
	public List<TransferEmployee> findByBusy(boolean busy) {
		return teDAO.findByBusy(busy);
	}

	@Override
	@Transactional(propagation = Propagation.REQUIRED, rollbackFor = Exception.class)
	public void removeById(long id) {
		teDAO.remove(teDAO.findById(id));

	}

	@Override
	@Transactional(propagation = Propagation.REQUIRED, rollbackFor = Exception.class)
	public void updateTransferEmployeeBusy(TransferEmployee te) {
		teDAO.merge(te);

	}

}
