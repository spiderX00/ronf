package it.unibo.ronf.client.record;

import com.smartgwt.client.widgets.grid.ListGridRecord;

/**
 * classe di tipo ListGridRecord per popolare la tabella di tipo ListGrid
 * 
 * @author Alessio De Vita alessio.dv@gmail.com
 */
public class GridRecord extends ListGridRecord {

	public GridRecord() {
	}

	public GridRecord(Long id, String name, String surname, Integer age, String userName) {
		setId(id);
		setName(name);
		setSurname(surname);
		setAge(age);
		setUserName(userName);
	}

	public GridRecord(Long id, String name, String surname, Integer age, String fiscalCode, String docNumber) {
		setId(id);
		setName(name);
		setSurname(surname);
		setAge(age);
		setFiscalCode(fiscalCode);
		setDocNumber(docNumber);
	}

	public Integer getAge() {
		return getAttributeAsInt("age");
	}

	public int getDocNumber() {
		return getAttributeAsInt("docNumber");
	}

	public int getFiscalode() {
		return getAttributeAsInt("fiscalCode");
	}

	public Long getId() {
		return getAttributeAsLong("id");
	}

	public String getName() {
		return getAttributeAsString("name");
	}

	public String getSurname() {
		return getAttributeAsString("surname");
	}

	public String getUserName() {
		return getAttributeAsString("userName");
	}

	public void setAge(Integer age) {
		setAttribute("age", age);
	}

	public void setDocNumber(String docNumber) {
		setAttribute("docNumber", docNumber);
	}

	public void setFiscalCode(String fiscalCode) {
		setAttribute("fiscalCode", fiscalCode);
	}

	public void setId(Long id) {
		setAttribute("id", id);
	}

	public void setName(String name) {
		setAttribute("name", name);
	}

	public void setSurname(String surname) {
		setAttribute("surname", surname);
	}

	public void setUserName(String userName) {
		setAttribute("userName", userName);
	}
}