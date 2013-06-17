package it.unibo.ronf.server.dao;

import static org.junit.Assert.*;
import it.unibo.ronf.shared.entities.MaintenanceEmployee;

import java.util.List;

import org.junit.Before;
import org.junit.Test;
import org.junit.runner.RunWith;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.test.context.ContextConfiguration;
import org.springframework.test.context.junit4.SpringJUnit4ClassRunner;
import org.springframework.transaction.annotation.Propagation;
import org.springframework.transaction.annotation.Transactional;

@ContextConfiguration(locations = { "/META-INF/applicationContext.xml" })
@RunWith(SpringJUnit4ClassRunner.class)
public class MaintenanceEmployeeDAOTest {

	@Autowired
	private MaintenanceEmployeeDAO test;
	private MaintenanceEmployee entity = new MaintenanceEmployee();

	@Before
	public void setUpBefore() throws Exception {
		int age = 15;
		String name = "NAME";
		String password = "PASSWORD";
		String surname = "SURNAME";
		String userName = "USERNAME";
		
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
	@Transactional(propagation = Propagation.REQUIRED, rollbackFor = Exception.class)
	public void testFindByBusy() {
		List<MaintenanceEmployee> result = test.findByBusy(false);
		assertTrue(result.size() > 0);
		for (int index = 0; index < result.size(); index++) {
			assertEquals(result.get(index).isBusy(), false);
		}
	}
}
