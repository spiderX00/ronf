package it.unibo.ronf.server.dao;

import static org.junit.Assert.*;
import it.unibo.ronf.shared.entities.Customer;

import org.junit.BeforeClass;
import org.junit.Test;
import org.springframework.beans.factory.annotation.Autowired;

public class CustomerDAOTest {

	@Autowired
	static CustomerDAO test = new CustomerDAO();
	static Customer entity = new Customer();
	static int age = 15;
	static String docNumber = "NUMBER";
	static String fiscalCode = "FISCALCODE";
	static long id = 1;
	static String name = "NAME";
	static String surname = "SURNAME";

	@BeforeClass
	public static void setUpBeforeClass() throws Exception {
		entity.setAge(age);
		entity.setDocNumber(docNumber);
		entity.setFiscalCode(fiscalCode);
		entity.setId(id);
		entity.setName(name);
		entity.setSurname(surname);

		test.persist(entity);
	}

	@Test
	public void testFindByFiscalCode() {
		Customer result = test.findByFiscalCode(fiscalCode);
		assertNotNull(result);
		assertEquals(result.getFiscalCode(), fiscalCode);
	}

	@Test
	public void testFindByDocNumber() {
		Customer result = test.findByDocNumber(docNumber);
		assertNotNull(result);
		assertEquals(result.getDocNumber(), docNumber);
	}

}
