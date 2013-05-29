package it.unibo.ronf.server.services;

import it.unibo.ronf.server.dao.RentalDAO;
import it.unibo.ronf.shared.entities.Agency;
import it.unibo.ronf.shared.entities.Optional;
import it.unibo.ronf.shared.entities.Rental;
import it.unibo.ronf.shared.services.RentalService;

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
	private RentalDAO rentalDAO;

	@Override
	@Transactional(propagation = Propagation.REQUIRED, rollbackFor = Exception.class)
	public void createRental(Rental rental) {
		if(rental.getStart().compareTo(rental.getEnd()) >= 0) {
			throw new IllegalArgumentException("You must specify a valid date range!");
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
	@Transactional(propagation=Propagation.REQUIRED,rollbackFor=Exception.class)
	public void updateRental(Rental r) {
		rentalDAO.merge(r);
	}

}
