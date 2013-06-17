package it.unibo.ronf.server.rest;

import it.unibo.ronf.shared.entities.Agency;

/**
 * Interfaccia di base per i client rest, ovvero quelle classi che si occupano di inoltrare
 * le richieste ad agenzie remote
 * 
 * @see it.unibo.ronf.rest.proxy
 */
public interface RestClient {

	/**
	 * URL di base del servizio 
	 * 
	 * @param a Agenzia verso la quale si invia la richiesta
	 * @return URL servizio REST remoto
	 */
	public String getBaseUrl(Agency a);

}
