package it.unibo.ronf.shared.entities;

import javax.persistence.*;
import javax.xml.bind.annotation.XmlRootElement;

@Entity
@XmlRootElement
public class CarType implements java.io.Serializable {

	private static final long serialVersionUID = 1L;

	@Id
	@GeneratedValue(strategy = GenerationType.IDENTITY)
	private long id;
	@Column(unique = true)
	private String type;
	private float dailyCost;

	public String getType() {
		return type;
	}

	public void setType(String type) {
		this.type = type;
	}

	public float getDailyCost() {
		return dailyCost;
	}

	public void setDailyCost(float dailyCost) {
		this.dailyCost = dailyCost;
	}

	public long getId() {
		return id;
	}

	public void setId(long id) {
		this.id = id;
	}

}
