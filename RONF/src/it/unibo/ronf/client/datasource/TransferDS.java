package it.unibo.ronf.client.datasource;

import it.unibo.ronf.client.record.TransferRecord;
import it.unibo.ronf.client.table.TabTransfer;
import it.unibo.ronf.shared.entities.Agency;
import it.unibo.ronf.shared.entities.Transfer;
import it.unibo.ronf.shared.services.TransferService;
import it.unibo.ronf.shared.services.TransferServiceAsync;

import java.util.List;
import java.util.Map;

import com.google.gwt.core.client.GWT;
import com.google.gwt.user.client.Window;
import com.google.gwt.user.client.rpc.AsyncCallback;
import com.smartgwt.client.data.DataSource;
import com.smartgwt.client.data.fields.DataSourceBooleanField;
import com.smartgwt.client.data.fields.DataSourceEnumField;
import com.smartgwt.client.data.fields.DataSourceIntegerField;

public class TransferDS extends DataSource {

	private static TransferDS instance = null;
	private final TransferServiceAsync transferService = GWT.create(TransferService.class);
	private static TransferRecord[] transferRecord;

	public static TransferDS getInstance(TabTransfer tabTransfer, Map<String, Agency> agencyMap) {
		if (instance == null) {
			instance = new TransferDS("transferDS", tabTransfer, agencyMap);
		}
		return instance;
	}

	public TransferDS(String id, final TabTransfer tabTransfer, Map<String, Agency> agencyMap) {

		setID(id);
		DataSourceIntegerField idField = new DataSourceIntegerField("id", "ID");
		idField.setPrimaryKey(true);

		final DataSourceEnumField startingAgencyField = new DataSourceEnumField("startAgency", "Agenzia di partenza");
		startingAgencyField.setRequired(true);
		if (agencyMap != null) {
			startingAgencyField.setValueMap(agencyMap.keySet().toArray(new String[] {}));
		}
		final DataSourceEnumField arrivalAgencyField = new DataSourceEnumField("arrivalAgency", "Agenzia di arrivo");
		arrivalAgencyField.setRequired(true);
		if (agencyMap != null) {
			arrivalAgencyField.setValueMap(agencyMap.keySet().toArray(new String[] {}));
		}
		DataSourceBooleanField successField = new DataSourceBooleanField("success", "Concluso");

		setFields(idField, startingAgencyField, arrivalAgencyField, successField);
		/** Effettuo la richiesta per la ricerca di tutti gli employee */
		transferService.findAll(new AsyncCallback<List<Transfer>>() {

			@Override
			public void onFailure(Throwable caught) {
				Window.alert("fallito");
			}

			/**
			 * In caso di successo creo un nuovo EmployeeRecord e itero su tutto il DB
			 */
			public void onSuccess(List<Transfer> result) {
				transferRecord = new TransferRecord[result.size()];

				int i = 0;
				for (Transfer p : result) {
					transferRecord[i] = new TransferRecord(p, p.getId(), p.getTransfers().size(), p.getStartAgency().getName(), p.getArrivalAgency().getName(), p.isSuccess());
					i++;

				}

				setTestData(transferRecord);
				/**
				 * Una volta essermi assicurato che la chiamata Asincrona ha avuto successo, posso
				 * mandare i dati alla ListGrid
				 */
				TabTransfer.setData(TransferDS.this, tabTransfer);

			}
		});

		setClientOnly(true);
	}
}
