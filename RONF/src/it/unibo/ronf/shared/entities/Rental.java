package it.unibo.ronf.shared.entities;
import java.util.Date;
import java.util.List;

public class Rental {
	
	private Date start;
	private Date end;
	private Car rentedCar;
	private CarType rentedType;
	private List<Optional> optional;
	private Agency startingAgency;
	private Agency arrivalAgency;
	private Payment payment;
	private float caution;
	private boolean confirmed;
	
	public Date getStart() {
		return start;
	}
	public void setStart(Date start) {
		this.start = start;
	}
	public Date getEnd() {
		return end;
	}
	public void setEnd(Date end) {
		this.end = end;
	}
	public Car getRentedCar() {
		return rentedCar;
	}
	public void setRentedCar(Car rentedCar) {
		this.rentedCar = rentedCar;
	}
	public CarType getRentedType() {
		return rentedType;
	}
	public void setRentedType(CarType rentedType) {
		this.rentedType = rentedType;
	}
	public List<Optional> getOptional() {
		return optional;
	}
	public void setOptional(List<Optional> optional) {
		this.optional = optional;
	}
	public Agency getStartingAgency() {
		return startingAgency;
	}
	public void setStartingAgency(Agency startingagency) {
		this.startingAgency = startingagency;
	}
	public Agency getArrivalAgency() {
		return arrivalAgency;
	}
	public void setArrivalAgency(Agency arrivalAgency) {
		this.arrivalAgency = arrivalAgency;
	}
	public Payment getPayment() {
		return payment;
	}
	public void setPayment(Payment payment) {
		this.payment = payment;
	}
	public float getCaution() {
		return caution;
	}
	public void setCaution(float caution) {
		this.caution = caution;
	}
	public boolean isConfirmed() {
		return confirmed;
	}
	public void setConfirmed(boolean confirmed) {
		this.confirmed = confirmed;
	}
	
	
	
}
