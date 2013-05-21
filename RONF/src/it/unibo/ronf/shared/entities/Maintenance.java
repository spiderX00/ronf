package it.unibo.ronf.shared.entities;
import java.util.Date;
import java.util.List;

public class Maintenance {
	
	private Car car;
	private Date date;
	private MaintenanceEmployee maintenanceEmployee;
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

}
