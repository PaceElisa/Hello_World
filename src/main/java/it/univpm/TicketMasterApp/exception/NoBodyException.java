/**
 * 
 */
package it.univpm.TicketMasterApp.exception;

/** Classe che viene lanciata quando si verifica un eccezione 
 * riguardo lassenza del body della richiesta
 * @author Elisa Pace
 * @author Marco Vassallo
 *
 */
public class NoBodyException extends Exception {

	/**
	 * 
	 */
	private static final long serialVersionUID = 1L;

	/**
	 * Costruttore
	 */
	public NoBodyException() {
		super("Errore: non è stato inserito nessun body...");
	}
	
	
	/**Metodo lanciato dalla classe ControllerApp
	 * @param message
	 * @return un messaggio di errore che mostra un esempio del corpo da inserire
	 */
	public String BodyErrorStats() {
		return("E' richiesto un body di questo tipo:\n"
				+ "{\r\n"
				+ "    \"promoter\": [\r\n"
				+ "        {\r\n"
				+ "            \"ID\": \"850\"\r\n"
				+ "        },\r\n"
				+ "        {\r\n"
				+ "            \"ID\": \"653\"\r\n"
				+ "        },\r\n"
				+ "        {\r\n"
				+ "            \"ID\": \"494\"\r\n"
				+ "        }\r\n"
				+ "    ]\r\n"
				+ "}");
		}
	

	
}
