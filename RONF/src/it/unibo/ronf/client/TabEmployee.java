package it.unibo.ronf.client;

import com.google.gwt.user.client.ui.RootPanel;
import com.smartgwt.client.types.Alignment;
import com.smartgwt.client.types.Autofit;
import com.smartgwt.client.widgets.grid.ListGrid;
import com.smartgwt.client.widgets.grid.ListGridField;
import com.smartgwt.client.widgets.layout.VLayout;

/**
 * Tabella, con live-filter
 * 
 * @author Alessio De Vita alessio.dv@gmail.com
 */
public class TabEmployee extends ListGrid {
	final static VLayout vPanel = new VLayout();
	final static RootPanel rp = RootPanel.get("content");

	public TabEmployee() {

		/** Creo una nuovo oggetto DataSource e gli passo questa listGrid */
		if (EmployeeDS.getInstance(TabEmployee.this) != null) {
			rp.clear();
			rp.add(vPanel);
		}

	}

	/**
	 * funzione che viene chiamata nell'EmployeeDS solo una volta che la
	 * chiamata Asincrona ha avuto successo
	 */
	static void setdata(EmployeeDS data, TabEmployee tabEmployee) {
		tabEmployee.setWidth("99%");
		vPanel.setWidth100();
		tabEmployee.setHeight(400);
		tabEmployee.setShowFilterEditor(true);
		tabEmployee.setFilterOnKeypress(true);
		tabEmployee.setDataSource(data);
		tabEmployee.setAutoFetchData(true);
		ListGridField nameField = new ListGridField("name", "Nome");
		ListGridField surnameField = new ListGridField("surname", "Cognome");
		ListGridField ageField = new ListGridField("age", "Età");
		ageField.setAlign(Alignment.LEFT);
		ListGridField userNameField = new ListGridField("username", "Username");

		tabEmployee.setFields(new ListGridField[] { nameField, surnameField,
				ageField, userNameField });
		vPanel.addChild(tabEmployee);
		rp.clear();
		rp.add(vPanel);
	}

}
