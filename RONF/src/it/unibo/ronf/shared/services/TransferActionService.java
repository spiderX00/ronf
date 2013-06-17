package it.unibo.ronf.shared.services;

import it.unibo.ronf.shared.entities.TransferAction;
import it.unibo.ronf.shared.entities.TransferEmployee;

import java.util.Date;
import java.util.List;

import com.google.gwt.user.client.rpc.RemoteService;
import com.google.gwt.user.client.rpc.RemoteServiceRelativePath;

@RemoteServiceRelativePath("ronfServices/transferActionService")
public interface TransferActionService extends RemoteService {

	public void createTransferAction(TransferAction ta);

	public List<TransferAction> findByDate(Date transferDate);

	public TransferAction findByEmployee(TransferEmployee te);

	public List<TransferAction> findBySuccess(boolean success);

	public void updateSuccessTransferAction(TransferAction ta);

}
