package it.unibo.ronf.server.dao;

import static org.junit.Assert.*;
import it.unibo.ronf.shared.entities.Agency;
import it.unibo.ronf.shared.entities.Car;
import it.unibo.ronf.shared.entities.CarType;
import it.unibo.ronf.shared.entities.Optional;
import it.unibo.ronf.shared.entities.Payment;
import it.unibo.ronf.shared.entities.Rental;

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
public class RentalDAOTest {

	@Autowired
	private CarDAO cardao;
	
	@Autowired
	private RentalDAO test;
	
	@Autowired
	private AgencyDAO agencydao;
	
	@Autowired
	private CarTypeDAO cartypedao;

	private int id = 10;
	private float caution = 1;
	private String code = "CODE1";
	private String name = "NAME1";
	private String address = "ADDRESS1";
	private String ipAddress = "1.1.1.1";

	private Agency startingAgency = new Agency();
	private Agency arrivalAgency = new Agency();
	private Payment payment = new Payment();
	private Rental entity = new Rental();
	private Car rentedCar = new Car();
	private CarType type = new CarType();

	private List<Rental> rentalList = new ArrayList<Rental>();
	private List<Optional> optional = new ArrayList<Optional>();

	private Date start = new Date(), end = new Date();

	@Before
	public void setUpBeforeClass() throws Exception {
		startingAgency.setId(id);
		startingAgency.setCode(code);
		startingAgency.setName(name);
		startingAgency.setAddress(address);
		startingAgency.setIpAddress(ipAddress);

		arrivalAgency.setId(0);
		arrivalAgency.setCode("code");
		arrivalAgency.setName("name");
		arrivalAgency.setAddress("address");
		arrivalAgency.setIpAddress("ipAddress");

		agencydao.persist(startingAgency);
		agencydao.persist(arrivalAgency);

		type.setId(0);
		type.setType("A1");
		type.setDailyCost(0.0F);

		cartypedao.persist(type);

		rentedCar.setOriginAgency(startingAgency);
		rentedCar.setGasolineType("gasolineType");
		rentedCar.setId(0);
		rentedCar.setModel("model");
		rentedCar.setPlate("plate");
		rentedCar.setSeatsNumber(0);
		rentedCar.setType(type);

		cardao.persist(rentedCar);

		start.setTime(10000);
		end.setTime(20000);

		entity.setStart(start);
		entity.setEnd(end);
		entity.setRentedCar(rentedCar);
		entity.setOptional(optional);
		entity.setStartingAgency(startingAgency);
		entity.setArrivalAgency(arrivalAgency);
		entity.setPayment(payment);
		entity.setCaution(caution);

		test.persist(entity);
	}

	@Test
	@Transactional(propagation = Propagation.REQUIRED, rollbackFor = Exception.class)
	public void testFindByStart() {
		List <Rental> rentalList = test.findByStart(start);
		assertTrue(rentalList.size() > 0);
		for (int i = 0; i < rentalList.size(); i++) {
			assertTrue(rentalList.get(i).getStart() == start);
		}
	}

	@Test
	@Transactional(propagation = Propagation.REQUIRED, rollbackFor = Exception.class)
	public void testFindByEnd() {
		List <Rental> rentalList = test.findByEnd(end);
		assertTrue(rentalList.size() > 0);
		for (int i = 0; i < rentalList.size(); i++) {
			assertTrue(rentalList.get(i).getEnd() == end);
		}
	}

	@Test
	@Transactional(propagation = Propagation.REQUIRED, rollbackFor = Exception.class)
	public void testFindByStartingAgency() {
		List <Rental> rentalList = test.findByStartingAgency(startingAgency);
		assertTrue(rentalList.size() > 0);
		for (int i = 0; i < rentalList.size(); i++) {
			assertTrue(rentalList.get(i).getStartingAgency() == startingAgency);
		}
	}

	@Test
	@Transactional(propagation = Propagation.REQUIRED, rollbackFor = Exception.class)
	public void testFindByArrivalAgency() {
		List <Rental> rentalList = test.findByArrivalAgency(arrivalAgency);
		assertTrue(rentalList.size() > 0);
		for (int i = 0; i < rentalList.size(); i++) {
			assertTrue(rentalList.get(i).getArrivalAgency() == arrivalAgency);
		}
	}
}
