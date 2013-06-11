package it.unibo.ronf.shared.services;

import java.util.Date;
import java.util.List;

import it.unibo.ronf.shared.entities.TransferAction;
import it.unibo.ronf.shared.entities.TransferEmployee;

import com.google.gwt.user.client.rpc.AsyncCallback;

public interface TransferActionServiceAsync {

	void findByEmployee(TransferEmployee te,
			AsyncCallback<TransferAction> callback);

	void findByDate(Date transferDate,
			AsyncCallback<List<TransferAction>> callback);

	void findBySuccess(boolean success,
			AsyncCallback<List<TransferAction>> callback);

	void createTransferAction(TransferAction ta, AsyncCallback<Void> callback);

}
