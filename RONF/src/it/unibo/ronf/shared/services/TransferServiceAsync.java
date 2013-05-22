package it.unibo.ronf.shared.services;

import it.unibo.ronf.shared.entities.Agency;
import it.unibo.ronf.shared.entities.Transfer;

import java.util.List;

import com.google.gwt.user.client.rpc.AsyncCallback;

public interface TransferServiceAsync {

	void findByStartAgency(Agency startAgency,
			AsyncCallback<List<Transfer>> callback);

	void findByArrivalAgency(Agency arrivalAgency,
			AsyncCallback<List<Transfer>> callback);

	void createTransfer(Transfer t, AsyncCallback<Void> callback);

	void removeById(long id, AsyncCallback<Boolean> callback);

}
