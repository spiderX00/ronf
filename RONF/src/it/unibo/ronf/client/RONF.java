package it.unibo.ronf.client;

import it.unibo.ronf.shared.entities.Employee;
import it.unibo.ronf.shared.services.EmployeeService;
import it.unibo.ronf.shared.services.EmployeeServiceAsync;

import com.google.gwt.core.client.EntryPoint;
import com.google.gwt.core.client.GWT;
import com.google.gwt.event.dom.client.ClickEvent;
import com.google.gwt.event.dom.client.ClickHandler;
import com.google.gwt.user.client.Window;
import com.google.gwt.user.client.rpc.AsyncCallback;
import com.google.gwt.user.client.ui.AbsolutePanel;
import com.google.gwt.user.client.ui.Button;
import com.google.gwt.user.client.ui.DialogBox;
import com.google.gwt.user.client.ui.FlowPanel;
import com.google.gwt.user.client.ui.Label;
import com.google.gwt.user.client.ui.PasswordTextBox;
import com.google.gwt.user.client.ui.RootPanel;
import com.google.gwt.user.client.ui.TextBox;
import com.google.gwt.user.client.ui.VerticalPanel;
import com.smartgwt.client.widgets.layout.FlowLayout;
import com.google.gwt.user.client.ui.HorizontalPanel;
import com.google.gwt.user.client.ui.HasHorizontalAlignment;
import com.google.gwt.user.client.ui.HasVerticalAlignment;
import com.google.gwt.user.client.ui.HTMLPanel;

/**
 * Entry point classes define <code>onModuleLoad()</code>.
 */

public class RONF implements EntryPoint {

	private final EmployeeServiceAsync employeeService = GWT
			.create(EmployeeService.class);

	/**
	 * @wbp.parser.entryPoint
	 */
	public void onModuleLoad() {
		/** associo al RootPanel il div con id contentmenu */
		 final RootPanel rp = RootPanel.get("contentmenu");
         final AbsolutePanel absolutePanel = new AbsolutePanel();
         absolutePanel.setSize("450px", "300px");
         Label lblNome = new Label("Username");
         absolutePanel.add(lblNome, 63, 58);

         final PasswordTextBox usrpsswd = new PasswordTextBox();
         usrpsswd.setSize("148px","15px");
         absolutePanel.add(usrpsswd, 157, 103);

         final TextBox usrNm = new TextBox();
         usrNm.setSize("148px","15px");
         absolutePanel.add(usrNm, 157, 58);

         Label lblPassword = new Label("Password");
         absolutePanel.add(lblPassword, 63, 115);

         final Button LoginButton = new Button("Login");
         absolutePanel.add(LoginButton, 224, 171);
         rp.add(absolutePanel);
		LoginButton.addClickHandler(new ClickHandler() {

			public void onClick(ClickEvent event) {
				sendLogin();
			}

			private void sendLogin() {

				String userName = usrNm.getText();
				String ppswd = usrpsswd.getText();

				Employee e = new Employee();
				e.setUserName(userName);
				e.setPassword(ppswd);

				LoginButton.setEnabled(false);

				employeeService.checkLogin(userName, ppswd,
						new AsyncCallback<Boolean>() {

							@Override
							public void onSuccess(Boolean result) {
								if (result == true) {
									Window.alert("Login correct!");
									rp.clear();
									/* carica menu principale */
									MainMenu menu = new MainMenu();
									/** aggiunge il menu al div "contentmenu" */
									rp.add(menu);
								}

								if (result == false) {
									Window.alert("Login Incorrect");
									LoginButton.setEnabled(true);
								}

							}

							@Override
							public void onFailure(Throwable caught) {
								LoginButton.setEnabled(true);
							}

						});
			}

		});

		/*************************************************/

		// Employee admin = new Employee();
		// admin.setAge(23);
		// admin.setName("Amministratore");
		// admin.setSurname("Admin");
		// admin.setUserName("admin");
		// admin.setPassword("admin");
		// employeeService.createEmployee(admin, new AsyncCallback<Void>() {
		// @Override
		// public void onSuccess(Void result) {
		// }
		//
		// @Override
		// public void onFailure(Throwable caught) {
		// Window.alert("Impossible to create admin!");
		// }
		// });
		// LoginDialog d = new LoginDialog();
		// d.setVisible(false);
	}
}
