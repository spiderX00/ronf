package it.unibo.ronf.server.services;

import java.util.List;

import it.unibo.ronf.server.dao.EmployeeDAO;
import it.unibo.ronf.shared.entities.Employee;
import it.unibo.ronf.shared.services.EmployeeService;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Propagation;
import org.springframework.transaction.annotation.Transactional;

@Service("employeeService")
public class EmployeeServiceImpl implements EmployeeService {

	@Autowired
	private EmployeeDAO employeeDAO;

	@Override
	public boolean checkLogin(String userName, String password) {
		return employeeDAO.checkLogin(userName, password);
	}

	@Override
	@Transactional(propagation = Propagation.REQUIRED, rollbackFor = Exception.class)
	public void createEmployee(Employee employee) {
		employeeDAO.persist(employee);
	}

	@Override
	public List<Employee> findAll() {
		return employeeDAO.findAll();
	}

	@Override
	public void remove(Employee employee) {
		employeeDAO.remove(employee);
	}

	@Override
	public Employee findByUserName(String userName) {
		return employeeDAO.findByUserName(userName);
	}

	@Override
	@Transactional(propagation = Propagation.REQUIRED, rollbackFor = Exception.class)
	public void removeById(long id) {
		employeeDAO.remove(employeeDAO.findById(id));

	}

}
