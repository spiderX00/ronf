package it.unibo.ronf.shared.entities;

import java.util.List;
import javax.persistence.*;
import javax.xml.bind.annotation.XmlRootElement;

/**
 * 
 * @author lory
 *
 */
@Entity
@XmlRootElement
public class Transfer implements java.io.Serializable {

	private static final long serialVersionUID = 1L;
	
	@Id @GeneratedValue(strategy=GenerationType.IDENTITY)
	private long id;
	
	@OneToMany
	private List<TransferAction> transfers;
	@OneToOne(cascade=CascadeType.PERSIST)
	private Agency startAgency;
	@OneToOne(cascade=CascadeType.PERSIST)
	private Agency arrivalAgency;
	private boolean success;

	

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

	public boolean isSuccess() {
		return success;
	}

	public void setSuccess(boolean success) {
		this.success = success;
	}

	

}
