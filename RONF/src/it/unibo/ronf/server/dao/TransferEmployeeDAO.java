package it.unibo.ronf.server.dao;

import it.unibo.ronf.shared.entities.TransferEmployee;
import java.util.List;


import javax.persistence.TypedQuery;

import org.springframework.stereotype.Repository;

@Repository("transferEmployeeDAO")
public class TransferEmployeeDAO extends JpaDAO<TransferEmployee> {

	public List<TransferEmployee> findByBusy(boolean busy) {

		TypedQuery<TransferEmployee> query = em.createQuery("SELECT te FROM TransferEmployee te WHERE te.busy = :busy", entityClass);

		query.setParameter("busy", busy);
		List<TransferEmployee> teList = query.getResultList();

		return teList;

	}

}
