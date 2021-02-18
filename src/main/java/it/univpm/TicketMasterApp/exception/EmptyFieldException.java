/**
 * 
 */
package it.univpm.TicketMasterApp.exception;

/**Metodo che lancia un eccezione quando un campo del body è vuoto
 * @author Elisa Pace
 * @author Marco Vassallo
 *
 */
public class EmptyFieldException extends Exception {
	/**
	 * messaggio passato quando viene lanciata l'eccezione
	 */
	String messaggio;

	/**
	 * 
	 */
	private static final long serialVersionUID = 1L;

	/**
	 * 
	 */
	public EmptyFieldException() {
		
	}
	/** costruttore con :
	 * @param message
	 */
	public EmptyFieldException(String messaggio) {
		this.messaggio=messaggio;
		
	}

	/**Messaggio di errore
	 * @return  ritorna un messaggio di errore scritto dal programmatore per la gestione della rotta filter
	 */
	
	public String Messaggio() {
		return messaggio;
	}

	

	

}
