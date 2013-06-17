package it.unibo.ronf.shared.services;

import it.unibo.ronf.shared.entities.Employee;

import java.util.List;

import com.google.gwt.user.client.rpc.AsyncCallback;

public interface EmployeeServiceAsync {

	public void checkLogin(String userName, String password, AsyncCallback<Boolean> callback);

	public void createEmployee(Employee employee, AsyncCallback<Void> callback);

	public void findAll(AsyncCallback<List<Employee>> callback);

	public void findByUserName(String userName, AsyncCallback<Employee> callback);

	public void remove(Employee employee, AsyncCallback<Void> callback);

	public void removeById(long id, AsyncCallback<Void> callback);

}
