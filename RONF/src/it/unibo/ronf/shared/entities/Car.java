package it.unibo.ronf.shared.entities;

import javax.persistence.*;
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

	public long getId() {
		return id;
	}

	public void setId(long id) {
		this.id = id;
	}

	public String getModel() {
		return model;
	}

	public void setModel(String model) {
		this.model = model;
	}

	public String getPlate() {
		return plate;
	}

	public void setPlate(String plate) {
		this.plate = plate;
	}

	public String getGasolineType() {
		return gasolineType;
	}

	public void setGasolineType(String gasolineType) {
		this.gasolineType = gasolineType;
	}

	public int getSeatsNumber() {
		return seatsNumber;
	}

	public void setSeatsNumber(int seatsNumber) {
		this.seatsNumber = seatsNumber;
	}

	public CarType getType() {
		return type;
	}

	public void setType(CarType type) {
		this.type = type;
	}

	public Agency getCurrentAgency() {
		return currentAgency;
	}

	public void setCurrentAgency(Agency currentAgency) {
		this.currentAgency = currentAgency;
	}

	public Agency getOriginAgency() {
		return originAgency;
	}

	public void setOriginAgency(Agency originAgency) {
		this.originAgency = originAgency;
	}

}
