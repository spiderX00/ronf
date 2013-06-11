package it.unibo.ronf.client.table;

import it.unibo.ronf.client.TransferActionDialog;
import it.unibo.ronf.client.datasource.TransferDS;
import it.unibo.ronf.client.record.TransferRecord;
import it.unibo.ronf.shared.services.TransferService;
import it.unibo.ronf.shared.services.TransferServiceAsync;

import com.google.gwt.core.client.GWT;
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
import com.smartgwt.client.widgets.grid.events.RecordDoubleClickEvent;
import com.smartgwt.client.widgets.grid.events.RecordDoubleClickHandler;
import com.smartgwt.client.widgets.layout.HLayout;
import com.smartgwt.client.widgets.layout.VLayout;

public class TabTransfer extends ListGrid {
	private final TransferServiceAsync transferService = GWT.create(TransferService.class);
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
								// transferService.removeById(
								// rollOverRecord.getAttributeAsLong("id"),
								// new AsyncCallback<Boolean>() {
								// @Override
								// public void onSuccess(Boolean result) {
								// }
								//
								// @Override
								// public void onFailure(
								// Throwable caught) {
								// Window.alert("Errore nell'eliminazione");
								// }
								// });
							}
						}
					});

				}
			});

			rollOverCanvas.addMember(removeImg);
		}
		return rollOverCanvas;

	}

	/**
	 * Con questo metodo coloro lo sfondo (rosso o verde) a seconda di come è
	 * settato l'attributo busy di un transfer employee
	 */
	@Override
	protected String getCellCSSText(ListGridRecord record, int rowNum, int colNum) {
		if (getFieldName(rowNum) != null) {
			TransferRecord transferRecord = (TransferRecord) record;
			if (transferRecord.getSuccess() == true) {
				return "font-weight:bold; background-color:#e60000;";
			} else {
				return "font-weight:bold; background-color:#00cc00;";
			}
		} else {
			return super.getCellCSSText(record, rowNum, colNum);
		}
	}

	public TabTransfer() {
		addRecordDoubleClickHandler(new RecordDoubleClickHandler() {

			@Override
			public void onRecordDoubleClick(RecordDoubleClickEvent event) {
				TransferRecord record = (TransferRecord) event.getRecord();
				TransferActionDialog transferActionDialog = new TransferActionDialog(record);
				transferActionDialog.show();
				transferActionDialog.centerInPage();
			}
		});

	}

	/**
	 * funzione che viene chiamata nell'EmployeeDS solo una volta che la
	 * chiamata Asincrona ha avuto successo
	 */
	public static void setData(TransferDS data, TabTransfer tabTransfer) {
		tabTransfer.setShowRollOverCanvas(true);
		tabTransfer.setWidth("99%");
		vPanel.setWidth100();
		tabTransfer.setHeight(400);
		tabTransfer.setShowFilterEditor(true);
		tabTransfer.setFilterOnKeypress(true);
		tabTransfer.setDataSource(data);
		tabTransfer.setAutoFetchData(true);
		ListGridField idField = new ListGridField("id", "ID");
		idField.setAlign(Alignment.LEFT);
		ListGridField startingAgencyField = new ListGridField("startAgency", "Agenzia di partenza");
		ListGridField arrivalAgencyField = new ListGridField("arrivalAgency", "Agenzia di arrivo");
		ListGridField successField = new ListGridField("success", "Concluso");
		tabTransfer.setFields(new ListGridField[] { idField, startingAgencyField, arrivalAgencyField, successField });
		vPanel.addChild(tabTransfer);
		rp.clear();
		rp.add(vPanel);
	}

}
