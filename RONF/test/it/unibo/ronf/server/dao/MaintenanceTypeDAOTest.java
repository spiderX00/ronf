package it.unibo.ronf.server.dao;

import it.unibo.ronf.shared.entities.MaintenanceType;

import static org.junit.Assert.*;

import org.junit.BeforeClass;
import org.junit.Test;

public class MaintenanceTypeDAOTest {

	static MaintenanceTypeDAO test = new MaintenanceTypeDAO();
	static MaintenanceType entity = new MaintenanceType();
	static String description = "DESCRIPTION";
	static String name = "NAME";

	@BeforeClass
	public static void setUpBeforeClass() throws Exception {
		entity.setCost(0.0F);
		entity.setDescription(description);
		entity.setId(0);
		entity.setName(name);

		test.persist(entity);
	}

	@Test
	public void testFindByName() {
		MaintenanceType result = test.findByName(name);
		assertNotNull(result);
		assertEquals(result.getName(), name);
	}

}
