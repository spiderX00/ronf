package it.unibo.ronf.shared.entities;

import java.util.List;

/**
 * 
 * @author lory
 *
 */
public class Transfer {

	private List<TransferAction> transfers;
	private Agency startAgency;
	private Agency arrivalAgency;

	public Transfer() {
		// TODO Auto-generated constructor stub
	}

	public List<TransferAction> getTransfers() {
		return transfers;
	}

	public void setTransfers(List<TransferAction> transfers) {
		this.transfers = transfers;
	}

	public Agency getStartAgency() {
		return startAgency;
	}

	public void setStartAgency(Agency startAgency) {
		this.startAgency = startAgency;
	}

	public Agency getArrivalAgency() {
		return arrivalAgency;
	}

	public void setArrivalAgency(Agency arrivalAgency) {
		this.arrivalAgency = arrivalAgency;
	}

}
