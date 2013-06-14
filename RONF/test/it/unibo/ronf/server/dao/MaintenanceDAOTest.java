package it.unibo.ronf.server.dao;

import static org.junit.Assert.*;

import it.unibo.ronf.shared.entities.*;

import java.util.*;

import org.junit.BeforeClass;
import org.junit.Test;

public class MaintenanceDAOTest {

	static MaintenanceDAO test = new MaintenanceDAO();
	static Maintenance entity = new Maintenance();
	static Car car = new Car();
	static String model = "AAAAAAAA";
	static String gasolineType = "ABC";
	static String plate = "PLATE";
	static String address = "ADDRESS";
	static String code = "CODE";
	static Date date = new Date();
	static MaintenanceEmployee maintenanceEmployee = new MaintenanceEmployee();
	static List<MaintenanceType> maintenances = new ArrayList<MaintenanceType>();
	static AgencyDAO agencydao = new AgencyDAO();
	static Agency agency = new Agency();
	static CarDAO cardao = new CarDAO();
	static CarType type = new CarType();
	static CarTypeDAO cartypedao = new CarTypeDAO();
	static MaintenanceEmployeeDAO maintenanceEmployeedao = new MaintenanceEmployeeDAO();

	@BeforeClass
	public static void setUpBeforeClass() throws Exception {

		agency.setAddress(address);
		agency.setCode(code);
		agency.setId(0);
		agency.setIpAddress("ipAddress");
		agency.setName("name");

		agencydao.persist(agency);

		type.setId(0);
		type.setType("A1");
		type.setDailyCost(0.0F);

		cartypedao.persist(type);

		car.setCurrentAgency(agency);
		car.setGasolineType(gasolineType);
		car.setId(0);
		car.setModel(model);
		car.setPlate(plate);
		car.setSeatsNumber(0);
		car.setType(type);

		cardao.persist(car);

		maintenanceEmployee.setAge(15);
		maintenanceEmployee.setBusy(false);
		maintenanceEmployee.setId(0);
		maintenanceEmployee.setName("name");
		maintenanceEmployee.setPassword("password");
		maintenanceEmployee.setSurname("surname");
		maintenanceEmployee.setUserName("userName");

		maintenanceEmployeedao.persist(maintenanceEmployee);

		entity.setCar(car);
		entity.setDate(date);
		entity.setId(0);
		entity.setMaintenanceEmployee(maintenanceEmployee);
		entity.setMaintenances(maintenances);

		test.persist(entity);
	}

	@Test
	public void testFindByCar() {
		Maintenance result = test.findByCar(car);
		assertNotNull(result);
		assertEquals(result.getCar(), car);
	}

	@Test
	public void testFindByDate() {
		List<Maintenance> result = test.findByMaintenanceEmployee(maintenanceEmployee);
		assertFalse(result.isEmpty());
		for (int index = 0; index < result.size(); index++) {
			assertEquals(result.get(index).getDate(), date);
		}
	}

	@Test
	public void testFindByMaintenanceEmployee() {
		List<Maintenance> result = test.findByMaintenanceEmployee(maintenanceEmployee);
		assertFalse(result.isEmpty());
		for (int index = 0; index < result.size(); index++) {
			assertEquals(result.get(index).getMaintenanceEmployee(), maintenanceEmployee);
		}
	}

}
