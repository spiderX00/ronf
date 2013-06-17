package it.unibo.ronf.shared.entities;

import java.util.Date;
import java.util.List;

import javax.persistence.CascadeType;
import javax.persistence.Entity;
import javax.persistence.FetchType;
import javax.persistence.GeneratedValue;
import javax.persistence.GenerationType;
import javax.persistence.Id;
import javax.persistence.OneToMany;
import javax.persistence.OneToOne;
import javax.persistence.Temporal;
import javax.persistence.TemporalType;

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
	@OneToMany(cascade = CascadeType.ALL, fetch = FetchType.EAGER)
	private List<MaintenanceType> maintenances;

	public Car getCar() {
		return car;
	}

	public Date getDate() {
		return date;
	}

	public long getId() {
		return id;
	}

	public MaintenanceEmployee getMaintenanceEmployee() {
		return maintenanceEmployee;
	}

	public List<MaintenanceType> getMaintenances() {
		return maintenances;
	}

	public void setCar(Car car) {
		this.car = car;
	}

	public void setDate(Date date) {
		this.date = date;
	}

	public void setId(long id) {
		this.id = id;
	}

	public void setMaintenanceEmployee(MaintenanceEmployee maintenanceEmployee) {
		this.maintenanceEmployee = maintenanceEmployee;
	}

	public void setMaintenances(List<MaintenanceType> maintenances) {
		this.maintenances = maintenances;
	}

}
