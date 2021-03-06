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

@Entity
public class Payment implements java.io.Serializable {

	private static final long serialVersionUID = 1L;

	@Id
	@GeneratedValue(strategy = GenerationType.IDENTITY)
	private long id;
	private float amount;
	private String paymentMethod;
	@Temporal(TemporalType.DATE)
	private Date dateOfPayment;
	@OneToOne(cascade = CascadeType.PERSIST)
	private Customer userPayer;

	public float getAmount() {
		return amount;
	}

	public Date getDateOfPayment() {
		return dateOfPayment;
	}

	public long getId() {
		return id;
	}

	public String getPaymentMethod() {
		return paymentMethod;
	}

	public Customer getUserPayer() {
		return userPayer;
	}

	public void setAmount(float amount) {
		this.amount = amount;
	}

	public void setDateOfPayment(Date dateOfPayment) {
		this.dateOfPayment = dateOfPayment;
	}

	public void setId(long id) {
		this.id = id;
	}

	public void setPaymentMethod(String paymentMethod) {
		this.paymentMethod = paymentMethod;
	}

	public void setUserPayer(Customer userPayer) {
		this.userPayer = userPayer;
	}

}
