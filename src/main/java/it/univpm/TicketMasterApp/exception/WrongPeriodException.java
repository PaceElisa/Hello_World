/**
 * 
 */
package it.univpm.TicketMasterApp.controller;

/**Metodo che viene lanciato quando il periodo inserito nel body non è consentito
 * @author Elisa Pace
 * @author Marco Vassallo
 *
 */
public class WrongPeriodException extends Exception {
	/**
	 * 
	 */
	private static final long serialVersionUID = 1L;
	/**
	 * messaggio passato quando viene lanciata l'eccezione
	 */
	String messaggio;

	/**
	 * 
	 */
	public WrongPeriodException() {
		super();
		
	}
	/** costruttore con :
	 * @param message
	 */
	public WrongPeriodException(String messaggio) {
		this.messaggio=messaggio;
		
	}

	/**Messaggio di errore
	 * @return  ritorna un messaggio di errore scritto dal programmatore per la gestione della rotta filter
	 */
	
	public String Messaggio() {
		return messaggio;
	}


}
