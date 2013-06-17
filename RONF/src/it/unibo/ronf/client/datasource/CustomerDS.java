package it.unibo.ronf.client.datasource;

import it.unibo.ronf.client.record.GridRecord;
import it.unibo.ronf.client.table.TabCustomer;
import it.unibo.ronf.shared.entities.Customer;
import it.unibo.ronf.shared.services.CustomerService;
import it.unibo.ronf.shared.services.CustomerServiceAsync;

import java.util.List;

import com.google.gwt.core.client.GWT;
import com.google.gwt.user.client.Window;
import com.google.gwt.user.client.rpc.AsyncCallback;
import com.smartgwt.client.data.DataSource;
import com.smartgwt.client.data.fields.DataSourceIntegerField;
import com.smartgwt.client.data.fields.DataSourceTextField;
import com.smartgwt.client.widgets.form.validator.IntegerRangeValidator;

/**
 * DataSource per il live-filter
 * 
 * @author Alessio De Vita alessio.dv@gmail.com
 */
public class CustomerDS extends DataSource {

	private static CustomerDS instance = null;
	private final CustomerServiceAsync customerService = GWT.create(CustomerService.class);
	private static GridRecord[] customerRecord;

	public static CustomerDS getInstance(TabCustomer tabCustomer) {
		if (instance == null) {
			instance = new CustomerDS("customerDS", tabCustomer);
		}
		return instance;
	}

	public CustomerDS(String id, final TabCustomer tabCustomer) {

		setID(id);
		DataSourceIntegerField idField = new DataSourceIntegerField("id", "ID");
		idField.setPrimaryKey(true);

		DataSourceTextField nameField = new DataSourceTextField("name", "Name");
		nameField.setRequired(true);

		DataSourceTextField surnameField = new DataSourceTextField("surname", "Surname");
		surnameField.setRequired(true);

		DataSourceIntegerField ageField = new DataSourceIntegerField("age", "Age");
		ageField.setRequired(true);
		IntegerRangeValidator rangeValidator = new IntegerRangeValidator();
		rangeValidator.setMin(0);
		ageField.setValidators(rangeValidator);
		DataSourceTextField fiscalCodeField = new DataSourceTextField("fiscalCode", "Fiscal code");
		fiscalCodeField.setRequired(true);

		DataSourceTextField docNumberField = new DataSourceTextField("docNumber", "Document n.");
		docNumberField.setRequired(true);

		setFields(idField, nameField, surnameField, ageField, fiscalCodeField, docNumberField);
		/** Effettuo la richiesta per la ricerca di tutti gli employee */
		customerService.findAll(new AsyncCallback<List<Customer>>() {

			@Override
			public void onFailure(Throwable caught) {
				Window.alert("fallito");
			}

			/**
			 * In caso di successo creo un nuovo EmployeeRecord e itero su tutto il DB
			 */
			@Override
			public void onSuccess(List<Customer> result) {
				customerRecord = new GridRecord[result.size()];

				int i = 0;
				for (Customer p : result) {
					customerRecord[i] = new GridRecord(p.getId(), p.getName(), p.getSurname(), p.getAge(), p.getFiscalCode(), p.getDocNumber());
					i++;

				}

				setTestData(customerRecord);
				/**
				 * Una volta essermi assicurato che la chiamata Asincrona ha avuto successo, posso
				 * mandare i dati alla ListGrid
				 */
				TabCustomer.setData(CustomerDS.this, tabCustomer);

			}
		});

		setClientOnly(true);
	}
}