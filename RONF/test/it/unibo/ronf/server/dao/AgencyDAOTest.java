package it.unibo.ronf.server.dao;

import static org.junit.Assert.*;

import it.unibo.ronf.shared.entities.*;

import org.junit.BeforeClass;
import org.junit.Test;

public class AgencyDAOTest {

	static Agency entity = new Agency();
	static AgencyDAO test = new AgencyDAO();
	static String address = "ADDRESS";
	static String code = "CODE";
	static String ipAddress = "ipAddress";
	static String name = "NAME";

	@BeforeClass
	public static void setUpBeforeClass() throws Exception {
		entity.setAddress(address);
		entity.setCode(code);
		entity.setId(0);
		entity.setIpAddress(ipAddress);
		entity.setName(name);
		entity.setPort(5555);
		test.persist(entity);
	}

	@Test
	public void testFindByCode() {
		Agency result = test.findByCode(code);
		assertNotNull(result);
		assertEquals(result.getCode(), code);
	}

	@Test
	public void testFindByName() {
		Agency result = test.findByName(name);
		assertNotNull(result);
		assertEquals(result.getName(), name);
	}

	@Test
	public void testGetCurrentAgency() {

	}

	@Test
	public void testSetCurrentAgency() {

	}

	@Test
	public void testGetOthers() {

	}

}
