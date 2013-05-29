package it.unibo.ronf.client.table;

import it.unibo.ronf.client.datasource.AgencyDS;
import it.unibo.ronf.shared.services.AgencyService;
import it.unibo.ronf.shared.services.AgencyServiceAsync;

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

public class TabAgency extends ListGrid{
	
	private final AgencyServiceAsync agencyService = GWT
			.create(AgencyService.class);
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
								agencyService.removeById(
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

	public TabAgency() {

		/** Creo una nuovo oggetto DataSource e gli passo questa listGrid */
		if (AgencyDS.getInstance(TabAgency.this) != null) {
			rp.clear();
			rp.add(vPanel);
		}

	}

	/**
	 * funzione che viene chiamata nell'EmployeeDS solo una volta che la
	 * chiamata Asincrona ha avuto successo
	 */
	public static void setData(AgencyDS data, TabAgency tabAgency) {
		tabAgency.setShowRollOverCanvas(true);
		tabAgency.setWidth("99%");
		vPanel.setWidth100();
		tabAgency.setHeight(400);
		tabAgency.setShowFilterEditor(true);
		tabAgency.setFilterOnKeypress(true);
		tabAgency.setDataSource(data);
		tabAgency.setAutoFetchData(true);
		ListGridField idField = new ListGridField("id", "ID");
		idField.setAlign(Alignment.LEFT);
		ListGridField codeField = new ListGridField("code", "Codice");
		ListGridField nameField = new ListGridField("name", "Nome");
		ListGridField addressField = new ListGridField("address", "Indirizzo");
		ListGridField ipAddressField = new ListGridField("ipAddress",
				"IP");

		tabAgency.setFields(new ListGridField[] { idField, codeField,
				nameField, addressField, ipAddressField});
		vPanel.addChild(tabAgency);
		rp.clear();
		rp.add(vPanel);
	}

}
