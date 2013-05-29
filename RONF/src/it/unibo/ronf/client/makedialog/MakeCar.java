package it.unibo.ronf.client.makedialog;

import it.unibo.ronf.client.datasource.CarDS;
import it.unibo.ronf.client.table.TabCar;
import it.unibo.ronf.shared.entities.Agency;
import it.unibo.ronf.shared.entities.Car;
import it.unibo.ronf.shared.entities.CarType;
import it.unibo.ronf.shared.services.AgencyService;
import it.unibo.ronf.shared.services.AgencyServiceAsync;
import it.unibo.ronf.shared.services.CarService;
import it.unibo.ronf.shared.services.CarServiceAsync;
import it.unibo.ronf.shared.services.CarTypeService;
import it.unibo.ronf.shared.services.CarTypeServiceAsync;

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
import com.smartgwt.client.widgets.form.fields.SelectItem;
import com.smartgwt.client.widgets.form.fields.events.ChangeEvent;
import com.smartgwt.client.widgets.form.fields.events.ChangeHandler;
import com.smartgwt.client.widgets.layout.HLayout;

public class MakeCar extends Dialog {

	private final CarServiceAsync carService = GWT.create(CarService.class);
	private final AgencyServiceAsync agencyService = GWT.create(AgencyService.class);
	private final CarTypeServiceAsync carTypeService = GWT.create(CarTypeService.class);

	private Car car;

	private Map<String, Agency> agencyMap = new HashMap<String, Agency>();
	private Map<String, CarType> carTypeMap = new HashMap<String, CarType>();

	private HLayout hLayout;
	private SelectItem carTypeItem;

	public MakeCar() {
		setSize("370", "500px");
		final DynamicForm dynamicForm = new DynamicForm();
		dynamicForm.setSize("350px", "194px");
		addItem(dynamicForm);
		hLayout = new HLayout();
		hLayout.setSize("351px", "46px");
		hLayout.setMembersMargin(40);
		final DynamicForm dynamicForm2 = new DynamicForm();
		carTypeItem = new SelectItem("carType", "Avaiable Type");
		dynamicForm2.setFields(carTypeItem);
		addItem(dynamicForm2);

		agencyService.findAll(new AsyncCallback<List<Agency>>() {

			@Override
			public void onSuccess(List<Agency> result) {
				for (Agency c : result) {
					agencyMap.put("" + c.getId() + " - " + c.getName(), c);

				}
				final TabCar tabCar = new TabCar();
				if (CarDS.getDataSource("carDS") != null) {
					CarDS.getDataSource("carDS").destroy();
				}
				dynamicForm.setDataSource(new CarDS("carDS", tabCar, agencyMap));
				dynamicForm.getField("id").hide();
			}

			@Override
			public void onFailure(Throwable caught) {
				Window.alert("Impossible to load agency: " + caught.getMessage());
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

		car = new Car();

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

				car.setModel(dynamicForm.getValueAsString("model"));
				car.setPlate(dynamicForm.getValueAsString("plate"));
				car.setGasolineType(dynamicForm.getValueAsString("gasolineType"));
				car.setSeatsNumber(Integer.parseInt(dynamicForm.getValueAsString("seatsNumber")));
				car.setAgency(agencyMap.get(dynamicForm.getValueAsString("agency")));
				car.setType(carTypeMap.get(dynamicForm.getValueAsString("carType")));
				carService.createCar(car, new AsyncCallback<Void>() {
					@Override
					public void onSuccess(Void result) {
						MakeCar.this.hide();
						Window.alert("Optional Created!");
					}

					@Override
					public void onFailure(Throwable caught) {
						Window.alert("Impossible to create Car : " + caught);
					}
				});
			}
		});

		btnCancel.addClickHandler(new ClickHandler() {
			@Override
			public void onClick(ClickEvent event) {
				MakeCar.this.hide();
			}
		});
		addItem(hLayout);
		hLayout.moveTo(30, 231);
	}
}
