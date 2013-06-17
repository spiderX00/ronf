package it.unibo.ronf.shared.entities;

import javax.persistence.Column;
import javax.persistence.Entity;

@Entity
public class Employee extends User implements java.io.Serializable {

	private static final long serialVersionUID = 1L;

	private String password;
	@Column(unique = true)
	private String userName;

	public String getPassword() {
		return password;
	}

	public String getUserName() {
		return userName;
	}

	public void setPassword(String password) {
		this.password = password;
	}

	public void setUserName(String userName) {
		this.userName = userName;
	}

}
