package it.unibo.ronf.shared.services;

import com.google.gwt.user.client.rpc.RemoteService;
import com.google.gwt.user.client.rpc.RemoteServiceRelativePath;

@RemoteServiceRelativePath("ronfServices/initService")
public interface InitService extends RemoteService {

	void preLoginInitEntities();

	void postLoginInitEntities();

}
