/**
 * 
 */
package it.univpm.TicketMasterApp.utils.Filter;

import java.util.Vector;

import org.json.simple.JSONArray;
import org.json.simple.JSONObject;

import it.univpm.TicketMasterApp.exception.EmptyFieldException;
import it.univpm.TicketMasterApp.exception.WrongParamException;
import it.univpm.TicketMasterApp.exception.WrongPeriodException;
import it.univpm.TicketMasterApp.exception.WrongStateCodeException;
import it.univpm.TicketMasterApp.model.Eventi;

/** Metodo che gestisce i filtri
 * @author Pace Elisa
 * @author Marco Vassallo
 *
 */
public class Filter {
	//vettore che contiene i dati filtrati che vengono poi utilizzati per calcolare le statistiche
	private Vector<Eventi> datifiltrati= new Vector<>();
	//vettore che contiene i filtri da applicare  alle satatistiche relativi al genere
	private Vector<String> filtgen = new Vector<>();
	//vettore che contiene i filtri da applicare  alle satatistiche relativi alla regione
	private Vector<String> filtreg=new Vector<>();
	//parametro che indica su quale statistica applicare i filtri
	private String stats;
	//indica il periodo di tempo su cui si vuole calcolare il minimo, massimo e medio degli eventi
	 private long periodo;
	/**
	 * costruutore
	 */
	public Filter() {}
	/**
	 * Metodo che estrapola i dati ,con iparametri da filtrare, dal body e controlla che siano corretti, se lo sono inizializza gli attributi della classe
	 * 
	 * @param body contiene i filtri da applicare alle statistiche
	 * @throws EmptyFieldException
	 * @throws WrongStateCodeException
	 * @throws WrongParamException
	 * @throws WrongPeriodException
	 */

	public void Parsing(Vector<String> stCod, Vector<String> ge, String parametro, long period) throws EmptyFieldException, WrongStateCodeException, WrongParamException, WrongPeriodException {
		
		if(!(stCod.isEmpty())) {
			
			for(String s: stCod) {
				Controlla(s);	
				if(!(s.equals("AB") || s.equals("QC") || s.equals("MB") || s.equals("NB") || s.equals("SK")))
						throw new WrongStateCodeException();
				filtreg.add(s);
		}
	}
		if(!(ge.isEmpty() || ge==null)) {
			
			for(String s: ge) {
				Controlla(s);	
				if(!(s.equals("Genere") || s.equals("Music") || s.equals("Sport") || s.equals("Altro") || s.equals("Art & Theatre")))
						throw new WrongParamException("I parametri inseriti non sono tra quelli consentiti o sono errati, perfavore scegliere tra questi:\r\n"
								+ "Music\r\n"
								+ "Sport\r\n"
								+ "Art & Theatre\r\n"
								+ "Film\r\n"
								+ "Altro");
				filtgen.add(s);
			
		}
      }
		
			periodo= period;
			if(!(periodo==3 || periodo==6)) throw new WrongPeriodException("E' possibile calcolare il periodo solo trimestralmente(£) o semestralmente(6), inserire uno di questi due valori");
			
		
		if(!(parametro.isEmpty() || parametro==null)) {
		 stats=parametro;
		 Controlla(stats);
		 if(!(stats.equals("statsReg") || stats.equals("statsProm"))) throw new WrongParamException("Errore: non è presente il parametro per distinguere quale statistica si vuole filtrare.... Si iserisca:\nstatsReg oppure\nstatsProm");
		 }
	}
	/**
	 * Metodo che controlla se i parametri inseriti sono nulli o vuoti, se lo sono lancia un eccezione
	 * @param s parametro da analizzare
	 * @throws EmptyFieldException
	 */
	public  void Controlla( String s) throws EmptyFieldException {
		if( s.isEmpty()) 
			throw new EmptyFieldException(s+"Hai dimenticato di inserire un campo nel body... Riprova aggiungendo tutti i campi");
		}
	/**
	 * Metodo get 
	 * @return the filtreg
	 */
	public Vector<String> GetFiltereg(){
		return filtreg;
	}

	/**Metodo get 
	 * @return the datifiltrati
	 */
	public Vector<Eventi> DatiFiltrati() {
		return datifiltrati;
	}

	
	

	/**Metodo get 
	 * @return the filtgen
	 */
	public Vector<String> getFiltgen() {
		return filtgen;
	}

	

	/**Metodo get 
	 * @return the stats
	 */
	public String getStats() {
		return stats;
	}

	

	/**Metodo get 
	 * @return the periodo
	 */
	public long getPeriodo() {
		return periodo;
	}
	/**
	 * Metodo che filtra gli eventi in base al parametro psssato
	 * @param eve
	 * @param s
	 */
	public void Filtraggio(Vector<Eventi> eve, String s) {
		for(Eventi e: eve) {
			if(e.getStateCode().equals(s))
				datifiltrati.add(e);
		}
	}

	
}
