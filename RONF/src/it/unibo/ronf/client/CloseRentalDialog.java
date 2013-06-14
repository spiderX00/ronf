package it.unibo.ronf.client;

import it.unibo.ronf.shared.entities.Agency;
import it.unibo.ronf.shared.entities.Rental;
import it.unibo.ronf.shared.services.AgencyService;
import it.unibo.ronf.shared.services.AgencyServiceAsync;
import it.unibo.ronf.shared.services.RentalRemoteService;
import it.unibo.ronf.shared.services.RentalRemoteServiceAsync;

import java.util.HashMap;
import java.util.List;
import java.util.Map;

import com.google.gwt.core.client.GWT;
import com.google.gwt.i18n.client.DateTimeFormat;
import com.google.gwt.i18n.client.DateTimeFormat.PredefinedFormat;
import com.google.gwt.user.client.Window;
import com.google.gwt.user.client.rpc.AsyncCallback;
import com.smartgwt.client.util.SC;
import com.smartgwt.client.widgets.Dialog;
import com.smartgwt.client.widgets.form.DynamicForm;
import com.smartgwt.client.widgets.form.fields.ButtonItem;
import com.smartgwt.client.widgets.form.fields.SelectItem;
import com.smartgwt.client.widgets.form.fields.TextItem;
import com.smartgwt.client.widgets.form.fields.events.ChangedEvent;
import com.smartgwt.client.widgets.form.fields.events.ChangedHandler;
import com.smartgwt.client.widgets.form.fields.events.ClickEvent;
import com.smartgwt.client.widgets.form.fields.events.ClickHandler;

public class CloseRentalDialog extends Dialog {

	private final AgencyServiceAsync agencyService = GWT.create(AgencyService.class);
	private final RentalRemoteServiceAsync rentalRemoteService = GWT.create(RentalRemoteService.class);

	private DynamicForm dynamicForm;
	private SelectItem agencyItem;
	private SelectItem rentalItem;
	private ButtonItem closeButton;
	private TextItem userIdTxtItem;
	private Map<String, Agency> agencyMap;
	private Map<String, Rental> rentalMap;

	public CloseRentalDialog() {
		setSize("370", "300px");

		dynamicForm = new DynamicForm();
		dynamicForm.setSize("350px", "194px");

		userIdTxtItem = new TextItem("userID", "User ID");
		agencyItem = new SelectItem("agency", "Start Agency");
		rentalItem = new SelectItem("rental", "User Rental");
		closeButton = new ButtonItem("close", "Close Selcted Rental");

		dynamicForm.setFields(userIdTxtItem, agencyItem, rentalItem, closeButton);

		agencyService.getOthersAgencies(new AsyncCallback<List<Agency>>() {

			@Override
			public void onSuccess(List<Agency> result) {
				agencyMap = new HashMap<String, Agency>();
				for (Agency c : result) {
					agencyMap.put(c.getName(), c);
				}
				agencyItem.setValueMap(agencyMap.keySet().toArray(new String[] {}));
			}

			@Override
			public void onFailure(Throwable caught) {
				Window.alert("Error while loading agency: " + caught.getMessage());
			}
		});

		agencyItem.addChangedHandler(new AgencyChangeHandler());
		closeButton.addClickHandler(new CloseBtnHandler());
		addItem(dynamicForm);
	}

	class AgencyChangeHandler implements ChangedHandler {

		@Override
		public void onChanged(ChangedEvent event) {
			String userIDStr = userIdTxtItem.getValueAsString();
			if (userIDStr.trim().isEmpty()) {
				Window.alert("Insert User ID!");
				return;
			}
			// andrebbe il controllo sulla correttezza
			int userID = Integer.parseInt(userIDStr);
			Agency a = agencyMap.get(agencyItem.getValueAsString());

			rentalRemoteService.getUserRemoteRental(userID, a, new AsyncCallback<List<Rental>>() {

				@Override
				public void onSuccess(List<Rental> result) {
					rentalMap = new HashMap<String, Rental>();

					for (Rental r : result) {
						String start = DateTimeFormat.getFormat(PredefinedFormat.DATE_SHORT).format(r.getStart());
						String end = DateTimeFormat.getFormat(PredefinedFormat.DATE_SHORT).format(r.getEnd());
						rentalMap.put(start + " - " + end, r);
					}
					rentalItem.clearValue();
					rentalItem.setValueMap(rentalMap.keySet().toArray(new String[] {}));
				}

				@Override
				public void onFailure(Throwable caught) {
					Window.alert("Error while loading remote Rental: " + caught.getMessage());
				}
			});
		}
	}

	class CloseBtnHandler implements ClickHandler {

		@Override
		public void onClick(ClickEvent event) {
			final Rental r = rentalMap.get(rentalItem.getValueAsString());
			rentalRemoteService.closeRemoteRental(r, new AsyncCallback<Void>() {

				@Override
				public void onSuccess(Void result) {
					SC.say("Rental successfully closed on: " + r.getStartingAgency().getName());
				}

				@Override
				public void onFailure(Throwable caught) {
					Window.alert("Error while closing remote Rental: " + caught.getMessage());
				}

			});
		}

	}

}
