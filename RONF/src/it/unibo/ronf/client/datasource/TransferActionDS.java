package it.unibo.ronf.client.datasource;

import it.unibo.ronf.client.record.TransferActionRecord;
import it.unibo.ronf.client.record.TransferRecord;
import it.unibo.ronf.shared.entities.TransferAction;
import it.unibo.ronf.shared.services.TransferService;
import it.unibo.ronf.shared.services.TransferServiceAsync;

import java.util.ArrayList;
import java.util.List;

import com.google.gwt.core.client.GWT;
import com.smartgwt.client.data.DataSource;
import com.smartgwt.client.data.fields.DataSourceDateField;
import com.smartgwt.client.data.fields.DataSourceIntegerField;
import com.smartgwt.client.data.fields.DataSourceTextField;

public class TransferActionDS extends DataSource {

	private static TransferActionDS instance = null;
	private static TransferActionRecord[] transferActionRecord;

	public static TransferActionDS getInstance(TransferRecord record) {
		if (instance == null) {
			instance = new TransferActionDS("transferActionDS", record);
		}
		return instance;
	}

	private TransferActionDS(String id, TransferRecord record) {

		setID(id);
		DataSourceIntegerField pkField = new DataSourceIntegerField("id");
		pkField.setPrimaryKey(true);

		DataSourceTextField carField = new DataSourceTextField("carRequired", "Car");
		carField.setRequired(true);

		DataSourceTextField employeeField = new DataSourceTextField("employee", "Employee");
		employeeField.setRequired(true);

		DataSourceDateField transferDateField = new DataSourceDateField("transferDate", "Data trasferimento");
		transferDateField.setRequired(true);

		setFields(pkField, carField, employeeField, transferDateField);

		/** Effettuo la richiesta per la ricerca di tutti gli employee */
		List<TransferAction> transferActionList = new ArrayList<TransferAction>();
		transferActionList = record.getObject().getTransfers();
		int i = 0;
		for (TransferAction t : transferActionList) {
			transferActionRecord[i] = new TransferActionRecord(t.getId(), t, t.getRequiredCar().getModel(), t.getTransferDate());
			i++;
		}

		setTestData(transferActionRecord);

		setClientOnly(true);
	}
}
