package it.unibo.ronf.server.services;

import it.unibo.ronf.server.dao.AgencyDAO;
import it.unibo.ronf.server.dao.CarDAO;
import it.unibo.ronf.server.dao.RentalDAO;
import it.unibo.ronf.server.dao.TransferEmployeeDAO;
import it.unibo.ronf.server.rest.client.RentalRestClient;
import it.unibo.ronf.server.rest.client.TransferRestClient;
import it.unibo.ronf.shared.entities.Agency;
import it.unibo.ronf.shared.entities.Car;
import it.unibo.ronf.shared.entities.Rental;
import it.unibo.ronf.shared.entities.Transfer;
import it.unibo.ronf.shared.entities.TransferAction;
import it.unibo.ronf.shared.services.RentalService;

import java.text.DateFormat;
import java.util.ArrayList;
import java.util.Date;
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

	@Autowired
	private RentalRestClient rentalRestClient;

	@Override
	@Transactional(propagation = Propagation.REQUIRED, rollbackFor = Exception.class)
	public void createRental(Rental rental) {
		if (rental.getStart().compareTo(rental.getEnd()) >= 0) {
			String start = DateFormat.getDateInstance().format(rental.getStart());
			String end = DateFormat.getDateInstance().format(rental.getEnd());
			throw new IllegalArgumentException("You must specify a valid date range! start:" + start + " end:" + end);
		}

		if (!rental.getRentedCar().getOriginAgency().getName().equals(agencyDAO.getCurrentAgency().getName())) {

			logger.debug("ATTENZIONE trasferimento richiesto da:" + rental.getRentedCar().getOriginAgency().getName());

			requestTransfer(rental);

			logger.debug("Richiesta trasferimento iniviata a:" + rental.getRentedCar().getOriginAgency().getName());

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

		logger.debug("Inizio inoltro richiesta trasferimento a: " + r.getRentedCar().getOriginAgency().getName());
		TransferAction transferAction = new TransferAction();
		transferAction.setRequiredCar(r.getRentedCar());
		transferAction.setTransferDate(r.getStart());

		Transfer transferToDo = new Transfer();
		transferToDo.setArrivalAgency(agencyDAO.getCurrentAgency());
		transferToDo.setStartAgency(r.getRentedCar().getOriginAgency());
		transferToDo.setSuccess(false);

		List<TransferAction> listAction = new ArrayList<TransferAction>();
		listAction.add(transferAction);

		transferToDo.setTransfers(listAction);

		try {
			transferRestClient.sendTransferRequest(transferToDo);
		} catch (Exception ex) {
			logger.error("error while sending request for transfer to other agency: -->" + ex.getMessage());
		}

	}

}
