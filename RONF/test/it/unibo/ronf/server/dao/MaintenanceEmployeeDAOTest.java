package it.unibo.ronf.server.dao;

import static org.junit.Assert.*;

import java.util.List;

import it.unibo.ronf.shared.entities.MaintenanceEmployee;

import org.junit.BeforeClass;
import org.junit.Test;

public class MaintenanceEmployeeDAOTest {

	static MaintenanceEmployeeDAO test = new MaintenanceEmployeeDAO();
	static MaintenanceEmployee entity = new MaintenanceEmployee();
	static int age = 15;
	static String name = "NAME";
	static String password = "PASSWORD";
	static String surname = "SURNAME";
	static String userName = "USERNAME";

	@BeforeClass
	public static void setUpBeforeClass() throws Exception {
		entity.setAge(age);
		entity.setBusy(false);
		entity.setId(0);
		entity.setName(name);
		entity.setPassword(password);
		entity.setSurname(surname);
		entity.setUserName(userName);

		test.persist(entity);
	}

	@Test
	public void testFindByBusy() {
		List<MaintenanceEmployee> result = test.findByBusy(false);
		assertFalse(result.isEmpty());
		for (int index = 0; index < result.size(); index++) {
			assertEquals(result.get(index).isBusy(), false);
		}
	}

}
