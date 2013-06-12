package it.unibo.ronf.server.dao;

import it.unibo.ronf.shared.entities.Employee;

import java.util.List;

import javax.persistence.NoResultException;
import javax.persistence.TypedQuery;

import org.springframework.stereotype.Repository;

@Repository("employeeDAO")
public class EmployeeDAO extends JpaDAO<Employee> {

	public boolean checkLogin(String userName, String password) {

		TypedQuery<Employee> query = em.createQuery("SELECT e FROM Employee e WHERE e.userName = :userName AND e.password = :password", entityClass);

		query.setParameter("userName", userName);
		query.setParameter("password", password);

		List<Employee> employeeList = query.getResultList();

		return !(employeeList.isEmpty());

	}

	public Employee findByUserName(String userName) {
		try {

			TypedQuery<Employee> query = em.createQuery("SELECT e FROM Employee e WHERE e.userName = :userName", entityClass);

			query.setParameter("userName", userName);

			return query.getSingleResult();
		} catch (NoResultException ex) {
			return null;
		}

	}

}
