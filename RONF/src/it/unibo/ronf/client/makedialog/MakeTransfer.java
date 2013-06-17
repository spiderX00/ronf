package it.unibo.ronf.client.makedialog;

import it.unibo.ronf.client.datasource.TransferDS;
import it.unibo.ronf.client.table.TabTransfer;
import it.unibo.ronf.shared.entities.Agency;
import it.unibo.ronf.shared.entities.Car;
import it.unibo.ronf.shared.entities.CarType;
import it.unibo.ronf.shared.entities.Transfer;
import it.unibo.ronf.shared.entities.TransferAction;
import it.unibo.ronf.shared.services.AgencyService;
import it.unibo.ronf.shared.services.AgencyServiceAsync;
import it.unibo.ronf.shared.services.CarService;
import it.unibo.ronf.shared.services.CarServiceAsync;
import it.unibo.ronf.shared.services.CarTypeService;
import it.unibo.ronf.shared.services.CarTypeServiceAsync;
import it.unibo.ronf.shared.services.TransferService;
import it.unibo.ronf.shared.services.TransferServiceAsync;

import java.util.ArrayList;
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
import com.smartgwt.client.types.MultiComboBoxLayoutStyle;
import com.smartgwt.client.util.SC;
import com.smartgwt.client.widgets.Button;
import com.smartgwt.client.widgets.Dialog;
import com.smartgwt.client.widgets.events.ClickEvent;
import com.smartgwt.client.widgets.events.ClickHandler;
import com.smartgwt.client.widgets.form.DynamicForm;
import com.smartgwt.client.widgets.form.fields.DateItem;
import com.smartgwt.client.widgets.form.fields.MultiComboBoxItem;
import com.smartgwt.client.widgets.form.fields.SelectItem;
import com.smartgwt.client.widgets.form.fields.events.ChangeEvent;
import com.smartgwt.client.widgets.form.fields.events.ChangeHandler;
import com.smartgwt.client.widgets.layout.HLayout;

public class MakeTransfer extends Dialog {

	private final TransferServiceAsync transferService = GWT.create(TransferService.class);
	private final AgencyServiceAsync agencyService = GWT.create(AgencyService.class);
	private final CarTypeServiceAsync carTypeService = GWT.create(CarTypeService.class);
	private final CarServiceAsync carService = GWT.create(CarService.class);
	private HLayout hLayout;
	private MultiComboBoxItem transferActionItem;

	private Map<String, Agency> agencyMap;
	private Map<String, CarType> carTypeMap;
	private Map<String, Car> carMap;

	private SelectItem carTypeItem;
	private TransferAction transferAction;
	private DateItem data;
	private Agency curreAgency;
	private DynamicForm dynamicForm;

	public MakeTransfer() {

		setSize("400px", "330px");
		dynamicForm = new DynamicForm();
		dynamicForm.setSize("350px", "30px");
		addItem(dynamicForm);
		hLayout = new HLayout();
		hLayout.setHeight("46px");
		hLayout.setMembersMargin(40);
		final DynamicForm dynamicForm3 = new DynamicForm();
		transferActionItem = new MultiComboBoxItem("transferAction", "Transfer Action");
		transferActionItem.setLayoutStyle(MultiComboBoxLayoutStyle.VERTICAL);
		carTypeItem = new SelectItem("carType", "Tipo");
		carTypeItem.setEmptyDisplayValue("Select Type");
		data = new DateItem("data", "Data transferimento");
		dynamicForm3.setFields(data, carTypeItem, transferActionItem);
		addItem(dynamicForm3);

		agencyService.getCurrentAgency(new AsyncCallback<Agency>() {
			@Override
			public void onFailure(Throwable caught) {
				Window.alert("Impossible to get current agency : " + caught);
			}

			@Override
			public void onSuccess(Agency result) {
				curreAgency = result;
			}
		});

		agencyService.getOthersAgencies(new AsyncCallback<List<Agency>>() {

			@Override
			public void onFailure(Throwable caught) {
				Window.alert("Error while loading Agency:" + caught.getMessage());
			}

			@Override
			public void onSuccess(List<Agency> result) {
				agencyMap = new HashMap<String, Agency>();
				for (Agency c : result) {
					agencyMap.put("" + c.getId() + " - " + c.getName(), c);
				}
				final TabTransfer tabTransfer = new TabTransfer();
				if (DataSource.getDataSource("transferDS") != null) {
					DataSource.getDataSource("transferDS").destroy();
				}
				dynamicForm.setDataSource(new TransferDS("transferDS", tabTransfer, agencyMap));
				dynamicForm.getField("id").hide();
				dynamicForm.getField("arrivalAgency").hide();
				dynamicForm.getField("success").hide();
				dynamicForm.getField("startAgency").addChangeHandler(new StartAgencyHandler());

			}
		});

		carTypeService.findAll(new AsyncCallback<List<CarType>>() {
			@Override
			public void onSuccess(List<CarType> result) {
				carTypeMap = new HashMap<String, CarType>();
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

		Button btnCancel = new Button("Cancel");
		btnCancel.setAlign(Alignment.CENTER);
		hLayout.addMember(btnCancel);

		Button btnCrea = new Button("Crea");
		hLayout.addMember(btnCrea);
		hLayout.setAlign(Alignment.CENTER);
		btnCrea.addClickHandler(new CreateBtnClickHandler());

		btnCancel.addClickHandler(new ClickHandler() {

			@Override
			public void onClick(ClickEvent event) {
				MakeTransfer.this.hide();

			}
		});
		addItem(hLayout);
		hLayout.moveTo(30, 231);

	}

	class StartAgencyHandler implements ChangeHandler {

		@Override
		public void onChange(ChangeEvent event) {
			String selectedItem = (String) event.getValue();
			carService.getAllFreeCars(agencyMap.get(selectedItem), new AsyncCallback<List<Car>>() {

				@Override
				public void onFailure(Throwable caught) {
					Window.alert("Error while loading Agency:" + caught.getMessage());
				}

				@Override
				public void onSuccess(List<Car> result) {
					carMap = new HashMap<String, Car>();
					for (Car c : result) {
						carMap.put(c.getModel(), c);
					}

					transferActionItem.setValueMap(carMap.keySet().toArray(new String[] {}));
				}

			});
		}

	}

	class CreateBtnClickHandler implements ClickHandler {

		@Override
		public void onClick(ClickEvent event) {
			final Transfer transfer = new Transfer();
			/** al click viene creato un nuovo Customer */
			dynamicForm.saveData(new DSCallback() {
				@Override
				public void execute(DSResponse response, Object rawData, DSRequest request) {
					dynamicForm.editNewRecord();
				}
			});
			transfer.setStartAgency(agencyMap.get(dynamicForm.getValueAsString("startAgency")));
			transfer.setSuccess(false);

			List<TransferAction> transferActionList = new ArrayList<TransferAction>();
			String s = transferActionItem.getDisplayValue();
			if (!s.trim().isEmpty()) {
				String[] optional = s.split(",");
				for (String o : optional) {
					transferAction = new TransferAction();
					transferAction.setRequiredCar(carMap.get(o));
					transferAction.setSuccessAction(false);
					transferAction.setTransferDate(data.getValueAsDate());
					transferActionList.add(transferAction);
				}
				transfer.setTransfers(transferActionList);
			}

			transfer.setArrivalAgency(curreAgency);
			transferService.createTransfer(transfer, new AsyncCallback<Void>() {
				@Override
				public void onSuccess(Void result) {
					MakeTransfer.this.hide();
					SC.say("Transfer Successfully Created!");
				}

				@Override
				public void onFailure(Throwable caught) {
					Window.alert("Impossible to create Transfer : " + caught.getMessage());
				}
			});
		}
	}
}
