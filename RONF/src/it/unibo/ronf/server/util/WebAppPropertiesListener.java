package it.unibo.ronf.server.util;

import javax.servlet.ServletContextEvent;
import javax.servlet.ServletContextListener;

/**
 * Definisce dei parametri di inizializzazione del webcontent (tomcat nel nostro caso)
 */
public class WebAppPropertiesListener implements ServletContextListener {

	@Override
	public void contextDestroyed(ServletContextEvent arg0) {
		// TODO Auto-generated method stub

	}

	@Override
	public void contextInitialized(ServletContextEvent sce) {
		//definisco il path assoluto della cartella che contiene 
		//l'applicazione corrente (RONF). la variabile webroot
		//viene poi utilizzata nel persistence.xml per istruire
		//hsqldb su dove salvare il database una volta chiuso 
		//il programma
		String rootPath = sce.getServletContext().getRealPath("/");
		System.setProperty("webroot", rootPath);

	}
}