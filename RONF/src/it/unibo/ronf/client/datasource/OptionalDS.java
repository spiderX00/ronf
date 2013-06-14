package it.unibo.ronf.client.datasource;

import it.unibo.ronf.client.record.OptionalRecord;
import it.unibo.ronf.client.table.TabOptional;
import it.unibo.ronf.shared.entities.Optional;
import it.unibo.ronf.shared.services.OptionalService;
import it.unibo.ronf.shared.services.OptionalServiceAsync;

import java.util.List;

import com.google.gwt.core.client.GWT;
import com.google.gwt.user.client.Window;
import com.google.gwt.user.client.rpc.AsyncCallback;
import com.smartgwt.client.data.DataSource;
import com.smartgwt.client.data.fields.DataSourceIntegerField;
import com.smartgwt.client.data.fields.DataSourceTextField;
import com.smartgwt.client.widgets.form.validator.FloatPrecisionValidator;
import com.smartgwt.client.widgets.form.validator.FloatRangeValidator;

/**
 * DataSource per il live-filter
 * 
 * @author Alessio De Vita alessio.dv@gmail.com
 */
public class OptionalDS extends DataSource {

	private static OptionalDS instance = null;
	private final OptionalServiceAsync optionalService = GWT.create(OptionalService.class);
	private static OptionalRecord[] optionalRecord;

	public static OptionalDS getInstance(TabOptional tabOptional) {
		if (instance == null) {
			instance = new OptionalDS("optionalDS", tabOptional);
		}
		return instance;
	}

	public OptionalDS(String id, final TabOptional tabOptional) {

		setID(id);
		DataSourceIntegerField pkField = new DataSourceIntegerField("id");
		pkField.setPrimaryKey(true);

		DataSourceTextField nameField = new DataSourceTextField("name", "Nome");
		nameField.setRequired(true);

		DataSourceIntegerField costField = new DataSourceIntegerField("cost", "Prezzo");
		costField.setRequired(true);
		FloatRangeValidator rangeValidator = new FloatRangeValidator();
		rangeValidator.setMin(0);
		rangeValidator.setErrorMessage("Please enter a valid (positive) cost");

		FloatPrecisionValidator precisionValidator = new FloatPrecisionValidator();
		precisionValidator.setPrecision(2);
		precisionValidator.setErrorMessage("The maximum allowed precision is 2");
		/** Restrizioni su numeri negativi ed oltre le due cifre dopo la virgola */
		costField.setValidators(rangeValidator, precisionValidator);

		DataSourceTextField descriptionField = new DataSourceTextField("description", "Descrizione");
		descriptionField.setRequired(true);

		setFields(pkField, nameField, costField, descriptionField);
		/** Effettuo la richiesta per la ricerca di tutti gli employee */
		optionalService.findAll(new AsyncCallback<List<Optional>>() {

			@Override
			public void onFailure(Throwable caught) {
				Window.alert("fallito");
			}

			/**
			 * In caso di successo creo un nuovo EmployeeRecord e itero su tutto il DB
			 */
			@Override
			public void onSuccess(List<Optional> result) {
				optionalRecord = new OptionalRecord[result.size()];

				int i = 0;
				for (Optional p : result) {
					optionalRecord[i] = new OptionalRecord(p.getId(), p.getName(), p.getCost(), p.getDescription());
					i++;

				}

				setTestData(optionalRecord);
				/**
				 * Una volta essermi assicurato che la chiamata Asincrona ha avuto successo, posso
				 * mandare i dati alla ListGrid
				 */
				TabOptional.setData(OptionalDS.this, tabOptional);

			}
		});

		setClientOnly(true);
	}
}