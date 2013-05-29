package it.unibo.ronf.client.makedialog;

import it.unibo.ronf.client.datasource.RentalDS;
import it.unibo.ronf.client.table.TabRental;
import it.unibo.ronf.shared.entities.Agency;
import it.unibo.ronf.shared.entities.Car;
import it.unibo.ronf.shared.entities.CarType;
import it.unibo.ronf.shared.entities.Customer;
import it.unibo.ronf.shared.entities.Optional;
import it.unibo.ronf.shared.entities.Payment;
import it.unibo.ronf.shared.entities.Rental;
import it.unibo.ronf.shared.services.AgencyService;
import it.unibo.ronf.shared.services.AgencyServiceAsync;
import it.unibo.ronf.shared.services.CarService;
import it.unibo.ronf.shared.services.CarServiceAsync;
import it.unibo.ronf.shared.services.CarTypeService;
import it.unibo.ronf.shared.services.CarTypeServiceAsync;
import it.unibo.ronf.shared.services.CustomerService;
import it.unibo.ronf.shared.services.CustomerServiceAsync;
import it.unibo.ronf.shared.services.OptionalService;
import it.unibo.ronf.shared.services.OptionalServiceAsync;
import it.unibo.ronf.shared.services.RentalService;
import it.unibo.ronf.shared.services.RentalServiceAsync;

import java.util.ArrayList;
import java.util.Arrays;
import java.util.Date;
import java.util.HashMap;
import java.util.List;
import java.util.Map;

import com.google.gwt.core.client.GWT;
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
import com.smartgwt.client.widgets.form.fields.MultiComboBoxItem;
import com.smartgwt.client.widgets.form.fields.SectionItem;
import com.smartgwt.client.widgets.form.fields.SelectItem;
import com.smartgwt.client.widgets.form.fields.TextItem;
import com.smartgwt.client.widgets.form.fields.events.ChangeEvent;
import com.smartgwt.client.widgets.form.fields.events.ChangeHandler;
import com.smartgwt.client.widgets.form.fields.events.ChangedEvent;
import com.smartgwt.client.widgets.form.fields.events.ChangedHandler;
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
	private final CarTypeServiceAsync carTypeService = GWT
			.create(CarTypeService.class);
	private final OptionalServiceAsync optionalService = GWT
			.create(OptionalService.class);
	final static VLayout vPanel = new VLayout();
	final static RootPanel rp = RootPanel.get("content");

	Map<String, Car> carMap = new HashMap<String, Car>();
	Map<String, Customer> customersMap = new HashMap<String, Customer>();
	Map<String, Agency> agencyMap = new HashMap<String, Agency>();
	Map<String, Payment> paymentMap = new HashMap<String, Payment>();
	Map<String, Optional> optionalMap = new HashMap<String, Optional>();

	private TextItem paymentMethod;
	private FloatItem amount;
	private DateItem dateOfPayment;
	private SelectItem carType;
	private SelectItem carModel;
	private MultiComboBoxItem optionalItem;
	private Rental rental = new Rental();

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
		final DynamicForm dynamicForm3 = new DynamicForm();
		carType = new SelectItem("carType", "Tipo");
		carType.setValueMap("Mini", "Family", "Sport", "Prestige");
		carModel = new SelectItem("carModel", "Modello");
		optionalItem = new MultiComboBoxItem("optional", "Optional");
		carModel.setEmptyDisplayValue("Select car");
		carType.setEmptyDisplayValue("Select Type");
		dynamicForm3.setFields(carType, carModel, optionalItem);
		final DynamicForm dynamicForm2 = new DynamicForm();
		dynamicForm2.setFields(sectionPayment, amount, paymentMethod,
				dateOfPayment);
		addItem(dynamicForm3);
		addItem(dynamicForm2);

		customerService.findAll(new AsyncCallback<List<Customer>>() {

			@Override
			public void onSuccess(List<Customer> result) {
				for (Customer c : result) {
					customersMap.put("" + c.getId() + " - " + c.getName(), c);
				}
				agencyService.findAll(new AsyncCallback<List<Agency>>() {

					@Override
					public void onSuccess(List<Agency> result) {
						for (Agency c : result) {
							agencyMap.put("" + c.getId() + " - " + c.getName(),
									c);

						}
						final TabRental tabRental = new TabRental();
						if (RentalDS.getDataSource("rentalDS") != null) {
							RentalDS.getDataSource("rentalDS").destroy();
						}
						dynamicForm.setDataSource(new RentalDS("rentalDS", tabRental,
								customersMap, agencyMap));
						dynamicForm.getField("id").hide();
						dynamicForm.getField("rentedCar").hide();
						dynamicForm.getField("optional").hide();

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

		carType.addChangeHandler(new ChangeHandler() {
			@Override
			public void onChange(ChangeEvent event) {
				String selectedItem = (String) event.getValue();
				carTypeService.findBytype(selectedItem,
						new AsyncCallback<CarType>() {
							@Override
							public void onSuccess(CarType result) {
								carService.findByType(result,
										new AsyncCallback<List<Car>>() {

											@Override
											public void onFailure(
													Throwable caught) {
												// TODO Auto-generated method
												// stub

											}

											@Override
											public void onSuccess(
													List<Car> result) {
												if (result.isEmpty()) {
													carModel.setValueMap(new String[] {});
													carModel.disable();
												} else {
													for (Car c : result) {
														carMap.put(
																c.getModel(), c);
													}
													carModel.enable();
													carModel.setValueMap(carMap
															.keySet()
															.toArray(
																	new String[] {}));
												}

											}

										});
							}

							@Override
							public void onFailure(Throwable caught) {
								Window.alert("Impossible to create optional : "
										+ caught);
							}
						});
			}
		});
		carModel.addChangeHandler(new ChangeHandler() {
			@Override
			public void onChange(ChangeEvent event) {
				String selectedItem = (String) event.getValue();
				rental.setRentedCar(carMap.get(selectedItem));
			}
		});
		optionalService.findAll(new AsyncCallback<List<Optional>>() {

			@Override
			public void onFailure(Throwable caught) {
				// TODO Auto-generated method stub

			}

			@Override
			public void onSuccess(List<Optional> result) {
				for (Optional o : result) {
					optionalMap.put(o.getName(), o);
				}
				optionalItem.setValueMap(optionalMap.keySet().toArray(
						new String[] {}));
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
				List<Optional> optionalList = new ArrayList<Optional>();
				String s = optionalItem.getDisplayValue();
				String[] optional = s.split(",");
				for (String o : optional) {
					optionalList.add(optionalMap.get(o));
				}
				rental.setStart((Date) dynamicForm.getValue("start"));
				rental.setEnd((Date) (dynamicForm.getValue("end")));
				// rental.setRentedType(carTypeMap.get(dynamicForm.getValueAsString("rentedType")));
				rental.setCustomer(customersMap.get(dynamicForm
						.getValueAsString("customer")));
				rental.setStartingAgency(agencyMap.get(dynamicForm
						.getValueAsString("startingAgency")));
				rental.setArrivalAgency(agencyMap.get(dynamicForm
						.getValueAsString("arrivalAgency")));
				rental.setOptional(optionalList);
				Payment payment = new Payment();
				payment.setAmount(Float.parseFloat(amount.getEnteredValue()));
				payment.setPaymentMethod(paymentMethod.getEnteredValue());
				payment.setDateOfPayment(dateOfPayment.getValueAsDate());
				// rental.setPayment(payment);
				rental.setCaution(Float.parseFloat(dynamicForm
						.getValueAsString("caution")));
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



}
