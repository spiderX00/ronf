package it.unibo.ronf.shared.services;

import com.google.gwt.user.client.rpc.RemoteService;
import com.google.gwt.user.client.rpc.RemoteServiceRelativePath;

import it.unibo.ronf.shared.entities.Optional;

@RemoteServiceRelativePath("ronfServices/optionalService")
public interface OptionalService extends RemoteService {
	
	public Optional findByName(String name);
	
	public void createOptional(Optional optional);
	
	public void removeById(long id);

}
