package it.unibo.ronf.client.record;

import it.unibo.ronf.shared.entities.Rental;

import java.util.Date;

import com.smartgwt.client.widgets.grid.ListGridRecord;

public class RentalRecord extends ListGridRecord {

	public RentalRecord(Long id, Date start, Date end, String rentedCar, String customer, String startingAgency, String arrivalAgency, Integer optional, String payment, float caution, boolean finished, Rental rental) {
		setId(id);
		setStart(start);
		setEnd(end);
		setRentedCar(rentedCar);
		setCustomer(customer);
		setStartingAgency(startingAgency);
		setArrivalAgency(arrivalAgency);
		setOptional(optional);
		setPayment(payment);
		setCaution(caution);
		setFinished(finished);
		setObject(rental);

	}

	public String getArrivalAgency() {
		return getAttributeAsString("arrivalAgency");
	}

	public float getCaution() {
		return getAttributeAsFloat("caution");
	}

	public String getCustomer() {
		return getAttributeAsString("customer");
	}

	public Date getEnd() {
		return getAttributeAsDate("end");
	}

	public boolean getFinished() {
		return getAttributeAsBoolean("finished");
	}

	public Long getId() {
		return getAttributeAsLong("id");
	}

	public Rental getObject() {
		return (Rental) getAttributeAsObject("rental");
	}

	public Integer getOptional() {
		return getAttributeAsInt("optional");
	}

	public String getPayment() {
		return getAttributeAsString("payment");
	}

	public String getRentedCar() {
		return getAttributeAsString("rentedCar");
	}

	public Date getStart() {
		return getAttributeAsDate("start");
	}

	public String getStartingAgency() {
		return getAttributeAsString("startingAgency");
	}

	public void setArrivalAgency(String arrivalAgency) {
		setAttribute("arrivalAgency", arrivalAgency);
	}

	public void setCaution(float caution) {
		setAttribute("caution", caution);
	}

	public void setCustomer(String customer) {
		setAttribute("customer", customer);
	}

	public void setEnd(Date end) {
		setAttribute("end", end);
	}

	public void setFinished(boolean finished) {
		setAttribute("finished", finished);
	}

	public void setId(Long id) {
		setAttribute("id", id);
	}

	public void setObject(Rental rental) {
		setAttribute("rental", rental);
	}

	public void setOptional(Integer optional) {
		setAttribute("optional", optional);
	}

	public void setPayment(String payment) {
		setAttribute("payment", payment);
	}

	public void setRentedCar(String rentedCar) {
		setAttribute("rentedCar", rentedCar);
	}

	public void setStart(Date start) {
		setAttribute("start", start);
	}

	public void setStartingAgency(String startingAgency) {
		setAttribute("startingAgency", startingAgency);
	}

}
