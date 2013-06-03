package it.unibo.ronf.shared.services;

import it.unibo.ronf.shared.entities.Payment;
import it.unibo.ronf.shared.entities.Rental;

import com.google.gwt.user.client.rpc.RemoteService;
import com.google.gwt.user.client.rpc.RemoteServiceRelativePath;

@RemoteServiceRelativePath("ronfServices/agencyService")
public interface PaymentService extends RemoteService {
	
	public Payment makePayment(Rental r);

}
