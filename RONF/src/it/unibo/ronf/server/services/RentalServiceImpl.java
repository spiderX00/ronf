package it.unibo.ronf.server.services;

import it.unibo.ronf.server.dao.RentalDAO;
import it.unibo.ronf.shared.entities.Rental;
import it.unibo.ronf.shared.entities.Agency;
import it.unibo.ronf.shared.services.RentalService;

import java.util.List;
import java.util.Date;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.transaction.annotation.Propagation;
import org.springframework.transaction.annotation.Transactional;

public class RentalServiceImpl implements RentalService {

	@Autowired
	private RentalDAO rentalDAO;

	@Override
	@Transactional(propagation = Propagation.REQUIRED, rollbackFor = Exception.class)
	public void createRental(Rental rental) {
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

}
