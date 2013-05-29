package it.unibo.ronf.client.makedialog;

import it.unibo.ronf.client.datasource.OptionalDS;
import it.unibo.ronf.client.table.TabOptional;
import it.unibo.ronf.shared.entities.Optional;
import it.unibo.ronf.shared.services.OptionalService;
import it.unibo.ronf.shared.services.OptionalServiceAsync;

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
import com.smartgwt.client.widgets.form.fields.FloatItem;
import com.smartgwt.client.widgets.form.fields.TextAreaItem;
import com.smartgwt.client.widgets.form.fields.TextItem;
import com.smartgwt.client.widgets.layout.HLayout;

/**
 * Dialog che prende in input il DouteSource e permette di aggiungere record
 * alla ListGrid e al DB
 * 
 * @author Alessio De Vita alessio.dv@gmail.com
 */
public class MakeOptional extends Dialog {
	private final OptionalServiceAsync optionalService = GWT
			.create(OptionalService.class);
	private HLayout hLayout;

	public MakeOptional() {
		setSize("400px", "330px");
		final DynamicForm dynamicForm = new DynamicForm();
		dynamicForm.setSize("350px", "194px");
		addItem(dynamicForm);
		hLayout = new HLayout();
		hLayout.setHeight("46px");
		hLayout.setMembersMargin(40);
		final TabOptional tabOptional = new TabOptional();
		dynamicForm.setDataSource(OptionalDS.getInstance(tabOptional));
		dynamicForm.getField("id").hide();
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
					public void execute(DSResponse response, Object rawData,
							DSRequest request) {
						dynamicForm.editNewRecord();
					}
				});
				Optional optional = new Optional();
				optional.setName(dynamicForm.getValueAsString("name"));
				optional.setCost(Float.parseFloat(dynamicForm
						.getValueAsString("cost")));
				optional.setDescription(dynamicForm
						.getValueAsString("description"));
				optionalService.createOptional(optional,
						new AsyncCallback<Void>() {
							@Override
							public void onSuccess(Void result) {
								MakeOptional.this.hide();
								Window.alert("Optional Created!");

							}

							@Override
							public void onFailure(Throwable caught) {
								Window.alert("Impossible to create optional : "
										+ caught);
							}
						});

			}
		});

		btnCancel.addClickHandler(new ClickHandler() {

			@Override
			public void onClick(ClickEvent event) {
				MakeOptional.this.hide();

			}
		});
		addItem(hLayout);
		hLayout.moveTo(30, 231);

	}
}
