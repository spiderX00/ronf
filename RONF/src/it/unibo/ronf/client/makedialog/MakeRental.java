package it.unibo.ronf.client.makedialog;

import it.unibo.ronf.client.datasource.RentalDS;
import it.unibo.ronf.client.table.TabRental;
import it.unibo.ronf.shared.dto.AvailableCarRequestDTO;
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
import it.unibo.ronf.shared.services.PaymentService;
import it.unibo.ronf.shared.services.PaymentServiceAsync;
import it.unibo.ronf.shared.services.RentalService;
import it.unibo.ronf.shared.services.RentalServiceAsync;

import java.util.ArrayList;
import java.util.Date;
import java.util.HashMap;
import java.util.List;
import java.util.Map;

import com.google.gwt.core.client.GWT;
import com.google.gwt.user.client.Window;
import com.google.gwt.user.client.rpc.AsyncCallback;
import com.smartgwt.client.data.DSCallback;
import com.smartgwt.client.data.DSRequest;
import com.smartgwt.client.data.DSResponse;
import com.smartgwt.client.types.Alignment;
import com.smartgwt.client.widgets.Button;
import com.smartgwt.client.widgets.Dialog;
import com.smartgwt.client.widgets.events.ClickEvent;
import com.smartgwt.client.widgets.events.ClickHandler;
import com.smartgwt.client.widgets.form.DynamicForm;
import com.smartgwt.client.widgets.form.fields.ButtonItem;
import com.smartgwt.client.widgets.form.fields.DateItem;
import com.smartgwt.client.widgets.form.fields.FloatItem;
import com.smartgwt.client.widgets.form.fields.MultiComboBoxItem;
import com.smartgwt.client.widgets.form.fields.SectionItem;
import com.smartgwt.client.widgets.form.fields.SelectItem;
import com.smartgwt.client.widgets.form.fields.TextItem;
import com.smartgwt.client.widgets.form.fields.events.ChangeEvent;
import com.smartgwt.client.widgets.form.fields.events.ChangeHandler;
import com.smartgwt.client.widgets.layout.HLayout;

public class MakeRental extends Dialog {

	private final RentalServiceAsync rentalService = GWT.create(RentalService.class);
	private final CarServiceAsync carService = GWT.create(CarService.class);
	private final CustomerServiceAsync customerService = GWT.create(CustomerService.class);
	private final AgencyServiceAsync agencyService = GWT.create(AgencyService.class);
	private final CarTypeServiceAsync carTypeService = GWT.create(CarTypeService.class);
	private final OptionalServiceAsync optionalService = GWT.create(OptionalService.class);
	private final PaymentServiceAsync paymentService = GWT.create(PaymentService.class);

	private Map<String, Car> carMap = new HashMap<String, Car>();
	private Map<String, Customer> customersMap = new HashMap<String, Customer>();
	private Map<String, Agency> agencyMap = new HashMap<String, Agency>();
	private Map<String, Payment> paymentMap = new HashMap<String, Payment>();
	private Map<String, Optional> optionalMap = new HashMap<String, Optional>();
	private Map<String, CarType> carTypeMap = new HashMap<String, CarType>();

	private TextItem paymentMethodItem;
	private FloatItem amountItem;
	private SelectItem carTypeItem;
	private SelectItem carModelItem;
	private MultiComboBoxItem optionalItem;
	private Rental rental = new Rental();
	private ButtonItem calcButtonItem;

	private HLayout hLayout;

	public MakeRental() {
		setSize("370", "500px");
		final DynamicForm dynamicForm = new DynamicForm();
		dynamicForm.setSize("350px", "194px");
		addItem(dynamicForm);
		hLayout = new HLayout();
		hLayout.setSize("351px", "46px");
		hLayout.setMembersMargin(40);
		paymentMethodItem = new TextItem("paymentMethod", "Metodo di Pagmaneto");
		amountItem = new FloatItem("amount", "Totale");
		amountItem.setCanEdit(false);
		final SectionItem sectionPayment = new SectionItem();
		sectionPayment.setDefaultValue("Pagamento");
		sectionPayment.setSectionExpanded(false);
		sectionPayment.disable();
		sectionPayment.setItemIds("paymentMethod", "amount");
		final DynamicForm dynamicForm3 = new DynamicForm();
		carTypeItem = new SelectItem("carType", "Tipo");
		carTypeItem.setEmptyDisplayValue("Select Type");
		carModelItem = new SelectItem("carModel", "Car");
		carModelItem.setEmptyDisplayValue("Select car");
		optionalItem = new MultiComboBoxItem("optional", "Optional");
		calcButtonItem = new ButtonItem("calcola", "Calcola");
		dynamicForm3.setFields(carTypeItem, carModelItem, optionalItem, calcButtonItem);
		final DynamicForm dynamicForm2 = new DynamicForm();
		dynamicForm2.setFields(sectionPayment, amountItem, paymentMethodItem);
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
							agencyMap.put("" + c.getId() + " - " + c.getName(), c);

						}
						final TabRental tabRental = new TabRental();
						if (RentalDS.getDataSource("rentalDS") != null) {
							RentalDS.getDataSource("rentalDS").destroy();
						}
						dynamicForm.setDataSource(new RentalDS("rentalDS", tabRental, customersMap, agencyMap));
						dynamicForm.getField("id").hide();
						dynamicForm.getField("rentedCar").hide();
						dynamicForm.getField("optional").hide();
						dynamicForm.getField("finished").hide();
					}

					@Override
					public void onFailure(Throwable caught) {
						Window.alert("Impossible to load agency: " + caught.getMessage());
					}
				});
			}

			@Override
			public void onFailure(Throwable caught) {
				Window.alert("Impossible to load customers: " + caught.getMessage());
			}
		});

		carTypeService.findAll(new AsyncCallback<List<CarType>>() {
			@Override
			public void onSuccess(List<CarType> result) {
				for (CarType ct : result) {
					carTypeMap.put(ct.getType(), ct);
				}
				carTypeItem.setValueMap(carTypeMap.keySet().toArray(new String[] {}));
			}

			@Override
			public void onFailure(Throwable caught) {
				Window.alert("Impossible to load car type: " + caught.getMessage());
			}
		});

		carTypeItem.addChangeHandler(new ChangeHandler() {
			@Override
			public void onChange(ChangeEvent event) {
				String selectedItem = (String) event.getValue();

				AvailableCarRequestDTO request = new AvailableCarRequestDTO(carTypeMap.get(selectedItem), (Date) (dynamicForm.getValue("start")),
						(Date) (dynamicForm.getValue("end")));
				carService.findAvailableCarsInAllAgencies(request, new AsyncCallback<List<Car>>() {
					@Override
					public void onSuccess(List<Car> result) {
						if (result.isEmpty()) {
							carModelItem.setValueMap(new String[] {});
							carModelItem.disable();
						} else {
							for (Car c : result) {
								carMap.put(c.getAgency().getName() + " - " + c.getModel(), c);
							}
							carModelItem.enable();
							carModelItem.setValueMap(carMap.keySet().toArray(new String[] {}));
						}
					}

					@Override
					public void onFailure(Throwable caught) {
						Window.alert("Impossible to load available cars:" + caught.getMessage());
					}

				});
			}
		});
		carModelItem.addChangeHandler(new ChangeHandler() {
			@Override
			public void onChange(ChangeEvent event) {
				String selectedItem = (String) event.getValue();
				rental.setRentedCar(carMap.get(selectedItem));
			}
		});
		optionalService.findAll(new AsyncCallback<List<Optional>>() {

			@Override
			public void onFailure(Throwable caught) {
				Window.alert("Impossible to load optional:" + caught.getMessage());
			}

			@Override
			public void onSuccess(List<Optional> result) {
				for (Optional o : result) {
					optionalMap.put(o.getName(), o);
				}

				optionalItem.setValueMap(optionalMap.keySet().toArray(new String[] {}));
			}

		});
		
		calcButtonItem.addClickHandler(new com.smartgwt.client.widgets.form.fields.events.ClickHandler() {
			
			@Override
			public void onClick(com.smartgwt.client.widgets.form.fields.events.ClickEvent event) {
				rental.setStart((Date) dynamicForm.getValue("start"));
				rental.setEnd((Date) (dynamicForm.getValue("end")));
				rental.setCaution(Float.parseFloat(dynamicForm.getValueAsString("caution")));
				List<Optional> optionalList = new ArrayList<Optional>();
				String s = optionalItem.getDisplayValue();
				if(!s.trim().isEmpty())  {
					String[] optional = s.split(",");
					for (String o : optional) {
						optionalList.add(optionalMap.get(o));
					}
				}
				
				rental.setOptional(optionalList);
				
				paymentService.makePayment(rental, new AsyncCallback<Payment>() {

					@Override
					public void onFailure(Throwable caught) {
						Window.alert("Impossibile to calculate amount!!!" + caught.getMessage());
					}

					@Override
					public void onSuccess(Payment result) {
						amountItem.setValue(result.getAmount());
						rental.setPayment(result);
						sectionPayment.enable();
						sectionPayment.expandSection();
						
						
					}
				});
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
					public void execute(DSResponse response, Object rawData, DSRequest request) {
						dynamicForm.editNewRecord();
					}
				});
				List<Optional> optionalList = new ArrayList<Optional>();
				String s = optionalItem.getDisplayValue();
				String[] optional = s.split(",");
				for (String o : optional) {
					optionalList.add(optionalMap.get(o));
				}
				
				rental.setCustomer(customersMap.get(dynamicForm.getValueAsString("customer")));
				rental.setStartingAgency(agencyMap.get(dynamicForm.getValueAsString("startingAgency")));
				rental.setArrivalAgency(agencyMap.get(dynamicForm.getValueAsString("arrivalAgency")));
				rental.setFinished(Boolean.valueOf(dynamicForm.getValueAsString("finished")));
				rental.getPayment().setPaymentMethod(paymentMethodItem.getEnteredValue());
				rentalService.createRental(rental, new AsyncCallback<Void>() {
					@Override
					public void onSuccess(Void result) {
						MakeRental.this.hide();
						Window.alert("Rent Created!");
					}

					@Override
					public void onFailure(Throwable caught) {

						Window.alert("Impossible to create rental : " + caught.getMessage());
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
