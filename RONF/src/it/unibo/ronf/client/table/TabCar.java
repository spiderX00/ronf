package it.unibo.ronf.client.table;

import it.unibo.ronf.client.datasource.CarDS;
import it.unibo.ronf.shared.services.CarService;
import it.unibo.ronf.shared.services.CarServiceAsync;

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

public class TabCar extends ListGrid {

	private final CarServiceAsync carService = GWT
			.create(CarService.class);
	final static VLayout vPanel = new VLayout();
	final static RootPanel rp = RootPanel.get("content");
	private HLayout rollOverCanvas;
	private ListGridRecord rollOverRecord;

	/**
	 * Canvas che permette la visualizzazione dei tasti quando si passa il mouse
	 * sopra una riga, permettendone la modifica
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
				public void onClick(ClickEvent event) {
					SC.confirm("Sei sicuro?", new BooleanCallback() {
						public void execute(Boolean value) {
							if (Boolean.TRUE.equals(value)) {
								removeData(rollOverRecord);
								carService.removeById(
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

	public TabCar() {

		/** Creo una nuovo oggetto DataSource e gli passo questa listGrid */
//		if (CarDS.getInstance(TabCar.this, null) != null) {
//			rp.clear();
//			rp.add(vPanel);
//		}

	}

	/**
	 * funzione che viene chiamata nell'EmployeeDS solo una volta che la
	 * chiamata Asincrona ha avuto successo
	 */
	public static void setData(CarDS data, TabCar tabCar) {
		tabCar.setShowRollOverCanvas(true);
		tabCar.setWidth("99%");
		vPanel.setWidth100();
		tabCar.setHeight(400);
		tabCar.setShowFilterEditor(true);
		tabCar.setFilterOnKeypress(true);
		tabCar.setDataSource(data);
		tabCar.setAutoFetchData(true);
		ListGridField idField = new ListGridField("id", "ID");
		idField.setAlign(Alignment.LEFT);
		ListGridField modelField = new ListGridField("model", "Modello");
		ListGridField plateField = new ListGridField("plate", "Targa");
		ListGridField gasolineTypeField = new ListGridField("gasolineType", "Alimentazione");
		ListGridField seatsNumberField = new ListGridField("seatsNumber", "N. Posti");
		seatsNumberField.setAlign(Alignment.LEFT);
		ListGridField agencyField = new ListGridField("agency", "Agenzia");
		ListGridField typeField = new ListGridField("type", "Tipo");
		tabCar.setFields(new ListGridField[] { idField, modelField, plateField,
				gasolineTypeField, seatsNumberField, agencyField, typeField });
		vPanel.addChild(tabCar);
		rp.clear();
		rp.add(vPanel);
	}


}
