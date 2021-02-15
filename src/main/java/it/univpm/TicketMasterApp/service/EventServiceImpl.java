/**
 * 
 */
package it.univpm.TicketMasterApp.service;

import java.util.Vector;

import it.univpm.TicketMasterApp.exception.NoPromoterException;
import it.univpm.TicketMasterApp.exception.WrongStateCodeException;
import it.univpm.TicketMasterApp.model.Promoter;

/**
 * @author Elisa Pace
 * @author Marco Vassallo
 *
 */
public class EventServiceImpl implements EventService {
	Vector<Promoter> giusti= new Vector<>();
	
	public Vector<Promoter> getPromoter(String stateCode) throws WrongStateCodeException, NoPromoterException{
		
		//giusti=null;
		DownloadEvent e= new DownloadEvent();
		
		if(e.Associa(stateCode)==null) throw new WrongStateCodeException();
		e.EventiInfo(stateCode);
		//controllo che i suoi elementi non siano settati a null o che l'id del promoter sia settato a id"320", indicano i promoter non definiti
		//poi aggiunge in giusti solo elementi non presenti gia al suo interno
		for(Promoter p: e.getListaProm() ) {//scorro lil vettore contente oggetti di tipo promoter
			if((!(p.getID() ==null) )&& (!(p.getID()=="320"))) {
				if(!(giusti.contains(p)))
					giusti.add(p);
			}
				
			
		}
		if(giusti.isEmpty()) throw new NoPromoterException();
		System.out.println(giusti);
		
		return giusti;
		
	}
	/*public JSONArray StatsReg() {
		Vector<String> regione= new Vector<>();
		regione.add("AB");
		regione.add("QC");
		regione.add("MB");
		regione.add("NB");
		regione.add("SK");
		
		DownloadEvent event= new DownloadEvent();
		
		for(String s:regione) {
			event.EventiInfo(s);
			
		}
		
		
		
		
	}*/

}
