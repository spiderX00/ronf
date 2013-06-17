package it.unibo.ronf.shared.services;

import it.unibo.ronf.shared.entities.TransferEmployee;

import java.util.List;

import com.google.gwt.user.client.rpc.AsyncCallback;

public interface TransferEmployeeServiceAsync {

	void createTransferEmployee(TransferEmployee te, AsyncCallback<Void> callback);

	void findAll(AsyncCallback<List<TransferEmployee>> callback);

	void findByBusy(boolean busy, AsyncCallback<List<TransferEmployee>> callback);

	void removeById(long id, AsyncCallback<Void> callback);

	void updateTransferEmployeeBusy(TransferEmployee te, AsyncCallback<Void> callback);

}
