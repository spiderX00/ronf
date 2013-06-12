package it.unibo.ronf.server.rest.services;

import it.unibo.ronf.server.dao.CustomerDAO;
import it.unibo.ronf.server.dao.RentalDAO;
import it.unibo.ronf.shared.dto.GetRentalByUserDTO;
import it.unibo.ronf.shared.entities.Rental;

import java.text.DateFormat;
import java.util.List;

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

@Service("rentalRestService")
@Path("/rental")
@Scope("request")
public class RentalRestService {
	
	private static final Logger logger = Logger.getLogger(RentalRestService.class);

	@Autowired
	private RentalDAO rentalDAO;

	@Autowired
	private CustomerDAO customerDAO;

	@POST
	@Path("/getRentals")
	@Produces({ MediaType.APPLICATION_XML })
	@Consumes({ MediaType.APPLICATION_XML })
	public List<Rental> getRentalForUser(GetRentalByUserDTO closeDto) {
		if (logger.isDebugEnabled()) {
			logger.debug("Serching rental for user: " + closeDto.getId());
		}
		return rentalDAO.findByUserId(closeDto.getId());
	}

	@POST
	@Path("/close")
	@Produces({ MediaType.APPLICATION_XML })
	@Consumes({ MediaType.APPLICATION_XML })
	@Transactional(propagation = Propagation.REQUIRED, rollbackFor = Exception.class)
	public void closeRental(Rental r) {
		if (logger.isDebugEnabled()) {
			String start = DateFormat.getDateInstance().format(r.getStart());
			String end = DateFormat.getDateInstance().format(r.getEnd());
			logger.debug("Closing rental started:" + start + " end:" + end + " of user:" + r.getCustomer().getId());
		}
		r.setFinished(true);
		rentalDAO.merge(r);
	}

}
