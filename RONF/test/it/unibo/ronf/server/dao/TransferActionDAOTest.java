package it.unibo.ronf.server.dao;

import static org.junit.Assert.*;

import it.unibo.ronf.shared.entities.*;

import java.util.*;

import org.junit.BeforeClass;
import org.junit.Test;

public class TransferActionDAOTest {

	static TransferActionDAO test = new TransferActionDAO();
	static TransferEmployeeDAO transferemployeedao = new TransferEmployeeDAO();
	static CarDAO cardao = new CarDAO();
	static CarTypeDAO cartypedao = new CarTypeDAO();
	static AgencyDAO agencydao = new AgencyDAO();

	static TransferAction entity = new TransferAction();
	static TransferEmployee employee = new TransferEmployee();
	static Car requiredCar = new Car();
	static CarType type = new CarType();
	static Date transferDate = new Date();
	static String model = "MODEL";
	static String password = "PASSWORD";
	static String surname = "SURNAME";
	static String userName = "USERNAME";
	static String address = "ADDRESS";
	static String code = "CODE";
	static String ipAddress = "IPADDRESS";
	static String name = "NAME";
	static String gasolineType = "GASOLINETYPE";
	static Agency agency = new Agency();
	static String plate = "PLATE";
	static List<TransferAction> resultList = new ArrayList<TransferAction>();

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
	public void testFindByDate() {
		resultList.clear();
		resultList = test.findByDate(transferDate);
		assertNotNull(resultList);
		for (int index = 0; index < resultList.size(); index++) {
			assertEquals(resultList.get(index).getTransferDate(), transferDate);
		}
	}

	@Test
	public void testFindBySuccess() {
		resultList.clear();
		resultList = test.findBySuccess(false);
		assertNotNull(resultList);
		for (int index = 0; index < resultList.size(); index++) {
			assertEquals(resultList.get(index).isSuccessAction(), false);
		}
	}

}
