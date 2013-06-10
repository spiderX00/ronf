package it.unibo.ronf.client.makedialog;

import it.unibo.ronf.client.datasource.EmployeeDS;
import it.unibo.ronf.client.table.TabEmployee;
import it.unibo.ronf.shared.entities.Employee;
import it.unibo.ronf.shared.services.EmployeeService;
import it.unibo.ronf.shared.services.EmployeeServiceAsync;

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
import com.smartgwt.client.widgets.layout.HLayout;

public class MakeEmployee extends Dialog {
	private final EmployeeServiceAsync employeeService = GWT
			.create(EmployeeService.class);
	private HLayout hLayout;
	
	private DynamicForm dynamicForm;

	public MakeEmployee() {

		setSize("400px", "330px");
		dynamicForm = new DynamicForm();
		dynamicForm.setSize("350px", "194px");
		addItem(dynamicForm);
		hLayout = new HLayout();
		hLayout.setHeight("46px");
		hLayout.setMembersMargin(40);
		final TabEmployee tabEmployee = new TabEmployee();
		dynamicForm.setDataSource(EmployeeDS.getInstance(tabEmployee));
		dynamicForm.getField("id").hide();
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
				MakeEmployee.this.hide();

			}
		});
		addItem(hLayout);
		hLayout.moveTo(30, 231);

	}
	
	class CreateBtnHandler implements ClickHandler {
		public void onClick(ClickEvent event) {
			/** al click viene creato un nuovo Customer */
			dynamicForm.saveData(new DSCallback() {
				public void execute(DSResponse response, Object rawData,
						DSRequest request) {
					dynamicForm.editNewRecord();
				}
			});
			Employee employee = new Employee();
			employee.setName(dynamicForm.getValueAsString("name"));
			employee.setSurname(dynamicForm.getValueAsString("surname"));
			employee.setAge(Integer.parseInt(dynamicForm
					.getValueAsString("age")));
			employee.setPassword(dynamicForm.getValueAsString("password"));
			employee.setUserName(dynamicForm.getValueAsString("userName"));
			employeeService.createEmployee(employee,
					new AsyncCallback<Void>() {
						@Override
						public void onSuccess(Void result) {
							MakeEmployee.this.hide();
							Window.alert("Optional Created!");

						}

						@Override
						public void onFailure(Throwable caught) {
							Window.alert("Impossible to create optional : "
									+ caught);
						}
					});

		}
	}
}
