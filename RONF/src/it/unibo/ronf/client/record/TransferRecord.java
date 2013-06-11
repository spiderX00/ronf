package it.unibo.ronf.client.record;

import it.unibo.ronf.shared.entities.Transfer;

import com.smartgwt.client.widgets.grid.ListGridRecord;

public class TransferRecord extends ListGridRecord {
	
	public TransferRecord (Transfer transfer, Long id, Integer transfers, String startAgency, String arrivalAgency, Boolean success) {
		setId(id);
		setObject(transfer);
		setTransfers(transfers);
		setStartAgency(startAgency);
		setArrivalAgency(arrivalAgency);
		setSuccess(success);
	}
	
	public void setObject(Transfer transfer) {
		setAttribute("transfer", transfer);
	}
	
	public Transfer getObject() {
		return (Transfer) getAttributeAsObject("transfer");
	}

	public void setTransfers(Integer transfers) {
		setAttribute("transfers", transfers);
	}
	
	public Integer getTransfers() {
		return getAttributeAsInt("transfers");
	}
	
	public void setStartAgency(String startAgency) {
		setAttribute("startAgency", startAgency);
	}
	
	public String getStartAgency() {
		return getAttributeAsString("startAgency");
	}
	
	public void setArrivalAgency(String arrivalAgency) {
		setAttribute("arrivalAgency", arrivalAgency);
	}
	
	public String getArrivalAgency() {
		return getAttributeAsString("arrivalAgency");
	}
	
	public void setId(Long id) {
		setAttribute("id", id);
	}

	public Long getId() {
		return getAttributeAsLong("id");
	}
	
	public void setSuccess(Boolean success){
		setAttribute("success", success);
	}
	
	public Boolean getSuccess() {
		return getAttributeAsBoolean("success");
	}
}
