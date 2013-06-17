package it.unibo.ronf.server.dao;

import it.unibo.ronf.shared.entities.MaintenanceEmployee;

import java.util.List;

import javax.persistence.TypedQuery;

import org.springframework.stereotype.Repository;

@Repository("maintenanceEmployeeDAO")
public class MaintenanceEmployeeDAO extends JpaDAO<MaintenanceEmployee> {

	public List<MaintenanceEmployee> findByBusy(boolean busy) {

		TypedQuery<MaintenanceEmployee> query = em.createQuery("SELECT me FROM MaintenanceEmployee me WHERE me.busy = :busy", entityClass);

		query.setParameter("busy", busy);

		return query.getResultList();

	}

}
