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
		return("L'ID inserito è errato, si consiglia di utilizzare la rotta\"/promoter\" per ottenere degli ID promoter validi" );
		
	}

}
