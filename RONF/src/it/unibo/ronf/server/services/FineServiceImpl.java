package it.unibo.ronf.server.services;

import org.springframework.stereotype.Service;

import it.unibo.ronf.shared.entities.Maintenance;
import it.unibo.ronf.shared.entities.MaintenanceType;
import it.unibo.ronf.shared.entities.Payment;
import it.unibo.ronf.shared.services.FineService;

@Service("fineService")
public class FineServiceImpl implements FineService {

	@Override
	public Payment calculateFine(Maintenance m) {

		float fineAmount = 0;

		for (MaintenanceType mt : m.getMaintenances()) {

			fineAmount = fineAmount + mt.getCost();

		}

		Payment fineToPay = new Payment();
		fineToPay.setAmount(fineAmount);
		fineToPay.setDateOfPayment(m.getDate());
		fineToPay.setDateOfPayment(m.getDate());
		return fineToPay;
	}
}
