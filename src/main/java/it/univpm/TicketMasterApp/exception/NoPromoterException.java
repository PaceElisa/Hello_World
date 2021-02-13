/**
 * 
 */
package it.univpm.TicketMasterApp.exception;

/** Metodo che segnala un eccezione dovuta al fatto che il vettore di promoters è vuoto
 * @author Elisa Pace
 * @author Marco Vassallo
 *
 */
public class NoPromoterException extends Exception {
	/**
	 * Costruttore
	 */
	public NoPromoterException() {
		super("Errore: non ci sono promoter...");
	}
	/**
	 * Messaggio mandato al controller come bad request
	 * @return "In questa regione non è specificato nessun promoter, prova altre regioni consentite"
	 */
	public String PromoterError() {
		return("In questa regione non è specificato nessun promoter, prova altre regioni consentite");
	}

}
