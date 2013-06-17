package it.unibo.ronf.shared.services;

import it.unibo.ronf.shared.entities.Customer;

import java.util.List;

import com.google.gwt.user.client.rpc.AsyncCallback;

public interface CustomerServiceAsync {

	public void createCustomer(Customer customer, AsyncCallback<Void> callback);

	public void findAll(AsyncCallback<List<Customer>> callback);

	public void findByDocNumber(String docNumber, AsyncCallback<Customer> callback);

	public void findByFiscalCode(String fiscalCode, AsyncCallback<Customer> callback);

	void findById(Long id, AsyncCallback<Customer> callback);

	void remove(Customer customer, AsyncCallback<Void> callback);

	void removeById(long id, AsyncCallback<Void> callback);

}
