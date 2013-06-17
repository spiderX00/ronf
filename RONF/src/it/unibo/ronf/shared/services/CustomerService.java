package it.unibo.ronf.shared.services;

import it.unibo.ronf.shared.entities.Customer;

import java.util.List;

import com.google.gwt.user.client.rpc.RemoteService;
import com.google.gwt.user.client.rpc.RemoteServiceRelativePath;

@RemoteServiceRelativePath("ronfServices/customerService")
public interface CustomerService extends RemoteService {

	public void createCustomer(Customer customer);

	public List<Customer> findAll();

	public Customer findByDocNumber(String docNumber);

	public Customer findByFiscalCode(String fiscalCode);

	public Customer findById(Long id);

	public void remove(Customer customer);

	public void removeById(long id);

}
