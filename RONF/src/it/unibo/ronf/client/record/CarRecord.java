package it.unibo.ronf.client.record;

import com.smartgwt.client.widgets.grid.ListGridRecord;

public class CarRecord extends ListGridRecord {

	public CarRecord(Long id, String model, String plate, String gasolineType, Integer seatsNumber, String agency, String type) {
		setId(id);
		setModel(model);
		setPlate(plate);
		setGasolineType(gasolineType);
		setSeatsNumber(seatsNumber);
		setAgency(agency);
		setType(type);
	}

	public String getAgency() {
		return getAttributeAsString("agency");
	}

	public String getGasolineType() {
		return getAttributeAsString("gasolineType");
	}

	public Long getId() {
		return getAttributeAsLong("id");
	}

	public String getModel() {
		return getAttributeAsString("model");
	}

	public String getPlate() {
		return getAttributeAsString("plate");
	}

	public Integer getSeatsNumber() {
		return getAttributeAsInt("seatsNumber");
	}

	public String getType() {
		return getAttributeAsString("type");
	}

	public void setAgency(String agency) {
		setAttribute("agency", agency);
	}

	public void setGasolineType(String gasolineType) {
		setAttribute("gasolineType", gasolineType);
	}

	public void setId(Long id) {
		setAttribute("id", id);
	}

	public void setModel(String model) {
		setAttribute("model", model);
	}

	public void setPlate(String plate) {
		setAttribute("plate", plate);
	}

	public void setSeatsNumber(Integer seatsNumber) {
		setAttribute("seatsNumber", seatsNumber);
	}

	public void setType(String type) {
		setAttribute("type", type);
	}

}
