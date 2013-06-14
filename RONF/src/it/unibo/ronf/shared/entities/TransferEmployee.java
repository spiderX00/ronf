package it.unibo.ronf.shared.entities;

import javax.persistence.*;
import javax.xml.bind.annotation.XmlRootElement;

@Entity
@XmlRootElement
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
