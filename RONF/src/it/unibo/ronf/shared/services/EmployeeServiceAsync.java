package it.unibo.ronf.shared.services;

import java.util.List;

import it.unibo.ronf.shared.entities.Employee;

import com.google.gwt.user.client.rpc.AsyncCallback;

public interface EmployeeServiceAsync {

	public void checkLogin(String userName, String password,
			AsyncCallback<Boolean> callback);

	public void createEmployee(Employee employee, AsyncCallback<Void> callback);

	public void findAll(AsyncCallback<List<Employee>> callback);

	public void remove(Employee employee, AsyncCallback<Void> callback);

	void findByUserName(String userName, AsyncCallback<Employee> callback);

	void removeById(long id, AsyncCallback<Void> callback);

}
