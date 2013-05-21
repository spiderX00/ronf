package it.unibo.ronf.server.dao;

import it.unibo.ronf.shared.entities.Customer;

import org.springframework.stereotype.Repository;

import java.util.List;

import javax.persistence.TypedQuery;

@Repository("customerDAO")
public class CustomerDAO extends JpaDAO<Customer> {
	
	public Customer findByFiscalCode(String fiscalCode) {
		
		TypedQuery<Customer> query = em.createQuery("SELECT c FROM Customer c WHERE c.fiscalCode = :fiscalCode", entityClass);
		
		query.setParameter("fiscalCode", fiscalCode);
		
		List<Customer> customerList = query.getResultList();
		
		return customerList.get(0);
		
	}
	
	
	public Customer findByDocNumber(String docNumber) {
		
		TypedQuery<Customer> query = em.createQuery("SELCT c FROM Customer C WHERE c.docNumber = :docNumber", entityClass);
		
		query.setParameter("docNumber", docNumber);
		
		List<Customer> customerList = query.getResultList();
		
		return customerList.get(0);
		
		
	}
	
	
	
	
}