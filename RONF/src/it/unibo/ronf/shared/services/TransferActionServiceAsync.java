package it.unibo.ronf.shared.services;

import it.unibo.ronf.shared.entities.TransferAction;
import it.unibo.ronf.shared.entities.TransferEmployee;

import java.util.Date;
import java.util.List;

import com.google.gwt.user.client.rpc.AsyncCallback;

public interface TransferActionServiceAsync {

	void createTransferAction(TransferAction ta, AsyncCallback<Void> callback);

	void findByDate(Date transferDate, AsyncCallback<List<TransferAction>> callback);

	void findByEmployee(TransferEmployee te, AsyncCallback<TransferAction> callback);

	void findBySuccess(boolean success, AsyncCallback<List<TransferAction>> callback);

	void updateSuccessTransferAction(TransferAction ta, AsyncCallback<Void> callback);

}
