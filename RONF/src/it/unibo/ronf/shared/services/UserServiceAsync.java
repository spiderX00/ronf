package it.unibo.ronf.shared.services;

import it.unibo.ronf.shared.entities.User;

import java.util.List;

import com.google.gwt.user.client.rpc.AsyncCallback;

public interface UserServiceAsync {

	public void findAll(AsyncCallback<List<User>> callback);

	public void findByNameAndSurname(String name, String surname, AsyncCallback<List<User>> callback);

	public void insertUser(User u, AsyncCallback<Void> callback);

	public void removeById(long id, AsyncCallback<Void> callback);

}
