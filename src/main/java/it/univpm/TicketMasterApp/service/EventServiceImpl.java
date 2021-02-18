/**
 * 
 */
package it.univpm.TicketMasterApp.service;

import java.util.ArrayList;
import java.util.Iterator;
import java.util.Vector;

import org.json.simple.JSONArray;
import org.json.simple.JSONObject;
import org.springframework.stereotype.Service;

import it.univpm.TicketMasterApp.exception.EmptyFieldException;
import it.univpm.TicketMasterApp.exception.EmptyIDException;

import it.univpm.TicketMasterApp.exception.NoPromoterException;
import it.univpm.TicketMasterApp.exception.WrongIDExceotion;
import it.univpm.TicketMasterApp.exception.WrongParamException;
import it.univpm.TicketMasterApp.exception.WrongPeriodException;
import it.univpm.TicketMasterApp.exception.WrongStateCodeException;

import it.univpm.TicketMasterApp.model.Promoter;
import it.univpm.TicketMasterApp.utils.Filter.Filter;
import it.univpm.TicketMasterApp.utils.Filter.FilterPeriodo;
import it.univpm.TicketMasterApp.utils.stats.StasReg;
import it.univpm.TicketMasterApp.utils.stats.Stats;
import it.univpm.TicketMasterApp.utils.stats.StatsProm;

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
	 * @param stateCode codice postale della regione di cui si richiede la lista di promoter
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
	 * @see Stats#getJSONObject()
	 * @return un jsonarray con tanti jsonobject quante le regioni analizzate, che contengono le statistiche di quest'ultime
	 */
	@SuppressWarnings("unchecked")
	public JSONArray StatsRegion() {
		//definisco e inizializzo il vettore che contiene i codici postali delle ergioni dalle quali voglio scaricare i dati
		ArrayList<String> regioni= new ArrayList<>();
		regioni.add("AB");
		regioni.add("QC");
		regioni.add("MB");
		regioni.add("NB");
		regioni.add("SK");
		Iterator<String> it= regioni.iterator();
		//download dati
		JSONArray sr=new JSONArray();
		DownloadEvent event= new DownloadEvent();
		
		event.EventiInfo("AB");
		event.EventiInfo("QC");
		event.EventiInfo("MB");
		event.EventiInfo("NB");
		event.EventiInfo("SK");
		
		//scorro l'array  regioni 
		while(it.hasNext()) {
			//quando it.next() raggiunge la fine del vettore si ferma, senza dare errore
			String r =it.next();
			if(r !=null) {
			    Stats statistica=new StasReg(event.getStrutturaDati(),r);
				sr.add(statistica.getJSONObject());
			}
		}
			
		return sr;
		
	}
	/**
	 * Metodo che mi restituisce un jsonarray con le statistiche relative ai promoters
	 * @param id_prom  vettore di stringhe che contiene tutti gli id_promoter passati al body
	 * @see DownloadEvent#EventiInfo(String)
	 * @see DownloadEvent#getListaProm()
	 * @see DownloadEvent#getStrutturaDati()
	 * @see Promoter#getID()
	 * @see Promoter#equals(Object)
	 * @see StatsProm#getJSONObject()
	 * @return un Jsonarray con tutte le statistiche di tutti i promoter passati
	 */
	@SuppressWarnings("unchecked")
	public JSONArray StatsPromoter(Vector<String> id_prom) throws EmptyIDException, WrongIDExceotion {
		Iterator<String> it= id_prom.iterator();
		JSONArray proms=new JSONArray();
		
		DownloadEvent event=new DownloadEvent();
		event.EventiInfo("AB");
		event.EventiInfo("QC");
		event.EventiInfo("MB");
		event.EventiInfo("NB");
		event.EventiInfo("SK");
		// verifico che tutti i campi del vettore id_prom siano non vuoti
		for(String s:id_prom) {
		if(s==null) throw new EmptyIDException();
		}
		while(it.hasNext()){
			String pro=it.next();
			if(pro!=null) {
			//verifico se gli id_promoter passati nel body esistano
			boolean test=true;
			for(Promoter p:event.getListaProm()) {
				if((p.getID().equals(pro))) test=false;
			}
			if(test) throw new WrongIDExceotion();
			
			Stats statistica= new StatsProm(event.getStrutturaDati(),(pro));
			proms.add(statistica.getJSONObject());
			}
		}
		
		
		return proms;
	}
	/**
	 * Metodo che calcola le statistiche filtrate
	 * @param body
	 * @return JSONArray
	 */
	@SuppressWarnings("unchecked")
	public JSONArray FilterStats(JSONObject bodyfilter) throws EmptyFieldException, WrongStateCodeException, WrongParamException, WrongPeriodException {
		DownloadEvent evento= new DownloadEvent();
		JSONArray finale= new JSONArray();
		JSONObject statreg= new JSONObject();
		evento.EventiInfo("AB");
		evento.EventiInfo("QC");
		evento.EventiInfo("MB");
		evento.EventiInfo("NB");
		evento.EventiInfo("SK");
		 
		Filter fo=new Filter();
		
		fo.Parsing(bodyfilter);
		
		if(fo.getStats().equals("statsReg")) {
			if(!(fo.GetFiltereg().isEmpty() && fo.getFiltgen().isEmpty())) {
				for(String s: fo.GetFiltereg()) {
					fo.Filtraggio(evento.getStrutturaDati(),s);
				
				Stats stats=new StasReg(fo.DatiFiltrati(), s);
				statreg.put("Regione", evento.Associa(s));
				statreg.put("Tot_Prom", stats.CalcoloTot());
				JSONObject appoggio=new JSONObject();
				JSONObject correzione=new JSONObject();
				
				correzione.put("Tot_Prom_Genere", stats.CalcoloGenere());
				for(String gen: fo.getFiltgen()) {
					correzione.put(gen,appoggio.get(gen) );
				}
				statreg.put("Tot_Prom_Genere", correzione);
				finale.add(statreg);
				
				}
			} if(fo.GetFiltereg().isEmpty() && !(fo.getFiltgen().isEmpty())) {
				
			}
			
			
		}
		if(fo.getStats().equals("StatsProm")){
			if(!(fo.GetFiltereg().isEmpty() && fo.getFiltgen().isEmpty())) {
				for(String s: fo.GetFiltereg()) {
					fo.Filtraggio(evento.getStrutturaDati(),s);
				
			}
			
			
		}if(fo.GetFiltereg().isEmpty() && !(fo.getFiltgen().isEmpty())) {
			
		}
		
		
		
	   }
		FilterPeriodo t=new FilterPeriodo(fo.DatiFiltrati());
		if(fo.getPeriodo()==3) {
			t.trimestrale();}
			else { if(fo.getPeriodo()==6)
				t.semestrale();
			}
		return finale;
    }
}


