package it.unibo.ronf.client.table;

import it.unibo.ronf.client.datasource.EmployeeDS;
import it.unibo.ronf.client.datasource.TransferEmployeeDS;
import it.unibo.ronf.client.record.TransferEmployeeRecord;
import it.unibo.ronf.shared.services.EmployeeService;
import it.unibo.ronf.shared.services.EmployeeServiceAsync;
import it.unibo.ronf.shared.services.TransferEmployeeService;
import it.unibo.ronf.shared.services.TransferEmployeeServiceAsync;

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
	private final TransferEmployeeServiceAsync transferEmployeeService = GWT
			.create(TransferEmployeeService.class);
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
								transferEmployeeService.removeById(
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
	/** Con questo metodo coloro lo sfondo (rosso o verde) a seconda di come è settato l'attributo busy di un transfer employee */
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

		/** Creo una nuovo oggetto DataSource e gli passo questa listGrid */
		if (TransferEmployeeDS.getInstance(TabTransferEmployee.this) != null) {
			rp.clear();
			rp.add(vPanel);
		}

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
		ListGridField nameField = new ListGridField("name", "Nome");
		ListGridField surnameField = new ListGridField("surname", "Cognome");
		ListGridField ageField = new ListGridField("age", "Età");
		ageField.setAlign(Alignment.LEFT);
		
		tabTransferEmployee.setFields(new ListGridField[] { idField, nameField, surnameField,
				ageField});
		vPanel.addChild(tabTransferEmployee);
		rp.clear();
		rp.add(vPanel);
	}

}

