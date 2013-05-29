package it.unibo.ronf.client;

import it.unibo.ronf.shared.entities.Car;
import it.unibo.ronf.shared.entities.CarType;
import it.unibo.ronf.shared.entities.Employee;

import it.unibo.ronf.shared.services.CarTypeService;
import it.unibo.ronf.shared.services.CarTypeServiceAsync;
import it.unibo.ronf.shared.services.ClientRestCarsService;
import it.unibo.ronf.shared.services.ClientRestCarsServiceAsync;
import it.unibo.ronf.shared.services.EmployeeService;
import it.unibo.ronf.shared.services.EmployeeServiceAsync;

import java.util.Date;
import java.util.List;

import com.google.gwt.core.client.EntryPoint;
import com.google.gwt.core.client.GWT;
import com.google.gwt.event.dom.client.ClickEvent;
import com.google.gwt.event.dom.client.ClickHandler;
import com.google.gwt.user.client.Window;
import com.google.gwt.user.client.rpc.AsyncCallback;
import com.google.gwt.user.client.ui.AbsolutePanel;
import com.google.gwt.user.client.ui.Button;
import com.google.gwt.user.client.ui.Label;
import com.google.gwt.user.client.ui.PasswordTextBox;
import com.google.gwt.user.client.ui.TextBox;
import com.smartgwt.client.widgets.WidgetCanvas;
import com.smartgwt.client.widgets.layout.VLayout;

/**
 * Entry point classes define <code>onModuleLoad()</code>.
 */

public class RONF implements EntryPoint {

	private final EmployeeServiceAsync employeeService = GWT
			.create(EmployeeService.class);

	private final ClientRestCarsServiceAsync clientRestCarService = GWT
			.create(ClientRestCarsService.class);

	private VLayout vPanel = new VLayout();

	/**
	 * @wbp.parser.entryPoint
	 */
	public void onModuleLoad() {

		CarType type = new CarType();
		type.setId(23);
		type.setType("Familiare");
		type.setDailyCost(40.5F);
		
		Date start = new Date(2013, 6, 1);
		Date end = new Date(2013, 6, 5);
		

		clientRestCarService.findAvailableCar(type, start, end,
				new AsyncCallback<List<Car>>() {

					@Override
					public void onFailure(Throwable caught) {
						Window.alert("Error");

					}

					@Override
					public void onSuccess(List<Car> result) {
						if (result.isEmpty()) {
							Window.alert("Car not found");
						} else {
							String cars = "";
							for(Car c : result) {
								cars += "- "+c.getPlate()+c.getType().getId()+"\n";
							}
							Window.alert("Car found: \n"+cars);
						}
					}

				});

		vPanel.setWidth100();
		final AbsolutePanel absolutePanel = new AbsolutePanel();
		absolutePanel.setSize("450px", "300px");
		Label lblNome = new Label("Username");
		absolutePanel.add(lblNome, 63, 58);

		final PasswordTextBox usrpsswd = new PasswordTextBox();
		usrpsswd.setSize("148px", "15px");
		absolutePanel.add(usrpsswd, 157, 103);

		final TextBox usrNm = new TextBox();
		usrNm.setSize("148px", "15px");
		absolutePanel.add(usrNm, 157, 58);

		Label lblPassword = new Label("Password");
		absolutePanel.add(lblPassword, 63, 115);

		final Button LoginButton = new Button("Login");
		absolutePanel.add(LoginButton, 224, 171);
		final WidgetCanvas widget = new WidgetCanvas(absolutePanel);
		vPanel.addMember(widget);
		vPanel.draw();
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
									vPanel.removeChild(widget);
									/* carica menu principale */
									new MainMenu();
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
		// rentalService.removeById(4,new AsyncCallback<Void>() {
		// @Override
		// public void onSuccess(Void result) {
		// Window.alert("rimosso");
		// }
		//
		// @Override
		// public void onFailure(Throwable caught) {
		// Window.alert("Impossible to create admin!");
		// }
		// });
		// rentalService.removeById(5,new AsyncCallback<Void>() {
		// @Override
		// public void onSuccess(Void result) {
		// Window.alert("rimosso");
		// }
		//
		// @Override
		// public void onFailure(Throwable caught) {
		// Window.alert("Impossible to create admin!");
		// }
		// });
		// Agency agency = new Agency();
		// agency.setCode("125");
		// agency.setName("agenzia Roma");
		// agency.setAddress("via roma, 23");
		// agency.setIpAddress("2.2.2.2");
		// agencyService.createAgency(agency, new AsyncCallback<Void>() {
		// @Override
		// public void onSuccess(Void result) {
		// }
		//
		// @Override
		// public void onFailure(Throwable caught) {
		// Window.alert("Impossible to create admin!");
		// }
		// });
		// Car car = new Car();
		// car.setModel("monovolume");
		// car.setSeatsNumber(4);
		// car.setPlate("prova");
		// car.setGasolineType("diesel");
		// carService.createCar(car, new AsyncCallback<Void>() {
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
