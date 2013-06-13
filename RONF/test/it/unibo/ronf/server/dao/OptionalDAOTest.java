package it.unibo.ronf.server.dao;

import static org.junit.Assert.*;

import org.junit.Test;
import org.junit.BeforeClass;

import it.unibo.ronf.shared.entities.Optional;

public class OptionalDAOTest {

	static Optional entity = new Optional();
	static OptionalDAO test = new OptionalDAO();
	static String constraints = "CONSTRAINTS";
	static String description = "DESCRIPTION";
	static String name = "NAME";

	@BeforeClass
	public static void setUpBeforeClass() throws Exception {
		entity.setCost(10.0F);
		entity.setDescription(description);
		entity.setId(0);
		entity.setName(name);
		test.persist(entity);
	}

	@Test
	public void testFindByName() {
		Optional result = test.findByName(name);
		assertNotNull(result);
		assertEquals(result.getName(), name);
	}

}
