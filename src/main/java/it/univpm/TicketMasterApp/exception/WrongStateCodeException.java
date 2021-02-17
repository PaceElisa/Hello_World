/**
 * 
 */
package it.univpm.TicketMasterApp.exception;

/**
 * @author Elisa Pace
 * @author Marco Vassallo
 *
 */
public class WrongStateCodeException extends Exception {
	/**
	 * Costruttore
	 */
	public WrongStateCodeException() {
		super("Errore: lo stateCode inserito non è corretto...");
	}
	/**
	 * Metodo passato al controller per la bad request
	 * @return "Gli stateCode consentiti sono: \nAB(Alberta),\nQC(Quebec),\nMB(Manitoba),\nSK(Saskatchewanc),\nNB(Nuovo Brunswick)"
	 */
	public String StateCodeError() {
		return ("Gli stateCode consentiti sono: \nAB(Alberta),\nQC(Quebec),\nMB(Manitoba),\nSK(Saskatchewanc),\nNB(Nuovo Brunswick)");
	}

}
