package it.unibo.ronf.client.datasource;

import it.unibo.ronf.client.record.CarRecord;
import it.unibo.ronf.client.table.TabCar;
import it.unibo.ronf.shared.entities.Agency;
import it.unibo.ronf.shared.entities.Car;
import it.unibo.ronf.shared.entities.Employee;
import it.unibo.ronf.shared.services.CarService;
import it.unibo.ronf.shared.services.CarServiceAsync;

import java.util.List;
import java.util.Map;

import com.google.gwt.core.client.GWT;
import com.google.gwt.user.client.Window;
import com.google.gwt.user.client.rpc.AsyncCallback;
import com.smartgwt.client.data.DataSource;
import com.smartgwt.client.data.fields.DataSourceEnumField;
import com.smartgwt.client.data.fields.DataSourceIntegerField;
import com.smartgwt.client.data.fields.DataSourceTextField;

public class CarDS extends DataSource {

	private static CarDS instance = null;
	private final CarServiceAsync carService = GWT.create(CarService.class);
	private static CarRecord[] carRecord;

	public static CarDS getInstance(TabCar tabCar, Map<String, Agency> agencyMap) {
		if (instance == null) {
			instance = new CarDS("carDS", tabCar, agencyMap);
		}
		return instance;
	}

	public CarDS(String id, final TabCar tabCar, Map<String, Agency> agencyMap) {

		setID(id);
		DataSourceIntegerField idField = new DataSourceIntegerField("id");
		idField.setPrimaryKey(true);

		DataSourceTextField modelField = new DataSourceTextField("model", "Model");
		modelField.setRequired(true);

		DataSourceTextField plateField = new DataSourceTextField("plate", "Plate");
		plateField.setRequired(true);

		DataSourceTextField gasolineTypeField = new DataSourceTextField("gasolineType", "Gasoline type");
		gasolineTypeField.setRequired(true);

		DataSourceIntegerField seatsNumberField = new DataSourceIntegerField("seatsNumber", "Seats n.");
		seatsNumberField.setRequired(true);
		DataSourceEnumField agencyField = new DataSourceEnumField("agency", "Agency");
		agencyField.setRequired(true);

		DataSourceTextField typeField = new DataSourceTextField("type", "Type");
		if (agencyMap != null) {
			agencyField.setValueMap(agencyMap.keySet().toArray(new String[] {}));
		}

		setFields(idField, modelField, plateField, gasolineTypeField, seatsNumberField, agencyField, typeField);

		/** Effettuo la richiesta per la ricerca di tutti gli employee */
		carService.findAll(new AsyncCallback<List<Car>>() {

			@Override
			public void onFailure(Throwable caught) {
				Window.alert("fallito");
			}

			/**
			 * In caso di successo creo un nuovo EmployeeRecord e itero su tutto il DB
			 */
			@Override
			public void onSuccess(List<Car> result) {
				carRecord = new CarRecord[result.size()];

				int i = 0;
				for (Car p : result) {
					carRecord[i] = new CarRecord(p.getId(), p.getModel(), p.getPlate(), p.getGasolineType(), p.getSeatsNumber(), p.getOriginAgency().getName(), p.getType().getType());
					i++;

				}

				setTestData(carRecord);
				/**
				 * Una volta essermi assicurato che la chiamata Asincrona ha avuto successo, posso
				 * mandare i dati alla ListGrid
				 */
				TabCar.setData(CarDS.this, tabCar);

			}
		});

		setClientOnly(true);
	}

}
