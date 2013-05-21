package it.unibo.ronf.client;

import it.unibo.ronf.shared.entities.Customer;
import it.unibo.ronf.shared.services.CustomerService;
import it.unibo.ronf.shared.services.CustomerServiceAsync;

import com.google.gwt.core.client.GWT;
import com.google.gwt.event.dom.client.ClickEvent;
import com.google.gwt.event.dom.client.ClickHandler;
import com.google.gwt.user.client.Window;
import com.google.gwt.user.client.rpc.AsyncCallback;
import com.google.gwt.user.client.ui.AbsolutePanel;
import com.google.gwt.user.client.ui.Button;
import com.google.gwt.user.client.ui.DialogBox;
import com.google.gwt.user.client.ui.Label;
import com.google.gwt.user.client.ui.TextBox;
import com.google.gwt.user.client.ui.VerticalPanel;
/**
 * finestra di dialogo per la creazione di un cliente
 *
 * @author Alessio De Vita alessio.dv@gmail.com
 */
public class MakeUser extends DialogBox {
	private final CustomerServiceAsync customerService = GWT
			.create(CustomerService.class);

	public MakeUser() {
		setText("Make User");
		setGlassEnabled(true);
		setSize("439px", "440px");

		AbsolutePanel absPan = new AbsolutePanel();
		absPan.setSize("410px", "397px");

		VerticalPanel verticalPanel = new VerticalPanel();
		absPan.add(verticalPanel, 24, 20);
		verticalPanel.setSize("100px", "270px");

		Label lblNome = new Label("Nome");
		verticalPanel.add(lblNome);

		Label lblCognome = new Label("Cognome");
		verticalPanel.add(lblCognome);

		Label lblEt = new Label("Età");
		verticalPanel.add(lblEt);

		Label lblCF = new Label("C. F.");
		verticalPanel.add(lblCF);

		Label lblNDocument = new Label("N. Documento");
		verticalPanel.add(lblNDocument);

		VerticalPanel verticalPanel_1 = new VerticalPanel();
		absPan.add(verticalPanel_1, 176, 20);
		verticalPanel_1.setSize("182px", "270px");

		final TextBox name = new TextBox();
		verticalPanel_1.add(name);

		final TextBox surname = new TextBox();
		verticalPanel_1.add(surname);

		final TextBox age = new TextBox();
		verticalPanel_1.add(age);

		final TextBox cf = new TextBox();
		verticalPanel_1.add(cf);

		final TextBox docNum = new TextBox();
		verticalPanel_1.add(docNum);

		Button makeButton = new Button("New button");
		makeButton.setText("Crea");
		absPan.add(makeButton, 254, 310);
		makeButton.addClickHandler(new ClickHandler() {
			public void onClick(ClickEvent event) {
				/** al click viene creato un nuovo Customer */
				Customer customer = new Customer();
				customer.setName(name.getText());
				customer.setSurname(surname.getText());
				customer.setAge(Integer.parseInt(age.getText()));
				customer.setFiscalCode(cf.getText());
				customer.setDocNumber(docNum.getText());
				customerService.createCustomer(customer,
						new AsyncCallback<Void>() {
							@Override
							public void onSuccess(Void result) {
								MakeUser.this.hide();
								Window.alert("Customer Created!");
							}

							@Override
							public void onFailure(Throwable caught) {
								Window.alert("Impossible to create customer!");
							}
						});

			}
		});
		Button cancelButton = new Button("New button");
		cancelButton.setText("Cancel");
		cancelButton.addClickHandler(new ClickHandler() {
			public void onClick(ClickEvent event) {
				MakeUser.this.hide();
				
			}
		});
		absPan.add(cancelButton, 60, 310);
		getElement().getStyle().setZIndex(8000001);
		setWidget(absPan);

	}
}
