package it.unibo.ronf.client.table;

import it.unibo.ronf.client.datasource.RentalDS;
import it.unibo.ronf.client.makedialog.CloseRental;
import it.unibo.ronf.client.record.RentalRecord;
import it.unibo.ronf.shared.services.CarService;
import it.unibo.ronf.shared.services.CarServiceAsync;

import com.google.gwt.core.client.GWT;
import com.google.gwt.user.client.ui.RootPanel;
import com.smartgwt.client.types.Alignment;
import com.smartgwt.client.types.DateDisplayFormat;
import com.smartgwt.client.widgets.Canvas;
import com.smartgwt.client.widgets.ImgButton;
import com.smartgwt.client.widgets.events.ClickEvent;
import com.smartgwt.client.widgets.events.ClickHandler;
import com.smartgwt.client.widgets.grid.ListGrid;
import com.smartgwt.client.widgets.grid.ListGridField;
import com.smartgwt.client.widgets.grid.ListGridRecord;
import com.smartgwt.client.widgets.layout.HLayout;
import com.smartgwt.client.widgets.layout.VLayout;

public class TabRental extends ListGrid {

	private final CarServiceAsync rentalService = GWT.create(CarService.class);
	final static VLayout vPanel = new VLayout();
	final static RootPanel rp = RootPanel.get("content");

	public static void setData(RentalDS data, TabRental tabRental) {
		tabRental.setShowRollOverCanvas(true);
		tabRental.setWidth("99%");
		vPanel.setWidth100();
		tabRental.setHeight(400);
		tabRental.setShowFilterEditor(true);
		tabRental.setFilterOnKeypress(true);
		tabRental.setDataSource(data);
		tabRental.setAutoFetchData(true);
		ListGridField idField = new ListGridField("id", "ID");
		idField.setAlign(Alignment.LEFT);
		ListGridField startField = new ListGridField("start", "Start date");
		startField.setDateFormatter(DateDisplayFormat.TOEUROPEANSHORTDATE);
		ListGridField endField = new ListGridField("end", "End date");
		endField.setDateFormatter(DateDisplayFormat.TOEUROPEANSHORTDATE);
		ListGridField rentedCarField = new ListGridField("rentedCar", "Rented Car");
		rentedCarField.setCanFilter(false);
		ListGridField customerField = new ListGridField("customer", "Customer");
		ListGridField startingAgencyField = new ListGridField("startingAgency", "Starting agency");
		ListGridField arrivalAgencyField = new ListGridField("arrivalAgency", "Arrival agency");
		ListGridField optionalField = new ListGridField("optional", "Optional n.");
		optionalField.setAlign(Alignment.LEFT);
		ListGridField paymentField = new ListGridField("payment", "payment");
		ListGridField cautionField = new ListGridField("caution", "caution");
		ListGridField finishedField = new ListGridField("finished", "Finished");

		tabRental.setFields(new ListGridField[] { idField, startField, endField, rentedCarField, customerField, startingAgencyField, arrivalAgencyField, optionalField, paymentField, cautionField, finishedField });
		vPanel.addChild(tabRental);
		rp.clear();
		rp.add(vPanel);
	}

	private HLayout rollOverCanvas;

	public TabRental() {

	}

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
			removeImg.setLayoutAlign(Alignment.CENTER);
			removeImg.setSrc("remove.png");
			removeImg.setPrompt("Remove record");
			removeImg.setHeight(16);
			removeImg.setWidth(16);
			removeImg.addClickHandler(new ClickHandler() {
				@Override
				public void onClick(ClickEvent event) {
					/** Dialog Chiusura Rental */
					if (rollOverRecord instanceof RentalRecord) {
						RentalRecord rentalRecord = (RentalRecord) rollOverRecord;
						CloseRental closeRental = new CloseRental(rentalRecord);
						closeRental.show();
						closeRental.centerInPage();
					}

				}
			});

			rollOverCanvas.addMember(removeImg);
		}
		return rollOverCanvas;

	}
}