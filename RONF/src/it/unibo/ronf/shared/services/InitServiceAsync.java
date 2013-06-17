package it.unibo.ronf.shared.services;

import com.google.gwt.user.client.rpc.AsyncCallback;

public interface InitServiceAsync {

	void postLoginInitEntities(AsyncCallback<Void> callback);

	void preLoginInitEntities(AsyncCallback<Void> callback);

}
