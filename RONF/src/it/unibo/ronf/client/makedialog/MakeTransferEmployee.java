package it.unibo.ronf.client.makedialog;

import it.unibo.ronf.client.datasource.RentalDS;
import it.unibo.ronf.client.datasource.TransferEmployeeDS;
import it.unibo.ronf.client.table.TabTransferEmployee;
import it.unibo.ronf.shared.entities.TransferEmployee;
import it.unibo.ronf.shared.services.TransferEmployeeService;
import it.unibo.ronf.shared.services.TransferEmployeeServiceAsync;

import com.google.gwt.core.client.GWT;
import com.google.gwt.user.client.Window;
import com.google.gwt.user.client.rpc.AsyncCallback;
import com.smartgwt.client.data.DSCallback;
import com.smartgwt.client.data.DSRequest;
import com.smartgwt.client.data.DSResponse;
import com.smartgwt.client.types.Alignment;
import com.smartgwt.client.util.SC;
import com.smartgwt.client.widgets.Button;
import com.smartgwt.client.widgets.Dialog;
import com.smartgwt.client.widgets.events.ClickEvent;
import com.smartgwt.client.widgets.events.ClickHandler;
import com.smartgwt.client.widgets.form.DynamicForm;
import com.smartgwt.client.widgets.layout.HLayout;

public class MakeTransferEmployee extends Dialog {

	private final TransferEmployeeServiceAsync transferEmployeeService = GWT.create(TransferEmployeeService.class);
	private HLayout hLayout;
	private DynamicForm dynamicForm;

	public MakeTransferEmployee() {

		setSize("400px", "330px");
		dynamicForm = new DynamicForm();
		dynamicForm.setSize("350px", "194px");
		addItem(dynamicForm);
		hLayout = new HLayout();
		hLayout.setHeight("46px");
		hLayout.setMembersMargin(40);
		final TabTransferEmployee tabTransferEmployee = new TabTransferEmployee();
		if (RentalDS.getDataSource("transferDS") != null) {
			RentalDS.getDataSource("transferDS").destroy();
		}
		dynamicForm.setDataSource(new TransferEmployeeDS("transferDS", tabTransferEmployee));
		dynamicForm.getField("id").hide();
		Button btnCancel = new Button("Cancel");
		btnCancel.setAlign(Alignment.CENTER);
		hLayout.addMember(btnCancel);

		Button btnCrea = new Button("Crea");
		hLayout.addMember(btnCrea);
		hLayout.setAlign(Alignment.CENTER);
		btnCrea.addClickHandler(new CreateHandler());

		btnCancel.addClickHandler(new ClickHandler() {

			@Override
			public void onClick(ClickEvent event) {
				MakeTransferEmployee.this.hide();

			}
		});
		addItem(hLayout);
		hLayout.moveTo(30, 231);

	}
	
	class CreateHandler implements ClickHandler {
		public void onClick(ClickEvent event) {
			/** al click viene creato un nuovo Customer */
			dynamicForm.saveData(new DSCallback() {
				public void execute(DSResponse response, Object rawData, DSRequest request) {
					dynamicForm.editNewRecord();
				}
			});
			TransferEmployee transferEmployee = new TransferEmployee();
			transferEmployee.setName(dynamicForm.getValueAsString("name"));
			transferEmployee.setSurname(dynamicForm.getValueAsString("surname"));
			transferEmployee.setAge(Integer.parseInt(dynamicForm.getValueAsString("age")));
			transferEmployee.setBusy(false);
			transferEmployeeService.createTransferEmployee(transferEmployee, new AsyncCallback<Void>() {
				@Override
				public void onSuccess(Void result) {
					MakeTransferEmployee.this.hide();
					SC.say("Transfer Employee Created!");
				}

				@Override
				public void onFailure(Throwable caught) {
					Window.alert("Impossible to create Transfer Employee : " + caught);
				}
			});

		}
	}
}
