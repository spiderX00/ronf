package it.unibo.ronf.client.makedialog;

import it.unibo.ronf.client.datasource.RentalDS;
import it.unibo.ronf.client.datasource.TransferDS;
import it.unibo.ronf.client.table.TabTransfer;
import it.unibo.ronf.shared.entities.Agency;
import it.unibo.ronf.shared.entities.Transfer;
import it.unibo.ronf.shared.services.AgencyService;
import it.unibo.ronf.shared.services.AgencyServiceAsync;
import it.unibo.ronf.shared.services.TransferService;
import it.unibo.ronf.shared.services.TransferServiceAsync;

import java.util.HashMap;
import java.util.List;
import java.util.Map;

import com.google.gwt.core.client.GWT;
import com.google.gwt.user.client.Window;
import com.google.gwt.user.client.rpc.AsyncCallback;
import com.smartgwt.client.data.DSCallback;
import com.smartgwt.client.data.DSRequest;
import com.smartgwt.client.data.DSResponse;
import com.smartgwt.client.types.Alignment;
import com.smartgwt.client.widgets.Button;
import com.smartgwt.client.widgets.Dialog;
import com.smartgwt.client.widgets.events.ClickEvent;
import com.smartgwt.client.widgets.events.ClickHandler;
import com.smartgwt.client.widgets.form.DynamicForm;
import com.smartgwt.client.widgets.layout.HLayout;

public class MakeTransfer extends Dialog {

	private final TransferServiceAsync transferService = GWT.create(TransferService.class);
	private final AgencyServiceAsync agencyService = GWT.create(AgencyService.class);
	private HLayout hLayout;
	private Map<String, Agency> agencyMap = new HashMap<String, Agency>();

	public MakeTransfer() {

		setSize("400px", "330px");
		final DynamicForm dynamicForm = new DynamicForm();
		dynamicForm.setSize("350px", "194px");
		addItem(dynamicForm);
		hLayout = new HLayout();
		hLayout.setHeight("46px");
		hLayout.setMembersMargin(40);
		agencyService.findAll(new AsyncCallback<List<Agency>>() {

			@Override
			public void onFailure(Throwable caught) {
				// TODO Auto-generated method stub

			}

			@Override
			public void onSuccess(List<Agency> result) {
				for (Agency c : result) {
					agencyMap.put("" + c.getId() + " - " + c.getName(), c);
				}
				final TabTransfer tabTransfer = new TabTransfer();
				if (TransferDS.getDataSource("transferDS") != null) {
					TransferDS.getDataSource("transferDS").destroy();
				}
				dynamicForm.setDataSource(new TransferDS("transferDS", tabTransfer, agencyMap));
				dynamicForm.getField("id").hide();
				dynamicForm.getField("success").hide();

			}
		});

		Button btnCancel = new Button("Cancel");
		btnCancel.setAlign(Alignment.CENTER);
		hLayout.addMember(btnCancel);

		Button btnCrea = new Button("Crea");
		hLayout.addMember(btnCrea);
		hLayout.setAlign(Alignment.CENTER);
		btnCrea.addClickHandler(new ClickHandler() {
			public void onClick(ClickEvent event) {
				/** al click viene creato un nuovo Customer */
				dynamicForm.saveData(new DSCallback() {
					public void execute(DSResponse response, Object rawData, DSRequest request) {
						dynamicForm.editNewRecord();
					}
				});
				Transfer transfer = new Transfer();
				transfer.setStartAgency(agencyMap.get(dynamicForm.getValueAsString("startAgency")));
				transfer.setArrivalAgency(agencyMap.get(dynamicForm.getValueAsString("arrivalAgency")));
				transfer.setSuccess(false);	
				transferService.createTransfer(transfer, new AsyncCallback<Void>() {
					@Override
					public void onSuccess(Void result) {
						MakeTransfer.this.hide();
						Window.alert("Transfer Created!");

					}

					@Override
					public void onFailure(Throwable caught) {
						Window.alert("Impossible to create Transfer : " + caught);
					}
				});

			}
		});

		btnCancel.addClickHandler(new ClickHandler() {

			@Override
			public void onClick(ClickEvent event) {
				MakeTransfer.this.hide();

			}
		});
		addItem(hLayout);
		hLayout.moveTo(30, 231);

	}

}
