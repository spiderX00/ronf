package it.unibo.ronf.shared.services;

import it.unibo.ronf.shared.entities.Payment;
import it.unibo.ronf.shared.entities.Maintenance;

import com.google.gwt.user.client.rpc.RemoteService;
import com.google.gwt.user.client.rpc.RemoteServiceRelativePath;

@RemoteServiceRelativePath("ronfServices/fineService")
public interface FineService extends RemoteService {
	
	Payment calculateFine(Maintenance m);

}
