package it.unibo.ronf.client.datasource;

import it.unibo.ronf.client.record.TransferEmployeeRecord;
import it.unibo.ronf.client.table.TabTransferEmployee;
import it.unibo.ronf.shared.entities.TransferEmployee;
import it.unibo.ronf.shared.services.TransferEmployeeService;
import it.unibo.ronf.shared.services.TransferEmployeeServiceAsync;

import java.util.List;

import com.google.gwt.core.client.GWT;
import com.google.gwt.user.client.Window;
import com.google.gwt.user.client.rpc.AsyncCallback;
import com.smartgwt.client.data.DataSource;
import com.smartgwt.client.data.fields.DataSourceIntegerField;
import com.smartgwt.client.data.fields.DataSourcePasswordField;
import com.smartgwt.client.data.fields.DataSourceTextField;

public class TransferEmployeeDS extends DataSource {
	private static TransferEmployeeDS instance = null;
	private final TransferEmployeeServiceAsync transferEmployeeService = GWT.create(TransferEmployeeService.class);
	private static TransferEmployeeRecord[] transferEmployeeRecord;

	public static TransferEmployeeDS getInstance(TabTransferEmployee tabTransferEmployee) {
		if (instance == null) {
			instance = new TransferEmployeeDS("transferEmployeeDS", tabTransferEmployee);
		}
		return instance;
	}

	private TransferEmployeeDS(String id, final TabTransferEmployee tabTransferEmployee) {

		setID(id);
		DataSourceIntegerField pkField = new DataSourceIntegerField("id");
		pkField.setPrimaryKey(true);

		DataSourceTextField nameField = new DataSourceTextField("name", "Nome");
		nameField.setRequired(true);

		DataSourceTextField surnameField = new DataSourceTextField("surname", "Cognome");
		surnameField.setRequired(true);

		DataSourcePasswordField passwordField = new DataSourcePasswordField("password", "Password");
		passwordField.setRequired(true);

		DataSourceIntegerField ageField = new DataSourceIntegerField("age", "Età");
		ageField.setRequired(true);

		setFields(pkField, nameField, surnameField, passwordField, ageField);

		/** Effettuo la richiesta per la ricerca di tutti gli employee */
		transferEmployeeService.findAll(new AsyncCallback<List<TransferEmployee>>() {

			@Override
			public void onFailure(Throwable caught) {
				Window.alert("fallito");
			}

			/**
			 * In caso di successo creo un nuovo EmployeeRecord e itero su tutto
			 * il DB
			 */
			public void onSuccess(List<TransferEmployee> result) {
				transferEmployeeRecord = new TransferEmployeeRecord[result.size()];

				int i = 0;
				for (TransferEmployee p : result) {
					transferEmployeeRecord[i] = new TransferEmployeeRecord(p.getId(), p.getName(), p.getSurname(), p.getAge(), p.isBusy(), p);
					i++;

				}

				setTestData(transferEmployeeRecord);
				/**
				 * Una volta essermi assicurato che la chiamata Asincrona ha
				 * avuto successo, posso mandare i dati alla ListGrid
				 */
				TabTransferEmployee.setData(TransferEmployeeDS.this, tabTransferEmployee);

			}
		});

		setClientOnly(true);
	}
}
