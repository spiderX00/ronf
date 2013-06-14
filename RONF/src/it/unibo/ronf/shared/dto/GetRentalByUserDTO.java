package it.unibo.ronf.shared.dto;

import java.io.Serializable;

import javax.xml.bind.annotation.XmlRootElement;

@XmlRootElement
public class GetRentalByUserDTO implements Serializable {

	private static final long serialVersionUID = 6973887571638996716L;

	private Long id;

	public GetRentalByUserDTO() {
	}

	public long getId() {
		return id;
	}

	public void setId(long id) {
		this.id = id;
	}

}
