package it.unibo.ronf.shared.entities;

import java.util.Date;
import java.util.List;

import javax.persistence.CascadeType;
import javax.persistence.Entity;
import javax.persistence.FetchType;
import javax.persistence.GeneratedValue;
import javax.persistence.GenerationType;
import javax.persistence.Id;
import javax.persistence.OneToMany;
import javax.persistence.OneToOne;
import javax.persistence.Temporal;
import javax.persistence.TemporalType;
import javax.xml.bind.annotation.XmlRootElement;

@Entity
@XmlRootElement
public class Rental implements java.io.Serializable {

	private static final long serialVersionUID = 1L;

	@Id
	@GeneratedValue(strategy = GenerationType.IDENTITY)
	private long id;
	@Temporal(TemporalType.DATE)
	private Date start;
	@Temporal(TemporalType.DATE)
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
	@OneToOne(cascade = CascadeType.PERSIST)
	private Payment payment;
	@OneToOne(cascade = CascadeType.PERSIST)
	private Payment fine;
	private float caution;
	private boolean finished = false;

	public Agency getArrivalAgency() {
		return arrivalAgency;
	}

	public float getCaution() {
		return caution;
	}

	public Customer getCustomer() {
		return customer;
	}

	public Date getEnd() {
		return end;
	}

	public Payment getFine() {
		return fine;
	}

	public long getId() {
		return id;
	}

	public List<Optional> getOptional() {
		return optional;
	}

	public Payment getPayment() {
		return payment;
	}

	public Car getRentedCar() {
		return rentedCar;
	}

	public Date getStart() {
		return start;
	}

	public Agency getStartingAgency() {
		return startingAgency;
	}

	public boolean isFinished() {
		return finished;
	}

	public void setArrivalAgency(Agency arrivalAgency) {
		this.arrivalAgency = arrivalAgency;
	}

	public void setCaution(float caution) {
		this.caution = caution;
	}

	public void setCustomer(Customer customer) {
		this.customer = customer;
	}

	public void setEnd(Date end) {
		this.end = end;
	}

	public void setFine(Payment fine) {
		this.fine = fine;
	}

	public void setFinished(boolean finished) {
		this.finished = finished;
	}

	public void setId(long id) {
		this.id = id;
	}

	public void setOptional(List<Optional> optional) {
		this.optional = optional;
	}

	public void setPayment(Payment payment) {
		this.payment = payment;
	}

	public void setRentedCar(Car rentedCar) {
		this.rentedCar = rentedCar;
	}

	public void setStart(Date start) {
		this.start = start;
	}

	public void setStartingAgency(Agency startingagency) {
		startingAgency = startingagency;
	}
}
