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
	public NoBodyException( String metodo) {
		super("Errore: non è stato inserito nessun body...");
		if(metodo=="BodyErrorStats")
			BodyErrorStats();
		else BodyErrorFilter();
			
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
	public String BodyErrorFilter() {
		return("E' richiesto un body di questo tipo:\n"
				+ "{\r\n"
				+ "    \"regione\": [\r\n"
				+ "        {\r\n"
				+ "            \"stateCode\": \"MB\"\r\n"
				+ "        },\r\n"
				+ "        {\r\n"
				+ "            \"stateCode\": \"AB\"\r\n"
				+ "        },\r\n"
				+ "        {\r\n"
				+ "            \"stateCode\": \"QC\"\r\n"
				+ "        }\r\n"
				+ "    ],\r\n"
				+ "    \"genere\": [\r\n"
				+ "        {\r\n"
				+ "            \"name\": \"Music\"\r\n"
				+ "        },\r\n"
				+ "        {\r\n"
				+ "            \"name\": \"Sport\"\r\n"
				+ "        }\r\n"
				+ "    ],\r\n"
				+ "    \"periodo\": {\r\n"
				+ "        \"data_inizio\": \"2021-03-01\",\r\n"
				+ "        \"data_fine\": \"2021-04-01\"\r\n"
				+ "    },\r\n"
				+ "    \"stats\": \"statsReg\"\r\n"
				+ "}");
		}

	
}
