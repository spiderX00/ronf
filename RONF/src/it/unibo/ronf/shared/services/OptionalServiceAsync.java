package it.unibo.ronf.shared.services;

import java.util.List;

import it.unibo.ronf.shared.entities.Optional;

import com.google.gwt.user.client.rpc.AsyncCallback;

public interface OptionalServiceAsync {

	void findByName(String name, AsyncCallback<Optional> callback);

	void createOptional(Optional optional, AsyncCallback<Void> callback);

	void removeById(long id, AsyncCallback<Void> callback);

	public void findAll(AsyncCallback<List<Optional>> callback);

}
