package it.unibo.ronf.server.dao;

import it.unibo.ronf.shared.entities.Optional;

import org.springframework.stereotype.Repository;

import java.util.List;

import javax.persistence.TypedQuery;

@Repository("optionalDAO")
public class OptionalDAO extends JpaDAO<Optional> {
	
	public Optional findByName(String name) {
		
		TypedQuery<Optional> query = em.createQuery(
				"SELECT o FROM Optional o WHERE o.name = :name", entityClass);
		
		query.setParameter("name", name);
		
		List<Optional> optionalResult = query.getResultList();
		
		return optionalResult.get(0);
		
	}
	
	

}
