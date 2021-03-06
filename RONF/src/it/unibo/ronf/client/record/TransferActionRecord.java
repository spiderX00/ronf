package it.unibo.ronf.client.record;

import it.unibo.ronf.shared.entities.TransferAction;

import java.util.Date;

import com.smartgwt.client.widgets.grid.ListGridRecord;

public class TransferActionRecord extends ListGridRecord {

	public TransferActionRecord(Long id, TransferAction transferAction, String carRequired, Date transferDate) {
		setId(id);
		setObject(transferAction);
		setCarRequired(carRequired);
		setTransferDate(transferDate);
	}

	public String getCarRequired() {
		return getAttributeAsString("carRequired");
	}

	public Long getId() {
		return getAttributeAsLong("id");
	}

	public TransferAction getObject() {
		return (TransferAction) getAttributeAsObject("transferAction");
	}

	public Date getTransferDate() {
		return getAttributeAsDate("transferDate");
	}

	public void setCarRequired(String carRequired) {
		setAttribute("carRequired", carRequired);
	}

	public void setId(Long id) {
		setAttribute("id", id);
	}

	public void setObject(TransferAction transferAction) {
		setAttribute("transferAction", transferAction);
	}

	public void setTransferDate(Date transferDate) {
		setAttribute("transferDate", transferDate);
	}

}
