package it.unibo.ronf.server.dao;

import static org.junit.Assert.*;
import it.unibo.ronf.shared.entities.Agency;
import it.unibo.ronf.shared.entities.Car;
import it.unibo.ronf.shared.entities.CarType;
import it.unibo.ronf.shared.entities.Maintenance;
import it.unibo.ronf.shared.entities.MaintenanceEmployee;
import it.unibo.ronf.shared.entities.MaintenanceType;

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
public class MaintenanceDAOTest {

	@Autowired
	private MaintenanceDAO test;
	private Maintenance entity = new Maintenance();
	private Car car = new Car();
	private String model = "AAAAAAAA";
	private String gasolineType = "ABC";
	private String plate = "PLATE";
	private String address = "ADDRESS";
	private String code = "CODE";
	private Date date = new Date();
	private MaintenanceEmployee maintenanceEmployee = new MaintenanceEmployee();
	
	
	@Autowired
	private AgencyDAO agencydao = new AgencyDAO();
	private Agency agency = new Agency();
	
	@Autowired
	private CarDAO cardao;
	private CarType type = new CarType();
	
	@Autowired
	private CarTypeDAO cartypedao;
	
	@Autowired
	private MaintenanceEmployeeDAO maintenanceEmployeedao;

	@Before
	public void setUpBefore() throws Exception {
		List<MaintenanceType> maintenances = new ArrayList<MaintenanceType>();
		
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
	@Transactional(propagation = Propagation.REQUIRED, rollbackFor = Exception.class)
	public void testFindByCar() {
		Maintenance result = test.findByCar(car);
		assertNotNull(result);
		assertEquals(result.getCar(), car);
	}

	@Test
	@Transactional(propagation = Propagation.REQUIRED, rollbackFor = Exception.class)
	public void testFindByDate() {
		List<Maintenance> result = test.findByMaintenanceEmployee(maintenanceEmployee);
		assertTrue(result.size() > 0);
		for (int index = 0; index < result.size(); index++) {
			assertEquals(result.get(index).getDate(), date);
		}
	}

	@Test
	@Transactional(propagation = Propagation.REQUIRED, rollbackFor = Exception.class)
	public void testFindByMaintenanceEmployee() {
		List<Maintenance> result = test.findByMaintenanceEmployee(maintenanceEmployee);
		assertTrue(result.size() > 0);
		for (int index = 0; index < result.size(); index++) {
			assertEquals(result.get(index).getMaintenanceEmployee(), maintenanceEmployee);
		}
	}

}
