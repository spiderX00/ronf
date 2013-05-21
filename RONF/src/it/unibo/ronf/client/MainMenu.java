package it.unibo.ronf.client;

import com.google.gwt.user.client.Command;
import com.google.gwt.user.client.ui.MenuBar;
import com.google.gwt.user.client.ui.MenuItem;

/**
 * menu principale dell'applicazione
 *
 * @author Alessio De Vita alessio.dv@gmail.com
 */
public class MainMenu extends MenuBar {

	public MainMenu() {
		
		setAnimationEnabled(true);
		setWidth("100%");
		Command makeUtente = new Command() {
			public void execute() {
				MakeUser makeUser = new MakeUser();
				makeUser.show();
				makeUser.center();
				
			}
		};
		Command viewEmployee = new Command() {
			public void execute() {
				new TabEmployee();
				
				
			}
		};
		Command viewCustomer = new Command() {
			public void execute() {
				new TabCustomer();
				
				
			}
		};
		Command makeEmployee = new Command() {
			public void execute() {
				MakeEmployee makeEmployee = new MakeEmployee();
				makeEmployee.show();
				makeEmployee.center();	
			}
		};
		MenuBar menuEmployee = new MenuBar();
		MenuBar menuCustomer = new MenuBar();
		/** cliccando su crea viene creata una finestra di dialogo MakeUser*/
		MenuItem subMakeCustomer = new MenuItem ("Crea", makeUtente);
		MenuItem subViewEmployee = new MenuItem("Visualizza", viewEmployee);
		MenuItem subMakeEmployee = new MenuItem("Crea", makeEmployee);	
		MenuItem subViewCustomer = new MenuItem("Visualizza", viewCustomer);	
		addItem("Employee", menuEmployee);
		addItem("Customer", menuCustomer);
		menuCustomer.addItem(subMakeCustomer);
		menuEmployee.addItem(subMakeEmployee);
		menuEmployee.addItem(subViewEmployee);
		menuCustomer.addItem(subViewCustomer);
//		RootPanel rp = RootPanel.get();
//		FlowPanel fp = new FlowPanel();
//		fp.add(menuBar);
//		fp.addStyleName("center");
//		rp.add(fp);
	}
}
