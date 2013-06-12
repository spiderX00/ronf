package it.unibo.ronf.server.rest.services;

import java.util.List;

import it.unibo.ronf.server.dao.CustomerDAO;
import it.unibo.ronf.server.dao.RentalDAO;
import it.unibo.ronf.shared.entities.Customer;
import it.unibo.ronf.shared.entities.Rental;

import javax.ws.rs.Consumes;
import javax.ws.rs.POST;
import javax.ws.rs.Path;
import javax.ws.rs.Produces;
import javax.ws.rs.core.MediaType;

import org.apache.log4j.Logger;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.context.annotation.Scope;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Propagation;
import org.springframework.transaction.annotation.Transactional;

@Service("rentalServiceRemote")
@Path("/rental")
@Scope("request")
public class RentalRestService {

	@Autowired
	RentalDAO rentalDAO;

	@Autowired
	CustomerDAO customerDAO;

	private static final Logger logger = Logger
			.getLogger(RentalRestService.class);

	@POST
	@Path("/getRentals")
	@Produces({ MediaType.APPLICATION_XML })
	@Consumes({ MediaType.APPLICATION_XML })
	@Transactional(propagation = Propagation.REQUIRED, rollbackFor = Exception.class)
	public List<Rental> getRentalForUser(long id) {

		return rentalDAO.findByUserId(id);

	}
	
	@POST
	@Path("/close")
	@Produces({ MediaType.APPLICATION_XML })
	@Consumes({ MediaType.APPLICATION_XML })
	@Transactional(propagation = Propagation.REQUIRED, rollbackFor = Exception.class)
	public void closeRental(Rental r) {
		rentalDAO.merge(r);
	}

}
