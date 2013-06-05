package it.unibo.ronf.server.dao;

import it.unibo.ronf.shared.entities.Customer;

import org.springframework.stereotype.Repository;

import javax.persistence.NoResultException;
import javax.persistence.TypedQuery;

@Repository("customerDAO")
public class CustomerDAO extends JpaDAO<Customer> {

	public Customer findByFiscalCode(String fiscalCode) {

		try {

			TypedQuery<Customer> query = em
					.createQuery(
							"SELECT c FROM Customer c WHERE c.fiscalCode = :fiscalCode",
							entityClass);

			query.setParameter("fiscalCode", fiscalCode);

			return query.getSingleResult();
		} catch (NoResultException ex) {
			return null;
		}

	}

	public Customer findByDocNumber(String docNumber) {

		try {

			TypedQuery<Customer> query = em.createQuery(
					"SELECT c FROM Customer C WHERE c.docNumber = :docNumber",
					entityClass);

			query.setParameter("docNumber", docNumber);

			return query.getSingleResult();
		} catch (NoResultException ex) {
			return null;
		}

	}

}