package it.unibo.ronf.server.rest.services;

import it.unibo.ronf.server.dao.CarDAO;
import it.unibo.ronf.shared.dto.AvailableCarRequestDTO;
import it.unibo.ronf.shared.entities.Car;

import java.util.List;

import javax.ws.rs.Consumes;
import javax.ws.rs.POST;
import javax.ws.rs.Path;
import javax.ws.rs.Produces;
import javax.ws.rs.core.MediaType;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.context.annotation.Scope;
import org.springframework.stereotype.Service;

@Service("carServiceRemote")
@Path("/cars")
@Scope("request")
public class CarRestService {
	
	@Autowired
	private CarDAO carDAO;

	@POST
	@Path("/available")
	@Produces({ MediaType.APPLICATION_XML })
	@Consumes({ MediaType.APPLICATION_XML })
	public List<Car> findAvailableCar(AvailableCarRequestDTO request) {
		return carDAO.findAvailableCar(request.getType(),
				request.getStart(), request.getEnd());
	}

	@POST
	@Path("/free")
	@Produces({ MediaType.APPLICATION_XML })
	@Consumes({ MediaType.APPLICATION_XML })
	public List<Car> findFreeCars() {
		return carDAO.getAllFreeCars();
	}

}
