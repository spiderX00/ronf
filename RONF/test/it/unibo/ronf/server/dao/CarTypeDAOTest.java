package it.unibo.ronf.server.dao;

import static org.junit.Assert.assertEquals;
import static org.junit.Assert.assertNotNull;
import it.unibo.ronf.shared.entities.CarType;

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
public class CarTypeDAOTest {

	@Autowired
	private CarTypeDAO test;

	private CarType entity = new CarType();

	@Before
	@Transactional(propagation = Propagation.REQUIRED, rollbackFor = Exception.class)
	public void setUpBefore() throws Exception {
		entity.setType("A1");
		entity.setDailyCost(0.0F);
		test.persist(entity);
	}

	@Test
	@Transactional(propagation = Propagation.REQUIRED, rollbackFor = Exception.class)
	public void testFindByType() {
		CarType result = test.findByType("A1");
		assertNotNull(result);
		assertEquals(result.getType(), "A1");
	}
}