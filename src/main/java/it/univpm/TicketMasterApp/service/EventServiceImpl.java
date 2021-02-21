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
	public JSONArray FilterStats(Vector<String> stateCode, Vector<String> generi, String param, long periodo) throws EmptyFieldException, WrongStateCodeException, WrongParamException, WrongPeriodException {
		DownloadEvent evento= new DownloadEvent();
		Vector<String> regioni= new Vector<>();
		JSONArray finale= new JSONArray();
		JSONObject completo= new JSONObject();
		JSONObject genere= new JSONObject();
		evento.EventiInfo("AB");
		evento.EventiInfo("QC");
		evento.EventiInfo("MB");
		evento.EventiInfo("NB");
		evento.EventiInfo("SK");
		// inizializzo il vettore che contiene i codici postali delle regioni 
		regioni.add("AB");
		regioni.add("QC");
		regioni.add("MB");
		regioni.add("NB");
		regioni.add("SK");
		 // inizializzazione filtro e parsing e controllo del body
		Filter fo=new Filter();		
		fo.Parsing(stateCode,generi,param,periodo);
		//definizione struttura dati filtrata o no su cui applicare le statistiche
		Vector<String> reg= new Vector<>();
		if(!(fo.GetFiltereg().isEmpty()))
			reg=fo.GetFiltereg();
		else reg=regioni;
		// inizio ciclo while in modo da ottenere un jsonobject per ogni regione contenuta nella struttura dati
		Iterator<String> itr= reg.iterator();
		while(itr.hasNext()) {
			//verifico che itr. next non finisca fuori della size delle vettore e che quindi sia nullo
			String stCode=itr.next();
			if(stCode!=null) {
				
				completo.put("Regione",evento.Associa(stCode) );
				
				//inizio distinzione tra statistiche su  regione e statistiche su promoter
				if(fo.getStats().equals("statsReg")) {
					
					Stats streg= new StasReg(evento.getStrutturaDati(), stCode);
					completo.put("Tot_Prom",streg.CalcoloTot());
					//se i filtri relativi al genere ci sono allora prendo solo i dati riferito a quel genere, altrimenti li prendo tutti
					if(fo.getFiltgen().isEmpty()) {
						completo.put("Tot_Prom_Genere", streg.CalcoloGenere());
					}else {
						JSONObject giusti= new JSONObject();
						genere=streg.CalcoloGenere();
						for(String sr: fo.getFiltgen())
							giusti.put(sr,genere.get(sr));
						completo.put("Tot_Prom_Genere", giusti);
					}
					//nel caso in cui sia i filtri relativi allo stato che al genere sono vuoti, si intuisce che
					//l'utente è interessato solo al calcolo del numero degli eventi minimi, massimi e medi in un periodo in quello stato
					//perciò si rimuove le parti di non interesse
					if(fo.GetFiltereg().isEmpty() && fo.getFiltgen().isEmpty()) {
						completo.remove("Tot_Prom");
						completo.remove("Tot_Prom_Genere");
						fo.Filtraggio(evento.getStrutturaDati(),stCode);
					}
					
					
				}else {// inizio parte relativa alle statistiche per promoter
					JSONObject promoter= new JSONObject();
					//uso DownloadEvent per scaricare una lista di promoter per lo stato passato, per po poter utilizzare
					//StatsProm
					//filtro la struttura dati rispetto a quella regione
					fo.Filtraggio(evento.getStrutturaDati(), stCode);
					DownloadEvent eve= new DownloadEvent();
					eve.EventiInfo(stCode);
					//prendo tutti gli id dei promoter
					Vector<String> listaPromoter= new Vector<>();
					for(Promoter p: eve.getListaProm()) {
					
						if(!(p.getID().equals("") || p.getID().equals("320"))) {
							if(!(listaPromoter.contains(p)))
								listaPromoter.add(p.getID());
						}
					
					}
						
					Iterator<String> itp= listaPromoter.iterator();
					JSONObject promoterobj= new JSONObject();
					//in questo caso ho tanti jsonobject quanti sono gli stati, ma al loro interno ho
					//tanti promoter quanti presenti in quella regione
					while(itp.hasNext()) {
						String prom=itp.next();
						if(prom!=null) {
							//inserisco l'id del promoter
							promoterobj.put("ID_Promoter", prom);
							Stats stprom= new StatsProm(fo.DatiFiltrati(),prom);
							//inserisco il numero totali di eventi sponsorizzati da quel promoter
							promoterobj.put("Tot_Eventi", stprom.CalcoloTot());
							promoterobj.put("Tot_Stati_Evento",stprom.CalcoloEvento());
							
							//controllo se ci sono filtri per il genere, se si li applico
							if(fo.getFiltgen().isEmpty()) {
								promoterobj.put("Tot_Prom_Genere", stprom.CalcoloGenere());
							}else {
								JSONObject giusti= new JSONObject();
								promoter=stprom.CalcoloGenere();
								for(String sr: fo.getFiltgen())
									giusti.put(sr,promoter.get(sr));
								promoterobj.put("Tot_Eventi_Genere", giusti);
							}
							//nel caso in cui sia i filtri relativi allo stato che al genere sono vuoti, si intuisce che
							//l'utente è interessato solo al calcolo del numero degli eventi minimi, massimi e medi in un periodo in quello stato
							//perciò si rimuove le parti di non interesse
							if(fo.GetFiltereg().isEmpty() && fo.getFiltgen().isEmpty()) {
								promoterobj.remove("Tot_Eventi");
								promoterobj.remove("Tot_Eventi_Genere");
								promoterobj.remove("Tot_Stati_Evento");
								
							}
							
							
						
						completo.put("Promoter", promoterobj);
						}
					}
					
				}
				FilterPeriodo fp= new FilterPeriodo(fo.DatiFiltrati());
				if(fo.getPeriodo()==3) 
					completo.put("Eventi_Trimestrali", fp.trimestrale());
				else completo.put("Eventi_Semestrali", fp.semestrale());
					
				finale.add(completo);
				}
			
			}
		
		return finale;
			
		
		
		    }
}


