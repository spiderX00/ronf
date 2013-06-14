package it.unibo.ronf.server.dao;

import static org.junit.Assert.*;
import it.unibo.ronf.shared.entities.Employee;

import org.junit.BeforeClass;
import org.junit.Test;

public class EmployeeDAOTest {

	static EmployeeDAO test = new EmployeeDAO();
	static Employee entity = new Employee();
	static int age = 15;
	static String name = "NAME";
	static String password = "PASSWD";
	static String surname = "SURNAME";
	static String userName = "USERNAME";

	@BeforeClass
	public static void setUpBeforeClass() throws Exception {
		entity.setAge(age);
		entity.setId(0);
		entity.setName(name);
		entity.setPassword(password);
		entity.setSurname(surname);
		entity.setUserName(userName);

		test.persist(entity);
	}

	@Test
	public void testCheckLogin() {
		assertTrue(test.checkLogin(userName, password));
	}

	@Test
	public void testFindByUserName() {
		Employee result = test.findByUserName(userName);
		assertNotNull(result);
		assertEquals(result.getUserName(), userName);
	}

}
