package it.unibo.ronf.server.dao;

import static org.junit.Assert.assertNotNull;
import static org.junit.Assert.assertTrue;
import it.unibo.ronf.shared.entities.TransferEmployee;

import java.util.ArrayList;
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
public class TransferEmployeeDAOTest {

	@Autowired
	private TransferEmployeeDAO test = new TransferEmployeeDAO();
	private TransferEmployee entity = new TransferEmployee();
	private String name = "NAME";
	private String password = "PASSWORD";
	private String surname = "SURNAME";
	private String userName = "USERNAME";
	private List<TransferEmployee> result = new ArrayList<TransferEmployee>();

	@Before
	public void setUpBeforeClass() throws Exception {
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
	@Transactional(propagation = Propagation.REQUIRED, rollbackFor = Exception.class)
	public void testFindByBusy() {
		result = test.findByBusy(false);
		assertTrue(result.size() > 0);
		for (int index = 0; index < result.size(); index++) {
			assertTrue(result.get(index).isBusy() == false);
		}
	}

}
