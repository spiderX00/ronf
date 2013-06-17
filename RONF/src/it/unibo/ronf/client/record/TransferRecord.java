package it.unibo.ronf.client.record;

import it.unibo.ronf.shared.entities.Transfer;

import com.smartgwt.client.widgets.grid.ListGridRecord;

public class TransferRecord extends ListGridRecord {

	public TransferRecord(Transfer transfer, Long id, Integer transfers, String startAgency, String arrivalAgency, Boolean success) {
		setId(id);
		setObject(transfer);
		setTransfers(transfers);
		setStartAgency(startAgency);
		setArrivalAgency(arrivalAgency);
		setSuccess(success);
	}

	public String getArrivalAgency() {
		return getAttributeAsString("arrivalAgency");
	}

	public Long getId() {
		return getAttributeAsLong("id");
	}

	public Transfer getObject() {
		return (Transfer) getAttributeAsObject("transfer");
	}

	public String getStartAgency() {
		return getAttributeAsString("startAgency");
	}

	public Boolean getSuccess() {
		return getAttributeAsBoolean("success");
	}

	public Integer getTransfers() {
		return getAttributeAsInt("transfers");
	}

	public void setArrivalAgency(String arrivalAgency) {
		setAttribute("arrivalAgency", arrivalAgency);
	}

	public void setId(Long id) {
		setAttribute("id", id);
	}

	public void setObject(Transfer transfer) {
		setAttribute("transfer", transfer);
	}

	public void setStartAgency(String startAgency) {
		setAttribute("startAgency", startAgency);
	}

	public void setSuccess(Boolean success) {
		setAttribute("success", success);
	}

	public void setTransfers(Integer transfers) {
		setAttribute("transfers", transfers);
	}
}
