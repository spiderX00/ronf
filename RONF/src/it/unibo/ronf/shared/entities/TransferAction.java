package it.unibo.ronf.shared.entities;

import java.util.Date;

import javax.persistence.*;
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

	private static final long serialVersionUID = 1L;

	@Id
	@GeneratedValue(strategy = GenerationType.IDENTITY)
	private long id;

	@OneToOne(cascade=CascadeType.PERSIST)
	private Car requiredCar;
	@OneToOne(cascade=CascadeType.PERSIST)
	private TransferEmployee employee;
	@Temporal(TemporalType.DATE)
	private Date transferDate;
	private boolean successAction;
	
	public Car getRequiredCar() {
		return requiredCar;
	}

	public void setRequiredCar(Car requiredCar) {
		this.requiredCar = requiredCar;
	}

	public TransferEmployee getEmployee() {
		return employee;
	}

	public void setEmployee(TransferEmployee employee) {
		this.employee = employee;
	}

	public Date getTransferDate() {
		return transferDate;
	}

	public void setTransferDate(Date transferDate) {
		this.transferDate = transferDate;
	}

	public boolean isSuccessAction() {
		return successAction;
	}

	public void setSuccessAction(boolean successAction) {
		this.successAction = successAction;
	}

	
}
