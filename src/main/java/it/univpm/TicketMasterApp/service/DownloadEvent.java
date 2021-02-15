/**
 * 
 */
package it.univpm.TicketMasterApp.service;

import java.io.BufferedReader;
import java.io.InputStream;
import java.io.InputStreamReader;
import java.net.HttpURLConnection;
import java.net.URL;
import java.util.Vector;

import org.json.simple.JSONArray;
import org.json.simple.JSONObject;
import org.json.simple.JSONValue;
import org.json.simple.parser.ParseException;

import it.univpm.TicketMasterApp.model.Eventi;
import it.univpm.TicketMasterApp.model.Genere;
import it.univpm.TicketMasterApp.model.Promoter;

/**
 * Classe che scarica i dati di alcune regioni del Canada e li sistema in una struttura dati
 * @author Elisa Pace
 * @author Marco Vassallo
 *
 */
public class DownloadEvent {
	/**
	 * Chiave di autenticazione per ottenere i dati da TicketMaster
	 */
	private String apikey="&apikey=n2GxsGGAemGGl1tFNAO23oGpb5NGoDx4";
	/**
	 * Struttura dati in cui viene inserito tutti dati necessari per definire un evento
	 * @see Eventi
	 */
	Vector<Eventi> eventi= new Vector<>();
	/**
	 * Vettore che contiene tutti i promoter 
	 */
	Vector<Promoter> listaprom =new Vector<>();

	
	/**
	 * Metodo che richiede l'accesso al server di TicketMaster ,scarica i dati relativi agli eventi di alcune regioni passate per stateCode
	 *  e restituisce un JSONArray che li contiene 
	 * @param stateCode  è il codice postale con cui scarico i dati di quella specifica regione
	 * @return events    è un JSONArray che contiene tutte informazioni di tutti gli eventi che hanno luogo 
	 * in quella regione(stateCode)
	 */
	public JSONArray DatiRegione(String stateCode) {
		JSONArray events=new JSONArray();
		String url="https://app.ticketmaster.com/discovery/v2/events.json?countryCode=CA&stateCode="+ stateCode+ apikey;
		
		try {
		 HttpURLConnection connessione= (HttpURLConnection) new URL(url).openConnection();
         connessione.setRequestMethod("GET");
         connessione.setRequestProperty("User-Agent", "Mozilla/5.0 (Windows NT 6.1; WOW64; rv:25.0) Gecko/20100101 Firefox/25.0");
         connessione.setRequestProperty("Content-Type", "application/json");
         
        
               InputStream in = connessione.getInputStream(); 
               String data = "";
               String line = "";
     
                System.out.println("Lettura dei dati...");
      
            try {
                 InputStreamReader inR = new InputStreamReader( in );
                 BufferedReader buf = new BufferedReader( inR );
         
                  while ( ( line = buf.readLine() ) != null ) {
                         data+= line;
                         System.out.println(line);
                       }
                  
                } finally {
                 in.close();
        }
            JSONObject totalobj = (JSONObject)JSONValue.parseWithException(data);
            JSONObject embedded = (JSONObject)(totalobj.get("_embedded"));
            events = (JSONArray)(embedded.get("events")); // JSONArray dove si trovano i dati da prelevare
            
            
		
		
	} catch(ParseException e){
		e.printStackTrace();
	}
		catch(Exception e) {
	       e.printStackTrace();
	}
		return events;
		
}
/**
 * Metodo che compone la mia struttura dati per calcolare le statistiche successivamente
 * @param stateCode
 * @see DownloadEvent#DatiRegione(String)
 * @see DownloadEvent#DatiGenere(JSONObject)
 * @see DownloadEvent#DatiPromoters(JSONObject)
 * @see DownloadEvent#Associa(String)
 */
	public void EventiInfo(String stateCode) {
		JSONArray event=DatiRegione(stateCode);
		for(Object o: event) { // scansiona ogni oggetto del JSONArrayarray event
			JSONObject n_eventi=(JSONObject) o;
			 
			Eventi e;
			String id=(String) n_eventi.get("id");
			String nome=(String) n_eventi.get("name");
			String url=(String) n_eventi.get("url");
			String descrizione=(String) n_eventi.get("description");
			if(descrizione==null)
				descrizione="Non specificata...";
			
			String regione=Associa(stateCode);
			
			JSONObject d=(JSONObject) n_eventi.get("dates");
			JSONObject start= (JSONObject) d.get("start");
			String data=(String) start.get("localDate");
			
			e=new Eventi(id, nome, url,descrizione,regione,stateCode);
			e.setPromoters(DatiPromoters(n_eventi));
			e.setGenere(DatiGenere(n_eventi));
			e.setData(data);
			eventi.add(e);
		}
		
	}
/**
 * Metodo che analizza un singolo JSONObject n_eventi all'interno del JSONArray event alla ricerca del JSONArray promoters
 * ,una volta trovato, salva i dati al suo interno in vettore di promoters
 * @param n_eventi
 * @return listaprom     restituisce il vettore vettorepro che contiene  oggetti di tipo promoters,
 *  relativi ad un JSONObject all'interno del JSONArray event
 */
	
	public Vector<Promoter> DatiPromoters(JSONObject n_eventi) {
		
		
		Promoter p;
		
		JSONArray promoters = (JSONArray) n_eventi.get("promoters"); 
		
			
			if(promoters == null) {
				String id_promoters ="";
		        String nome_promoters = "";             
		        String promoters_descr = "";
		        p=new Promoter(id_promoters, nome_promoters, promoters_descr);
		        listaprom.add(p);
			} else {
					for(Object er : promoters) {
					JSONObject op = (JSONObject) er;
					String id_promoters = (String) op.get("id");
			        String nome_promoters = (String) op.get("name");             
			        String promoters_descr = (String) op.get("description");
			        if(promoters_descr==null)
			        	promoters_descr="Non specificata";
			        p=new Promoter(id_promoters, nome_promoters, promoters_descr);
			        listaprom.add(p);
			        }
			}
		
		return listaprom;
	}
		
		
		
	
	/**
	 * Metodo che analizza un singolo JSONObject n_eventi all'interno del JSONArray event alla ricerca dei dati relativi al genere dell'evento
 * ,una volta trovato, salva i dati al suo interno in un'istanza di Genere
	 * @param n_eventi
	 * @return gen      un istanza di Genere che contiene id e nome del genere di un evento
	 */
	public Genere DatiGenere(JSONObject n_eventi) {
		
		Genere gen=new Genere();
		
		
			JSONArray classification= (JSONArray) n_eventi.get("classifications");
			
			for(Object g: classification) {
				JSONObject object=(JSONObject)g;
				
				
				JSONObject segment=(JSONObject) object.get("segment");
				String id=(String)segment.get("id");
				String name=(String)segment.get("name");
				gen.setID(id);
				gen.setName(name);
				
			}
		
		return gen;
		
		
	}
	/**
	 * Metodo che associa al codice postale il nome della regione
	 * @param stateCode
	 * @return il nome della regione(regione) associato al codice postale(stateCode)
	 */
	public String Associa(String stateCode) {
		String regione="";
		switch(stateCode) {
		case "AB": regione="Alberta";break;
		case "QC": regione="Quebec";break;
		//case "BC": regione="Columbia Britannica";break;
		//case "ON": regione="Ontario";break;
		case "SK": regione="Saskatchewanc";break;
		case "MB": regione="Manitoba";break;
		case "NB": regione="Nuovo Brunswick";break;
		//case "NS": regione="Nuova Scozia";break;
		//case "PE": regione="Isola del Principe Edoardo";break;
		//case "NL": regione="Terranova e Labrador";break;
		default: regione=null;
		
		}
		return regione;
	}
	
	/**
	 * 
	 * @return ritorna tutta la mia struttura dati completa
	 */
	public Vector<Eventi> getStrutturaDati(){
		return eventi;
	}
	/**
	 * @return listaprom ritorna il vettore dei promoter
	 */
	public Vector<Promoter> getListaProm(){
		return listaprom;
	}

}
