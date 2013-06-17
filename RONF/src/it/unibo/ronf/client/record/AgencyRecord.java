package it.unibo.ronf.client.record;

import com.smartgwt.client.widgets.grid.ListGridRecord;

public class AgencyRecord extends ListGridRecord {

	public AgencyRecord(Long id, String code, String name, String address, String ipAddress) {
		setId(id);
		setCode(code);
		setName(name);
		setAddress(address);
		setIpAddress(ipAddress);
	}

	public String getAddress() {
		return getAttributeAsString("address");
	}

	public String getCode() {
		return getAttributeAsString("code");
	}

	public Long getId() {
		return getAttributeAsLong("id");
	}

	public String getIpAddress() {
		return getAttributeAsString("ipAddress");
	}

	public String getName() {
		return getAttributeAsString("name");
	}

	public void setAddress(String address) {
		setAttribute("address", address);
	}

	public void setCode(String code) {
		setAttribute("code", code);
	}

	public void setId(Long id) {
		setAttribute("id", id);
	}

	public void setIpAddress(String ipAddress) {
		setAttribute("ipAddress", ipAddress);
	}

	public void setName(String name) {
		setAttribute("name", name);
	}

}
