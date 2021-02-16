/**
 * 
 */
package it.univpm.TicketMasterApp.exception;

/**
 * @author Elisa Pace
 * @author Marco Vassallo
 *
 */
public class EmptyIDException extends Exception {

	/**
	 * 
	 */
	private static final long serialVersionUID = 1L;

	/**
	 * 
	 */
	public EmptyIDException() {
		super("Errore: campo vuoto nel corpo della richiesta...");
		
	}

	/**
	 * @param message
	 */
	public String EmptyIDError() {
		return("E' necessario riempire tutti i campi del corpo per un corretto funzionamento...");
	}

}
