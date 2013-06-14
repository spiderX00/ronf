package it.unibo.ronf.server.dao;

import static org.junit.Assert.*;
import it.unibo.ronf.shared.entities.*;

import java.util.ArrayList;
import java.util.List;
import java.util.Date;

import org.junit.BeforeClass;
import org.junit.Test;

public class CarDAOTest {

	static List<Car> carList = new ArrayList<Car>();

	static AgencyDAO agencydao = new AgencyDAO();
	static CarTypeDAO cartypedao = new CarTypeDAO();
	static CarDAO test = new CarDAO();
	static Car entity = new Car();
	static CarType type = new CarType();
	static Agency agency = new Agency();
	static Rental rental = new Rental();
	static RentalDAO rentaldao = new RentalDAO();
	static Customer customer = new Customer();
	static Payment fine = new Payment();
	static Payment payment = new Payment();

	static List<Optional> optionals = new ArrayList<Optional>();

	static String model = "AAAAAAAA";
	static String gasolineType = "ABC";
	static String plate = "PLATE";
	static String address = "ADDRESS";
	static String code = "CODE";
	static Date start = new Date(), end = new Date();

	@BeforeClass
	public static void setUpBeforeClass() throws Exception {

		type.setId(0);
		type.setDailyCost(0);
		type.setType("A1");

		cartypedao.persist(type);

		agency.setAddress(address);
		agency.setCode(code);
		agency.setId(0);
		agency.setIpAddress("ipAddress");
		agency.setName("name");

		agencydao.persist(agency);

		entity.setCurrentAgency(agency);
		entity.setGasolineType(gasolineType);
		entity.setModel(model);
		entity.setPlate(plate);
		entity.setSeatsNumber(0);
		entity.setType(type);

		rental.setArrivalAgency(agency);
		rental.setCaution(0);
		rental.setCustomer(customer);
		rental.setEnd(end);
		rental.setFine(fine);
		rental.setFinished(false);
		rental.setId(0);
		rental.setOptional(optionals);
		rental.setPayment(payment);
		rental.setPayment(payment);
		rental.setRentedCar(entity);
		rental.setStart(start);
		rental.setStartingAgency(agency);

		test.persist(entity);
	}

	@Test
	public void testFindByModel() {
		carList.clear();
		carList = test.findByModel(model);

		for (int i = 0; i < carList.size(); i++) {
			assertEquals(carList.get(i).getModel(), model);
		}
	}

	@Test
	public void testFindByPlate() {
		carList.clear();
		Car result = test.findByPlate(plate);
		assertEquals(result.getPlate(), plate);
	}

	@Test
	public void testFindByGasolineType() {
		carList.clear();
		carList = test.findByGasolineType(gasolineType);

		for (int i = 0; i < carList.size(); i++) {
			assertEquals(carList.get(i).getGasolineType(), gasolineType);
		}
	}

	@Test
	public void testFindBySeatsNumber() {
		carList.clear();
		carList = test.findBySeatsNumber(0);

		for (int i = 0; i < carList.size(); i++) {
			if (carList.get(i).equals(entity)) {
				assertEquals(carList.get(i).getSeatsNumber(), 0);
			}
		}
	}

	@Test
	public void testFindAvailableCar() {

	}

	@Test
	public void testFindByType() {
		carList.clear();
		carList = test.findByType(type);

		for (int i = 0; i < carList.size(); i++) {
			assertEquals(carList.get(i).getType(), type);
		}
	}

}
