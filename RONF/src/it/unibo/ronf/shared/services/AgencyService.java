package it.unibo.ronf.shared.services;

import it.unibo.ronf.shared.entities.Agency;

import java.util.List;

import com.google.gwt.user.client.rpc.RemoteService;
import com.google.gwt.user.client.rpc.RemoteServiceRelativePath;

@RemoteServiceRelativePath("ronfServices/agencyService")
public interface AgencyService extends RemoteService {

	public void createAgency(Agency agency);

	public List<Agency> findAll();

	public Agency findByCode(String code);

	public Agency findByName(String name);

	public Agency getCurrentAgency();

	public List<Agency> getOthersAgencies();

	public void removeById(long id);

	public void setCurrentAgency(Agency a);

}
