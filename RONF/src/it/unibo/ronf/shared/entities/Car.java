package it.unibo.ronf.shared.entities;

import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.GeneratedValue;
import javax.persistence.GenerationType;
import javax.persistence.Id;
import javax.persistence.OneToOne;
import javax.xml.bind.annotation.XmlRootElement;

@Entity
@XmlRootElement
public class Car implements java.io.Serializable {

	private static final long serialVersionUID = 1L;

	@Id
	@GeneratedValue(strategy = GenerationType.IDENTITY)
	private long id;
	private String model;
	@Column(unique = true)
	private String plate;
	private String gasolineType;
	private int seatsNumber;
	@OneToOne
	private Agency currentAgency;
	@OneToOne
	private Agency originAgency;
	@OneToOne
	private CarType type;

	public Agency getCurrentAgency() {
		return currentAgency;
	}

	public String getGasolineType() {
		return gasolineType;
	}

	public long getId() {
		return id;
	}

	public String getModel() {
		return model;
	}

	public Agency getOriginAgency() {
		return originAgency;
	}

	public String getPlate() {
		return plate;
	}

	public int getSeatsNumber() {
		return seatsNumber;
	}

	public CarType getType() {
		return type;
	}

	public void setCurrentAgency(Agency currentAgency) {
		this.currentAgency = currentAgency;
	}

	public void setGasolineType(String gasolineType) {
		this.gasolineType = gasolineType;
	}

	public void setId(long id) {
		this.id = id;
	}

	public void setModel(String model) {
		this.model = model;
	}

	public void setOriginAgency(Agency originAgency) {
		this.originAgency = originAgency;
	}

	public void setPlate(String plate) {
		this.plate = plate;
	}

	public void setSeatsNumber(int seatsNumber) {
		this.seatsNumber = seatsNumber;
	}

	public void setType(CarType type) {
		this.type = type;
	}

}
