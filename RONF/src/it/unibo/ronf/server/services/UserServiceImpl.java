package it.unibo.ronf.server.services;

import it.unibo.ronf.server.dao.UserDAO;
import it.unibo.ronf.shared.entities.User;
import it.unibo.ronf.shared.services.UserService;

import java.util.List;

import org.apache.log4j.Logger;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Propagation;
import org.springframework.transaction.annotation.Transactional;

@Service("userService")
public class UserServiceImpl implements UserService {

	private static final Logger logger = Logger.getLogger(UserServiceImpl.class);

	@Autowired
	private UserDAO userDAO;

	@Override
	public List<User> findByNameAndSurname(String name, String surname) {
		return userDAO.findByNameAndSurname(name, surname);
	}

	@Override
	@Transactional(propagation = Propagation.REQUIRED, rollbackFor = Exception.class)
	public void insertUser(User u) {
		userDAO.persist(u);
	}

	@Override
	public List<User> findAll() {
		return userDAO.findAll();
	}

	@Override
	@Transactional(propagation = Propagation.REQUIRED, rollbackFor = Exception.class)
	public void removeById(long id) {
		userDAO.remove(userDAO.findById(id));

	}

}
