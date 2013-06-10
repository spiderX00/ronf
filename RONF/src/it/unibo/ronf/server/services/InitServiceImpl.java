/* utente admin, tipi macchina, macchine, agenzie, */

package it.unibo.ronf.server.services;

import org.apache.log4j.Logger;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Propagation;
import org.springframework.transaction.annotation.Transactional;

import it.unibo.ronf.server.dao.AgencyDAO;
import it.unibo.ronf.server.dao.CarDAO;
import it.unibo.ronf.server.dao.CarTypeDAO;
import it.unibo.ronf.server.dao.CustomerDAO;
import it.unibo.ronf.server.dao.EmployeeDAO;
import it.unibo.ronf.server.dao.OptionalDAO;
import it.unibo.ronf.server.dao.TransferEmployeeDAO;
import it.unibo.ronf.shared.entities.Agency;
import it.unibo.ronf.shared.entities.Car;
import it.unibo.ronf.shared.entities.CarType;
import it.unibo.ronf.shared.entities.Customer;
import it.unibo.ronf.shared.entities.Employee;
import it.unibo.ronf.shared.entities.Optional;
import it.unibo.ronf.shared.entities.TransferEmployee;
import it.unibo.ronf.shared.services.InitService;

/**
 * Questa classe serve in fase di development poichè abbiamo impostato un
 * parametro nel JPA di EclipseLink il quale, ad ogni avvio dell'applicazione,
 * cancella tutto il database in modo che, per ogni prova/test, abbiamo a che
 * fare sempre con un database pulito ed re-inizializzato con i suoi componenti
 * fondamentali come l'utente admin, i tipi di macchine, le macchine e le
 * agenzie.
 * 
 * @author Mone e Lory
 * 
 */
@Service("initService")
public class InitServiceImpl implements InitService {

	private static final Logger logger = Logger.getLogger(InitServiceImpl.class);

	@Autowired
	private EmployeeDAO employeeDAO;
	@Autowired
	private AgencyDAO agencyDAO;
	@Autowired
	private CarTypeDAO carTypeDAO;
	@Autowired
	private OptionalDAO optionalDAO;
	@Autowired
	private CarDAO carDAO;
	@Autowired
	private CustomerDAO customerDAO;
	@Autowired
	private TransferEmployeeDAO teDAO;

	@Override
	@Transactional(propagation = Propagation.REQUIRED, rollbackFor = Exception.class)
	public void preLoginInitEntities() {
		try {
			/* Aggiungo l'admin */
			Employee admin = new Employee();
			admin.setUserName("admin");
			admin.setPassword("admin");
			admin.setAge(23);
			admin.setName("Simone");
			admin.setSurname("Rondelli");

			employeeDAO.persist(admin);

			/* Aggiungo le agenzie */
			Agency a1 = new Agency();
			a1.setAddress("Via Zamboni");
			a1.setCode("a1");
			a1.setIpAddress("127.0.0.1");
			a1.setName("Herz centrale 8080");
			a1.setPort(8080);

			Agency a2 = new Agency();
			a2.setAddress("Via Stalingrado");
			a2.setCode("a2");
			a2.setIpAddress("127.0.0.1");
			a2.setName("Herz periferia 8081");
			a2.setPort(8081);

			agencyDAO.persist(a1);
			agencyDAO.persist(a2);
		} catch (Exception ex) {
			logger.error("preLoginInitEntities", ex);
		}

	}

	/**
	 * A seconda della currentAgency contenuta dentro AgencyDAO, aggiungo
	 * qualche macchina nei rispettivi database
	 */
	@Override
	@Transactional(propagation = Propagation.REQUIRED, rollbackFor = Exception.class)
	public void postLoginInitEntities() {
		try {
			Agency agency = agencyDAO.getCurrentAgency();

			/* Aggiungo i tipi di macchina */
			CarType c1 = new CarType();
			c1.setType("mini");
			c1.setDailyCost(26.5F);

			CarType c2 = new CarType();
			c2.setType("family");
			c2.setDailyCost(36.5F);

			CarType c3 = new CarType();
			c3.setType("sport");
			c3.setDailyCost(45.10F);

			CarType c4 = new CarType();
			c4.setType("prestige");
			c4.setDailyCost(56F);

			carTypeDAO.persist(c1);
			carTypeDAO.persist(c2);
			carTypeDAO.persist(c3);
			carTypeDAO.persist(c4);

			/* Aggiungo Optional */
			Optional o1 = new Optional();
			o1.setName("Guidatore aggiuntivo");
			o1.setCost(21.8F);
			o1.setDescription("Inserire il numero del documento dell'altro guidatore");

			Optional o2 = new Optional();
			o2.setName("Seggiolino auto");
			o2.setCost(11F);
			o2.setDescription("Per richiedere più di un seggiolino, inserire un nuovo optional");

			Optional o3 = new Optional();
			o3.setName("Navigatore satellitare");
			o3.setCost(30.8F);
			o3.setDescription("");

			optionalDAO.persist(o1);
			optionalDAO.persist(o2);
			optionalDAO.persist(o3);

			/* Salvo alcune macchine nei rispettivi DB delle rispettive agenzie */
			if (agencyDAO.getCurrentAgency().getPort() == 8080) {
				Car car1 = new Car();
				car1.setOriginAgency(agency);
				car1.setGasolineType("Benzina");
				car1.setModel("Fiat MITO");
				car1.setPlate("abc456");
				car1.setSeatsNumber(4);
				car1.setType(c3);

				Car car2 = new Car();
				car2.setOriginAgency(agency);
				car2.setGasolineType("Benzina");
				car2.setModel("Lancia y");
				car2.setPlate("def789");
				car2.setSeatsNumber(5);
				car2.setType(c2);
				
				Car car3 = new Car();
				car3.setOriginAgency(agency);
				car3.setGasolineType("Diesel");
				car3.setModel("Fiat Doblò");
				car3.setPlate("6dh74w");
				car3.setSeatsNumber(5);
				car3.setType(c2);
				

				carDAO.persist(car1);
				carDAO.persist(car2);
				carDAO.persist(car3);

				Customer cus = new Customer();
				cus.setAge(36);
				cus.setDocNumber("5sg17f");
				cus.setFiscalCode("cvdnfu64cu2");
				cus.setName("Valerio");
				cus.setSurname("Enzino");

				customerDAO.persist(cus);
			}

			if (agencyDAO.getCurrentAgency().getPort() == 8081) {
				Car car4 = new Car();
				car4.setOriginAgency(agency);
				car4.setGasolineType("Benzina");
				car4.setModel("Seat Leon");
				car4.setPlate("123ghi");
				car4.setSeatsNumber(5);
				car4.setType(c3);
				
				Car car5 = new Car();
				car5.setOriginAgency(agency);
				car5.setGasolineType("Benzina");
				car5.setModel("Smart");
				car5.setPlate("j747iy");
				car5.setSeatsNumber(2);
				car5.setType(c1);

				carDAO.persist(car4);
				carDAO.persist(car5);

				Customer cus = new Customer();
				cus.setAge(29);
				cus.setDocNumber("hzeya");
				cus.setFiscalCode("aryya5ry2");
				cus.setName("Alessio");
				cus.setSurname("De Vita");

				customerDAO.persist(cus);

				TransferEmployee te = new TransferEmployee();
				te.setAge(26);
				te.setBusy(false);
				te.setName("Marco");
				te.setSurname("Di Nicola");
				te.setPassword("bob");
				te.setUserName("bob");

				teDAO.persist(te);
			}

		} catch (Exception ex) {
			logger.error("postLoginInitEntities", ex);
		}
	}

}
