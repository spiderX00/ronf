package it.unibo.ronf.shared.services;

import com.google.gwt.user.client.rpc.RemoteService;
import com.google.gwt.user.client.rpc.RemoteServiceRelativePath;

import it.unibo.ronf.shared.entities.TransferAction;
import it.unibo.ronf.shared.entities.TransferEmployee;

import java.util.Date;
import java.util.List;

@RemoteServiceRelativePath("ronfServices/transferActionService")
public interface TransferActionService extends RemoteService {

	public TransferAction findByEmployee(TransferEmployee te);

	public List<TransferAction> findByDate(Date transferDate);

	public List<TransferAction> findBySuccess(boolean success);

	public void createTransferAction(TransferAction ta);

	public void updateSuccessTransferAction(TransferAction ta);

}
