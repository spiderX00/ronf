package it.unibo.ronf.shared.entities;

import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.GeneratedValue;
import javax.persistence.GenerationType;
import javax.persistence.Id;
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

	public float getDailyCost() {
		return dailyCost;
	}

	public long getId() {
		return id;
	}

	public String getType() {
		return type;
	}

	public void setDailyCost(float dailyCost) {
		this.dailyCost = dailyCost;
	}

	public void setId(long id) {
		this.id = id;
	}

	public void setType(String type) {
		this.type = type;
	}

}
