package it.unibo.ronf.shared.services;

import it.unibo.ronf.shared.entities.User;

import java.util.List;

import com.google.gwt.user.client.rpc.RemoteService;
import com.google.gwt.user.client.rpc.RemoteServiceRelativePath;

@RemoteServiceRelativePath("ronfServices/userService")
public interface UserService extends RemoteService {

	public List<User> findAll();

	public List<User> findByNameAndSurname(String name, String surname);

	public void insertUser(User u);

	public void removeById(long id);

}
