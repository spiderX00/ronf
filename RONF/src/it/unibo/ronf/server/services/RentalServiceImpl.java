package it.unibo.ronf.server.services;

import it.unibo.ronf.server.dao.AgencyDAO;
import it.unibo.ronf.server.dao.CarDAO;
import it.unibo.ronf.server.dao.RentalDAO;
import it.unibo.ronf.server.dao.TransferEmployeeDAO;
import it.unibo.ronf.server.rest.TransferRestClient;
import it.unibo.ronf.shared.entities.Agency;
import it.unibo.ronf.shared.entities.Car;
import it.unibo.ronf.shared.entities.Rental;
import it.unibo.ronf.shared.entities.Transfer;
import it.unibo.ronf.shared.entities.TransferAction;
import it.unibo.ronf.shared.services.RentalService;

import java.util.Date;
import java.util.LinkedList;
import java.util.List;

import org.apache.log4j.Logger;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Propagation;
import org.springframework.transaction.annotation.Transactional;

@Service("rentalService")
public class RentalServiceImpl implements RentalService {

	private static final Logger logger = Logger.getLogger(RentalServiceImpl.class);

	@Autowired
	private CarDAO carDAO;

	@Autowired
	private RentalDAO rentalDAO;

	@Autowired
	private AgencyDAO agencyDAO;

	@Autowired
	private TransferEmployeeDAO teDAO;

	@Autowired
	private TransferRestClient transferRestClient;

	@Override
	@Transactional(propagation = Propagation.REQUIRED, rollbackFor = Exception.class)
	public void createRental(Rental rental) {
		if (rental.getStart().compareTo(rental.getEnd()) >= 0) {
			throw new IllegalArgumentException("You must specify a valid date range!");
		}

		if (!rental.getRentedCar().getOriginAgency().getName().equals(agencyDAO.getCurrentAgency().getName())) {

			logger.debug("ATTENZIONE trasferimento richiesto!");
			
			requestTransfer(rental);
			
			logger.debug("Richiesta trasferimento iniviata!");

			Car tempCar = new Car();
			tempCar.setCurrentAgency(agencyDAO.getCurrentAgency());
			tempCar.setGasolineType(rental.getRentedCar().getGasolineType());
			tempCar.setModel(rental.getRentedCar().getModel());
			tempCar.setOriginAgency(rental.getRentedCar().getOriginAgency());
			tempCar.setPlate(rental.getRentedCar().getPlate());
			tempCar.setSeatsNumber(rental.getRentedCar().getSeatsNumber());
			tempCar.setType(rental.getRentedCar().getType());

			
			carDAO.persist(tempCar);
			rental.setRentedCar(tempCar);
			rentalDAO.persist(rental);
			return;

		}

		rentalDAO.persist(rental);

	}

	@Override
	public List<Rental> findByStart(Date start) {
		return rentalDAO.findByStart(start);
	}

	@Override
	public List<Rental> findByEnd(Date end) {
		return rentalDAO.findByEnd(end);
	}

	@Override
	public List<Rental> findByStartingAgency(Agency startingAgency) {
		return rentalDAO.findByStartingAgency(startingAgency);
	}

	@Override
	public List<Rental> findByArrivalAgency(Agency arrivalAgency) {
		return rentalDAO.findByArrivalAgency(arrivalAgency);
	}

	@Override
	public List<Rental> findAll() {
		return rentalDAO.findAll();
	}

	@Override
	@Transactional(propagation = Propagation.REQUIRED, rollbackFor = Exception.class)
	public void removeById(long id) {
		rentalDAO.remove(rentalDAO.findById(id));

	}

	@Override
	@Transactional(propagation = Propagation.REQUIRED, rollbackFor = Exception.class)
	public void updateRental(Rental r) {
		rentalDAO.merge(r);
	}

	private void requestTransfer(Rental r) {

		logger.debug("Inoltrata richiesta trasferimento!");
		TransferAction transferAction = new TransferAction();
		transferAction.setRequiredCar(r.getRentedCar());
		transferAction.setSuccessAction(false);
		transferAction.setTransferDate(r.getStart());

		Transfer transferToDo = new Transfer();
		transferToDo.setArrivalAgency(agencyDAO.getCurrentAgency());
		transferToDo.setStartAgency(r.getRentedCar().getOriginAgency());
		transferToDo.setSuccess(false);
		
		List<TransferAction> listAction = new LinkedList<TransferAction>();
		listAction.add(transferAction);
		
		transferToDo.setTransfers(listAction);
		

		try {
			logger.debug("Invio richiesta trasferimento al client!");
			transferRestClient.sendRequestTransfer(transferToDo);
		} catch (Exception ex) {
			logger.error("error while sending request for transfer to other agency: -->" + ex.getMessage());
		}

	}

}
