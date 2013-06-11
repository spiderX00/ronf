package it.unibo.ronf.client;

import it.unibo.ronf.client.datasource.TransferActionDS;
import it.unibo.ronf.client.record.TransferRecord;

import com.smartgwt.client.types.Alignment;
import com.smartgwt.client.widgets.Dialog;
import com.smartgwt.client.widgets.grid.ListGrid;
import com.smartgwt.client.widgets.grid.ListGridField;

public class TransferActionDialog extends Dialog{
	
	public TransferActionDialog(TransferRecord record){
		setSize("600px", "450px");
		ListGrid transferActionGrid = new ListGrid();
		transferActionGrid.setWidth100();
		transferActionGrid.setHeight(400);
		transferActionGrid.setShowFilterEditor(true);
		transferActionGrid.setFilterOnKeypress(true);
		transferActionGrid.setDataSource(TransferActionDS.getInstance(record));
		transferActionGrid.setAutoFetchData(true);
		ListGridField idField = new ListGridField("id", "ID");
		idField.setAlign(Alignment.LEFT);
		ListGridField startingAgencyField = new ListGridField("carRequired", "Car");
		ListGridField transferDateField = new ListGridField("transferDate", "Data di Transferimento");
		transferActionGrid.setFields(startingAgencyField,transferDateField);
		addItem(transferActionGrid);
		transferActionGrid.draw();
		
	}

}
