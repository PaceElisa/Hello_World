/**
 * 
 */
package it.univpm.TicketMasterApp.utils.stats;

import java.util.Iterator;
import java.util.Vector;

import org.json.simple.JSONObject;

import it.univpm.TicketMasterApp.model.Eventi;
import it.univpm.TicketMasterApp.model.Promoter;

/**Sottoclasse che calcola le statistiche per uno o più id_promoter passati come parametri
 * @author Elisa Pace
 * @author Marco Vassallo
 *
 */
public class StatsProm extends Stats {
	//Vettore che contiene solo gli eventi relativi al promoter che ho poassato 
	Vector<Eventi> eve_p=new Vector<>();
	

	/** costruttore
	 * @param eventi  contiene tutta la mia struttura dati
	 * @param parametro indica gli id_promoter per cui si effettua la statistica
	 */
	public StatsProm(Vector<Eventi> eventi, String parametro) {
		super(eventi, parametro);
		//riempe il vettore solo con eventi che sponsorizza quel promoter
		for(Eventi ev: eventi){
			for(Promoter p: ev.getPromoters()) {
				if(p.getID().equals(parametro))
					eve_p.add(ev);
			}
			
			}
	}
	/**
	 * Metodo che calcola il totale degli eventi sponsorizzati dal promoter
	 * @return un intero con il totale degli eventi
	 */

	@Override
	public int CalcoloTot() {
		int cont=eve_p.size();
		return cont;
	}

	/**Metodo che calcola il totale degli eventi sponsorizzati dal promoter, suddivisi per genere
	 * @return un jsonobject con all'interno tutti i generi degli eventi e in quale quantità ognuno viene sponsorizzato 
	 * 
	 */
	@Override
	@SuppressWarnings("unchecked")
	public JSONObject CalcoloGenere() {
		JSONObject generi= new JSONObject();
		int contMusic=0, contSport=0, contArt=0, contFilm=0, contAltro=0;
		for(Eventi e: eve_p) {
			
			switch(e.getGenere().getName()) {
			
			case"Sport": contSport++;break;
			case"Music": contMusic++;break;
			case"Art & Theatre": contArt++;break;
			case"Film": contFilm++;break;
			default: contAltro++;
			}
			generi.put("Sport", contSport);
			generi.put("Music", contMusic);
			generi.put("Art & Theatre", contArt);
			generi.put("Film", contFilm);
			generi.put("Altro", contAltro);
		}
		return generi;
				
	}
    /**
     * Metodo che calcola il numero di stati in cui il promoter sponsorizza l'evento
     * @see Eventi#getID()
     * @see Eventi#getStateCode()
     * @return un jsonobject che contiene i nomi di tutti gli eventi che sponsorizza il promoter con affianco il numero di stati in cui viene sponsorizzato l'evento
     */
	@SuppressWarnings("unchecked")
	@Override
	public JSONObject CalcoloEvento() {
		Iterator <Eventi> ite= eve_p.iterator();
		JSONObject n_stati= new JSONObject();
		Vector<Eventi> giusti= new Vector<>();
		//contatore settato ad 1 perchè considero già lo stateCode in cui si trova l'evento preso in considerazione
		
		//fisso un elemento del vettore eve_p e ricerco sempre su di esso gli eventi con lo stesso id 
		
		while(ite.hasNext()) {
			Eventi eve=new Eventi();
			eve=ite.next();
			if(eve!=null) {
			int cont=1;
			for(Eventi e: eve_p) {
				// se si tratta dell0 stesso evento e quest'ultimo ha uno statecode diverso, allora controllo che non l'abbia già contanto e poi incremento contatore
				if(e.getID().equals(eve.getID()) && !(e.getStateCode().equals(eve.getStateCode()))) {
					if(!(giusti.contains(e))) {
						giusti.add(e);
						cont++;
						
					}
				}
			}
			n_stati.put(eve.getName(),cont );
			//risetto il contatore pre il prossimo elemento ite
			cont=1;
			}
		}
		return n_stati;
	}
	/**Metodo che ingloba tutti gli altri jsonobject calcolati
	 * @see StatsProm#CalcoloTot()
	 * @see StatsProm#CalcoloGenere()
	 * @see StatsProm#CalcoloEvento()
	 * @return un jsonobject con tutte le informazioni relative alle statistiche sul promoter
	 */
	@SuppressWarnings("unchecked")
	public JSONObject getJSONObject() {
		 JSONObject promoter= new JSONObject();
		 promoter.put("ID_Promoter", getParametro());
		 promoter.put("Tot_Eventi", CalcoloTot());
		 promoter.put("Tot_Eventi_Genere", CalcoloGenere());
		 promoter.put("Tot_Stati_Evento",CalcoloEvento());
		 return promoter;
		
		 
	}

}
