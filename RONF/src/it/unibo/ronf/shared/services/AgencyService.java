package it.unibo.ronf.shared.services;

import it.unibo.ronf.shared.entities.Agency;

import com.google.gwt.user.client.rpc.RemoteService;
import com.google.gwt.user.client.rpc.RemoteServiceRelativePath;

@RemoteServiceRelativePath("ronfServices/agencyService")
public interface AgencyService extends RemoteService {

	public Agency findByCode(String code);

	public Agency findByName(String name);

	public void createAgency(Agency agency);

	public void removeById(long id);

}