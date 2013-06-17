package it.unibo.ronf.server.dao;

import it.unibo.ronf.shared.entities.TransferEmployee;
import it.unibo.ronf.shared.entities.TransferAction;

import org.springframework.stereotype.Repository;

import java.util.List;
import java.util.Date;

import javax.persistence.NoResultException;
import javax.persistence.TypedQuery;

@Repository("transferActionDAO")
public class TransferActionDAO extends JpaDAO<TransferAction> {

	public TransferAction findByEmployee(TransferEmployee te) {
		try {

			TypedQuery<TransferAction> query = em.createQuery("SELECT ta FROM TransferAction ta WHERE ta.employee = :te", entityClass);

			query.setParameter("te", te);

			return query.getSingleResult();
		} catch (NoResultException ex) {
			return null;
		}

	}

	public List<TransferAction> findByDate(Date transferDate) {

		TypedQuery<TransferAction> query = em.createQuery("SELECT ta FROM TransferAction ta WHERE ta.transferDate = :transferDate", entityClass);

		query.setParameter("transferDate", transferDate);

		List<TransferAction> transferActDate = query.getResultList();

		return transferActDate;

	}

	public List<TransferAction> findBySuccess(boolean success) {

		TypedQuery<TransferAction> query = em.createQuery("SELECT ta FROM TransferAction ta WHERE ta.successAction = :success", entityClass);

		query.setParameter("success", success);

		List<TransferAction> transferActSuc = query.getResultList();

		return transferActSuc;

	}

}
