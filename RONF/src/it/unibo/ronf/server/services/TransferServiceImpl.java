package it.unibo.ronf.server.services;

import java.util.List;

import it.unibo.ronf.server.dao.TransferDAO;
import it.unibo.ronf.shared.entities.Transfer;
import it.unibo.ronf.shared.services.TransferService;
import it.unibo.ronf.shared.entities.Agency;
import it.unibo.ronf.shared.entities.TransferAction;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Propagation;
import org.springframework.transaction.annotation.Transactional;

@Service("transferService")
public class TransferServiceImpl implements TransferService {
	
	@Autowired
	TransferDAO transferDAO;

	@Override
	public List<Transfer> findByStartAgency(Agency startAgency) {
		return transferDAO.findByStartAgency(startAgency);
	}

	@Override
	public List<Transfer> findByArrivalAgency(Agency arrivalAgency) {
		return transferDAO.findByArrivalAgency(arrivalAgency);
	}

	@Override
	@Transactional(propagation=Propagation.REQUIRED,rollbackFor=Exception.class)
	public void createTransfer(Transfer t) {
		transferDAO.persist(t);
		
	}

	/**
	 * Questa funzione, prima di eliminare l'intero trasferimento dal database,
	 * verifica se tutte le macchine facenti parte di tale richiesta di trasferimento
	 * sono state consegnate, quindi se ogni TransferAction ha success = true.
	 * Se tutte sono state consegnate (tutte le TransferAction sono true)
	 * allora si elimina l'intera richiesta di trasferimento.
	 * Sennò restituisce false
	 * 
	 * @param param1 id della richiesta trasferimento da eliminare
	 * @return tru, se il trasferimento di ogni macchina è completato
	 * 
	 */
	@Override
	@Transactional(propagation=Propagation.REQUIRED,rollbackFor=Exception.class)
	public boolean removeById(long id) {
		
		Transfer toRemove = transferDAO.findById(id);
		
		List<TransferAction> taList = toRemove.getTransfers();
				
		for(int i = 0; i<taList.size(); i++) {
			
			if (taList.get(i).isSuccess())
				continue;
			else
				return false;
		}
		
		transferDAO.remove(toRemove);
		return true;
		
		
	}
	
	
	
	

}