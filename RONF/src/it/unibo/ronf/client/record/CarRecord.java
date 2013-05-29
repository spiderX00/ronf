package it.unibo.ronf.client.record;

import com.smartgwt.client.widgets.grid.ListGridRecord;

public class CarRecord extends ListGridRecord {

	public CarRecord(Long id, String model, String plate, String gasolineType,
			Integer seatsNumber, String agency, String type) {
		setId(id);
		setModel(model);
		setPlate(plate);
		setGasolineType(gasolineType);
		setSeatsNumber(seatsNumber);
		setAgency(agency);
		setType(type);
	}

	public void setId(Long id) {
		setAttribute("id", id);
	}

	public Long getId() {
		return getAttributeAsLong("id");
	}

	public void setModel(String model) {
		setAttribute("model", model);
	}

	public String getModel() {
		return getAttributeAsString("model");
	}

	public void setPlate(String plate) {
		setAttribute("plate", plate);
	}

	public String getPlate() {
		return getAttributeAsString("plate");
	}

	public void setGasolineType(String gasolineType) {
		setAttribute("gasolineType", gasolineType);
	}

	public String getGasolineType() {
		return getAttributeAsString("gasolineType");
	}

	public void setSeatsNumber(Integer seatsNumber) {
		setAttribute("seatsNumber", seatsNumber);
	}

	public Integer getSeatsNumber() {
		return getAttributeAsInt("seatsNumber");
	}

	public void setAgency(String agency) {
		setAttribute("agency", agency);
	}

	public String getAgency() {
		return getAttributeAsString("agency");
	}

	public void setType(String type) {
		setAttribute("type", type);
	}

	public String getType() {
		return getAttributeAsString("type");
	}

}
