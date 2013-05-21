package it.unibo.ronf.shared.services;

import java.util.List;

import it.unibo.ronf.shared.entities.Customer;

import com.google.gwt.user.client.rpc.RemoteService;
import com.google.gwt.user.client.rpc.RemoteServiceRelativePath;

@RemoteServiceRelativePath("ronfServices/customerService")
public interface CustomerService extends RemoteService {
	
	public void createCustomer(Customer customer);
	
	public Customer findByFiscalCode(String fiscalCode);
	
	public Customer findByDocNumber(String docNumber);

	public List<Customer> findAll();
	
	public Customer findById(Long id);
	
	public void remove(Customer customer);

}
