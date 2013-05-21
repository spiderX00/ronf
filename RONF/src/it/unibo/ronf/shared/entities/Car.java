package it.unibo.ronf.shared.entities;

public class Car {
	
	private String model;
	private String plate;
	private String gasolineType;
	private int seatsNumber;
	private Agency agency;
	private CarType type;
	
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
	public Agency getAgency() {
		return agency;
	}
	public void setAgency(Agency agency) {
		this.agency = agency;
	}
	public CarType getType() {
		return type;
	}
	public void setType(CarType type) {
		this.type = type;
	}
	
	
	

}
