package it.unibo.ronf.shared.services;

import it.unibo.ronf.shared.entities.Agency;

import java.util.List;

import com.google.gwt.user.client.rpc.AsyncCallback;

public interface AgencyServiceAsync {

	public void createAgency(Agency agency, AsyncCallback<Void> callback);

	public void findAll(AsyncCallback<List<Agency>> callback);

	public void findByCode(String code, AsyncCallback<Agency> callback);

	public void findByName(String name, AsyncCallback<Agency> callback);

	public void getCurrentAgency(AsyncCallback<Agency> callback);

	public void getOthersAgencies(AsyncCallback<List<Agency>> callback);

	public void removeById(long id, AsyncCallback<Void> callback);

	public void setCurrentAgency(Agency a, AsyncCallback<Void> callback);

}
