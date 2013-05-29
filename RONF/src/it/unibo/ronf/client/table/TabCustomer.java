package it.unibo.ronf.client.table;

import it.unibo.ronf.client.datasource.CustomerDS;
import it.unibo.ronf.shared.services.CustomerService;
import it.unibo.ronf.shared.services.CustomerServiceAsync;

import com.google.gwt.core.client.GWT;
import com.google.gwt.user.client.Window;
import com.google.gwt.user.client.rpc.AsyncCallback;
import com.google.gwt.user.client.ui.RootPanel;
import com.smartgwt.client.types.Alignment;
import com.smartgwt.client.util.BooleanCallback;
import com.smartgwt.client.util.SC;
import com.smartgwt.client.widgets.Canvas;
import com.smartgwt.client.widgets.ImgButton;
import com.smartgwt.client.widgets.events.ClickEvent;
import com.smartgwt.client.widgets.events.ClickHandler;
import com.smartgwt.client.widgets.grid.ListGrid;
import com.smartgwt.client.widgets.grid.ListGridField;
import com.smartgwt.client.widgets.grid.ListGridRecord;
import com.smartgwt.client.widgets.layout.HLayout;
import com.smartgwt.client.widgets.layout.VLayout;

/**
 * Tabella, con live-filter
 * 
 * @author Alessio De Vita alessio.dv@gmail.com
 */
public class TabCustomer extends ListGrid {
	private final CustomerServiceAsync customerService = GWT
			.create(CustomerService.class);
	private HLayout rollOverCanvas;
	private ListGridRecord rollOverRecord;
	final static VLayout vPanel = new VLayout();
	final static RootPanel rp = RootPanel.get("content");

	@Override
	protected Canvas getRollOverCanvas(Integer rowNum, Integer colNum) {
		rollOverRecord = this.getRecord(rowNum);

		if (rollOverCanvas == null) {
			rollOverCanvas = new HLayout(3);
			rollOverCanvas.setSnapTo("TR");
			rollOverCanvas.setWidth(50);
			rollOverCanvas.setHeight(22);

			ImgButton removeImg = new ImgButton();
			removeImg.setShowDown(false);
			removeImg.setShowRollOver(false);
			removeImg.setLayoutAlign(Alignment.CENTER);
			removeImg.setSrc("remove.png");
			removeImg.setPrompt("Remove record");
			removeImg.setHeight(16);
			removeImg.setWidth(16);
			removeImg.addClickHandler(new ClickHandler() {
				public void onClick(ClickEvent event) {
					SC.confirm("Sei sicuro?", new BooleanCallback() {
						public void execute(Boolean value) {
							if (Boolean.TRUE.equals(value)) {
								removeData(rollOverRecord);
								customerService.removeById(
										rollOverRecord.getAttributeAsLong("id"),
										new AsyncCallback<Void>() {
											@Override
											public void onSuccess(Void result) {
											}

											@Override
											public void onFailure(
													Throwable caught) {
												Window.alert("Errore nell'eliminazione");
											}
										});
							}
						}
					});

				}
			});

			rollOverCanvas.addMember(removeImg);
		}
		return rollOverCanvas;

	}

	public TabCustomer() {

		/** Creo una nuovo oggetto DataSource e gli passo questa listGrid */
		if (CustomerDS.getInstance(TabCustomer.this) != null) {
			rp.clear();
			rp.add(vPanel);
		}

	}

	/**
	 * funzione che viene chiamata nell'EmployeeDS solo una volta che la
	 * chiamata Asincrona ha avuto successo
	 */
	public static void setData(CustomerDS data, TabCustomer tabCustomer) {
		tabCustomer.setShowRollOverCanvas(true);
		tabCustomer.setWidth("99%");
		vPanel.setWidth100();
		tabCustomer.setHeight(400);
		tabCustomer.setShowFilterEditor(true);
		tabCustomer.setFilterOnKeypress(true);
		tabCustomer.setDataSource(data);
		tabCustomer.setAutoFetchData(true);
		ListGridField idField = new ListGridField("id", "ID");
		idField.setAlign(Alignment.LEFT);
		ListGridField nameField = new ListGridField("name", "Nome");
		ListGridField surnameField = new ListGridField("surname", "Cognome");
		ListGridField ageField = new ListGridField("age", "EtÃ ");
		ageField.setAlign(Alignment.LEFT);
		ListGridField fiscalCodeField = new ListGridField("fiscalCode",
				"Cod. Fiscale");
		ListGridField docNumberField = new ListGridField("docNumber",
				"Documento n.");

		tabCustomer.setFields(new ListGridField[] { idField, nameField,
				surnameField, ageField, fiscalCodeField, docNumberField });
		vPanel.addChild(tabCustomer);
		rp.clear();
		rp.add(vPanel);
	}

}
