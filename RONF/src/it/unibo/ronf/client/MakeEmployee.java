package it.unibo.ronf.client;

import it.unibo.ronf.shared.entities.Customer;
import it.unibo.ronf.shared.entities.Employee;
import it.unibo.ronf.shared.services.EmployeeService;
import it.unibo.ronf.shared.services.EmployeeServiceAsync;

import com.google.gwt.core.client.GWT;
import com.google.gwt.event.dom.client.ClickEvent;
import com.google.gwt.event.dom.client.ClickHandler;
import com.google.gwt.user.client.Window;
import com.google.gwt.user.client.rpc.AsyncCallback;
import com.google.gwt.user.client.ui.DialogBox;
import com.google.gwt.user.client.ui.AbsolutePanel;
import com.google.gwt.user.client.ui.VerticalPanel;
import com.google.gwt.user.client.ui.Label;
import com.google.gwt.user.client.ui.TextBox;
import com.google.gwt.user.client.ui.PasswordTextBox;
import com.google.gwt.user.client.ui.HasHorizontalAlignment;
import com.google.gwt.user.client.ui.Button;

public class MakeEmployee extends DialogBox {
	private final EmployeeServiceAsync employeeService = GWT
			.create(EmployeeService.class);

	public MakeEmployee() {

		AbsolutePanel absolutePanel = new AbsolutePanel();
		setWidget(absolutePanel);
		absolutePanel.setSize("385px", "331px");

		VerticalPanel verticalPanel = new VerticalPanel();
		absolutePanel.add(verticalPanel, 33, 10);
		verticalPanel.setSize("107px", "254px");

		Label lblNome = new Label("Nome");
		lblNome.setHorizontalAlignment(HasHorizontalAlignment.ALIGN_RIGHT);
		verticalPanel.add(lblNome);

		Label lblCognome = new Label("Cognome");
		lblCognome.setHorizontalAlignment(HasHorizontalAlignment.ALIGN_RIGHT);
		verticalPanel.add(lblCognome);

		Label lblEt = new Label("Età");
		lblEt.setHorizontalAlignment(HasHorizontalAlignment.ALIGN_RIGHT);
		verticalPanel.add(lblEt);

		Label lblPassword = new Label("Password");
		lblPassword.setHorizontalAlignment(HasHorizontalAlignment.ALIGN_RIGHT);
		verticalPanel.add(lblPassword);

		VerticalPanel verticalPanel_1 = new VerticalPanel();
		absolutePanel.add(verticalPanel_1, 165, 10);
		verticalPanel_1.setSize("184px", "254px");

		final TextBox name = new TextBox();
		verticalPanel_1.add(name);

		final TextBox surname = new TextBox();
		verticalPanel_1.add(surname);

		final TextBox age = new TextBox();
		verticalPanel_1.add(age);

		final PasswordTextBox passwd = new PasswordTextBox();
		verticalPanel_1.add(passwd);
		passwd.setSize("177px", "27");

		Button btnCancel = new Button("Cancel");
		absolutePanel.add(btnCancel, 49, 294);

		Button makeButton = new Button("Crea");
		absolutePanel.add(makeButton, 220, 294);

		makeButton.addClickHandler(new ClickHandler() {
			public void onClick(ClickEvent event) {
				/** al click viene creato un nuovo Customer */
				Employee employee = new Employee();
				employee.setName(name.getText());
				employee.setSurname(surname.getText());
				employee.setAge(Integer.parseInt(age.getText()));
				employee.setPassword(passwd.getText());
				employeeService.createEmployee(employee,
						new AsyncCallback<Void>() {
							@Override
							public void onSuccess(Void result) {
								MakeEmployee.this.hide();
								Window.alert("Customer Created!");
							}

							@Override
							public void onFailure(Throwable caught) {
								Window.alert("Impossible to create customer!");
							}
						});

			}
		});

		btnCancel.addClickHandler(new ClickHandler() {
			public void onClick(ClickEvent event) {
				MakeEmployee.this.hide();

			}
		});
		getElement().getStyle().setZIndex(8000001);
		setWidget(absolutePanel);
	}
}
