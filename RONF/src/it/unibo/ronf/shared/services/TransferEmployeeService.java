package it.unibo.ronf.shared.services;

import java.util.List;

import it.unibo.ronf.shared.entities.TransferEmployee;

import com.google.gwt.user.client.rpc.RemoteService;
import com.google.gwt.user.client.rpc.RemoteServiceRelativePath;

@RemoteServiceRelativePath("ronfServices/transferEmployeeService")
public interface TransferEmployeeService extends RemoteService {

	public List<TransferEmployee> findByBusy(boolean busy);

	public void createTransferEmployee(TransferEmployee te);

	public void removeById(long id);

	public void updateTransferEmployeeBusy(TransferEmployee te);

}
