package it.unibo.ronf.shared.entities;

import java.util.Date;
import java.util.List;

import javax.persistence.*;

@Entity
public class Rental implements java.io.Serializable {

	private static final long serialVersionUID = 1L;
	
	@Id
	@GeneratedValue(strategy = GenerationType.IDENTITY)
	private long id;
	private Date start;
	private Date end;
	private Car rentedCar;
	private CarType rentedType;
	private Customer customer;
	private List<Optional> optional;
	private Agency startingAgency;
	private Agency arrivalAgency;
	private Payment payment;
	private float caution;
	private boolean confirmed;
	private boolean finished;

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
	
	public Customer getCustomer() {
		return customer;
	}

	public void setCustomer(Customer customer) {
		this.customer = customer;
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

	public long getId() {
		return id;
	}

	public void setId(long id) {
		this.id = id;
	}
	
	public boolean isFinished() {
		return finished;
	}

	public void setFinished(boolean finished) {
		this.finished = finished;
	}


}
