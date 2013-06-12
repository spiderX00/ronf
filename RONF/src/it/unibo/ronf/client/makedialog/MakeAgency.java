package it.unibo.ronf.client.makedialog;

import it.unibo.ronf.client.datasource.AgencyDS;
import it.unibo.ronf.client.table.TabAgency;
import it.unibo.ronf.shared.entities.Agency;
import it.unibo.ronf.shared.services.AgencyService;
import it.unibo.ronf.shared.services.AgencyServiceAsync;

import com.google.gwt.core.client.GWT;
import com.google.gwt.user.client.Window;
import com.google.gwt.user.client.rpc.AsyncCallback;
import com.smartgwt.client.data.DSCallback;
import com.smartgwt.client.data.DSRequest;
import com.smartgwt.client.data.DSResponse;
import com.smartgwt.client.types.Alignment;
import com.smartgwt.client.util.SC;
import com.smartgwt.client.widgets.Button;
import com.smartgwt.client.widgets.Dialog;
import com.smartgwt.client.widgets.events.ClickEvent;
import com.smartgwt.client.widgets.events.ClickHandler;
import com.smartgwt.client.widgets.form.DynamicForm;
import com.smartgwt.client.widgets.layout.HLayout;

public class MakeAgency extends Dialog {
	private final AgencyServiceAsync agencyService = GWT.create(AgencyService.class);
	private HLayout hLayout;
	private DynamicForm dynamicForm;

	public MakeAgency() {
		setSize("400px", "330px");
		dynamicForm = new DynamicForm();
		dynamicForm.setSize("350px", "194px");
		addItem(dynamicForm);
		hLayout = new HLayout();
		hLayout.setHeight("46px");
		hLayout.setMembersMargin(40);
		final TabAgency tabAgency = new TabAgency();
		dynamicForm.setDataSource(AgencyDS.getInstance(tabAgency));
		dynamicForm.getField("id").hide();
		Button btnCancel = new Button("Cancel");
		btnCancel.setAlign(Alignment.CENTER);
		hLayout.addMember(btnCancel);

		Button btnCrea = new Button("Crea");
		hLayout.addMember(btnCrea);
		hLayout.setAlign(Alignment.CENTER);
		btnCrea.addClickHandler(new CreateBtnHandler());

		btnCancel.addClickHandler(new ClickHandler() {

			@Override
			public void onClick(ClickEvent event) {
				MakeAgency.this.hide();

			}
		});
		addItem(hLayout);
		hLayout.moveTo(30, 231);

	}
	
	class CreateBtnHandler implements ClickHandler {
		public void onClick(ClickEvent event) {
			/** al click viene creato un nuovo Customer */
			dynamicForm.saveData(new DSCallback() {
				public void execute(DSResponse response, Object rawData,
						DSRequest request) {
					dynamicForm.editNewRecord();
				}
			});
			Agency agency = new Agency();
			agency.setCode(dynamicForm.getValueAsString("code"));
			agency.setName(dynamicForm.getValueAsString("name"));
			agency.setAddress(dynamicForm.getValueAsString("address"));
			agency.setIpAddress(dynamicForm.getValueAsString("ipAddress"));
			agencyService.createAgency(agency,
					new AsyncCallback<Void>() {
						@Override
						public void onSuccess(Void result) {
							MakeAgency.this.hide();
							SC.say("Optional Created!");
						}

						@Override
						public void onFailure(Throwable caught) {
							Window.alert("Impossible to create optional : "
									+ caught);
						}
					});
		}
	}

}
