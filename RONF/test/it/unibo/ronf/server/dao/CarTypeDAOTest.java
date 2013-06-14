package it.unibo.ronf.server.dao;

import it.unibo.ronf.shared.entities.CarType;
import static org.junit.Assert.*;

import org.junit.BeforeClass;
import org.junit.Test;

public class CarTypeDAOTest {

	static CarType entity = new CarType();
	static CarTypeDAO test = new CarTypeDAO();

	@BeforeClass
	public static void setUpBeforeClass() throws Exception {
		entity.setId(0);
		entity.setType("A1");
		entity.setDailyCost(0.0F);

		test.persist(entity);
	}

	@Test
	public void testFindByType() {
		CarType result = test.findByType("A1");
		assertNotNull(result);
		assertEquals(result.getType(), "A1");
	}

}
