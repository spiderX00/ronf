package it.unibo.ronf.shared.util;

import com.google.gwt.core.client.GWT;

public class Utils {

	/**
	 * Indica se l'applicazione è lanciata in modalità debug
	 * 
	 * @return true se l'applicazione è lanciata in development mode
	 */
	public static boolean isDevelopmentMode() {
		return !GWT.isProdMode() && GWT.isClient();
	}

	/**
	 * Indica se l'applicazione è in esecuzione su un applicationserver
	 * 
	 * @return true se stiamo eseguendo il server su un applicationserver tipo tomcat
	 */
	public static boolean isProductionMode() {
		return GWT.isProdMode();
	}

}
