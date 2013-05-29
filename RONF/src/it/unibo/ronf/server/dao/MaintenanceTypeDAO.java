package it.unibo.ronf.server.dao;

import javax.persistence.TypedQuery;

import org.springframework.stereotype.Repository;

import it.unibo.ronf.shared.entities.MaintenanceType;

@Repository("maintenanceTypeDAO")
public class MaintenanceTypeDAO extends JpaDAO<MaintenanceType> {
	
	public MaintenanceType findByName(String name) {
		
		TypedQuery<MaintenanceType> query = em.createQuery(
				"SELECT mt FROM MaintenanceType a WHERE mt.name = :name", entityClass);
		
		query.setParameter("name", name);
		
		return query.getSingleResult();
	}
	
	

}
