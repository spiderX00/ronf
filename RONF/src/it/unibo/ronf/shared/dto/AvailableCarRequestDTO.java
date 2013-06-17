package it.unibo.ronf.shared.dto;

import it.unibo.ronf.shared.entities.CarType;

import java.io.Serializable;
import java.util.Date;

import javax.xml.bind.annotation.XmlRootElement;

@XmlRootElement
public class AvailableCarRequestDTO implements Serializable {

	private static final long serialVersionUID = -3792673978865268789L;
	private CarType type;
	private Date start;
	private Date end;

	public AvailableCarRequestDTO() {
		// TODO Auto-generated constructor stub
	}

	public AvailableCarRequestDTO(CarType type2, Date start2, Date end2) {
		type = type2;
		start = start2;
		end = end2;
	}

	public Date getEnd() {
		return end;
	}

	public Date getStart() {
		return start;
	}

	public CarType getType() {
		return type;
	}

	public void setEnd(Date end) {
		this.end = end;
	}

	public void setStart(Date start) {
		this.start = start;
	}

	public void setType(CarType type) {
		this.type = type;
	}

}
