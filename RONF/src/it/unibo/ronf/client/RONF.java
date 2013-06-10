package it.unibo.ronf.client;

import it.unibo.ronf.shared.entities.Agency;

import it.unibo.ronf.shared.entities.Employee;

import it.unibo.ronf.shared.services.AgencyService;
import it.unibo.ronf.shared.services.AgencyServiceAsync;

import it.unibo.ronf.shared.services.EmployeeService;
import it.unibo.ronf.shared.services.EmployeeServiceAsync;
import it.unibo.ronf.shared.services.InitService;
import it.unibo.ronf.shared.services.InitServiceAsync;

import java.util.HashMap;
import java.util.List;
import java.util.Map;

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
import com.smartgwt.client.widgets.form.fields.SelectItem;
import com.smartgwt.client.widgets.form.fields.TextItem;
import com.smartgwt.client.widgets.form.fields.events.ChangeEvent;
import com.smartgwt.client.widgets.form.fields.events.ChangeHandler;
import com.smartgwt.client.widgets.layout.HLayout;
import com.smartgwt.client.widgets.layout.VLayout;

/**
 * Entry point classes define <code>onModuleLoad()</code>.
 */

public class RONF implements EntryPoint {

	private final EmployeeServiceAsync employeeService = GWT.create(EmployeeService.class);
	private final AgencyServiceAsync agencyService = GWT.create(AgencyService.class);
	private final InitServiceAsync initService = GWT.create(InitService.class);
	
	private VLayout layoutMain = new VLayout();
	private HLayout layoutForm = new HLayout();
	private HLayout layoutButton = new HLayout();
	private TextItem usernameItem;
	private PasswordItem passwordItem;
	private Button loginButton;
	private SelectItem agencySelectItem;

	private Map<String, Agency> agencyMap;

	/**
	 * @wbp.parser.entryPoint
	 */
	@Override
	public void onModuleLoad() {

		initService.preLoginInitEntities(new AsyncCallback<Void>() {

			@Override
			public void onSuccess(Void result) {
				// TODO Auto-generated method stub
			}

			@Override
			public void onFailure(Throwable caught) {
				Window.alert("Fallito preLogin()");

			}
		});

		final DynamicForm loginForm = new DynamicForm();
		layoutMain.setWidth100();
		loginButton = new Button("Login");
		layoutButton.addMember(loginButton);
		layoutButton.setWidth100();
		layoutButton.setAlign(Alignment.CENTER);
		layoutForm.addMember(loginForm);
		layoutForm.setWidth100();
		layoutForm.setAlign(Alignment.CENTER);
		layoutMain.addMember(layoutForm);
		layoutMain.addMember(layoutButton);
		layoutMain.setAlign(Alignment.CENTER);

		usernameItem = new TextItem("username", "Username");
		passwordItem = new PasswordItem("password", "Password");
		agencySelectItem = new SelectItem("agency", "Agency");
		agencySelectItem.setEmptyDisplayValue("Select Agency");
		loginForm.setFields(usernameItem, passwordItem, agencySelectItem);
		loginForm.setSize("283px", "113px");

		layoutMain.draw();

		loginButton.addClickHandler(new ClickHandler() {

			@Override
			public void onClick(ClickEvent event) {
				sendLogin();
			}
		});

		agencyMap = new HashMap<String, Agency>();

		agencyService.findAll(new AsyncCallback<List<Agency>>() {
			@Override
			public void onSuccess(List<Agency> result) {
				for (Agency a : result) {
					agencyMap.put(a.getName(), a);
				}
				agencySelectItem.setValueMap(agencyMap.keySet().toArray(new String[] {}));
			}

			@Override
			public void onFailure(Throwable caught) {
				Window.alert("Impossible to load Agency: " + caught.getMessage());

			}
		});

		agencySelectItem.addChangeHandler(new ChangeHandler() {

			@Override
			public void onChange(ChangeEvent event) {
				String selectedItem = (String) event.getValue();
				Agency selectedAgency = agencyMap.get(selectedItem);
				agencyService.setCurrentAgency(selectedAgency, new AsyncCallback<Void>() {
					@Override
					public void onFailure(Throwable caught) {
						Window.alert("Failed to set the current Agency: " + caught.getMessage());
					}

					@Override
					public void onSuccess(Void result) {
					}
				});
			}
		});
	}

	private void sendLogin() {

		String userName = usernameItem.getEnteredValue();
		String pwd = passwordItem.getEnteredValue();

		Employee e = new Employee();
		e.setUserName(userName);
		e.setPassword(pwd);

		loginButton.disable();

		employeeService.checkLogin(userName, pwd, new AsyncCallback<Boolean>() {

			@Override
			public void onSuccess(Boolean result) {
				if (result == true) {
					layoutMain.removeChild(layoutForm);
					layoutMain.removeChild(layoutButton);
					/* carica menu principale */
					initService.postLoginInitEntities(new AsyncCallback<Void>() {

						@Override
						public void onSuccess(Void result) {
							// TODO Auto-generated method stub
						}

						@Override
						public void onFailure(Throwable caught) {
							Window.alert("Fallito postLogin()");

						}
					});
					
					
					new MainMenu();
				}

				if (result == false) {
					Window.alert("Login Incorrect");
					loginButton.enable();
				}

			}

			@Override
			public void onFailure(Throwable caught) {
				Window.alert("Error while tryng login: " + caught.getMessage());
				loginButton.enable();
			}
		});
	}

}
