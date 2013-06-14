package it.unibo.ronf.server.dao;

import java.util.ArrayList;
import java.util.List;

import it.unibo.ronf.shared.entities.*;

import static org.junit.Assert.*;

import org.junit.BeforeClass;
import org.junit.Test;

public class TransferDAOTest {

	static TransferDAO test = new TransferDAO();
	static Transfer entity = new Transfer();
	static List<Transfer> resultList = new ArrayList<Transfer>();
	static List<TransferAction> transfers = new ArrayList<TransferAction>();
	static Agency arrivalAgency = new Agency();
	static Agency startAgency = new Agency();
	static AgencyDAO agencydao = new AgencyDAO();
	static String address = "ADDRESS";
	static String code = "CODE";
	static String ipAddress = "IPADDRESS";
	static String name = "NAME";

	@BeforeClass
	public static void setUpBeforeClass() throws Exception {
		arrivalAgency.setAddress(address);
		arrivalAgency.setName(name);
		arrivalAgency.setCode(code);
		arrivalAgency.setId(0);
		arrivalAgency.setIpAddress(ipAddress);

		startAgency.setAddress("address");
		startAgency.setName("name");
		startAgency.setCode("code");
		startAgency.setId(0);
		startAgency.setIpAddress("ipAddress");

		agencydao.persist(startAgency);
		agencydao.persist(arrivalAgency);

		entity.setArrivalAgency(arrivalAgency);
		entity.setStartAgency(startAgency);
		entity.setTransfers(transfers);

		test.persist(entity);
	}

	@Test
	public void testFindByStartAgency() {
		resultList.clear();
		resultList = test.findByStartAgency(startAgency);
		assertNotNull(resultList);
		for (int index = 0; index < resultList.size(); index++) {
			assertTrue(resultList.get(index).getStartAgency().equals(startAgency));
		}
	}

	@Test
	public void testFindByArrivalAgency() {
		resultList.clear();
		resultList = test.findByArrivalAgency(arrivalAgency);
		assertNotNull(resultList);
		for (int index = 0; index < resultList.size(); index++) {
			assertTrue(resultList.get(index).getArrivalAgency().equals(arrivalAgency));
		}
	}

}
