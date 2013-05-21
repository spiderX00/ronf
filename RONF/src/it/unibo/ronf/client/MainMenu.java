package it.unibo.ronf.client;

import com.google.gwt.user.client.Command;
import com.google.gwt.user.client.ui.FlowPanel;
import com.google.gwt.user.client.ui.MenuBar;
import com.google.gwt.user.client.ui.MenuItem;
import com.google.gwt.user.client.ui.RootPanel;

/**
 * menu principale dell'applicazione
 *
 * @author Alessio De Vita alessio.dv@gmail.com
 */
public class MainMenu extends MenuBar {

	public MainMenu() {

		setAnimationEnabled(true);
//		setSize("190px", "29px");
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
		MenuBar menuEmployee = new MenuBar();
		MenuBar menuCustomer = new MenuBar();
		/** cliccando su crea viene creata una finestra di dialogo MakeUser*/
		MenuItem subMakeCustomer = new MenuItem ("Crea", makeUtente);
		MenuItem subViewEmployee = new MenuItem("Visualizza", viewEmployee);	
		MenuItem subViewCustomer = new MenuItem("Visualizza", viewCustomer);	
		addItem("Employee", menuEmployee);
		addItem("Customer", menuCustomer);
		menuCustomer.addItem(subMakeCustomer);
		menuEmployee.addItem(subViewEmployee);
		menuCustomer.addItem(subViewCustomer);
//		RootPanel rp = RootPanel.get();
//		FlowPanel fp = new FlowPanel();
//		fp.add(menuBar);
//		fp.addStyleName("center");
//		rp.add(fp);
	}
}
