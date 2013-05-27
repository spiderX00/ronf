package it.unibo.ronf.client;

import it.unibo.ronf.shared.entities.Agency;
import it.unibo.ronf.shared.entities.Car;
import it.unibo.ronf.shared.entities.CarType;
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

public class RentalDS extends DataSource {

	private static RentalDS instance = null;
	private final RentalServiceAsync rentalService = GWT
			.create(RentalService.class);
	private static RentalRecord[] rentalRecord;
	Map<String, Customer> customersMap = new HashMap<String, Customer>();
	Map<String, Agency> agencyMap = new HashMap<String, Agency>();
	Map<String, Car> carMap = new HashMap<String, Car>();

	public static RentalDS getInstance(Map<String, Car> carMap,
			Map<String, Customer> customersMap, Map<String, Agency> agencyMap,
			Map<String, CarType> carTypeMap) {
		if (instance == null) {
			instance = new RentalDS("rentalDS", carMap, customersMap,
					agencyMap, carTypeMap);
		}
		return instance;
	}

	public RentalDS(String id, Map<String, Car> carMap,
			Map<String, Customer> customersMap, Map<String, Agency> agencyMap,
			Map<String, CarType> carTypeMap) {

		setID(id);
		DataSourceIntegerField idField = new DataSourceIntegerField("id", "ID");
		idField.setPrimaryKey(true);
		DataSourceDateField startField = new DataSourceDateField("start",
				"Data Inizio");
		startField.setRequired(true);
		DataSourceDateField endField = new DataSourceDateField("end",
				"Data Fine");
		endField.setRequired(true);

		DataSourceEnumField rentedCarField = new DataSourceEnumField(
				"rentedCar", "Macchina");
		rentedCarField.setRequired(true);
		if (carMap != null) {
			rentedCarField
					.setValueMap(carMap.keySet().toArray(new String[] {}));
		}
		final DataSourceEnumField carTypeField = new DataSourceEnumField(
				"rentedType", "Tipo Macchina");
		carTypeField.setRequired(true);
		if (carTypeMap != null) {
			carTypeField.setValueMap(carTypeMap.keySet().toArray(
					new String[] {}));
		}
		DataSourceEnumField customerField = new DataSourceEnumField("customer",
				"Cliente");
		customerField.setRequired(true);
		if (customersMap != null) {
			customerField.setValueMap(customersMap.keySet().toArray(
					new String[] {}));
		}
		final DataSourceEnumField startingAgencyField = new DataSourceEnumField(
				"startingAgency", "Agenzia di partenza");
		startingAgencyField.setRequired(true);
		if (agencyMap != null) {
			startingAgencyField.setValueMap(agencyMap.keySet().toArray(
					new String[] {}));
		}
		final DataSourceEnumField arrivalAgencyField = new DataSourceEnumField(
				"arrivalAgency", "Agenzia di arrivo");
		arrivalAgencyField.setRequired(true);
		if (agencyMap != null) {
			arrivalAgencyField.setValueMap(agencyMap.keySet().toArray(
					new String[] {}));
		}
		DataSourceIntegerField cautionField = new DataSourceIntegerField(
				"caution", "Cauzione");
		cautionField.setRequired(true);

		DataSourceBooleanField confirmedField = new DataSourceBooleanField(
				"confirmed", "Conferma");

		DataSourceBooleanField finishedField = new DataSourceBooleanField(
				"finished", "Concluso");

		setFields(idField, startField, endField, rentedCarField, carTypeField,
				customerField, startingAgencyField, arrivalAgencyField,
				cautionField, confirmedField, finishedField);

		/** Effettuo la richiesta per la ricerca di tutti gli employee */
		rentalService.findAll(new AsyncCallback<List<Rental>>() {

			@Override
			public void onFailure(Throwable caught) {
				Window.alert("fallito");
			}

			/**
			 * In caso di successo creo un nuovo EmployeeRecord e itero su tutto
			 * il DB
			 */
			public void onSuccess(List<Rental> result) {
				rentalRecord = new RentalRecord[result.size()];

				int i = 0;
				for (Rental p : result) {
					rentalRecord[i] = new RentalRecord(p.getId(), p.getStart(),
							p.getEnd(), p.getRentedCar().getModel(), "carType",
							("" + p.getCustomer().getId() + " - " + p
									.getCustomer().getName()), (""
									+ p.getStartingAgency().getId() + " - " + p
									.getStartingAgency().getName()), (""
									+ p.getArrivalAgency().getId() + " - " + p
									.getArrivalAgency().getName()),
							"pagamento", p.getCaution(), p.isConfirmed(), p
									.isFinished());
					i++;

				}

				setTestData(rentalRecord);
				/**
				 * Una volta essermi assicurato che la chiamata Asincrona ha
				 * avuto successo, posso mandare i dati alla ListGrid
				 */
				MakeRental.setdata(RentalDS.this);

			}
		});

		setClientOnly(true);
	}
}
