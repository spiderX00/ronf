package it.unibo.ronf.server.dao;

import static org.junit.Assert.assertEquals;
import static org.junit.Assert.assertTrue;
import it.unibo.ronf.shared.entities.Agency;
import it.unibo.ronf.shared.entities.Car;
import it.unibo.ronf.shared.entities.CarType;
import it.unibo.ronf.shared.entities.TransferAction;
import it.unibo.ronf.shared.entities.TransferEmployee;

import java.util.ArrayList;
import java.util.Date;
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
public class TransferActionDAOTest {

	@Autowired
	private TransferActionDAO test;
	
	@Autowired
	private TransferEmployeeDAO transferemployeedao;
	
	@Autowired
	private CarDAO cardao;
	
	@Autowired
	private CarTypeDAO cartypedao;
	
	@Autowired
	private AgencyDAO agencydao;

	private Date transferDate = new Date();
	
	private TransferAction entity = new TransferAction();
	private TransferEmployee employee = new TransferEmployee();
	private Car requiredCar = new Car();
	private CarType type = new CarType();

	@Before
	public void setUpBeforeClass() throws Exception {
		
		final String model = "MODEL";
		final String password = "PASSWORD";
		final String surname = "SURNAME";
		final String userName = "USERNAME";
		final String address = "ADDRESS";
		final String code = "CODE";
		final String ipAddress = "IPADDRESS";
		final String name = "NAME";
		final String gasolineType = "GASOLINETYPE";
		final Agency agency = new Agency();
		final String plate = "PLATE";

		agency.setAddress(address);
		agency.setName(name);
		agency.setCode(code);
		agency.setId(0);
		agency.setIpAddress(ipAddress);
		agency.setPort(5555);

		agencydao.persist(agency);

		employee.setAge(15);
		employee.setBusy(false);
		employee.setId(0);
		employee.setName(name);
		employee.setPassword(password);
		employee.setSurname(surname);
		employee.setPassword(password);
		employee.setUserName(userName);

		transferemployeedao.persist(employee);

		type.setId(0);
		type.setDailyCost(0);
		type.setType("A1");

		cartypedao.persist(type);

		requiredCar.setCurrentAgency(agency);
		requiredCar.setGasolineType(gasolineType);
		requiredCar.setId(0);
		requiredCar.setModel(model);
		requiredCar.setPlate(plate);
		requiredCar.setSeatsNumber(0);
		requiredCar.setType(type);

		cardao.persist(requiredCar);

		entity.setRequiredCar(requiredCar);
		entity.setSuccessAction(false);
		entity.setTransferDate(transferDate);

		test.persist(entity);
	}

	@Test
	@Transactional(propagation = Propagation.REQUIRED, rollbackFor = Exception.class)
	public void testFindByDate() {
		List<TransferAction> resultList = new ArrayList<TransferAction>();
		resultList = test.findByDate(transferDate);
		assertTrue(resultList.size() > 0);
		for (int index = 0; index < resultList.size(); index++) {
			assertEquals(resultList.get(index).getTransferDate(), transferDate);
		}
	}

	@Test
	@Transactional(propagation = Propagation.REQUIRED, rollbackFor = Exception.class)
	public void testFindBySuccess() {
		List<TransferAction> resultList = new ArrayList<TransferAction>();
		resultList = test.findBySuccess(false);
		assertTrue(resultList.size() > 0);
		for (int index = 0; index < resultList.size(); index++) {
			assertEquals(resultList.get(index).isSuccessAction(), false);
		}
	}
}
