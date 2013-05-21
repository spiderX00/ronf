package it.unibo.ronf.shared.entities;
import java.util.Date;

/**
 * Denota una azione di trasferimento di una singola macchina
 * 
 * @author lory
 *
 */
public class TransferAction {
	
	private Car requiredCar;
	private TransferEmployee employee;
	private Date transferDate;
	private boolean success;
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
	public boolean isSuccess() {
		return success;
	}
	public void setSuccess(boolean success) {
		this.success = success;
	}
	
	

}
