package it.unibo.ronf.shared.entities;
import java.util.Date;

public class Payment {
	
	private float amount;
	private String paymentMethod;
	private Date dateOfPayment;
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
	
	

}
