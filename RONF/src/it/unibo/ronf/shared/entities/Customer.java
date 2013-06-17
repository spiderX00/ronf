package it.unibo.ronf.shared.entities;

import javax.persistence.Column;
import javax.persistence.Entity;
import javax.xml.bind.annotation.XmlRootElement;

@Entity
@XmlRootElement
public class Customer extends User implements java.io.Serializable {

	private static final long serialVersionUID = 1L;

	@Column(unique = true)
	private String fiscalCode;
	@Column(unique = true)
	private String docNumber;

	public String getDocNumber() {
		return docNumber;
	}

	public String getFiscalCode() {
		return fiscalCode;
	}

	public void setDocNumber(String docNumber) {
		this.docNumber = docNumber;
	}

	public void setFiscalCode(String fiscalCode) {
		this.fiscalCode = fiscalCode;
	}
}
