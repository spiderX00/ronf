package it.unibo.ronf.client;

import it.unibo.ronf.server.rest.client.RentalRestClient;
import it.unibo.ronf.shared.entities.Agency;
import it.unibo.ronf.shared.entities.Rental;
import it.unibo.ronf.shared.services.AgencyService;
import it.unibo.ronf.shared.services.AgencyServiceAsync;

import java.text.DateFormat;
import java.util.HashMap;
import java.util.List;
import java.util.Map;

import org.springframework.beans.factory.annotation.Autowired;

import com.google.gwt.core.client.GWT;
import com.google.gwt.user.client.Window;
import com.google.gwt.user.client.rpc.AsyncCallback;
import com.smartgwt.client.widgets.Dialog;
import com.smartgwt.client.widgets.form.DynamicForm;
import com.smartgwt.client.widgets.form.fields.ButtonItem;
import com.smartgwt.client.widgets.form.fields.SelectItem;
import com.smartgwt.client.widgets.form.fields.TextItem;
import com.smartgwt.client.widgets.form.fields.events.ChangedEvent;
import com.smartgwt.client.widgets.form.fields.events.ChangedHandler;

public class CloseRentalDialog extends Dialog {

	private final AgencyServiceAsync agencyService = GWT.create(AgencyService.class);
	
	@Autowired
	private RentalRestClient rentalRestClient;

	private DynamicForm dynamicForm;
	private SelectItem agencyItem;
	private SelectItem rentalItem;
	private ButtonItem closeButton;
	private TextItem userIdTxtItem;
	private Map<String, Agency> agencyMap;
	private Map<String, Rental> rentalMap;

	public CloseRentalDialog() {
		setSize("370", "500px");

		dynamicForm = new DynamicForm();
		dynamicForm.setSize("350px", "194px");

		userIdTxtItem = new TextItem("userID", "User ID");
		agencyItem = new SelectItem("agency", "Start Agency");
		rentalItem = new SelectItem("rental", "User Rental");
		closeButton = new ButtonItem("close", "Close Selcted Rental");

		dynamicForm.setFields(agencyItem, rentalItem);

		agencyService.findAll(new AsyncCallback<List<Agency>>() {

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

			List<Rental> rentalList = rentalRestClient.getUserRemoteRental(userID, a);

			rentalMap = new HashMap<>();

			for (Rental r : rentalList) {
				String start = DateFormat.getDateInstance().format(r.getStart());
				String end = DateFormat.getDateInstance().format(r.getEnd());
				rentalMap.put(start + " - " + end, r);
			}
			rentalItem.setValueMap(rentalMap.keySet().toArray(new String[]{}));
		}
	}

}
