package it.unibo.ronf.shared.services;

import it.unibo.ronf.shared.entities.Payment;
import it.unibo.ronf.shared.entities.Rental;

import com.google.gwt.user.client.rpc.AsyncCallback;

public interface PaymentServiceAsync {

	void makePayment(Rental r, AsyncCallback<Payment> callback);

}
