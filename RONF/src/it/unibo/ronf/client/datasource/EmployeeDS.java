package it.unibo.ronf.client.datasource;

import it.unibo.ronf.client.record.GridRecord;
import it.unibo.ronf.client.table.TabEmployee;
import it.unibo.ronf.shared.entities.Employee;
import it.unibo.ronf.shared.services.EmployeeService;
import it.unibo.ronf.shared.services.EmployeeServiceAsync;

import java.util.List;

import com.google.gwt.core.client.GWT;
import com.google.gwt.user.client.Window;
import com.google.gwt.user.client.rpc.AsyncCallback;
import com.smartgwt.client.data.DataSource;
import com.smartgwt.client.data.fields.DataSourceIntegerField;
import com.smartgwt.client.data.fields.DataSourcePasswordField;
import com.smartgwt.client.data.fields.DataSourceTextField;

/**
 * DataSource per il live-filter
 * 
 * @author Alessio De Vita alessio.dv@gmail.com
 */
public class EmployeeDS extends DataSource {

	private static EmployeeDS instance = null;
	private final EmployeeServiceAsync employeeService = GWT.create(EmployeeService.class);
	private static GridRecord[] employeeRecord;

	public static EmployeeDS getInstance(TabEmployee tabEmployee) {
		if (instance == null) {
			instance = new EmployeeDS("employeeDS", tabEmployee);
		}
		return instance;
	}

	private EmployeeDS(String id, final TabEmployee tabEmployee) {

		setID(id);
		DataSourceIntegerField pkField = new DataSourceIntegerField("id");
		pkField.setPrimaryKey(true);

		DataSourceTextField nameField = new DataSourceTextField("name", "Name");
		nameField.setRequired(true);

		DataSourceTextField surnameField = new DataSourceTextField("surname", "Surname");
		surnameField.setRequired(true);

		DataSourcePasswordField passwordField = new DataSourcePasswordField("password", "Password");
		passwordField.setRequired(true);

		DataSourceIntegerField ageField = new DataSourceIntegerField("age", "Age");
		ageField.setRequired(true);
		DataSourceTextField userNameField = new DataSourceTextField("userName", "Username");
		userNameField.setRequired(true);

		setFields(pkField, nameField, surnameField, passwordField, ageField, userNameField);

		/** Effettuo la richiesta per la ricerca di tutti gli employee */
		employeeService.findAll(new AsyncCallback<List<Employee>>() {

			@Override
			public void onFailure(Throwable caught) {
				Window.alert("fallito");
			}

			/**
			 * In caso di successo creo un nuovo EmployeeRecord e itero su tutto il DB
			 */
			@Override
			public void onSuccess(List<Employee> result) {
				employeeRecord = new GridRecord[result.size()];

				int i = 0;
				for (Employee p : result) {
					employeeRecord[i] = new GridRecord(p.getId(), p.getName(), p.getSurname(), p.getAge(), p.getUserName());
					i++;

				}

				setTestData(employeeRecord);
				/**
				 * Una volta essermi assicurato che la chiamata Asincrona ha avuto successo, posso
				 * mandare i dati alla ListGrid
				 */
				TabEmployee.setData(EmployeeDS.this, tabEmployee);

			}
		});

		setClientOnly(true);
	}
}