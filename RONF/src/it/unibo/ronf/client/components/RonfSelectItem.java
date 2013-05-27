package it.unibo.ronf.client.components;

import java.util.HashMap;
import java.util.Map;

import com.smartgwt.client.widgets.form.fields.SelectItem;
import com.smartgwt.client.widgets.form.fields.events.ChangeEvent;
import com.smartgwt.client.widgets.form.fields.events.ChangeHandler;

public class RonfSelectItem<T> extends SelectItem {

	private Map<String, T> values;
	private T selected;

	public RonfSelectItem(String title) {
		setTitle(title);
		setName(title.toLowerCase().replaceAll(" ", "_"));
		values = new HashMap<String, T>();
		
		addChangeHandler(new ChangeHandler() {
			@Override
			public void onChange(ChangeEvent event) {
				String selectedItem = (String) event.getValue();
				selected = values.get(selectedItem);
			}
		});
	}

	public void setValues(Map<String, T> values) {
		this.values.putAll(values);
		setValueMap(values.keySet().toArray(new String[] {}));
	}

	public T getSelectedValue() {
		return selected;
	}

}