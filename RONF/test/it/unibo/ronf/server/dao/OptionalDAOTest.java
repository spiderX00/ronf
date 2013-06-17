package it.unibo.ronf.server.dao;

import static org.junit.Assert.assertEquals;
import static org.junit.Assert.assertNotNull;
import it.unibo.ronf.shared.entities.Optional;

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
public class OptionalDAOTest {

	@Autowired
	private OptionalDAO test;

	private String name = "NAME";

	@Before
	public void setUpBefore() throws Exception {
		Optional entity = new Optional();
		String description = "DESCRIPTION";

		entity.setCost(10.0F);
		entity.setDescription(description);
		entity.setName(name);
		test.persist(entity);

	}

	@Test
	@Transactional(propagation = Propagation.REQUIRED, rollbackFor = Exception.class)
	public void testFindByName() {

		Optional result = test.findByName(name);
		assertNotNull(result);
		assertEquals(result.getName(), name);
	}

}
