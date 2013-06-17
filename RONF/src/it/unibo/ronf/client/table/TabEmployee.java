package it.unibo.ronf.client.table;

import it.unibo.ronf.client.datasource.EmployeeDS;
import it.unibo.ronf.client.record.RentalRecord;
import it.unibo.ronf.shared.services.EmployeeService;
import it.unibo.ronf.shared.services.EmployeeServiceAsync;
import it.unibo.ronf.shared.services.TransferService;
import it.unibo.ronf.shared.services.TransferServiceAsync;

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
public class TabEmployee extends ListGrid {
	private final EmployeeServiceAsync employeeService = GWT.create(EmployeeService.class);
	final static VLayout vPanel = new VLayout();
	final static RootPanel rp = RootPanel.get("content");
	private HLayout rollOverCanvas;
	private ListGridRecord rollOverRecord;

	/**
	 * Canvas che permette la visualizzazione dei tasti quando si passa il mouse sopra una riga,
	 * permettendone la modifica
	 */
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
				@Override
				public void onClick(ClickEvent event) {
					SC.confirm("Sei sicuro?", new BooleanCallback() {
						@Override
						public void execute(Boolean value) {
							if (Boolean.TRUE.equals(value)) {
								removeData(rollOverRecord);
								employeeService.removeById(rollOverRecord.getAttributeAsLong("id"), new AsyncCallback<Void>() {
									@Override
									public void onSuccess(Void result) {
									}

									@Override
									public void onFailure(Throwable caught) {
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

	public TabEmployee() {

		/** Creo una nuovo oggetto DataSource e gli passo questa listGrid */
		if (EmployeeDS.getInstance(TabEmployee.this) != null) {
			rp.clear();
			rp.add(vPanel);
		}

	}

	/**
	 * funzione che viene chiamata nell'EmployeeDS solo una volta che la chiamata Asincrona ha avuto
	 * successo
	 */
	public static void setData(EmployeeDS data, TabEmployee tabEmployee) {
		tabEmployee.setShowRollOverCanvas(true);
		tabEmployee.setWidth("99%");
		vPanel.setWidth100();
		tabEmployee.setHeight(400);
		tabEmployee.setShowFilterEditor(true);
		tabEmployee.setFilterOnKeypress(true);
		tabEmployee.setDataSource(data);
		tabEmployee.setAutoFetchData(true);
		ListGridField idField = new ListGridField("id", "ID");
		idField.setAlign(Alignment.LEFT);
		ListGridField nameField = new ListGridField("name", "Name");
		ListGridField surnameField = new ListGridField("surname", "Surname");
		ListGridField ageField = new ListGridField("age", "Age");
		ageField.setAlign(Alignment.LEFT);
		ListGridField userNameField = new ListGridField("userName", "Username");

		tabEmployee.setFields(new ListGridField[] { idField, nameField, surnameField, ageField, userNameField });
		vPanel.addChild(tabEmployee);
		rp.clear();
		rp.add(vPanel);
	}

}
