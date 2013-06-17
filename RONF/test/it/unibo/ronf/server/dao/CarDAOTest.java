package it.unibo.ronf.server.dao;

import static org.junit.Assert.*;
import it.unibo.ronf.shared.entities.Agency;
import it.unibo.ronf.shared.entities.Car;
import it.unibo.ronf.shared.entities.CarType;
import it.unibo.ronf.shared.entities.Customer;
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
public class CarDAOTest {

	@Autowired
	private AgencyDAO agencydao = new AgencyDAO();
	
	@Autowired
	private CarTypeDAO cartypedao = new CarTypeDAO();
	
	@Autowired
	private CarDAO test = new CarDAO();
	
	@Autowired
	private RentalDAO rentaldao = new RentalDAO();
	
	private Car entity = new Car();
	private CarType type = new CarType();
	private Agency agency = new Agency();
	private Rental rental = new Rental();	
	private Customer customer = new Customer();
	private Payment fine = new Payment();
	private Payment payment = new Payment();

	private List<Optional> optionals = new ArrayList<Optional>();

	private String model = "AAAAAAAA";
	private String gasolineType = "ABC";
	private String plate = "PLATE";
	private String address = "ADDRESS";
	private String code = "CODE";
	private Date start = new Date(), end = new Date();

	@Before
	public void setUpBefore() throws Exception {

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
	@Transactional(propagation = Propagation.REQUIRED, rollbackFor = Exception.class)
	public void testFindByModel() {
		List <Car> carList = test.findByModel(model);
		assertTrue(carList.size() > 0);
		for (int i = 0; i < carList.size(); i++) {
			assertEquals(carList.get(i).getModel(), model);
		}
	}

	@Test
	@Transactional(propagation = Propagation.REQUIRED, rollbackFor = Exception.class)
	public void testFindByPlate() {
		Car result = test.findByPlate(plate);
		assertNotNull(result);
		assertEquals(result.getPlate(), plate);
	}

	@Test
	@Transactional(propagation = Propagation.REQUIRED, rollbackFor = Exception.class)
	public void testFindByGasolineType() {
		List <Car> carList = test.findByGasolineType(gasolineType);
		assertTrue(carList.size() > 0);
		for (int i = 0; i < carList.size(); i++) {
			assertEquals(carList.get(i).getGasolineType(), gasolineType);
		}
	}

	@Test
	@Transactional(propagation = Propagation.REQUIRED, rollbackFor = Exception.class)
	public void testFindBySeatsNumber() {
		List <Car> carList = test.findBySeatsNumber(0);
		assertTrue(carList.size() > 0);
		for (int i = 0; i < carList.size(); i++) {
			if (carList.get(i).equals(entity)) {
				assertEquals(carList.get(i).getSeatsNumber(), 0);
			}
		}
	}

	@Test
	@Transactional(propagation = Propagation.REQUIRED, rollbackFor = Exception.class)
	public void testFindAvailableCar() {

	}

	@Test
	@Transactional(propagation = Propagation.REQUIRED, rollbackFor = Exception.class)
	public void testFindByType() {
		List <Car> carList = test.findByType(type);
		assertTrue(carList.size() > 0);
		for (int i = 0; i < carList.size(); i++) {
			assertEquals(carList.get(i).getType(), type);
		}
	}

}
