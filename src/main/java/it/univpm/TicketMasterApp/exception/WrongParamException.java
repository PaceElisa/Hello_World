/**
 * 
 */
package it.univpm.TicketMasterApp.exception;

/** Metodo che viene lanciato quando uno dei parametri del body sono errati o non esistono tra quelli consentiti
 * @author Elisa Pace
 * @author Marco Vassallo
 *
 */
public class WrongParamException extends Exception {
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
	public WrongParamException() {
		
	}
	/** costruttore con :
	 * @param message
	 */
	public WrongParamException(String messaggio) {
		this.messaggio=messaggio;
		
	}

	/**Messaggio di errore
	 * @return  ritorna un messaggio di errore scritto dal programmatore per la gestione della rotta filter
	 */
	
	public String Messaggio() {
		return messaggio;
	}


}
