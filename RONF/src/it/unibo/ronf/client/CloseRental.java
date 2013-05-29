package it.unibo.ronf.client;

import com.smartgwt.client.widgets.Dialog;
import com.smartgwt.client.widgets.form.DynamicForm;
import com.smartgwt.client.widgets.form.fields.MultiComboBoxItem;
import com.smartgwt.client.widgets.form.fields.TextItem;

public class CloseRental extends Dialog{
	
	private DynamicForm closeRentalForm;
	private TextItem userItem;
	private TextItem carItem;
	private MultiComboBoxItem maintenanceTypeItem;
	
	public CloseRental(String user, String car, String start, String end) {
		
		closeRentalForm = new DynamicForm();
		userItem = new TextItem("user","User");
		carItem = new TextItem("car", "Car");
		userItem.setValue(user);
		userItem.setCanEdit(false);
		carItem.setValue(car);
		carItem.setCanEdit(false);
		maintenanceTypeItem = new MultiComboBoxItem("maintenanceType", "Tipi di Manutenzione");
		/** servizio manutenzione */
		
		
		
		
	}
}
