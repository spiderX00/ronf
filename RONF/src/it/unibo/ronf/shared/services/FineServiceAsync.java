package it.unibo.ronf.shared.services;

import it.unibo.ronf.shared.entities.Maintenance;
import it.unibo.ronf.shared.entities.Payment;

import com.google.gwt.user.client.rpc.AsyncCallback;

public interface FineServiceAsync {

	void calculateFine(Maintenance m, AsyncCallback<Payment> callback);

}
