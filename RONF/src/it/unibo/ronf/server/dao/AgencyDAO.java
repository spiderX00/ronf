package it.unibo.ronf.server.dao;

import it.unibo.ronf.shared.entities.Agency;

import java.util.ArrayList;
import java.util.List;

import javax.persistence.NoResultException;
import javax.persistence.TypedQuery;

import org.springframework.stereotype.Repository;

@Repository("agencyDAO")
public class AgencyDAO extends JpaDAO<Agency> {
	
	/*private static List<Agency> fakeAgency;
	
	static {
		fakeAgency  = new ArrayList<>();
		Agency a1 = new Agency();
		a1.setAddress("Casa di Mone");
		a1.setCode("a1");
		a1.setIpAddress("localhost");
		a1.setPort(8080);
		a1.setName("Agenzmone");
		Agency a2 = new Agency();
		a2.setAddress("Casa di valerio");
		a2.setCode("a2");
		a2.setIpAddress("localhost");
		a2.setPort(8081);
		a2.setName("Agenzerio");
		
		fakeAgency.add(a1);
		fakeAgency.add(a2);
	}
	
	public List<Agency> findAll() {
		return fakeAgency;
	}*/

	public Agency findByCode(String code) {
		try {
			TypedQuery<Agency> query = em.createQuery(
					"SELECT a FROM Agency a WHERE a.code = :code", entityClass);

			query.setParameter("code", code);

			return query.getSingleResult();
		} catch (NoResultException ex) {
			return null;
		}

	}

	public Agency findByName(String name) {

		try {

			TypedQuery<Agency> query = em.createQuery(
					"SELECT a FROM Agency a WHERE a.name = :name", entityClass);

			query.setParameter("name", name);

			return query.getSingleResult();

		} catch (NoResultException ex) {
			return null;
		}

	}

}
