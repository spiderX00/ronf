/**
 * Le classi di questo package si occupano di leggere/scrivere i dati sul database
 * attraverso jpa. Il pattern DAO (Data Access Object) è stato creato quando ancora 
 * strumenti come JPA non esistevano ma penso che comunque sia molto utile al fine di 
 * mantenere High Coesion e Low Coupling. 
 * <br>
 * NB: I metodi che eseguono modifche sul databse (create/update/delete) vanno annotati
 * @Transactional in quanto Spring si occuperà poi di rendere il contesto del metodo
 * sotto transazione. 
 */
package it.unibo.ronf.server.dao;