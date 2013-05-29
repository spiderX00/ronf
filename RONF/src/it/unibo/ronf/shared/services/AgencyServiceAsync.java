package it.unibo.ronf.shared.services;

import java.util.List;

import it.unibo.ronf.shared.entities.Agency;

import com.google.gwt.user.client.rpc.AsyncCallback;

public interface AgencyServiceAsync {

	public void findByCode(String code, AsyncCallback<Agency> callback);

	public void findByName(String name, AsyncCallback<Agency> callback);

	public void createAgency(Agency agency, AsyncCallback<Void> callback);

	public void removeById(long id, AsyncCallback<Void> callback);

	public void findAll(AsyncCallback<List<Agency>> callback);

}
