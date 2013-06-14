package it.unibo.ronf.server.dao;

import java.util.*;

import static org.junit.Assert.*;
import it.unibo.ronf.shared.entities.Agency;
import it.unibo.ronf.shared.entities.Car;
import it.unibo.ronf.shared.entities.CarType;
import it.unibo.ronf.shared.entities.Optional;
import it.unibo.ronf.shared.entities.Payment;
import it.unibo.ronf.shared.entities.Rental;

import org.junit.BeforeClass;
import org.junit.Test;

public class RentalDAOTest {

	static CarDAO cardao = new CarDAO();
	static RentalDAO test = new RentalDAO();
	static AgencyDAO agencydao = new AgencyDAO();
	static CarTypeDAO cartypedao = new CarTypeDAO();

	static int id = 10;
	static float caution = 1;
	static String code = "CODE1";
	static String name = "NAME1";
	static String address = "ADDRESS1";
	static String ipAddress = "1.1.1.1";
	static boolean confirmed = true;

	static Agency startingAgency = new Agency();
	static Agency arrivalAgency = new Agency();
	static Payment payment = new Payment();
	static Rental entity = new Rental();
	static Car rentedCar = new Car();
	static CarType type = new CarType();

	static List<Rental> rentalList = new ArrayList<Rental>();
	static List<Optional> optional = new ArrayList<Optional>();

	static Date start = new Date(), end = new Date();

	@BeforeClass
	public static void setUpBeforeClass() throws Exception {
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
	public void testFindByStart() {
		rentalList = test.findByStart(start);
		assertNotNull(rentalList);
		for (int i = 0; i < rentalList.size(); i++) {
			assertTrue(rentalList.get(i).getStart() == start);
		}
	}

	@Test
	public void testFindByEnd() {
		rentalList = test.findByEnd(end);
		assertNotNull(rentalList);
		for (int i = 0; i < rentalList.size(); i++) {
			assertTrue(rentalList.get(i).getEnd() == end);
		}
	}

	@Test
	public void testFindByStartingAgency() {
		rentalList = test.findByStartingAgency(startingAgency);
		assertNotNull(rentalList);
		for (int i = 0; i < rentalList.size(); i++) {
			assertTrue(rentalList.get(i).getStartingAgency() == startingAgency);
		}
	}

	@Test
	public void testFindByArrivalAgency() {
		rentalList = test.findByArrivalAgency(arrivalAgency);
		assertNotNull(rentalList);
		for (int i = 0; i < rentalList.size(); i++) {
			assertTrue(rentalList.get(i).getArrivalAgency() == arrivalAgency);
		}
	}

}
