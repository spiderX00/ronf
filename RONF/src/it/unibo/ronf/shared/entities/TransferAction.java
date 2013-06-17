package it.unibo.ronf.shared.entities;

import java.util.Date;

import javax.persistence.CascadeType;
import javax.persistence.Entity;
import javax.persistence.GeneratedValue;
import javax.persistence.GenerationType;
import javax.persistence.Id;
import javax.persistence.OneToOne;
import javax.persistence.Temporal;
import javax.persistence.TemporalType;
import javax.xml.bind.annotation.XmlRootElement;

/**
 * Denota una azione di trasferimento di una singola macchina
 * 
 * @author lory
 * 
 */
@Entity
@XmlRootElement
public class TransferAction implements java.io.Serializable {

	private static final long serialVersionUID = -3718963073333055327L;

	@Id
	@GeneratedValue(strategy = GenerationType.IDENTITY)
	private long id;

	@OneToOne(cascade = CascadeType.PERSIST)
	private Car requiredCar;
	@Temporal(TemporalType.DATE)
	private Date transferDate;
	private boolean successAction;

	public long getId() {
		return id;
	}

	public Car getRequiredCar() {
		return requiredCar;
	}

	public Date getTransferDate() {
		return transferDate;
	}

	public boolean isSuccessAction() {
		return successAction;
	}

	public void setId(long id) {
		this.id = id;
	}

	public void setRequiredCar(Car requiredCar) {
		this.requiredCar = requiredCar;
	}

	public void setSuccessAction(boolean successAction) {
		this.successAction = successAction;
	}

	public void setTransferDate(Date transferDate) {
		this.transferDate = transferDate;
	}

}
