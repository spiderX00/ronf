package it.unibo.ronf.client.datasource;

import it.unibo.ronf.client.record.RentalRecord;
import it.unibo.ronf.client.table.TabRental;
import it.unibo.ronf.shared.entities.Agency;
import it.unibo.ronf.shared.entities.Customer;
import it.unibo.ronf.shared.entities.Rental;
import it.unibo.ronf.shared.services.RentalService;
import it.unibo.ronf.shared.services.RentalServiceAsync;

import java.util.HashMap;
import java.util.List;
import java.util.Map;

import com.google.gwt.core.client.GWT;
import com.google.gwt.user.client.Window;
import com.google.gwt.user.client.rpc.AsyncCallback;
import com.smartgwt.client.data.DataSource;
import com.smartgwt.client.data.fields.DataSourceBooleanField;
import com.smartgwt.client.data.fields.DataSourceDateField;
import com.smartgwt.client.data.fields.DataSourceEnumField;
import com.smartgwt.client.data.fields.DataSourceIntegerField;
import com.smartgwt.client.data.fields.DataSourceTextField;

public class RentalDS extends DataSource {

	private static RentalDS instance = null;
	private final RentalServiceAsync rentalService = GWT.create(RentalService.class);
	private static RentalRecord[] rentalRecord;
	Map<String, Customer> customersMap = new HashMap<String, Customer>();
	Map<String, Agency> agencyMap = new HashMap<String, Agency>();

	public static RentalDS getInstance(TabRental tabRental, Map<String, Customer> customersMap, Map<String, Agency> agencyMap) {
		if (instance == null) {
			instance = new RentalDS("rentalDS", tabRental, customersMap, agencyMap);
		}
		return instance;
	}

	public RentalDS(String id, final TabRental tabRental, Map<String, Customer> customersMap, Map<String, Agency> agencyMap) {

		setID(id);
		DataSourceIntegerField idField = new DataSourceIntegerField("id", "ID");
		idField.setPrimaryKey(true);
		DataSourceDateField startField = new DataSourceDateField("start", "Data Inizio");
		startField.setRequired(true);
		DataSourceDateField endField = new DataSourceDateField("end", "Data Fine");
		endField.setRequired(true);

		DataSourceTextField rentedCarField = new DataSourceTextField("rentedCar", "Macchina");

		DataSourceEnumField customerField = new DataSourceEnumField("customer", "Cliente");
		customerField.setRequired(true);
		if (customersMap != null) {
			customerField.setValueMap(customersMap.keySet().toArray(new String[] {}));
		}
		final DataSourceEnumField startingAgencyField = new DataSourceEnumField("startingAgency", "Agenzia di partenza");
		startingAgencyField.setRequired(true);
		if (agencyMap != null) {
			startingAgencyField.setValueMap(agencyMap.keySet().toArray(new String[] {}));
		}
		final DataSourceEnumField arrivalAgencyField = new DataSourceEnumField("arrivalAgency", "Agenzia di arrivo");
		arrivalAgencyField.setRequired(true);
		if (agencyMap != null) {
			arrivalAgencyField.setValueMap(agencyMap.keySet().toArray(new String[] {}));
		}

		DataSourceIntegerField optionalField = new DataSourceIntegerField("optional", "N.Optional");
		DataSourceIntegerField cautionField = new DataSourceIntegerField("caution", "Cauzione");
		cautionField.setRequired(true);

		DataSourceBooleanField finishedField = new DataSourceBooleanField("finished", "Concluso");

		setFields(idField, startField, endField, rentedCarField, customerField, startingAgencyField, arrivalAgencyField, optionalField, cautionField, finishedField);

		/** Effettuo la richiesta per la ricerca di tutti gli employee */
		rentalService.findAll(new AsyncCallback<List<Rental>>() {

			@Override
			public void onFailure(Throwable caught) {
				Window.alert("fallito");
			}

			/**
			 * In caso di successo creo un nuovo EmployeeRecord e itero su tutto il DB
			 */
			@Override
			public void onSuccess(List<Rental> result) {
				rentalRecord = new RentalRecord[result.size()];

				int i = 0;
				for (Rental p : result) {
					rentalRecord[i] = new RentalRecord(p.getId(), p.getStart(), p.getEnd(), p.getRentedCar().getModel(), ("" + p.getCustomer().getId() + " - " + p.getCustomer().getName()), ("" + p.getStartingAgency().getId() + " - " + p.getStartingAgency().getName()), ("" + p.getArrivalAgency().getId() + " - " + p.getArrivalAgency().getName()), p.getOptional().size(), "pagamento", p.getCaution(), p.isFinished(), p);
					i++;

				}

				setTestData(rentalRecord);
				/**
				 * Una volta essermi assicurato che la chiamata Asincrona ha avuto successo, posso
				 * mandare i dati alla ListGrid
				 */
				TabRental.setData(RentalDS.this, tabRental);

			}
		});

		setClientOnly(true);
	}
}
