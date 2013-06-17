package it.unibo.ronf.server.dao;

import static org.junit.Assert.assertEquals;
import static org.junit.Assert.assertNotNull;
import it.unibo.ronf.shared.entities.Agency;

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
public class AgencyDAOTest {

	@Autowired
	private AgencyDAO test = new AgencyDAO();

	private String code = "CODE";
	private String name = "NAME";

	@Before
	public void setUpBeforeClass() throws Exception {
		Agency entity = new Agency();
		String address = "ADDRESS";
		String ipAddress = "ipAddress";

		entity.setAddress(address);
		entity.setCode(code);
		entity.setId(0);
		entity.setIpAddress(ipAddress);
		entity.setName(name);
		entity.setPort(5555);

		test.persist(entity);
	}

	@Test
	@Transactional(propagation = Propagation.REQUIRED, rollbackFor = Exception.class)
	public void testFindByCode() {
		Agency result = test.findByCode(code);
		assertNotNull(result);
		assertEquals(result.getCode(), code);
	}

	@Test
	@Transactional(propagation = Propagation.REQUIRED, rollbackFor = Exception.class)
	public void testFindByName() {
		Agency result = test.findByName(name);
		assertNotNull(result);
		assertEquals(result.getName(), name);
	}

	@Test
	@Transactional(propagation = Propagation.REQUIRED, rollbackFor = Exception.class)
	public void testGetCurrentAgency() {

	}

	@Test
	public void testGetOthers() {

	}

	@Test
	public void testSetCurrentAgency() {

	}
}
