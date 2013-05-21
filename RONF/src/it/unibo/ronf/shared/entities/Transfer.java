package it.unibo.ronf.shared.entities;

import java.util.List;
import javax.persistence.*;

/**
 * 
 * @author lory
 *
 */
@Entity
public class Transfer implements java.io.Serializable {

	private static final long serialVersionUID = 1L;
	
	@Id @GeneratedValue(strategy=GenerationType.IDENTITY)
	private long id;
	
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
