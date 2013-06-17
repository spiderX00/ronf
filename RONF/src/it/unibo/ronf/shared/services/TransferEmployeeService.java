package it.unibo.ronf.shared.services;

import it.unibo.ronf.shared.entities.TransferEmployee;

import java.util.List;

import com.google.gwt.user.client.rpc.RemoteService;
import com.google.gwt.user.client.rpc.RemoteServiceRelativePath;

@RemoteServiceRelativePath("ronfServices/transferEmployeeService")
public interface TransferEmployeeService extends RemoteService {

	public void createTransferEmployee(TransferEmployee te);

	public List<TransferEmployee> findAll();

	public List<TransferEmployee> findByBusy(boolean busy);

	public void removeById(long id);

	public void updateTransferEmployeeBusy(TransferEmployee te);

}
