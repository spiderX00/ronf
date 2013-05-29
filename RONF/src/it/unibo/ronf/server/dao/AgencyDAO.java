package it.unibo.ronf.server.dao;

import it.unibo.ronf.shared.entities.Agency;

import org.springframework.stereotype.Repository;

import java.util.List;

import javax.persistence.TypedQuery;

@Repository("agencyDAO")
public class AgencyDAO extends JpaDAO<Agency> {
	
	public Agency findByCode(String code) {
		
		TypedQuery<Agency> query = em.createQuery(
				"SELECT a FROM Agency a WHERE a.code = :code", entityClass);
		
		query.setParameter("code", code);
		
		List<Agency> agencyResult = query.getResultList();
		
		return agencyResult.get(0);
		
	}
	
	public Agency findByName(String name) {
		
		TypedQuery<Agency> query = em.createQuery(
				"SELECT a FROM Agency a WHERE a.name = :name", entityClass);
		
		query.setParameter("name", name);
		
		List<Agency> agencyResult = query.getResultList();
		
		return agencyResult.get(0);
		
	}
	
	

}
