package it.unibo.ronf.server.services;

import java.util.List;
import java.util.Date;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.transaction.annotation.Propagation;
import org.springframework.transaction.annotation.Transactional;

import it.unibo.ronf.server.dao.TransferActionDAO;
import it.unibo.ronf.shared.entities.TransferAction;
import it.unibo.ronf.shared.services.TransferActionService;
import it.unibo.ronf.shared.entities.TransferEmployee;

import org.springframework.stereotype.Service;

@Service("transferActionService")
public class TransferActionServiceImpl implements TransferActionService {

	@Autowired
	private TransferActionDAO taDAO;

	@Override
	public TransferAction findByEmployee(TransferEmployee te) {
		return taDAO.findByEmployee(te);
	}

	@Override
	public List<TransferAction> findByDate(Date transferDate) {
		return taDAO.findByDate(transferDate);
	}

	@Override
	public List<TransferAction> findBySuccess(boolean success) {
		return taDAO.findBySuccess(success);
	}

	@Override
	@Transactional(propagation = Propagation.REQUIRED, rollbackFor = Exception.class)
	public void createTransferAction(TransferAction ta) {
		taDAO.persist(ta);

	}

	@Override
	@Transactional(propagation = Propagation.REQUIRED, rollbackFor = Exception.class)
	public void updateSuccessTransferAction(TransferAction ta) {
		taDAO.merge(ta);

	}

}
