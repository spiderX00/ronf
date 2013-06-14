package it.unibo.ronf.server.dao;

import it.unibo.ronf.shared.entities.User;

import java.util.List;

import javax.persistence.TypedQuery;

import org.springframework.stereotype.Repository;

@Repository("userDAO")
public class UserDAO extends JpaDAO<User> {

	public List<User> findByNameAndSurname(String name, String surname) {
		TypedQuery<User> query = em.createQuery("SELECT u FROM User u WHERE u.name = :name AND u.surname = :surname", entityClass);

		query.setParameter("name", name);
		query.setParameter("surname", surname);

		return query.getResultList();
	}

}
