package it.unibo.ronf.shared.util;

import com.google.gwt.core.client.GWT;
import com.google.gwt.core.client.GWTBridge;

public class Utils {

	public static boolean isProductionMode() {
		return GWT.isProdMode();
	}

	public static boolean isDevelopmentMode() {
		return !GWT.isProdMode() && GWT.isClient();
	}

}
