package it.unibo.ronf.client;

import it.unibo.ronf.client.datasource.TransferActionDS;
import it.unibo.ronf.client.record.TransferActionRecord;
import it.unibo.ronf.client.record.TransferRecord;
import it.unibo.ronf.shared.entities.TransferAction;
import it.unibo.ronf.shared.services.TransferActionService;
import it.unibo.ronf.shared.services.TransferActionServiceAsync;

import com.google.gwt.core.client.GWT;
import com.google.gwt.user.client.Window;
import com.google.gwt.user.client.rpc.AsyncCallback;
import com.smartgwt.client.types.Alignment;
import com.smartgwt.client.types.DateDisplayFormat;
import com.smartgwt.client.util.BooleanCallback;
import com.smartgwt.client.util.SC;
import com.smartgwt.client.widgets.Canvas;
import com.smartgwt.client.widgets.Dialog;
import com.smartgwt.client.widgets.ImgButton;
import com.smartgwt.client.widgets.events.ClickEvent;
import com.smartgwt.client.widgets.events.ClickHandler;
import com.smartgwt.client.widgets.grid.ListGrid;
import com.smartgwt.client.widgets.grid.ListGridField;
import com.smartgwt.client.widgets.grid.ListGridRecord;
import com.smartgwt.client.widgets.layout.HLayout;

public class TransferActionDialog extends Dialog {
	private HLayout rollOverCanvas;
	private final TransferActionServiceAsync transferActionService = GWT.create(TransferActionService.class);

	public TransferActionDialog(TransferRecord record) {

		setSize("550px", "250px");
		HLayout h = new HLayout();
		h.setSize("450px", "200px");
		final ListGrid transferActionGrid = new ListGrid() {
			@Override
			protected Canvas getRollOverCanvas(Integer rowNum, Integer colNum) {
				final ListGridRecord rollOverRecord = this.getRecord(rowNum);

				if (rollOverCanvas == null) {
					rollOverCanvas = new HLayout(3);
					rollOverCanvas.setSnapTo("TR");
					rollOverCanvas.setWidth(50);
					rollOverCanvas.setHeight(22);
					ImgButton removeImg = new ImgButton();
					removeImg.setShowDown(false);
					removeImg.setShowRollOver(false);
					removeImg.setLayoutAlign(Alignment.RIGHT);
					removeImg.setSrc("remove.png");
					removeImg.setPrompt("Set Success");
					removeImg.setHeight(16);
					removeImg.setWidth(16);
					removeImg.addClickHandler(new ClickHandler() {
						@Override
						public void onClick(ClickEvent event) {
							SC.confirm("Transfer avvenuto con successo?", new BooleanCallback() {

								@Override
								public void execute(Boolean value) {
									if (Boolean.TRUE.equals(value)) {
										TransferActionRecord ta = (TransferActionRecord) rollOverRecord;
										ta.getObject().setSuccessAction(true);
										transferActionService.updateSuccessTransferAction(ta.getObject(), new AsyncCallback<Void>() {

											@Override
											public void onSuccess(Void result) {
												SC.say("Update Success!");
											}

											@Override
											public void onFailure(Throwable caught) {
												Window.alert("Error to update Success " + caught.getMessage());
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

			@Override
			protected String getCellCSSText(ListGridRecord record, int rowNum, int colNum) {
				if (getFieldName(rowNum) != null) {
					TransferActionRecord transferRecord = (TransferActionRecord) record;
					if (transferRecord.getObject().isSuccessAction() == false) {
						return "font-weight:bold; background-color:#e60000;";
					} else {
						return "font-weight:bold; background-color:#00cc00;";
					}
				} else {
					return super.getCellCSSText(record, rowNum, colNum);
				}
			}
		};
		transferActionGrid.setShowRollOverCanvas(true);
		transferActionGrid.setWidth(500);
		transferActionGrid.setHeight(200);
		transferActionGrid.setShowFilterEditor(true);
		transferActionGrid.setFilterOnKeypress(true);
		transferActionGrid.setDataSource(TransferActionDS.getInstance(record));
		transferActionGrid.setAutoFetchData(true);
		ListGridField idField = new ListGridField("id", "ID");
		idField.setAlign(Alignment.LEFT);
		ListGridField startingAgencyField = new ListGridField("carRequired", "Car");
		ListGridField transferDateField = new ListGridField("transferDate", "Data di Transferimento");
		transferDateField.setAlign(Alignment.LEFT);
		transferDateField.setDateFormatter(DateDisplayFormat.TOEUROPEANSHORTDATE);
		transferActionGrid.setFields(startingAgencyField, transferDateField);
		h.addMember(transferActionGrid);
		// transferActionGrid.draw();
		addItem(h);
	}

}
