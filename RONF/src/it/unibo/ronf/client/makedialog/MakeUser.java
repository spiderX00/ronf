package it.unibo.ronf.client.makedialog;

import it.unibo.ronf.client.datasource.CustomerDS;
import it.unibo.ronf.client.table.TabCustomer;
import it.unibo.ronf.shared.entities.Customer;
import it.unibo.ronf.shared.services.CustomerService;
import it.unibo.ronf.shared.services.CustomerServiceAsync;

import com.google.gwt.core.client.GWT;
import com.google.gwt.user.client.Window;
import com.google.gwt.user.client.rpc.AsyncCallback;
import com.smartgwt.client.data.DSCallback;
import com.smartgwt.client.data.DSRequest;
import com.smartgwt.client.data.DSResponse;
import com.smartgwt.client.types.Alignment;
import com.smartgwt.client.util.SC;
import com.smartgwt.client.widgets.Button;
import com.smartgwt.client.widgets.Dialog;
import com.smartgwt.client.widgets.events.ClickEvent;
import com.smartgwt.client.widgets.events.ClickHandler;
import com.smartgwt.client.widgets.form.DynamicForm;
import com.smartgwt.client.widgets.layout.HLayout;

/**
 * finestra di dialogo per la creazione di un cliente
 * 
 * @author Alessio De Vita alessio.dv@gmail.com
 */
public class MakeUser extends Dialog {
	private final CustomerServiceAsync customerService = GWT.create(CustomerService.class);
	private HLayout hLayout;

	public MakeUser() {
		setSize("400px", "330px");
		final DynamicForm dynamicForm = new DynamicForm();
		dynamicForm.setSize("350px", "194px");
		addItem(dynamicForm);
		hLayout = new HLayout();
		hLayout.setHeight("46px");
		hLayout.setMembersMargin(40);
		final TabCustomer tabCustomer = new TabCustomer();
		dynamicForm.setDataSource(CustomerDS.getInstance(tabCustomer));
		dynamicForm.getField("id").hide();
		Button btnCancel = new Button("Cancel");
		btnCancel.setAlign(Alignment.CENTER);
		hLayout.addMember(btnCancel);

		Button btnCrea = new Button("Crea");
		hLayout.addMember(btnCrea);
		hLayout.setAlign(Alignment.CENTER);
		btnCrea.addClickHandler(new ClickHandler() {
			public void onClick(ClickEvent event) {
				/** al click viene creato un nuovo Customer */
				dynamicForm.saveData(new DSCallback() {
					public void execute(DSResponse response, Object rawData, DSRequest request) {
						dynamicForm.editNewRecord();
					}
				});
				Customer customer = new Customer();
				customer.setName(dynamicForm.getValueAsString("name"));
				customer.setSurname(dynamicForm.getValueAsString("surname"));
				customer.setAge(Integer.parseInt(dynamicForm.getValueAsString("age")));
				customer.setFiscalCode(dynamicForm.getValueAsString("fiscalCode"));
				customer.setDocNumber(dynamicForm.getValueAsString("docNumber"));
				customerService.createCustomer(customer, new AsyncCallback<Void>() {
					@Override
					public void onSuccess(Void result) {
						MakeUser.this.hide();
						SC.say("Optional Created!");

					}

					@Override
					public void onFailure(Throwable caught) {
						Window.alert("Impossible to create optional : " + caught);
					}
				});

			}
		});

		btnCancel.addClickHandler(new ClickHandler() {

			@Override
			public void onClick(ClickEvent event) {
				MakeUser.this.hide();

			}
		});
		addItem(hLayout);
		hLayout.moveTo(30, 231);

	}
}
