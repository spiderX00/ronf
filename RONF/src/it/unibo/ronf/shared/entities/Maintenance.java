package it.unibo.ronf.shared.entities;

import java.util.Date;
import java.util.List;

import javax.persistence.*;

@Entity
public class Maintenance implements java.io.Serializable {

	
	private static final long serialVersionUID = 1L;

	@Id
	@GeneratedValue(strategy = GenerationType.IDENTITY)
	private long id;

	@OneToOne
	private Car car;
	@Temporal(TemporalType.DATE)
	private Date date;
	@OneToOne
	private MaintenanceEmployee maintenanceEmployee;
	@OneToMany
	private List<MaintenanceType> maintenances;

	public Car getCar() {
		return car;
	}

	public void setCar(Car car) {
		this.car = car;
	}

	public Date getDate() {
		return date;
	}

	public void setDate(Date date) {
		this.date = date;
	}

	public MaintenanceEmployee getMaintenanceEmployee() {
		return maintenanceEmployee;
	}

	public void setMaintenanceEmployee(MaintenanceEmployee maintenanceEmployee) {
		this.maintenanceEmployee = maintenanceEmployee;
	}

	public List<MaintenanceType> getMaintenances() {
		return maintenances;
	}

	public void setMaintenances(List<MaintenanceType> maintenances) {
		this.maintenances = maintenances;
	}

	public long getId() {
		return id;
	}

	public void setId(long id) {
		this.id = id;
	}

}
