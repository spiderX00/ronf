package it.unibo.ronf.server.rest.services;

import it.unibo.ronf.server.dao.CarDAO;
import it.unibo.ronf.server.dao.TransferDAO;
import it.unibo.ronf.shared.entities.Car;
import it.unibo.ronf.shared.entities.Transfer;
import it.unibo.ronf.shared.entities.TransferAction;

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

@Service("transferRestService")
@Path("/transfer")
@Scope("request")
public class TransferRestService {
	
	private static final Logger logger = Logger.getLogger(TransferRestService.class);
	
	@Autowired
	private CarDAO carDAO;
	
	@Autowired
	private TransferDAO transferDAO;

	@POST
	@Produces({ MediaType.APPLICATION_XML })
	@Consumes({ MediaType.APPLICATION_XML })
	@Transactional(propagation=Propagation.REQUIRED,rollbackFor=Exception.class)
	public void createTransfer(Transfer t) {
		logger.debug("createTransfer() called from: "+t.getArrivalAgency().getName());
		for(TransferAction ta : t.getTransfers()) {
			logger.debug("Transfer car: "+ta.getRequiredCar().getModel());
			Car c = carDAO.findByPlate(ta.getRequiredCar().getPlate());
			if (c == null) {
				throw new IllegalStateException("Impossible to find car with plate: "+ta.getRequiredCar().getPlate()+" in this agency");
			}
			ta.setRequiredCar(c);
		}
		transferDAO.persist(t);
		logger.debug("transfer successfully created");
	}
	
	@POST
	@Path("/update")
	@Produces({ MediaType.APPLICATION_XML })
	@Consumes({ MediaType.APPLICATION_XML })
	@Transactional(propagation=Propagation.REQUIRED,rollbackFor=Exception.class)
	public void setSuccessfulTransfer(Transfer t) {
		transferDAO.merge(t);
	}

}
