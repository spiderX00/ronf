package it.unibo.ronf.server.dao;

import it.unibo.ronf.shared.entities.Transfer;
import it.unibo.ronf.shared.entities.Agency;

import java.util.List;

import javax.persistence.TypedQuery;

import org.springframework.stereotype.Repository;

@Repository("transferDAO")
public class TransferDAO extends JpaDAO<Transfer> {
	
	public List<Transfer> findByStartAgency(Agency startAgency) {
		
		TypedQuery<Transfer> query = em.createQuery(
				"SELECT t FROM Transfer t WHERE t.startAgency = :startAgency", entityClass);

		query.setParameter("startAgency", startAgency);

		List<Transfer> transferList = query.getResultList();

		return transferList;
		
	}
	
	public List<Transfer> findByArrivalAgency(Agency arrivalAgency) {
		
		TypedQuery<Transfer> query = em.createQuery(
				"SELECT t FROM Transfer t WHERE t.arrivalAgency = :arrivalAgency", entityClass);

		query.setParameter("arrivalAgency", arrivalAgency);

		List<Transfer> transferList = query.getResultList();

		return transferList;
		
	}
	
	public List<Transfer> findAllPending() {
				
		TypedQuery<Transfer> query = em.createQuery(
				"SELECT t FROM Transfer t WHERE t.success = :state", entityClass);
		
		query.setParameter("state", false);
		
		return query.getResultList();
		
	}
	
	
}
