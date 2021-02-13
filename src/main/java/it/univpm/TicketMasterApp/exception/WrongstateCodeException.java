/**
 * 
 */
package it.univpm.TicketMasterApp.exception;

/**
 * @author Utente
 *
 */
public class WrongstateCodeException extends Exception {
	public WrongstateCodeException() {
		super("Errore: lo stateCode inserito non è corretto...");
	}
	
	public String StateCodeError() {
		return ("Gli stateCode consentiti sono: \nAB(Alberta),\nQC(Quebec),\nMB(Manitoba),\nSK(Saskatchewanc),\nNB(Nuovo Brunswick)");
	}

}
