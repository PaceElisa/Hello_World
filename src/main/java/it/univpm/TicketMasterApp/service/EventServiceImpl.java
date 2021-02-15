/**
 * 
 */
package it.univpm.TicketMasterApp.service;

import java.util.Vector;

import org.json.simple.JSONArray;
import org.springframework.stereotype.Service;
import it.univpm.TicketMasterApp.exception.NoPromoterException;
import it.univpm.TicketMasterApp.exception.WrongStateCodeException;
import it.univpm.TicketMasterApp.model.Promoter;
import it.univpm.TicketMasterApp.utils.stats.StasReg;
import it.univpm.TicketMasterApp.utils.stats.Stats;

/**Classe che gestisce i metodi chiamati dalla classe ControllerApp
 * @author Elisa Pace
 * @author Marco Vassallo
 *
 */
@Service
public class EventServiceImpl implements EventService {
	
	
	/**
	 * Costruttore vuoto
	 */
	public EventServiceImpl() {}
	
	/**Metodo che ritorna la lista dei promoter di una regione scelta dall'utente come suggerimento 
	 * @param stateCode cosice postale della regione di cui si richiede la lista di promoter
	 * @see DownloadEvent#Associa(String)
	 * @see DownloadEvent#EventiInfo(String)
	 * @see DownloadEvent#getListaProm()
	 * @see Promoter#equals(Object)
	 * @see Promoter#getID()
	 * @return
	 */
	public Vector<Promoter> getPromoter(String stateCode) throws WrongStateCodeException, NoPromoterException{
		Vector<Promoter> giusti= new Vector<>();
		//giusti=null;
		DownloadEvent e= new DownloadEvent();
		
		if(e.Associa(stateCode)==null) throw new WrongStateCodeException();
		e.EventiInfo(stateCode);
		//controllo che i suoi elementi non siano settati a "" o che l'id del promoter sia settato a id"320", indicano i promoter non definiti
		//poi aggiunge in giusti solo elementi non presenti gia al suo interno
		for(Promoter p: e.getListaProm() ) {//scorro lil vettore contente oggetti di tipo promoter
			if(!(p.getID().equals("") || p.getID().equals("320"))) {
				if(!(giusti.contains(p)))
					giusti.add(p);
			}
				
			
		}
		if(giusti.isEmpty()) throw new NoPromoterException();
		System.out.println(giusti);
		
		return giusti;
		
	}
	/**
	 * Metodo che mi restituisce un jsonarray con tutte le statistiche di ogni regione
	 * @see DownloadEvent#EventiInfo(String)
	 * @see DownloadEvent#getStrutturaDati()
	 * @see DownloadEvent#clear()
	 * @see Stats#getJSONObject()
	 * @return un jsonarray con tanti jsonobject quante le regioni analizzate, che contengono le statistiche di quest'ultime
	 */
	@SuppressWarnings("unchecked")
	public JSONArray StatsRegion() {
		JSONArray sr=new JSONArray();
		//definisco un vettore di String con gli stateCode delle regioni che ho deciso di analizzare
		Vector<String> regione= new Vector<>();
		regione.add("AB");
		regione.add("QC");
		regione.add("MB");
		regione.add("NB");
		regione.add("SK");
		
		DownloadEvent event= new DownloadEvent();
		//per ogni elemnto di regione scarico i dati relativi ad esso e lo passo come parametri al costruttore della classe statistica
		for(String s:regione) {
			event.EventiInfo(s);
			Stats statistica=new StasReg(event.getStrutturaDati(),s);
			sr.add(statistica.getJSONObject());
			//dopo ogni operazione ho la necessita di cancellare il contenuto di event per permettere alla mia struttura dati di contenere
			//solo informazioni di quella regione
			event.clear();
		}
		return sr;
		
		
		
		
	}

}
