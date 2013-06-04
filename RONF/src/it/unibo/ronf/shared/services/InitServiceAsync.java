package it.unibo.ronf.shared.services;

import com.google.gwt.user.client.rpc.AsyncCallback;

public interface InitServiceAsync {

	void initEntities(AsyncCallback<Void> callback);

}
