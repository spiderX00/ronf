package it.unibo.ronf.shared.entities;

import javax.persistence.Column;
import javax.persistence.Entity;

@Entity
public class Customer extends User implements java.io.Serializable{
	
	private static final long serialVersionUID = 1L;

	@Column(unique=true)
	private String fiscalCode;
	@Column(unique=true)
	private String docNumber;
	
	public String getFiscalCode() {
		return fiscalCode;
	}
	public void setFiscalCode(String fiscalCode) {
		this.fiscalCode = fiscalCode;
	}
	public String getDocNumber() {
		return docNumber;
	}
	public void setDocNumber(String docNumber) {
		this.docNumber = docNumber;
	}
}
