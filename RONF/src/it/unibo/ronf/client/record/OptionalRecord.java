package it.unibo.ronf.client.record;

import com.smartgwt.client.widgets.grid.ListGridRecord;

public class OptionalRecord extends ListGridRecord {

	public OptionalRecord(Long id, String name, Float cost, String description) {
		setId(id);
		setName(name);
		setCost(cost);
		setDescription(description);
	}

	public float getCost() {
		return getAttributeAsFloat("cost");
	}

	public String getDescription() {
		return getAttributeAsString("description");
	}

	public Long getId() {
		return getAttributeAsLong("id");
	}

	public String getName() {
		return getAttributeAsString("name");
	}

	public void setCost(float cost) {
		setAttribute("cost", cost);
	}

	public void setDescription(String description) {
		setAttribute("description", description);
	}

	public void setId(Long id) {
		setAttribute("id", id);
	}

	public void setName(String name) {
		setAttribute("name", name);
	}
}
