package it.unibo.ronf.client;

import it.unibo.ronf.shared.entities.CarType;
import it.unibo.ronf.shared.entities.Employee;
import it.unibo.ronf.shared.services.CarService;
import it.unibo.ronf.shared.services.CarServiceAsync;
import it.unibo.ronf.shared.services.CarTypeService;
import it.unibo.ronf.shared.services.CarTypeServiceAsync;
import it.unibo.ronf.shared.services.EmployeeService;
import it.unibo.ronf.shared.services.EmployeeServiceAsync;
import it.unibo.ronf.shared.services.RentalService;
import it.unibo.ronf.shared.services.RentalServiceAsync;

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

	private final EmployeeServiceAsync employeeService = GWT
			.create(EmployeeService.class);
	private final RentalServiceAsync rentalService = GWT
			.create(RentalService.class);
	VLayout layoutMain = new VLayout();
	HLayout layoutForm = new HLayout();
	HLayout layoutButton = new HLayout();
	private TextItem username;
	private PasswordItem password;
	private Button loginButton;

	/**
	 * @wbp.parser.entryPoint
	 */
	public void onModuleLoad() {
		layoutMain.setWidth100();
		final DynamicForm loginForm = new DynamicForm();
		username = new TextItem("username", "Username");
		password = new PasswordItem("password", "Password");
		loginButton = new Button("Login");
		loginForm.setFields(username,password);
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

			private void sendLogin() {

				String userName = username.getEnteredValue();
				String ppswd = password.getEnteredValue();

				Employee e = new Employee();
				e.setUserName(userName);
				e.setPassword(ppswd);

				loginButton.disable();

				employeeService.checkLogin(userName, ppswd,
						new AsyncCallback<Boolean>() {

							@Override
							public void onSuccess(Boolean result) {
								if (result == true) {
									Window.alert("Login correct!");
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
								loginButton.enable();
							}

						});
			

	

		/*************************************************/
//		CarType carType = new CarType();
//		carType.setType("Mini");
//		rentalService.setDailyCost(Float.parseFloat("20"));
//		 rentalService.removeById(24,new AsyncCallback<Void>() {
//		 @Override
//		 public void onSuccess(Void result) {
//		 Window.alert("rimosso");
//		 }
//		
//		 @Override
//		 public void onFailure(Throwable caught) {
//		 Window.alert("Impossible to create admin!");
//		 }
//		 });
	
//		 CarType carType2 = new CarType();
//			carType2.setType("Family");
//			carType2.setDailyCost(Float.parseFloat("35"));
//			 carTypeService.insertCarType(carType2,new AsyncCallback<Void>() {
//			 @Override
//			 public void onSuccess(Void result) {
//			 Window.alert("aggiunto");
//			 }
//			
//			 @Override
//			 public void onFailure(Throwable caught) {
//			 Window.alert("Impossible to create admin!");
//			 }
//			 });
//			 CarType carType3 = new CarType();
//				carType3.setType("Sport");
//				carType3.setDailyCost(Float.parseFloat("60"));
//				 carTypeService.insertCarType(carType3,new AsyncCallback<Void>() {
//				 @Override
//				 public void onSuccess(Void result) {
//				 Window.alert("aggiunto");
//				 }
//				
//				 @Override
//				 public void onFailure(Throwable caught) {
//				 Window.alert("Impossible to create admin!");
//				 }
//				 });
//				 CarType carType4 = new CarType();
//					carType4.setType("Prestige");
//					carType4.setDailyCost(Float.parseFloat("100"));
//					 carTypeService.insertCarType(carType4,new AsyncCallback<Void>() {
//					 @Override
//					 public void onSuccess(Void result) {
//					 Window.alert("aggiunto");
//					 }
//					
//					 @Override
//					 public void onFailure(Throwable caught) {
//					 Window.alert("Impossible to create admin!");
//					 }
//					 });
		 
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
		

