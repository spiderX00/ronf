package it.unibo.ronf.shared.services;

import it.unibo.ronf.shared.entities.Agency;
import it.unibo.ronf.shared.entities.Transfer;

import java.util.List;

import com.google.gwt.user.client.rpc.RemoteService;
import com.google.gwt.user.client.rpc.RemoteServiceRelativePath;

@RemoteServiceRelativePath("ronfServices/transferService")
public interface TransferService extends RemoteService {

	public List<Transfer> findByStartAgency(Agency startAgency);

	public List<Transfer> findByArrivalAgency(Agency arrivalAgency);

	public void createTransfer(Transfer t);

	public List<Transfer> findAllPending();

	public List<Transfer> findAll();

	public boolean updateSuccessTransfer(Transfer t);

	public void SetEmployeePerTransfer(Transfer t);

}
