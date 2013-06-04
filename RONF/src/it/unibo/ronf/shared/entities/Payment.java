package it.unibo.ronf.shared.entities;

import java.util.Date;

import javax.persistence.*;

@Entity
public class Payment implements java.io.Serializable {

	private static final long serialVersionUID = 1L;
	
	@Id
	@GeneratedValue(strategy = GenerationType.IDENTITY)
	private long id;
	private float amount;
	private String paymentMethod;
	private Date dateOfPayment;
	@OneToOne
	private Customer userPayer;

	public float getAmount() {
		return amount;
	}

	public void setAmount(float amount) {
		this.amount = amount;
	}

	public String getPaymentMethod() {
		return paymentMethod;
	}

	public void setPaymentMethod(String paymentMethod) {
		this.paymentMethod = paymentMethod;
	}

	public Date getDateOfPayment() {
		return dateOfPayment;
	}

	public void setDateOfPayment(Date dateOfPayment) {
		this.dateOfPayment = dateOfPayment;
	}

	public Customer getUserPayer() {
		return userPayer;
	}

	public void setUserPayer(Customer userPayer) {
		this.userPayer = userPayer;
	}

	public long getId() {
		return id;
	}

	public void setId(long id) {
		this.id = id;
	}

}
