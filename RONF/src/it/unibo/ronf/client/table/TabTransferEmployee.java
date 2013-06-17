package it.unibo.ronf.client.table;

import it.unibo.ronf.client.datasource.TransferEmployeeDS;
import it.unibo.ronf.client.record.TransferEmployeeRecord;
import it.unibo.ronf.shared.entities.Transfer;
import it.unibo.ronf.shared.services.TransferEmployeeService;
import it.unibo.ronf.shared.services.TransferEmployeeServiceAsync;
import it.unibo.ronf.shared.services.TransferService;
import it.unibo.ronf.shared.services.TransferServiceAsync;

import java.util.List;

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

public class TabTransferEmployee extends ListGrid {
	private final TransferEmployeeServiceAsync transferEmployeeService = GWT.create(TransferEmployeeService.class);
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
			ImgButton editImg = new ImgButton();
			editImg.setShowDown(false);
			editImg.setShowRollOver(false);
			editImg.setLayoutAlign(Alignment.CENTER);
			editImg.setSrc("transfer.png");
			editImg.setPrompt("AssignEmployee");
			editImg.setHeight(16);
			editImg.setWidth(16);
			editImg.addClickHandler(new ClickHandler() {
				@Override
				public void onClick(ClickEvent event) {
					SC.confirm("Assegnare un Transfer Employee a un Transfer?", new BooleanCallback() {

						@Override
						public void execute(Boolean value) {
							final TransferEmployeeRecord employeeRecord = (TransferEmployeeRecord) rollOverRecord;
							transferService.findAllPending(new AsyncCallback<List<Transfer>>() {

								@Override
								public void onFailure(Throwable caught) {
									Window.alert("Error to find pending Transfer!" + caught.getMessage());

								}

								@Override
								public void onSuccess(List<Transfer> result) {
									if (!result.isEmpty()) {
										employeeRecord.getObject().setBusy(true);
										result.get(0).setTransferEmployee(employeeRecord.getObject());
										transferService.SetEmployeePerTransfer(result.get(0), new AsyncCallback<Void>() {

											@Override
											public void onFailure(Throwable caught) {
												Window.alert("Error to set Transfer Employee " + caught.getMessage());
											}

											@Override
											public void onSuccess(Void result) {
												Window.alert("Transfer Employee Assegnato con successo!");

											}
										});
									} else {
										Window.alert("Transfer Not Found");
									}
								}
							});

						}
					});

				}
			});
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
								transferEmployeeService.removeById(rollOverRecord.getAttributeAsLong("id"), new AsyncCallback<Void>() {
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

			rollOverCanvas.addMembers(removeImg, editImg);
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
			TransferEmployeeRecord transferEmployeeRecord = (TransferEmployeeRecord) record;
			if (transferEmployeeRecord.getBusy() == true) {
				return "font-weight:bold; background-color:#e60000;";
			} else {
				return "font-weight:bold; background-color:#00cc00;";
			}
		} else {
			return super.getCellCSSText(record, rowNum, colNum);
		}
	}

	public TabTransferEmployee() {

	}

	/**
	 * funzione che viene chiamata nell'EmployeeDS solo una volta che la
	 * chiamata Asincrona ha avuto successo
	 */
	public static void setData(TransferEmployeeDS data, TabTransferEmployee tabTransferEmployee) {
		tabTransferEmployee.setShowRollOverCanvas(true);
		tabTransferEmployee.setWidth("99%");
		vPanel.setWidth100();
		tabTransferEmployee.setHeight(400);
		tabTransferEmployee.setShowFilterEditor(true);
		tabTransferEmployee.setFilterOnKeypress(true);
		tabTransferEmployee.setDataSource(data);
		tabTransferEmployee.setAutoFetchData(true);
		ListGridField idField = new ListGridField("id", "ID");
		idField.setAlign(Alignment.LEFT);
		ListGridField nameField = new ListGridField("name", "Name");
		ListGridField surnameField = new ListGridField("surname", "Surname");
		ListGridField ageField = new ListGridField("age", "Age");
		ageField.setAlign(Alignment.LEFT);

		tabTransferEmployee.setFields(new ListGridField[] { idField, nameField, surnameField, ageField });
		vPanel.addChild(tabTransferEmployee);
		rp.clear();
		rp.add(vPanel);
	}

}
