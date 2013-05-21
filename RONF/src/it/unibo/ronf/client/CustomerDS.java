package it.unibo.ronf.client;

import it.unibo.ronf.shared.entities.Customer;
import it.unibo.ronf.shared.entities.Employee;
import it.unibo.ronf.shared.services.CustomerService;
import it.unibo.ronf.shared.services.CustomerServiceAsync;

import java.util.List;

import com.google.gwt.core.client.GWT;
import com.google.gwt.user.client.Window;
import com.google.gwt.user.client.rpc.AsyncCallback;
import com.smartgwt.client.data.DataSource;
import com.smartgwt.client.data.fields.DataSourceIntegerField;
import com.smartgwt.client.data.fields.DataSourceTextField;
 
/**
 * DataSource per il live-filter
 *
 * @author Alessio De Vita alessio.dv@gmail.com
 */
public class CustomerDS extends DataSource {  
  
    private static CustomerDS instance = null;  
    private final CustomerServiceAsync customerService =  GWT
			.create(CustomerService.class);
	private static GridRecord[] customerRecord;
    public static CustomerDS getInstance(TabCustomer tabCustomer) {  
        if (instance == null) {  
            instance = new CustomerDS("customerDS", tabCustomer); 
        }  
        return instance;  
    }  
  
    public CustomerDS(String id, final TabCustomer tabCustomer) {  
  
        setID(id);  
        DataSourceIntegerField pkField = new DataSourceIntegerField("pk");  
        pkField.setHidden(true);  
        pkField.setPrimaryKey(true);  
        DataSourceIntegerField idField = new DataSourceIntegerField("id", "ID");  

        DataSourceTextField nameField = new DataSourceTextField("name", "Nome");  
        nameField.setRequired(true);  
  
        DataSourceTextField surnameField = new DataSourceTextField("surname", "Cognome");  
        surnameField.setRequired(true);  
  
        DataSourceIntegerField ageField = new DataSourceIntegerField("age", "Capital");  
        DataSourceTextField fiscalCodeField = new DataSourceTextField("fiscalCode", "Cod. Fiscale");  
  
        DataSourceTextField docNumberField = new DataSourceTextField("docNumber", "Documento n.");  
  
          
        setFields(pkField, idField, nameField, surnameField, ageField, fiscalCodeField, docNumberField); 
        /** Effettuo la richiesta per la ricerca di tutti gli employee */
        customerService.findAll( new AsyncCallback<List<Customer>>() {

    		@Override
    		public void onFailure(Throwable caught) {
    			Window.alert("fallito");
    		}

    		/**In caso di successo creo un nuovo EmployeeRecord e itero su tutto il DB */
    		public void onSuccess(List<Customer> result) {
    			customerRecord = new GridRecord[result.size()];

    			int i = 0;
    			for(Customer p : result) {
    				customerRecord[i] = new GridRecord(p.getId(), p.getName(), p.getSurname(), p.getAge(), p.getFiscalCode(), p.getDocNumber());
    				i++;
    				
    			}
    			
    			
    			
    			setTestData(customerRecord);  
    			/** Una volta essermi assicurato che la chiamata Asincrona ha avuto successo, posso mandare i dati alla ListGrid */
    			TabCustomer.setdata(CustomerDS.this, tabCustomer);

    		}
        });
  
        setClientOnly(true);  
    }  
} 