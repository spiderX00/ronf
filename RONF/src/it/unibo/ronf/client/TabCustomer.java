package it.unibo.ronf.client;

import com.google.gwt.user.client.ui.RootPanel;
import com.smartgwt.client.types.Alignment;
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
	private HLayout rollOverCanvas;  
    private ListGridRecord rollOverRecord;  
    final static VLayout vPanel = new VLayout();
	final static RootPanel rp = RootPanel.get("content");
	 @Override  
     protected Canvas getRollOverCanvas(Integer rowNum, Integer colNum) {  
         rollOverRecord = this.getRecord(rowNum);  

         if(rollOverCanvas == null) {  
             rollOverCanvas = new HLayout(3);  
             rollOverCanvas.setSnapTo("TR");  
             rollOverCanvas.setWidth(50);  
             rollOverCanvas.setHeight(22);  

             ImgButton editImg = new ImgButton();  
             editImg.setShowDown(false);  
             editImg.setShowRollOver(false);  
             editImg.setLayoutAlign(Alignment.CENTER);  
             editImg.setSrc("comment_edit.png");  
             editImg.setPrompt("Edit Comments");  
             editImg.setHeight(16);  
             editImg.setWidth(16);  
             editImg.addClickHandler(new ClickHandler() {  
                 public void onClick(ClickEvent event) {  
                     SC.say("Edit Comment Icon Clicked for country : " + rollOverRecord.getAttribute("countryName"));  
                 }  
             });  

             ImgButton chartImg = new ImgButton();  
             chartImg.setShowDown(false);  
             chartImg.setShowRollOver(false);  
             chartImg.setLayoutAlign(Alignment.CENTER);  
             chartImg.setSrc("chart_bar.png");  
             chartImg.setPrompt("View Chart");  
             chartImg.setHeight(16);  
             chartImg.setWidth(16);  
             chartImg.addClickHandler(new ClickHandler() {  
                 public void onClick(ClickEvent event) {  
                     SC.say("Chart Icon Clicked for country : " + rollOverRecord.getAttribute("countryName"));  
                 }  
             });  

             rollOverCanvas.addMember(editImg);  
             rollOverCanvas.addMember(chartImg);  
         }  
         return rollOverCanvas;  

     } 
	
	
	public TabCustomer() {
		
		
		setShowRollOverCanvas(true);
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
	static void setdata(CustomerDS data, TabCustomer tabCustomer) {
		
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
		ListGridField ageField = new ListGridField("age", "Età");
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
