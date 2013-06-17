package it.unibo.ronf.shared.entities;

import java.util.List;

import javax.persistence.CascadeType;
import javax.persistence.Entity;
import javax.persistence.FetchType;
import javax.persistence.GeneratedValue;
import javax.persistence.GenerationType;
import javax.persistence.Id;
import javax.persistence.OneToMany;
import javax.persistence.OneToOne;
import javax.xml.bind.annotation.XmlRootElement;

/**
 * 
 * @author lory
 * 
 */
@Entity
@XmlRootElement
public class Transfer implements java.io.Serializable {

	private static final long serialVersionUID = 2721176873450373970L;

	@Id
	@GeneratedValue(strategy = GenerationType.IDENTITY)
	private long id;

	// Quando un entity contiene relazioni OneToMany è obbligatorio mettere il FetchType.EAGER
	// in quanto altrimenti con il FetchType.LAZY eclipselink sostituisce l'implememntazione della
	// lista
	// con un IndirectList (che gwt non riesce a serializzare) anzichè ArrayList
	@OneToMany(cascade = CascadeType.ALL, fetch = FetchType.EAGER)
	private List<TransferAction> transfers;
	@OneToOne
	private Agency startAgency;
	@OneToOne
	private Agency arrivalAgency;
	private boolean success;
	@OneToOne
	private TransferEmployee transferEmployee;

	public Transfer() {
		// TODO Auto-generated constructor stub
	}

	public Agency getArrivalAgency() {
		return arrivalAgency;
	}

	public long getId() {
		return id;
	}

	public Agency getStartAgency() {
		return startAgency;
	}

	public TransferEmployee getTransferEmployee() {
		return transferEmployee;
	}

	public List<TransferAction> getTransfers() {
		return transfers;
	}

	public boolean isSuccess() {
		return success;
	}

	public void setArrivalAgency(Agency arrivalAgency) {
		this.arrivalAgency = arrivalAgency;
	}

	public void setId(long id) {
		this.id = id;
	}

	public void setStartAgency(Agency startAgency) {
		this.startAgency = startAgency;
	}

	public void setSuccess(boolean success) {
		this.success = success;
	}

	public void setTransferEmployee(TransferEmployee transferEmployee) {
		this.transferEmployee = transferEmployee;
	}

	public void setTransfers(List<TransferAction> transfers) {
		this.transfers = transfers;
	}
}
