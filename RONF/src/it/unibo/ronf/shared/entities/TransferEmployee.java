package it.unibo.ronf.shared.entities;
import javax.persistence.*;

@Entity
public class TransferEmployee extends Employee implements java.io.Serializable {
	
	private static final long serialVersionUID = 1L;
	
	private boolean busy;

	public boolean isBusy() {
		return busy;
	}

	public void setBusy(boolean busy) {
		this.busy = busy;
	}
	
	

}
