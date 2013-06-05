package it.unibo.ronf.shared.services;

import com.google.gwt.user.client.rpc.AsyncCallback;

public interface InitServiceAsync {

	void preLoginInitEntities(AsyncCallback<Void> callback);

	void postLoginInitEntities(AsyncCallback<Void> callback);

}
