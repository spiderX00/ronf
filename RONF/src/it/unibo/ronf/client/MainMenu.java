package it.unibo.ronf.client;

import it.unibo.ronf.client.makedialog.MakeAgency;
import it.unibo.ronf.client.makedialog.MakeCar;
import it.unibo.ronf.client.makedialog.MakeEmployee;
import it.unibo.ronf.client.makedialog.MakeOptional;
import it.unibo.ronf.client.makedialog.MakeRental;
import it.unibo.ronf.client.makedialog.MakeUser;
import it.unibo.ronf.client.table.TabAgency;
import it.unibo.ronf.client.table.TabCustomer;
import it.unibo.ronf.client.table.TabEmployee;
import it.unibo.ronf.client.table.TabOptional;

import com.smartgwt.client.widgets.layout.VLayout;
import com.smartgwt.client.widgets.menu.Menu;
import com.smartgwt.client.widgets.menu.MenuBar;
import com.smartgwt.client.widgets.menu.MenuItem;
import com.smartgwt.client.widgets.menu.events.ClickHandler;
import com.smartgwt.client.widgets.menu.events.MenuItemClickEvent;

/**
 * menu principale dell'applicazione
 * 
 * @author Alessio De Vita alessio.dv@gmail.com
 */
public class MainMenu extends MenuBar {
	VLayout main = new VLayout();
	VLayout mainmenu = new VLayout();
	VLayout content = new VLayout();

	public MainMenu() {
		super();
		setShowShadow(true);
		mainmenu.addMember(MainMenu.this);
		main.addMember(mainmenu);
		main.draw();

		/** cliccando su crea viene creata una finestra di dialogo MakeUser */
		MenuItem subMakeCustomer = new MenuItem("Crea");
		MenuItem subViewEmployee = new MenuItem("Visualizza");
		MenuItem subMakeEmployee = new MenuItem("Crea");
		MenuItem subViewCustomer = new MenuItem("Visualizza");
		MenuItem subMakeOptional = new MenuItem("Crea");
		MenuItem subViewOptional = new MenuItem("Visualizza");
		MenuItem subViewRental = new MenuItem("Visualizza");
		MenuItem subMakeRental = new MenuItem("Crea");
		MenuItem subViewCar = new MenuItem("Visualizza");
		MenuItem subMakeCar = new MenuItem("Crea");
		MenuItem subViewAgency = new MenuItem("Visualizza");
		MenuItem subMakeAgency = new MenuItem("Crea");
		Menu optionalMenu = new Menu();
		Menu employeeMenu = new Menu();
		Menu customerMenu = new Menu();
		Menu rentalMenu = new Menu();
		Menu carMenu = new Menu();
		Menu agencyMenu = new Menu();
		agencyMenu.setShowShadow(true);
		carMenu.setShowShadow(true);
		optionalMenu.setShowShadow(true);
		employeeMenu.setShowShadow(true);
		customerMenu.setShowShadow(true);
		rentalMenu.setShowShadow(true);
		agencyMenu.setTitle("Agency");
		carMenu.setTitle("Car");
		employeeMenu.setTitle("Employee");
		customerMenu.setTitle("Customer");
		rentalMenu.setTitle("Rental");
		optionalMenu.setTitle("Optional");
		optionalMenu.setItems(subMakeOptional, subViewOptional);
		employeeMenu.setItems(subMakeEmployee, subViewEmployee);
		customerMenu.setItems(subMakeCustomer, subViewCustomer);
		carMenu.setItems(subMakeCar, subViewCar);
		agencyMenu.setItems(subMakeAgency, subViewAgency);
		rentalMenu.setItems(subMakeRental, subViewRental);
		subViewOptional.addClickHandler(new ClickHandler() {

			@Override
			public void onClick(MenuItemClickEvent event) {
				new TabOptional();

			}
		});
		subMakeOptional.addClickHandler(new ClickHandler() {

			@Override
			public void onClick(MenuItemClickEvent event) {
				MakeOptional makeOptional = new MakeOptional();
				makeOptional.show();
				makeOptional.centerInPage();

			}
		});
		subViewCustomer.addClickHandler(new ClickHandler() {

			@Override
			public void onClick(MenuItemClickEvent event) {
				new TabCustomer();

			}
		});
		subMakeCustomer.addClickHandler(new ClickHandler() {

			@Override
			public void onClick(MenuItemClickEvent event) {
				MakeUser makeUser = new MakeUser();
				makeUser.show();
				makeUser.centerInPage();

			}
		});
		subViewRental.addClickHandler(new ClickHandler() {

			@Override
			public void onClick(MenuItemClickEvent event) {
				MakeRental makeRental = new MakeRental();
				makeRental.hide();

			}
		});
		subMakeRental.addClickHandler(new ClickHandler() {

			@Override
			public void onClick(MenuItemClickEvent event) {
				MakeRental makeRental = new MakeRental();
				makeRental.show();
				makeRental.centerInPage();

			}
		});
		subViewEmployee.addClickHandler(new ClickHandler() {

			@Override
			public void onClick(MenuItemClickEvent event) {
				new TabEmployee();

			}
		});
		subMakeEmployee.addClickHandler(new ClickHandler() {

			@Override
			public void onClick(MenuItemClickEvent event) {
				MakeEmployee makeEmployee = new MakeEmployee();
				makeEmployee.show();
				makeEmployee.centerInPage();

			}
		});
		subViewCar.addClickHandler(new ClickHandler() {

			@Override
			public void onClick(MenuItemClickEvent event) {
				MakeCar makeCar = new MakeCar();
				makeCar.hide();

			}
		});
		subMakeCar.addClickHandler(new ClickHandler() {

			@Override
			public void onClick(MenuItemClickEvent event) {
				MakeCar makeCar = new MakeCar();
				makeCar.show();
				makeCar.centerInPage();

			}
		});
		subViewAgency.addClickHandler(new ClickHandler() {

			@Override
			public void onClick(MenuItemClickEvent event) {
				new TabAgency();

			}
		});
		subMakeAgency.addClickHandler(new ClickHandler() {

			@Override
			public void onClick(MenuItemClickEvent event) {
				MakeAgency makeAgency = new MakeAgency();
				makeAgency.show();
				makeAgency.centerInPage();

			}
		});
		setMenus(employeeMenu, customerMenu, optionalMenu, rentalMenu, carMenu, agencyMenu);
	}
}