package it.unibo.ronf.server.services;

import java.util.Date;

import org.springframework.stereotype.Service;

import it.unibo.ronf.shared.entities.Optional;
import it.unibo.ronf.shared.entities.Payment;
import it.unibo.ronf.shared.entities.Rental;
import it.unibo.ronf.shared.services.PaymentService;

@Service("paymentService")
public class PaymentServiceImpl implements PaymentService {

	@Override
	public Payment makePayment(Rental r) {
		
		Date end = r.getEnd();
		Date start = r.getStart();

		long days = ((end.getTime() - start.getTime()) / 1000) / 86400;
		
		float totalForCar = r.getRentedCar().getType().getDailyCost() * days;
		float caution = r.getCaution();
		
		float total = totalForCar+caution;
		
		if(!(r.getOptional().isEmpty())) {
			
			for(Optional o : r.getOptional()) {
				
				total = total+o.getCost();
				
			}
		}
		
		Payment totalPayment = new Payment();
		totalPayment.setAmount(total);
		totalPayment.setDateOfPayment(r.getStart());
		totalPayment.setPaymentMethod(r.getPayment().getPaymentMethod());
		return totalPayment;

	}

}
