package it.unibo.ronf.shared.services;

import java.util.List;

import it.unibo.ronf.shared.entities.Customer;

import com.google.gwt.user.client.rpc.AsyncCallback;

public interface CustomerServiceAsync {
	
	public void createCustomer(Customer customer, AsyncCallback<Void> callback);

	public void findByFiscalCode(String fiscalCode, AsyncCallback<Customer> callback);

	public void findByDocNumber(String docNumber, AsyncCallback<Customer> callback);

	public void findAll(AsyncCallback<List<Customer>> callback);
	
}

