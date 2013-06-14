package it.unibo.ronf.server.dao;

import static org.junit.Assert.assertEquals;
import static org.junit.Assert.assertNotNull;
import it.unibo.ronf.shared.entities.User;

import java.util.ArrayList;
import java.util.List;

import org.junit.BeforeClass;
import org.junit.Test;
import org.junit.runner.RunWith;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.test.context.ContextConfiguration;
import org.springframework.test.context.junit4.SpringJUnit4ClassRunner;

@RunWith(SpringJUnit4ClassRunner.class)
@ContextConfiguration(locations={"/applicationContext.xml"} )
public class UserDAOTest {

	@Autowired
	static private UserDAO test = new UserDAO();

	static List<User> resultList = new ArrayList<User>();
	static User entity = new User();
	static int age = 15;
	static int id = 1;
	static String name = "NAME";
	static String surname = "SURNAME";

	@BeforeClass
	public static void setUpBeforeClass() throws Exception {
		entity.setAge(age);
		entity.setId(id);
		entity.setName(name);
		entity.setSurname(surname);

		test.persist(entity);
	}

	@Test
	public void testFindByNameAndSurname() {
		resultList = test.findByNameAndSurname(name, surname);
		assertNotNull(resultList);
		for (int index = 0; index < resultList.size(); index++) {
			assertEquals(resultList.get(index).getName(), name);
			assertEquals(resultList.get(index).getSurname(), surname);
		}
	}
}
