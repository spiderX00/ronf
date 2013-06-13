package it.unibo.ronf.server.dao;

import it.unibo.ronf.shared.entities.TransferEmployee;
import static org.junit.Assert.*;

import java.util.*;

import org.junit.BeforeClass;
import org.junit.Test;

public class TransferEmployeeDAOTest {

	static TransferEmployeeDAO test = new TransferEmployeeDAO();
	static TransferEmployee entity = new TransferEmployee();
	static String name = "NAME";
	static String password = "PASSWORD";
	static String surname = "SURNAME";
	static String userName = "USERNAME";
	List<TransferEmployee> result = new ArrayList<TransferEmployee>();

	@BeforeClass
	public static void setUpBeforeClass() throws Exception {
		entity.setAge(15);
		entity.setId(0);
		entity.setName(name);
		entity.setBusy(false);
		entity.setPassword(password);
		entity.setSurname(surname);
		entity.setUserName(userName);

		test.persist(entity);
	}

	@Test
	public void testFindByBusy() {
		result = test.findByBusy(false);
		assertNotNull(result);
		for (int index = 0; index < result.size(); index++) {
			assertTrue(result.get(index).isBusy() == false);
		}
	}

}
