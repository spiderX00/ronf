package it.unibo.ronf.shared.dto;

import javax.xml.bind.annotation.XmlRootElement;
import java.io.Serializable;

@XmlRootElement
public class CloseRentalDTO implements Serializable {

	private static final long serialVersionUID = 6973887571638996716L;

	private long id;

	public CloseRentalDTO() {

	}

	public long getId() {
		return id;
	}

	public void setId(long id) {
		this.id = id;
	}

}
