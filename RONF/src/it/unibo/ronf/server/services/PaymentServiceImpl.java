package it.unibo.ronf.server.services;

import it.unibo.ronf.shared.entities.Optional;
import it.unibo.ronf.shared.entities.Payment;
import it.unibo.ronf.shared.entities.Rental;
import it.unibo.ronf.shared.services.PaymentService;

import java.util.Date;

import org.apache.log4j.Logger;
import org.springframework.stereotype.Service;

@Service("paymentService")
public class PaymentServiceImpl implements PaymentService {

	private static final Logger logger = Logger.getLogger(PaymentServiceImpl.class);

	@Override
	public Payment makePayment(Rental r) {

		Date end = r.getEnd();
		Date start = r.getStart();

		long days = ((end.getTime() - start.getTime()) / 1000) / 86400;

		float totalForCar = r.getRentedCar().getType().getDailyCost() * days;
		float caution = r.getCaution();

		float total = totalForCar + caution;

		for (Optional o : r.getOptional()) {
			total = total + o.getCost();
		}

		Payment totalPayment = new Payment();
		totalPayment.setAmount(total);
		totalPayment.setDateOfPayment(r.getStart());
		return totalPayment;

	}

}
