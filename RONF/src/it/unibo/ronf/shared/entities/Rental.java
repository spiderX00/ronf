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
	@OneToOne
	private Car rentedCar;
	@OneToOne
	private CarType rentedType;
	@OneToOne
	private Customer customer;
	@OneToMany(fetch = FetchType.EAGER)
	private List<Optional> optional;
	@OneToOne
	private Agency startingAgency;
	@OneToOne
	private Agency arrivalAgency;
	@OneToOne(cascade=CascadeType.PERSIST)
	private Payment payment;
	private float caution;
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
