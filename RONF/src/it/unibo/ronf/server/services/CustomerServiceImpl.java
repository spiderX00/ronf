package it.unibo.ronf.server.services;

import java.util.List;

import it.unibo.ronf.server.dao.CustomerDAO;
import it.unibo.ronf.shared.entities.Customer;
import it.unibo.ronf.shared.entities.Employee;
import it.unibo.ronf.shared.services.CustomerService;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Propagation;
import org.springframework.transaction.annotation.Transactional;

@Service("customerService")
public class CustomerServiceImpl implements CustomerService {
	
	@Autowired
	private CustomerDAO customerDAO;
	
	@Override
	@Transactional(propagation=Propagation.REQUIRED,rollbackFor=Exception.class)
	public void createCustomer(Customer customer) {
		customerDAO.persist(customer);
	}

	@Override
	public Customer findByFiscalCode(String fiscalCode) {
		return customerDAO.findByFiscalCode(fiscalCode);
	}

	@Override
	public Customer findByDocNumber(String docNumber) {
		return customerDAO.findByDocNumber(docNumber);
	}
	
	@Override
	public List<Customer> findAll() {
		return customerDAO.findAll();
	}

	@Override
	public Customer findById(Long id) {
		return customerDAO.findById(id);
	}

	@Override
	public void remove(Customer customer) {
		customerDAO.remove(customer);
		
	}

}
