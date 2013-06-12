package it.unibo.ronf.server.services;

import it.unibo.ronf.server.dao.CustomerDAO;
import it.unibo.ronf.shared.entities.Customer;
import it.unibo.ronf.shared.services.CustomerService;

import java.util.List;

import javax.ws.rs.GET;
import javax.ws.rs.Path;
import javax.ws.rs.Produces;
import javax.ws.rs.core.MediaType;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.context.annotation.Scope;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Propagation;
import org.springframework.transaction.annotation.Transactional;

@Service("customerService")
@Path("/customers")
@Scope("request")
public class CustomerServiceImpl implements CustomerService {

	@Autowired
	private CustomerDAO customerDAO;

	@Override
	@Transactional(propagation = Propagation.REQUIRED, rollbackFor = Exception.class)
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
	@GET
	@Produces({ MediaType.APPLICATION_XML })
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

	@Override
	@Transactional(propagation = Propagation.REQUIRED, rollbackFor = Exception.class)
	public void removeById(long id) {
		customerDAO.remove(customerDAO.findById(id));

	}

}
