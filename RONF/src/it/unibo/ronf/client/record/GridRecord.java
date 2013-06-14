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

	public void setId(Long id) {
		setAttribute("id", id);
	}

	public Long getId() {
		return getAttributeAsLong("id");
	}

	public void setName(String name) {
		setAttribute("name", name);
	}

	public String getName() {
		return getAttributeAsString("name");
	}

	public void setSurname(String surname) {
		setAttribute("surname", surname);
	}

	public String getSurname() {
		return getAttributeAsString("surname");
	}

	public void setAge(Integer age) {
		setAttribute("age", age);
	}

	public Integer getAge() {
		return getAttributeAsInt("age");
	}

	public void setUserName(String userName) {
		setAttribute("userName", userName);
	}

	public String getUserName() {
		return getAttributeAsString("userName");
	}

	public void setFiscalCode(String fiscalCode) {
		setAttribute("fiscalCode", fiscalCode);
	}

	public int getFiscalode() {
		return getAttributeAsInt("fiscalCode");
	}

	public void setDocNumber(String docNumber) {
		setAttribute("docNumber", docNumber);
	}

	public int getDocNumber() {
		return getAttributeAsInt("docNumber");
	}
}