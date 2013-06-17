package it.unibo.ronf.server.dao;

import static org.junit.Assert.assertEquals;
import static org.junit.Assert.assertNotNull;
import static org.junit.Assert.assertTrue;
import it.unibo.ronf.shared.entities.Employee;

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
public class EmployeeDAOTest {

	@Autowired
	private EmployeeDAO test;
	
	String password = "PASSWD";
	String userName = "USERNAME";
	private Employee entity = new Employee();

	@Before
	public void setUpBefore() throws Exception {
		int age = 15;
		String name = "NAME";
		String surname = "SURNAME";
		
		entity.setAge(age);
		entity.setId(0);
		entity.setName(name);
		entity.setPassword(password);
		entity.setSurname(surname);
		entity.setUserName(userName);

		test.persist(entity);
	}

	@Test
	@Transactional(propagation = Propagation.REQUIRED, rollbackFor = Exception.class)
	public void testCheckLogin() {
		List <Employee> result = test.findAll();
		assertTrue(result.size() > 0);
		assertTrue(test.checkLogin(userName, password));
	}

	@Test
	@Transactional(propagation = Propagation.REQUIRED, rollbackFor = Exception.class)
	public void testFindByUserName() {
		Employee result = test.findByUserName(userName);
		assertNotNull(result);
		assertEquals(result.getUserName(), userName);
	}
}