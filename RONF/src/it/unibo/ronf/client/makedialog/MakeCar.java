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
import com.smartgwt.client.data.DataSource;
import com.smartgwt.client.types.Alignment;
import com.smartgwt.client.util.SC;
import com.smartgwt.client.widgets.Button;
import com.smartgwt.client.widgets.Dialog;
import com.smartgwt.client.widgets.events.ClickEvent;
import com.smartgwt.client.widgets.events.ClickHandler;
import com.smartgwt.client.widgets.form.DynamicForm;
import com.smartgwt.client.widgets.form.fields.SelectItem;
import com.smartgwt.client.widgets.form.fields.events.ChangedEvent;
import com.smartgwt.client.widgets.form.fields.events.ChangedHandler;
import com.smartgwt.client.widgets.layout.HLayout;

public class MakeCar extends Dialog {

	class CreateBtnHandler implements ClickHandler {
		@Override
		public void onClick(ClickEvent event) {
			/** al click viene creato un nuovo Customer */
			dynamicForm.saveData(new DSCallback() {
				@Override
				public void execute(DSResponse response, Object rawData, DSRequest request) {
					dynamicForm.editNewRecord();
				}
			});

			car.setModel(dynamicForm.getValueAsString("model"));
			car.setPlate(dynamicForm.getValueAsString("plate"));
			car.setGasolineType(dynamicForm.getValueAsString("gasolineType"));
			car.setSeatsNumber(Integer.parseInt(dynamicForm.getValueAsString("seatsNumber")));
			car.setOriginAgency(agencyMap.get(dynamicForm.getValueAsString("agency")));
			carService.createCar(car, new AsyncCallback<Void>() {
				@Override
				public void onFailure(Throwable caught) {
					Window.alert("Impossible to create Car : " + caught);
				}

				@Override
				public void onSuccess(Void result) {
					MakeCar.this.hide();
					SC.say("Car Created!");
				}
			});
		}
	}

	private final CarServiceAsync carService = GWT.create(CarService.class);
	private final AgencyServiceAsync agencyService = GWT.create(AgencyService.class);

	private final CarTypeServiceAsync carTypeService = GWT.create(CarTypeService.class);

	private Car car;
	private Map<String, Agency> agencyMap;

	private Map<String, CarType> carTypeMap;

	private DynamicForm dynamicForm;
	private HLayout hLayout;

	private SelectItem carTypeItem;

	public MakeCar() {
		setSize("370", "500px");
		dynamicForm = new DynamicForm();
		dynamicForm.setSize("350px", "30px");
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
			public void onFailure(Throwable caught) {
				Window.alert("Impossible to load agency: " + caught.getMessage());
			}

			@Override
			public void onSuccess(List<Agency> result) {
				agencyMap = new HashMap<String, Agency>();
				for (Agency c : result) {
					agencyMap.put("" + c.getId() + " - " + c.getName(), c);

				}
				final TabCar tabCar = new TabCar();
				if (DataSource.getDataSource("carDS") != null) {
					DataSource.getDataSource("carDS").destroy();
				}
				dynamicForm.setDataSource(new CarDS("carDS", tabCar, agencyMap));
				dynamicForm.getField("id").hide();
				dynamicForm.getField("type").hide();
			}
		});

		carTypeService.findAll(new AsyncCallback<List<CarType>>() {
			@Override
			public void onFailure(Throwable caught) {
				Window.alert("Impossible to load car type: " + caught.getMessage());
			}

			@Override
			public void onSuccess(List<CarType> result) {
				carTypeMap = new HashMap<String, CarType>();
				for (CarType ct : result) {
					carTypeMap.put(ct.getType(), ct);
				}
				carTypeItem.clearValue();
				carTypeItem.setValueMap(carTypeMap.keySet().toArray(new String[] {}));
			}
		});

		car = new Car();

		carTypeItem.addChangedHandler(new ChangedHandler() {

			@Override
			public void onChanged(ChangedEvent event) {
				String selectedItem = (String) event.getValue();
				car.setType(carTypeMap.get(selectedItem));
				dynamicForm.getField("type").setValue(car.getType().getType());
			}
		});
		Button btnCancel = new Button("Cancel");
		btnCancel.setAlign(Alignment.CENTER);
		hLayout.addMember(btnCancel);

		Button btnCrea = new Button("Crea");
		hLayout.addMember(btnCrea);
		hLayout.setAlign(Alignment.CENTER);
		btnCrea.addClickHandler(new CreateBtnHandler());

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
