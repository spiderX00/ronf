package it.unibo.ronf.server.rest;

import it.unibo.ronf.shared.entities.Transfer;

/**
 * Interfaccia del servizio REST relativo ai trasferimenti.
 */
public interface TransferRestService {

	public abstract void createTransfer(Transfer t);
}