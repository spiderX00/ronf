package it.unibo.ronf.client;

import it.unibo.ronf.shared.entities.Agency;
import it.unibo.ronf.shared.entities.Car;
import it.unibo.ronf.shared.entities.CarType;
import it.unibo.ronf.shared.entities.Customer;
import it.unibo.ronf.shared.entities.Payment;
import it.unibo.ronf.shared.entities.Rental;
import it.unibo.ronf.shared.services.AgencyService;
import it.unibo.ronf.shared.services.AgencyServiceAsync;
import it.unibo.ronf.shared.services.CarService;
import it.unibo.ronf.shared.services.CarServiceAsync;
import it.unibo.ronf.shared.services.CustomerService;
import it.unibo.ronf.shared.services.CustomerServiceAsync;
import it.unibo.ronf.shared.services.RentalService;
import it.unibo.ronf.shared.services.RentalServiceAsync;

import java.util.Date;
import java.util.HashMap;
import java.util.List;
import java.util.Map;

import com.google.gwt.core.client.GWT;
import com.google.gwt.user.client.DOM;
import com.google.gwt.user.client.Window;
import com.google.gwt.user.client.rpc.AsyncCallback;
import com.google.gwt.user.client.ui.RootPanel;
import com.smartgwt.client.data.DSCallback;
import com.smartgwt.client.data.DSRequest;
import com.smartgwt.client.data.DSResponse;
import com.smartgwt.client.types.Alignment;
import com.smartgwt.client.types.DateDisplayFormat;
import com.smartgwt.client.widgets.Button;
import com.smartgwt.client.widgets.Dialog;
import com.smartgwt.client.widgets.events.ClickEvent;
import com.smartgwt.client.widgets.events.ClickHandler;
import com.smartgwt.client.widgets.form.DynamicForm;
import com.smartgwt.client.widgets.form.fields.DateItem;
import com.smartgwt.client.widgets.form.fields.FloatItem;
import com.smartgwt.client.widgets.form.fields.SectionItem;
import com.smartgwt.client.widgets.form.fields.TextItem;
import com.smartgwt.client.widgets.grid.ListGrid;
import com.smartgwt.client.widgets.grid.ListGridField;
import com.smartgwt.client.widgets.layout.HLayout;
import com.smartgwt.client.widgets.layout.VLayout;

public class MakeRental extends Dialog {

	private final RentalServiceAsync rentalService = GWT
			.create(RentalService.class);
	private final CarServiceAsync carService = GWT.create(CarService.class);
	private final CustomerServiceAsync customerService = GWT
			.create(CustomerService.class);
	private final AgencyServiceAsync agencyService = GWT
			.create(AgencyService.class);
	final static VLayout vPanel = new VLayout();
	final static RootPanel rp = RootPanel.get("content");

	Map<String, Car> carMap = new HashMap<String, Car>();
	Map<String, Customer> customersMap = new HashMap<String, Customer>();
	Map<String, Agency> agencyMap = new HashMap<String, Agency>();
	Map<String, CarType> carTypeMap = new HashMap<String, CarType>();
	Map<String, Payment> paymentMap = new HashMap<String, Payment>();

	private TextItem paymentMethod;
	private FloatItem amount;
	private DateItem dateOfPayment;

	private HLayout hLayout;

	public MakeRental() {
		setSize("370", "500px");
		final DynamicForm dynamicForm = new DynamicForm();
		dynamicForm.setSize("350px", "194px");
		addItem(dynamicForm);
		hLayout = new HLayout();
		hLayout.setSize("351px", "46px");
		hLayout.setMembersMargin(40);
		paymentMethod = new TextItem("paymentMethod", "Metodo di Pagmaneto");
		amount = new FloatItem("amount", "Totale");
		dateOfPayment = new DateItem("dateOfPayment", "Data pagamento");
		SectionItem sectionPayment = new SectionItem();
		sectionPayment.setDefaultValue("Pagamento");
		sectionPayment.setSectionExpanded(false);
		sectionPayment.setItemIds("paymentMethod", "amount", "dateOfPayment");
		final DynamicForm dynamicForm2 = new DynamicForm();
		dynamicForm2.setFields(sectionPayment, amount, paymentMethod,
				dateOfPayment);
		addItem(dynamicForm2);

		carService.findAll(new AsyncCallback<List<Car>>() {

			@Override
			public void onSuccess(List<Car> result) {
				for (Car c : result) {
					carMap.put("" + c.getId() + " - " + c.getModel(), c);

				}
				customerService.findAll(new AsyncCallback<List<Customer>>() {

					@Override
					public void onSuccess(List<Customer> result) {
						for (Customer c : result) {
							customersMap.put(
									"" + c.getId() + " - " + c.getName(), c);
						}
						agencyService
								.findAll(new AsyncCallback<List<Agency>>() {

									@Override
									public void onSuccess(List<Agency> result) {
										for (Agency c : result) {
											agencyMap.put("" + c.getId()
													+ " - " + c.getName(), c);

										}

										CarType carType = new CarType();
										carType.setType("monovolume");
										carType.setDailyCost(Float
												.parseFloat("30"));
										carTypeMap.put("" + carType.getId()
												+ " - " + carType.getType(),
												carType);
										if (RentalDS.getDataSource("rentalDS") != null) {
											RentalDS.getDataSource("rentalDS")
													.destroy();
										}
										dynamicForm.setDataSource(new RentalDS(
												"rentalDS", carMap,
												customersMap, agencyMap,
												carTypeMap));
										dynamicForm.getField("id").hide();

									}

									@Override
									public void onFailure(Throwable caught) {
									}
								});

					}

					@Override
					public void onFailure(Throwable caught) {
					}
				});

			}

			@Override
			public void onFailure(Throwable caught) {
			}
		});

		Button btnCancel = new Button("Cancel");
		btnCancel.setAlign(Alignment.CENTER);
		hLayout.addMember(btnCancel);

		Button btnCrea = new Button("Crea");
		hLayout.addMember(btnCrea);
		hLayout.setAlign(Alignment.CENTER);
		btnCrea.addClickHandler(new ClickHandler() {
			public void onClick(ClickEvent event) {
				/** al click viene creato un nuovo Customer */
				dynamicForm.saveData(new DSCallback() {
					public void execute(DSResponse response, Object rawData,
							DSRequest request) {
						dynamicForm.editNewRecord();
					}
				});

				Rental rental = new Rental();

				rental.setStart((Date) dynamicForm.getValue("start"));
				rental.setEnd((Date) (dynamicForm.getValue("end")));
				rental.setRentedCar(carMap.get(dynamicForm
						.getValueAsString("rentedCar")));
				// rental.setRentedType(carTypeMap.get(dynamicForm.getValueAsString("rentedType")));
				rental.setCustomer(customersMap.get(dynamicForm
						.getValueAsString("customer")));
				rental.setStartingAgency(agencyMap.get(dynamicForm
						.getValueAsString("startingAgency")));
				rental.setArrivalAgency(agencyMap.get(dynamicForm
						.getValueAsString("arrivalAgency")));

				// GWT.log(""+ payment.getPaymentMethod());
				Payment payment = new Payment();
				payment.setAmount(Float.parseFloat(amount.getValueAsString()));
				payment.setPaymentMethod(paymentMethod.getValueAsString());
				payment.setDateOfPayment(dateOfPayment.getValueAsDate());
				// rental.setPayment(payment);
				rental.setCaution(Float.parseFloat(dynamicForm
						.getValueAsString("caution")));
				rental.setConfirmed(Boolean.valueOf(dynamicForm
						.getValueAsString("confirmed")));
				rental.setFinished(Boolean.valueOf(dynamicForm
						.getValueAsString("finished")));
				rentalService.createRental(rental, new AsyncCallback<Void>() {
					@Override
					public void onSuccess(Void result) {
						MakeRental.this.hide();
						Window.alert("Optional Created!");

					}

					@Override
					public void onFailure(Throwable caught) {
						Window.alert("Impossible to create optional : "
								+ caught);
					}
				});

			}
		});

		btnCancel.addClickHandler(new ClickHandler() {

			@Override
			public void onClick(ClickEvent event) {
				MakeRental.this.hide();

			}
		});
		addItem(hLayout);
		hLayout.moveTo(30, 231);

	}

	static void setdata(RentalDS data) {
		ListGrid tabRental = new ListGrid();
		tabRental.setShowRollOverCanvas(true);
		tabRental.setWidth("99%");
		vPanel.setWidth100();
		tabRental.setHeight(400);
		tabRental.setShowFilterEditor(true);
		tabRental.setFilterOnKeypress(true);
		tabRental.setDataSource(data);
		tabRental.setAutoFetchData(true);
		ListGridField idField = new ListGridField("id", "ID");
		idField.setAlign(Alignment.LEFT);
		ListGridField startField = new ListGridField("start", "Data Inizio");
		startField.setDateFormatter(DateDisplayFormat.TOEUROPEANSHORTDATE);
		ListGridField endField = new ListGridField("end", "Data Fine");
		endField.setDateFormatter(DateDisplayFormat.TOEUROPEANSHORTDATE);
		ListGridField rentedCarField = new ListGridField("rentedCar",
				"Macchina");
		rentedCarField.setCanFilter(false);
		ListGridField carTypeField = new ListGridField("rentedType",
				"Tipo Macchina");
		carTypeField.setCanFilter(false);
		ListGridField customerField = new ListGridField("customer", "Cliente");
		ListGridField startingAgencyField = new ListGridField("startingAgency",
				"Agenzia di partenza");
		ListGridField arrivalAgencyField = new ListGridField("arrivalAgency",
				"Agenzia di arrivo");
		ListGridField paymentField = new ListGridField("payment", "Pagamento");
		ListGridField cautionField = new ListGridField("caution", "Cauzione");
		ListGridField confirmedField = new ListGridField("confirmed",
				"Conferma");
		ListGridField finishedField = new ListGridField("finished", "Concluso");

		tabRental.setFields(new ListGridField[] { idField, startField,
				endField, rentedCarField, carTypeField, customerField,
				startingAgencyField, arrivalAgencyField, paymentField,
				cautionField, confirmedField, finishedField });
		vPanel.addChild(tabRental);
		rp.clear();
		rp.add(vPanel);
//		tabRental.setHtmlElement(DOM.getElementById("content"));
//		tabRental.draw();
	}

}
