/**
 * 
 */
package it.univpm.TicketMasterApp.exception;

/**Metodo che viene lanciato se il body della mia richiesta è vuoto
 * @author Elisa Pace
 * @author Marco Vassallo
 *
 */
public class EmptyIDException extends Exception {

	
	private static final long serialVersionUID = 1L;

	/**
	 * costruttore
	 */
	public EmptyIDException() {
		super("Errore: campo vuoto nel corpo della richiesta...");
		
	}

	

	/**
	 *Messaggio di errore 
	 *@return un messaggio di errore prefissato per la gestione della rotta statsProm
	 */
	public String EmptyIDError() {
		return("E' necessario riempire tutti i campi del corpo per un corretto funzionamento...");
	}
	

}
