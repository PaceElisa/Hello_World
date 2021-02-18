/**
 * 
 */
package it.univpm.TicketMasterApp.exception;

/**Classe che viene lanciata quando i parametri passati al body non esistono o sono errati
 * @author Elisa Pace
 * @author UtenteMarco Vassallo
 *
 */
public class WrongIDExceotion extends Exception {

	/**
	 * 
	 */
	private static final long serialVersionUID = 1L;
	/**
	 * costruttore
	 */
	public WrongIDExceotion() {
		super("Errore: ID inserito errato...");
		
	}
	/**
	 * Metodo che ritorno un messaggio di errore al ControllerApp
	 * @return messaggio di errore
	 */
	public String IDError() {
		return("L'ID inserito è errato o non esiste, si prega di utilizzare la rotta\"localhost:8080/promoter\" per ottenre una lista di ID promoter validi" );
		
	}

}
