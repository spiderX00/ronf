package it.unibo.ronf.server.dao;

import static org.junit.Assert.assertEquals;
import static org.junit.Assert.assertNotNull;
import it.unibo.ronf.shared.entities.User;

import java.util.ArrayList;
import java.util.List;

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
public class UserDAOTest {
	
	@Autowired
	private UserDAO test;

	private List<User> resultList = new ArrayList<User>();
	private User entity = new User();
	private  int age = 15;
	private int id = 1;
	private String name = "NAME";
	private String surname = "SURNAME";

	@Before
	public void setUpBefore() throws Exception {
		entity.setAge(age);
		entity.setId(id);
		entity.setName(name);
		entity.setSurname(surname);
		test.persist(entity);
	}

	@Test
	@Transactional(propagation = Propagation.REQUIRED, rollbackFor = Exception.class)
	public void testFindByNameAndSurname() {
		resultList = test.findByNameAndSurname(name, surname);
		assertNotNull(resultList);
		for (int index = 0; index < resultList.size(); index++) {
			assertEquals(resultList.get(index).getName(), name);
			assertEquals(resultList.get(index).getSurname(), surname);
		}
	}
}
