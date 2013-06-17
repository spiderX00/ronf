package it.unibo.ronf.server.services;

import it.unibo.ronf.server.dao.TransferDAO;
import it.unibo.ronf.server.dao.TransferEmployeeDAO;
import it.unibo.ronf.server.rest.client.TransferRestProxy;
import it.unibo.ronf.shared.entities.Agency;
import it.unibo.ronf.shared.entities.Transfer;
import it.unibo.ronf.shared.entities.TransferAction;
import it.unibo.ronf.shared.services.TransferService;

import java.util.List;

import org.apache.log4j.Logger;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Propagation;
import org.springframework.transaction.annotation.Transactional;

@Service("transferService")
public class TransferServiceImpl implements TransferService {

	private static final Logger logger = Logger.getLogger(TransferServiceImpl.class);

	@Autowired
	private TransferDAO transferDAO;
	@Autowired
	private TransferEmployeeDAO teDAO;

	@Autowired
	private TransferRestProxy transferRestClient;

	@Override
	public List<Transfer> findByStartAgency(Agency startAgency) {
		return transferDAO.findByStartAgency(startAgency);
	}

	@Override
	public List<Transfer> findByArrivalAgency(Agency arrivalAgency) {
		return transferDAO.findByArrivalAgency(arrivalAgency);
	}

	@Override
	public List<Transfer> findAllPending() {
		return transferDAO.findAllPending();
	}

	@Override
	public List<Transfer> findAll() {
		return transferDAO.findAll();
	}

	@Override
	public void createTransfer(Transfer t) {
		transferRestClient.createTransfer(t);
	}

	@Override
	public boolean updateSuccessTransfer(Transfer t) {

		boolean res = false;
		for (TransferAction ta : t.getTransfers()) {
			if (ta.isSuccessAction()) {
				continue;
			} else {
				logger.debug("false");
				return res;
			}
		}
		
		logger.debug("true");
	
		transferDAO.merge(t);
		return true;
	}

	@Override
	@Transactional(propagation = Propagation.REQUIRED, rollbackFor = Exception.class)
	public void SetEmployeePerTransfer(Transfer t) {
		transferDAO.merge(t);
		teDAO.merge(t.getTransferEmployee());

	}
}
