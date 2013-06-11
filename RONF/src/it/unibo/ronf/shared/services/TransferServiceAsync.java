package it.unibo.ronf.shared.services;

import it.unibo.ronf.shared.entities.Agency;
import it.unibo.ronf.shared.entities.Transfer;

import java.util.List;

import com.google.gwt.user.client.rpc.AsyncCallback;

public interface TransferServiceAsync {

	public void findByStartAgency(Agency startAgency,
			AsyncCallback<List<Transfer>> callback);

	public void findByArrivalAgency(Agency arrivalAgency,
			AsyncCallback<List<Transfer>> callback);

	public void createTransfer(Transfer t, AsyncCallback<Void> callback);

	public void findAllPending(AsyncCallback<List<Transfer>> callback);

	public void findAll(AsyncCallback<List<Transfer>> callback);

	public void updateSuccessTransfer(Transfer t, AsyncCallback<Void> callback);

	void SetEmployeePerTransfer(Transfer t, AsyncCallback<Void> callback);

}
