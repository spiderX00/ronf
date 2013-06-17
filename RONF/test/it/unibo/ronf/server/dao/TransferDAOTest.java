package it.unibo.ronf.server.dao;

import static org.junit.Assert.assertTrue;
import it.unibo.ronf.shared.entities.Agency;
import it.unibo.ronf.shared.entities.Transfer;
import it.unibo.ronf.shared.entities.TransferAction;

import java.util.ArrayList;
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
public class TransferDAOTest {

	@Autowired
	private TransferDAO test ;
	
	private Transfer entity = new Transfer();
	private List<Transfer> resultList = new ArrayList<Transfer>();
	private List<TransferAction> transfers = new ArrayList<TransferAction>();
	private Agency arrivalAgency = new Agency();
	private Agency startAgency = new Agency();
	
	@Autowired
	private AgencyDAO agencydao;
	
	private String address = "ADDRESS";
	private String code = "CODE";
	private String ipAddress = "IPADDRESS";
	private String name = "NAME";

	@Before
	public void setUpBeforeClass() throws Exception {
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
	@Transactional(propagation = Propagation.REQUIRED, rollbackFor = Exception.class)
	public void testFindByStartAgency() {
		resultList.clear();
		resultList = test.findByStartAgency(startAgency);
		assertTrue(resultList.size() > 0);
		for (int index = 0; index < resultList.size(); index++) {
			assertTrue(resultList.get(index).getStartAgency().equals(startAgency));
		}
	}

	@Test
	@Transactional(propagation = Propagation.REQUIRED, rollbackFor = Exception.class)
	public void testFindByArrivalAgency() {
		resultList.clear();
		resultList = test.findByArrivalAgency(arrivalAgency);
		assertTrue(resultList.size() > 0);
		for (int index = 0; index < resultList.size(); index++) {
			assertTrue(resultList.get(index).getArrivalAgency().equals(arrivalAgency));
		}
	}
}
