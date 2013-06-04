package it.unibo.ronf.client;

import java.util.List;

import it.unibo.ronf.shared.entities.Agency;
import it.unibo.ronf.shared.entities.Employee;
import it.unibo.ronf.shared.entities.MaintenanceType;
import it.unibo.ronf.shared.services.AgencyService;
import it.unibo.ronf.shared.services.AgencyServiceAsync;
import it.unibo.ronf.shared.services.EmployeeService;
import it.unibo.ronf.shared.services.EmployeeServiceAsync;
import it.unibo.ronf.shared.services.MaintenanceTypeService;
import it.unibo.ronf.shared.services.MaintenanceTypeServiceAsync;

import com.google.gwt.core.client.EntryPoint;
import com.google.gwt.core.client.GWT;
import com.google.gwt.user.client.Window;
import com.google.gwt.user.client.rpc.AsyncCallback;
import com.smartgwt.client.types.Alignment;
import com.smartgwt.client.widgets.Button;
import com.smartgwt.client.widgets.events.ClickEvent;
import com.smartgwt.client.widgets.events.ClickHandler;
import com.smartgwt.client.widgets.form.DynamicForm;
import com.smartgwt.client.widgets.form.fields.PasswordItem;
import com.smartgwt.client.widgets.form.fields.TextItem;
import com.smartgwt.client.widgets.layout.HLayout;
import com.smartgwt.client.widgets.layout.VLayout;

/**
 * Entry point classes define <code>onModuleLoad()</code>.
 */

public class RONF implements EntryPoint {

	private final EmployeeServiceAsync employeeService = GWT.create(EmployeeService.class);
	private final MaintenanceTypeServiceAsync maintenanceTypeService = GWT.create(MaintenanceTypeService.class);


	private final AgencyServiceAsync agencyService = GWT
			.create(AgencyService.class);

	private VLayout layoutMain = new VLayout();
	private HLayout layoutForm = new HLayout();
	private HLayout layoutButton = new HLayout();
	private TextItem username;
	private PasswordItem password;
	private Button loginButton;

	/**
	 * @wbp.parser.entryPoint
	 */
	@Override
	public void onModuleLoad() {

//		loadAgencies();

		layoutMain.setWidth100();
		final DynamicForm loginForm = new DynamicForm();
		username = new TextItem("username", "Username");
		password = new PasswordItem("password", "Password");
		loginButton = new Button("Login");
		loginForm.setFields(username, password);
		loginForm.setSize("283px", "113px");
		layoutButton.addMember(loginButton);
		layoutButton.setWidth100();
		layoutButton.setAlign(Alignment.CENTER);
		layoutForm.addMember(loginForm);
		layoutForm.setWidth100();
		layoutForm.setAlign(Alignment.CENTER);
		layoutMain.addMember(layoutForm);
		layoutMain.addMember(layoutButton);
		layoutMain.setAlign(Alignment.CENTER);
		layoutMain.draw();

		loginButton.addClickHandler(new ClickHandler() {

			@Override
			public void onClick(ClickEvent event) {
				sendLogin();
			}
		});
	}

//	private void loadAgencies() {
//		System.out.println("suuuuuuka");
//
//		Agency a1 = new Agency();
//		a1.setAddress("Via Zamboni");
//		a1.setCode("a1");
//		a1.setIpAddress("127.0.0.1");
//		a1.setName("Herz centrale 8080");
//		a1.setPort(8080);
//
//		Agency a2 = new Agency();
//		a2.setAddress("Via Stalingrado");
//		a2.setCode("a2");
//		a2.setIpAddress("127.0.0.1");
//		a2.setName("Herz periferia 8081");
//		a2.setPort(8081);
//
//		agencyService.createAgency(a1, new AsyncCallback<Void>() {
//
//			@Override
//			public void onFailure(Throwable caught) {
//				Window.alert("Agency not created!");
//
//			}
//
//			@Override
//			public void onSuccess(Void result) {
//				Window.alert("Agency created!");
//
//			}
//
//		});
//
//		agencyService.createAgency(a2, new AsyncCallback<Void>() {
//
//			@Override
//			public void onFailure(Throwable caught) {
//				Window.alert("Agency not created!");
//
//			}
//
//			@Override
//			public void onSuccess(Void result) {
//				Window.alert("Agency created!");
//
//			}
//
//		});
//	}

	private void sendLogin() {

		String userName = username.getEnteredValue();
		String ppswd = password.getEnteredValue();

		Employee e = new Employee();
		e.setUserName(userName);
		e.setPassword(ppswd);

		loginButton.disable();

		employeeService.checkLogin(userName, ppswd, new AsyncCallback<Boolean>() {

			@Override
			public void onSuccess(Boolean result) {
				if (result == true) {
					layoutMain.removeChild(layoutForm);
					layoutMain.removeChild(layoutButton);
					/* carica menu principale */
					new MainMenu();
				}

				if (result == false) {
					Window.alert("Login Incorrect");
					loginButton.enable();
				}

			}


					@Override
					public void onFailure(Throwable caught) {
						Window.alert("Error while tryng login: "
								+ caught.getMessage());
						loginButton.enable();
					}
				});

		/*************************************************/
//		 MaintenanceType mainte = new MaintenanceType();
//		 mainte.setName("Rifornimento");
//		 mainte.setCost(50F);
//		 mainte.setDescription("Pieno di Carburante");
//		 maintenanceTypeService.createMaintenanceType(mainte,new AsyncCallback<Void>() {
//		 @Override
//		 public void onSuccess(Void result) {
//		 Window.alert("aggiunto");
//		 }
//		
//		 @Override
//		 public void onFailure(Throwable caught) {
//		 Window.alert("Impossible to create admin!");
//		 }
//		 });
//
//		 MaintenanceType maintez = new MaintenanceType();
//		 maintez.setName("Pulizia");
//		 maintez.setCost(25F);
//		 maintez.setDescription("Pulizia Auto");
//		 maintenanceTypeService.createMaintenanceType(maintez,new AsyncCallback<Void>() {
//		 @Override
//		 public void onSuccess(Void result) {
//		 Window.alert("aggiunto");
//		 }
//		
//		 @Override
//		 public void onFailure(Throwable caught) {
//		 Window.alert("Impossible to create admin!");
//		 }
//		 });
		// CarType carType3 = new CarType();
		// carType3.setType("Sport");
		// carType3.setDailyCost(Float.parseFloat("60"));
		// carTypeService.insertCarType(carType3,new AsyncCallback<Void>() {
		// @Override
		// public void onSuccess(Void result) {
		// Window.alert("aggiunto");
		// }
		//
		// @Override
		// public void onFailure(Throwable caught) {
		// Window.alert("Impossible to create admin!");
		// }
		// });
		// CarType carType4 = new CarType();
		// carType4.setType("Prestige");
		// carType4.setDailyCost(Float.parseFloat("100"));
		// carTypeService.insertCarType(carType4,new AsyncCallback<Void>() {
		// @Override
		// public void onSuccess(Void result) {
		// Window.alert("aggiunto");
		// }
		//
		// @Override
		// public void onFailure(Throwable caught) {
		// Window.alert("Impossible to create admin!");
		// }
		// });

	}
}
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
