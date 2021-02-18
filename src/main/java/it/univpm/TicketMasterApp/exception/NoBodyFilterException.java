package it.univpm.TicketMasterApp.exception;
/** metodo che viene lanciato quando il body della rotta filter è vuoto
 * @author Elisa Pace
 * @author  Marco Vassallo
 *
 */

public class NoBodyFilterException extends Exception {
	/**
	 * 
	 */
	private static final long serialVersionUID = 1L;

	/**costruttore
	 * 
	 */
	public NoBodyFilterException() {
		super("Errore: non è stato inserito nessun body...");
		
	}

	/**
	 * Metodo che viene lanciato quando viene a mancare il body relativa alla rotta filter
	 * @return   un messaggio di errore che mostra un esempio del corpo da inserire
	 */
	public String BodyErrorFilter() {
		return("E' richiesto un body di questo tipo:\n"
				+ "{\r\n"
				+ "    \"regione\":[\r\n"
				+ "        {\r\n"
				+ "          \"stateCode\": \"AB\"\r\n"
				+ "        },\r\n"
				+ "        {\r\n"
				+ "            \"stateCode\":\"QC\"\r\n"
				+ "        }\r\n"
				+ "    ],\r\n"
				+ "    \"genere\":[\r\n"
				+ "        {\r\n"
				+ "            \"name\": \"Music\"\r\n"
				+ "        },\r\n"
				+ "        {\r\n"
				+ "            \"name\": \"Sport\"\r\n"
				+ "        }\r\n"
				+ "    ],\r\n"
				+ "    \"periodo\": 3,\r\n"
				+ "    \"param\": \"statsreg\"\r\n"
				+ "}");
				
		}

}
