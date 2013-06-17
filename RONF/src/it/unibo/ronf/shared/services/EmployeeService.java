package it.unibo.ronf.shared.services;

import it.unibo.ronf.shared.entities.Employee;

import java.util.List;

import com.google.gwt.user.client.rpc.RemoteService;
import com.google.gwt.user.client.rpc.RemoteServiceRelativePath;

@RemoteServiceRelativePath("ronfServices/employeeService")
public interface EmployeeService extends RemoteService {

	public boolean checkLogin(String userName, String password);

	public void createEmployee(Employee employee);

	public List<Employee> findAll();

	public Employee findByUserName(String userName);

	public void remove(Employee employee);

	public void removeById(long id);

}
