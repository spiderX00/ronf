package it.unibo.ronf.shared.services;

import it.unibo.ronf.shared.entities.Optional;

import java.util.List;

import com.google.gwt.user.client.rpc.AsyncCallback;

public interface OptionalServiceAsync {

	void createOptional(Optional optional, AsyncCallback<Void> callback);

	public void findAll(AsyncCallback<List<Optional>> callback);

	void findByName(String name, AsyncCallback<Optional> callback);

	void removeById(long id, AsyncCallback<Void> callback);

}
