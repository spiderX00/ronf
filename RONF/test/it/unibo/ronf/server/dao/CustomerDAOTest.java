package it.unibo.ronf.server.dao;

import static org.junit.Assert.assertEquals;
import static org.junit.Assert.assertNotNull;
import it.unibo.ronf.shared.entities.Customer;

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
public class CustomerDAOTest {

	@Autowired
	private CustomerDAO test = new CustomerDAO();
	private Customer entity = new Customer();
	private int age = 15;
	private String docNumber = "NUMBER";
	private String fiscalCode = "FISCALCODE";
	private long id = 1;
	private String name = "NAME";
	private String surname = "SURNAME";

	@Before
	public void setUpBefore() throws Exception {
		entity.setAge(age);
		entity.setDocNumber(docNumber);
		entity.setFiscalCode(fiscalCode);
		entity.setId(id);
		entity.setName(name);
		entity.setSurname(surname);

		test.persist(entity);
	}

	@Test
	@Transactional(propagation = Propagation.REQUIRED, rollbackFor = Exception.class)
	public void testFindByFiscalCode() {
		Customer result = test.findByFiscalCode(fiscalCode);
		assertNotNull(result);
		assertEquals(result.getFiscalCode(), fiscalCode);
	}

	@Test
	@Transactional(propagation = Propagation.REQUIRED, rollbackFor = Exception.class)
	public void testFindByDocNumber() {
		Customer result = test.findByDocNumber(docNumber);
		assertNotNull(result);
		assertEquals(result.getDocNumber(), docNumber);
	}
}