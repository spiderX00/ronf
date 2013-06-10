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

	public void setObject(TransferEmployee transferEmployee) {
		setAttribute("transferEmployee", transferEmployee);
	}

	public TransferEmployee getObject() {
		return (TransferEmployee) getAttributeAsObject("transferEmployee");
	}

	public void setId(Long id) {
		setAttribute("id", id);
	}

	public Long getId() {
		return getAttributeAsLong("id");
	}

	public void setName(String name) {
		setAttribute("name", name);
	}

	public String getName() {
		return getAttributeAsString("name");
	}

	public void setSurname(String surname) {
		setAttribute("surname", surname);
	}

	public String getSurname() {
		return getAttributeAsString("surname");
	}

	public void setAge(Integer age) {
		setAttribute("age", age);
	}

	public Integer getAge() {
		return getAttributeAsInt("age");
	}

	public void setBusy(Boolean busy) {
		setAttribute("busy", busy);
	}

	public Boolean getBusy() {
		return getAttributeAsBoolean("busy");
	}

}
