package it.unibo.ronf.client.makedialog;

import it.unibo.ronf.client.record.RentalRecord;
import it.unibo.ronf.shared.entities.Maintenance;
import it.unibo.ronf.shared.entities.MaintenanceType;
import it.unibo.ronf.shared.entities.Payment;
import it.unibo.ronf.shared.entities.Rental;
import it.unibo.ronf.shared.services.FineService;
import it.unibo.ronf.shared.services.FineServiceAsync;
import it.unibo.ronf.shared.services.MaintenanceTypeService;
import it.unibo.ronf.shared.services.MaintenanceTypeServiceAsync;
import it.unibo.ronf.shared.services.RentalService;
import it.unibo.ronf.shared.services.RentalServiceAsync;

import java.util.ArrayList;
import java.util.HashMap;
import java.util.List;
import java.util.Map;

import com.google.gwt.core.client.GWT;
import com.google.gwt.user.client.Window;
import com.google.gwt.user.client.rpc.AsyncCallback;
import com.smartgwt.client.types.Alignment;
import com.smartgwt.client.types.MultiComboBoxLayoutStyle;
import com.smartgwt.client.util.SC;
import com.smartgwt.client.widgets.Button;
import com.smartgwt.client.widgets.Dialog;
import com.smartgwt.client.widgets.events.ClickEvent;
import com.smartgwt.client.widgets.events.ClickHandler;
import com.smartgwt.client.widgets.form.DynamicForm;
import com.smartgwt.client.widgets.form.fields.MultiComboBoxItem;
import com.smartgwt.client.widgets.form.fields.TextItem;
import com.smartgwt.client.widgets.layout.HLayout;

public class CloseRental extends Dialog {

	private DynamicForm closeRentalForm;
	private TextItem userItem;
	private TextItem carItem;
	private MultiComboBoxItem maintenanceTypeItem;
	private final MaintenanceTypeServiceAsync maintenanceTypeService = GWT.create(MaintenanceTypeService.class);
	private final RentalServiceAsync rentalService = GWT.create(RentalService.class);
	private final FineServiceAsync fineService = GWT.create(FineService.class);
	private Map<String, MaintenanceType> maintenanceTypeMap = new HashMap<String, MaintenanceType>();
	private Button confirmButton;
	private Button cancelButton;
	private HLayout hLayout;

	public CloseRental(final RentalRecord rentalRecord) {
		final Rental rental = rentalRecord.getObject();
		String user = rentalRecord.getCustomer();
		String car = rentalRecord.getRentedCar();
		@SuppressWarnings("deprecation")
		String start = rentalRecord.getStart().toLocaleString().substring(0, 11);
		@SuppressWarnings("deprecation")
		String end = rentalRecord.getEnd().toLocaleString().substring(0, 11);
		setTitle("" + start + " - " + end);
		setSize("340px", "300px");
		hLayout = new HLayout();
		hLayout.setSize("290px", "46px");
		hLayout.setMembersMargin(40);
		closeRentalForm = new DynamicForm();
		closeRentalForm.setSize("250px", "194px");
		userItem = new TextItem("user", "User");
		carItem = new TextItem("car", "Car");
		userItem.setValue(user);
		userItem.setCanEdit(false);
		carItem.setValue(car);
		carItem.setCanEdit(false);
		confirmButton = new Button("OK");
		cancelButton = new Button("Cancel");
		maintenanceTypeItem = new MultiComboBoxItem("maintenanceType", "Tipi di Manutenzione");
		closeRentalForm.setFields(userItem, carItem, maintenanceTypeItem);
		addItem(closeRentalForm);
		hLayout.addMember(cancelButton);
		cancelButton.setAlign(Alignment.CENTER);
		hLayout.addMember(confirmButton);
		confirmButton.setAlign(Alignment.CENTER);

		/** servizio manutenzione */
		maintenanceTypeService.findAll(new AsyncCallback<List<MaintenanceType>>() {

			@Override
			public void onFailure(Throwable caught) {
				Window.alert("Impossible to load type of maintenance:" + caught.getMessage());

			}

			@Override
			public void onSuccess(List<MaintenanceType> result) {
				for (MaintenanceType mt : result) {
					maintenanceTypeMap.put(mt.getName(), mt);
				}
				maintenanceTypeItem.setValueMap(maintenanceTypeMap.keySet().toArray(new String[] {}));
				maintenanceTypeItem.setLayoutStyle(MultiComboBoxLayoutStyle.VERTICAL);
			}
		});
		confirmButton.addClickHandler(new ClickHandler() {

			@Override
			public void onClick(ClickEvent event) {
				rental.setFinished(true);
				List<MaintenanceType> maintenanceTypeList = new ArrayList<MaintenanceType>();
				String s = maintenanceTypeItem.getDisplayValue();
				if (!s.trim().isEmpty()) {
					String[] optional = s.split(",");
					for (String o : optional) {
						maintenanceTypeList.add(maintenanceTypeMap.get(o));
					}
					Maintenance maintenance = new Maintenance();
					maintenance.setCar(rental.getRentedCar());
					maintenance.setDate(rental.getEnd());
					maintenance.setMaintenances(maintenanceTypeList);
					fineService.calculateFine(maintenance, new AsyncCallback<Payment>() {

						@Override
						public void onFailure(Throwable caught) {
							Window.alert("Impossible to calculate Fine!!!");
						}

						@Override
						public void onSuccess(Payment result) {
							rental.setFine(result);
							rentalService.updateRental(rental, new AsyncCallback<Void>() {

								@Override
								public void onFailure(Throwable caught) {
									Window.alert("Error to update rental!!");

								}

								@Override
								public void onSuccess(Void result) {

								}
							});

						}
					});
				}
				SC.say("Rental concluded");
				CloseRental.this.hide();
			}
		});

		cancelButton.addClickHandler(new ClickHandler() {

			@Override
			public void onClick(ClickEvent event) {
				CloseRental.this.hide();

			}
		});
		addItem(hLayout);
		hLayout.moveTo(30, 231);
	}
}
