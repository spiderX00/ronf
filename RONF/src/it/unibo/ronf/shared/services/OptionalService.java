package it.unibo.ronf.shared.services;

import it.unibo.ronf.shared.entities.Optional;

import java.util.List;

import com.google.gwt.user.client.rpc.RemoteService;
import com.google.gwt.user.client.rpc.RemoteServiceRelativePath;

@RemoteServiceRelativePath("ronfServices/optionalService")
public interface OptionalService extends RemoteService {

	public void createOptional(Optional optional);

	public List<Optional> findAll();

	public Optional findByName(String name);

	public void removeById(long id);

}
