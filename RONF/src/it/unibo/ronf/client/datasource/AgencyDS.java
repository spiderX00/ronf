package it.unibo.ronf.client.datasource;

import it.unibo.ronf.client.record.AgencyRecord;
import it.unibo.ronf.client.table.TabAgency;
import it.unibo.ronf.shared.entities.Agency;
import it.unibo.ronf.shared.services.AgencyService;
import it.unibo.ronf.shared.services.AgencyServiceAsync;

import java.util.List;

import com.google.gwt.core.client.GWT;
import com.google.gwt.user.client.Window;
import com.google.gwt.user.client.rpc.AsyncCallback;
import com.smartgwt.client.data.DataSource;
import com.smartgwt.client.data.fields.DataSourceIntegerField;
import com.smartgwt.client.data.fields.DataSourceTextField;

public class AgencyDS extends DataSource {

	private static AgencyDS instance = null;
	private final AgencyServiceAsync agencyService = GWT.create(AgencyService.class);
	private static AgencyRecord[] agencyRecord;

	public static AgencyDS getInstance(TabAgency tabAgency) {
		if (instance == null) {
			instance = new AgencyDS("agencyDS", tabAgency);
		}
		return instance;
	}

	private AgencyDS(String id, final TabAgency tabAgency) {

		setID(id);
		DataSourceIntegerField idField = new DataSourceIntegerField("id", "ID");
		idField.setPrimaryKey(true);

		DataSourceTextField codeField = new DataSourceTextField("code", "Code");
		codeField.setRequired(true);

		DataSourceTextField nameField = new DataSourceTextField("name", "Name");
		nameField.setRequired(true);

		DataSourceTextField addressField = new DataSourceTextField("address", "Address");
		addressField.setRequired(true);

		DataSourceTextField ipAddressField = new DataSourceTextField("ipAddress", "IP");
		ipAddressField.setRequired(true);

		setFields(idField, codeField, nameField, addressField, ipAddressField);
		/** Effettuo la richiesta per la ricerca di tutti gli employee */
		agencyService.findAll(new AsyncCallback<List<Agency>>() {

			@Override
			public void onFailure(Throwable caught) {
				Window.alert("fallito");
			}

			/**
			 * In caso di successo creo un nuovo EmployeeRecord e itero su tutto il DB
			 */
			@Override
			public void onSuccess(List<Agency> result) {
				agencyRecord = new AgencyRecord[result.size()];

				int i = 0;
				for (Agency p : result) {
					agencyRecord[i] = new AgencyRecord(p.getId(), p.getCode(), p.getName(), p.getAddress(), p.getIpAddress());
					i++;

				}

				setTestData(agencyRecord);
				/**
				 * Una volta essermi assicurato che la chiamata Asincrona ha avuto successo, posso
				 * mandare i dati alla ListGrid
				 */
				TabAgency.setData(AgencyDS.this, tabAgency);
			}
		});

		setClientOnly(true);
	}

}
