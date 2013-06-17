package it.unibo.ronf.server.dao;

import static org.junit.Assert.assertEquals;
import static org.junit.Assert.assertNotNull;
import it.unibo.ronf.shared.entities.MaintenanceType;

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
public class MaintenanceTypeDAOTest {

	@Autowired
	private MaintenanceTypeDAO test;
	
	private String name = "NAME";

	@Before
	public void setUpBeforeClass() throws Exception {
		MaintenanceType entity = new MaintenanceType();
		String description = "DESCRIPTION";
		entity.setCost(0.0F);
		entity.setDescription(description);
		entity.setId(0);
		entity.setName(name);

		test.persist(entity);
	}

	@Test
	@Transactional(propagation = Propagation.REQUIRED, rollbackFor = Exception.class)
	public void testFindByName() {
		MaintenanceType result = test.findByName(name);
		assertNotNull(result);
		assertEquals(result.getName(), name);
	}
}
