/**
 * Contiene degli delle classi che incapsulano le richieste da mandare ai servizi REST.
 * Siccome attraverso Jersey non si riesce a mandare piu di un parametro per richiesta
 * allora incapsuliamo i vari parametri della richietsa dentro a un unica classe che viene
 * "jaxbata" e inviata in remoto
 */
package it.unibo.ronf.shared.dto;