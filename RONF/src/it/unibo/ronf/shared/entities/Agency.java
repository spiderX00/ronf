package it.unibo.ronf.shared.entities;

import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.GeneratedValue;
import javax.persistence.GenerationType;
import javax.persistence.Id;
import javax.xml.bind.annotation.XmlRootElement;

@Entity
@XmlRootElement
public class Agency implements java.io.Serializable {

	/**
	 * 
	 */
	private static final long serialVersionUID = 1L;
	@Id
	@GeneratedValue(strategy = GenerationType.IDENTITY)
	private long id;
	@Column(unique = true)
	private String code;
	@Column(unique = true)
	private String name;
	@Column(unique = true)
	private String address;
	private String ipAddress;
	private int port;

	public Agency() {
	}

	public String getAddress() {
		return address;
	}

	public String getCode() {
		return code;
	}

	public long getId() {
		return id;
	}

	public String getIpAddress() {
		return ipAddress;
	}

	public String getName() {
		return name;
	}

	public int getPort() {
		return port;
	}

	public void setAddress(String address) {
		this.address = address;
	}

	public void setCode(String code) {
		this.code = code;
	}

	public void setId(long id) {
		this.id = id;
	}

	public void setIpAddress(String ipAddress) {
		this.ipAddress = ipAddress;
	}

	public void setName(String name) {
		this.name = name;
	}

	public void setPort(int port) {
		this.port = port;
	}
}
