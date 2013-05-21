package it.unibo.ronf.shared.entities;

import javax.persistence.GeneratedValue;
import javax.persistence.GenerationType;
import javax.persistence.Id;

public class CarType {

	@Id @GeneratedValue(strategy = GenerationType.IDENTITY)
	private long id;
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
