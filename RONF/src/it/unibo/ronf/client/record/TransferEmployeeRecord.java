package it.unibo.ronf.client.record;

import it.unibo.ronf.shared.entities.TransferEmployee;

import com.smartgwt.client.widgets.grid.ListGridRecord;

public class TransferEmployeeRecord extends ListGridRecord {

	public TransferEmployeeRecord(Long id, String name, String surname, Integer age, Boolean busy, TransferEmployee transferEmployee) {
		setId(id);
		setName(name);
		setSurname(surname);
		setAge(age);
		setBusy(busy);
		setObject(transferEmployee);
	}

	public Integer getAge() {
		return getAttributeAsInt("age");
	}

	public Boolean getBusy() {
		return getAttributeAsBoolean("busy");
	}

	public Long getId() {
		return getAttributeAsLong("id");
	}

	public String getName() {
		return getAttributeAsString("name");
	}

	public TransferEmployee getObject() {
		return (TransferEmployee) getAttributeAsObject("transferEmployee");
	}

	public String getSurname() {
		return getAttributeAsString("surname");
	}

	public void setAge(Integer age) {
		setAttribute("age", age);
	}

	public void setBusy(Boolean busy) {
		setAttribute("busy", busy);
	}

	public void setId(Long id) {
		setAttribute("id", id);
	}

	public void setName(String name) {
		setAttribute("name", name);
	}

	public void setObject(TransferEmployee transferEmployee) {
		setAttribute("transferEmployee", transferEmployee);
	}

	public void setSurname(String surname) {
		setAttribute("surname", surname);
	}

}
