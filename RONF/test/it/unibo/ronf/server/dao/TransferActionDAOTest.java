package it.unibo.ronf.server.dao;

import static org.junit.Assert.assertEquals;
import static org.junit.Assert.assertNotNull;
import it.unibo.ronf.shared.entities.Agency;
import it.unibo.ronf.shared.entities.Car;
import it.unibo.ronf.shared.entities.CarType;
import it.unibo.ronf.shared.entities.TransferAction;
import it.unibo.ronf.shared.entities.TransferEmployee;

import java.util.ArrayList;
import java.util.Date;
import java.util.List;

import org.junit.BeforeClass;
import org.junit.Test;
import org.springframework.transaction.annotation.Propagation;
import org.springframework.transaction.annotation.Transactional;

public class TransferActionDAOTest {

	private static TransferActionDAO test = new TransferActionDAO();
	private static TransferEmployeeDAO transferemployeedao = new TransferEmployeeDAO();
	private static CarDAO cardao = new CarDAO();
	private static CarTypeDAO cartypedao = new CarTypeDAO();
	private static AgencyDAO agencydao = new AgencyDAO();

	private static TransferAction entity = new TransferAction();
	private static TransferEmployee employee = new TransferEmployee();
	private static Car requiredCar = new Car();
	private static CarType type = new CarType();
	private static Date transferDate = new Date();
	private static String model = "MODEL";
	private static String password = "PASSWORD";
	private static String surname = "SURNAME";
	private static String userName = "USERNAME";
	private static String address = "ADDRESS";
	private static String code = "CODE";
	private static String ipAddress = "IPADDRESS";
	private static String name = "NAME";
	private static String gasolineType = "GASOLINETYPE";
	private static Agency agency = new Agency();
	private static String plate = "PLATE";

	@BeforeClass
	public static void setUpBeforeClass() throws Exception {

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
		assertNotNull(resultList);
		for (int index = 0; index < resultList.size(); index++) {
			assertEquals(resultList.get(index).getTransferDate(), transferDate);
		}
	}

	@Test
	@Transactional(propagation = Propagation.REQUIRED, rollbackFor = Exception.class)
	public void testFindBySuccess() {
		List<TransferAction> resultList = new ArrayList<TransferAction>();
		resultList = test.findBySuccess(false);
		assertNotNull(resultList);
		for (int index = 0; index < resultList.size(); index++) {
			assertEquals(resultList.get(index).isSuccessAction(), false);
		}
	}

}
