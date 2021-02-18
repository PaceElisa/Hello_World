/**
 * 
 */
package it.univpm.TicketMasterApp.utils.stats;

import java.time.LocalDate;

import java.util.Vector;

import org.json.simple.JSONObject;

import it.univpm.TicketMasterApp.model.Eventi;
import it.univpm.TicketMasterApp.model.Promoter;
import it.univpm.TicketMasterApp.service.DownloadEvent;

/**Sottoclasee che calcola la statistiche per ogni regione
 * @author Elisa Pace
 * @author Marco Vassallo
 *
 */
public class StasReg extends Stats {
	//private long periodo;
	//vettore degli eventi che contiene solo dati per il parametro passato
	public Vector<Eventi> eve_s= new Vector<>();
	
	String regioneStatecode;
	
	
	
	
/**
 * Costruttore
 * @param eventi vettore di eventi passati 
 * @param parametro regione su  cui si effettua la statistica
 */
	public StasReg(Vector<Eventi> eventi,String parametro) {
		super(eventi, parametro);
		for(Eventi ev: eventi){
			
				if(ev.getStateCode().equals(parametro))
					eve_s.add(ev);
			}
		
		regioneStatecode=parametro;
		
	}
	
	/**Metodo che calcola il numero totale di promoter che sponsorizzano eventi in quella regione
	 * @see Promoter#getID()
	 * @see Promoter#equals(Object)
	 * @return cont intero con totale dei promoter in quella regione
	 */
	public int CalcoloTot() {
		int cont=0;
		Vector<Promoter> giusti= new Vector<>();
		//esploro il vettore di eventi, al suo interno prendo il vettore dei promoter ed esploro anche quello
		for(Eventi e: eve_s) {
			for(Promoter p: e.getPromoters()) {
				//conto solo i promoter che non sono nulli o che non sono NOT DEFINE con ID "320"
				if(!(p.getID().equals("") || p.getID().equals("320"))) {
					//Se non ho già aggiunto e contato quel relativo promoter, allora lo faccio, altrimenti se è già presente non lo conto
					if(!(giusti.contains(p))) {
						giusti.add(p);
						cont++;
						
					}
						
				}
							
			}
			
		}
		return cont;
		
	}
	/**Metodo che calcola il numero di promoter divisi per genere
	 * @see Genere#getName
	 * @return  generi un Jsonobject contene il numero di promoter divisi  per genere
	 * 
	 */
	@SuppressWarnings("unchecked")
	public JSONObject CalcoloGenere () {
		
		JSONObject generi= new JSONObject();
		int contMusic=0, contSport=0, contArt=0, contFilm=0, contAltro=0;
		for(Eventi e:eve_s) {
			
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
	/**Metodo che mi calcola il minimo, massimo e la media di eventi in un mese, nell'anno corrente
	 * @see Eventi#getData()
	 * @return un JSONObject con dentroil numero minim, massimo e medio di eventi mensili nell'anno corrente
	 */
	@SuppressWarnings("unchecked")
	public JSONObject CalcoloEvento() {
		JSONObject event_men= new JSONObject();
		//operazioni che serve per accertarsi che gli eventi che si analizzano siano dell'anno corrente
		LocalDate oggi=LocalDate.now();
		String anno_prossimo=Integer.toString(oggi.plusYears(1).getYear());
		String primo_giorno_anno= anno_prossimo+"-01-01";
		LocalDate fine_anno= LocalDate.parse(primo_giorno_anno);
		int gen=0, feb=0, marz=0,apr=0,magg=0,giu=0,lu=0,ago=0,sett=0, ott=0,nov=0, dic=0;
		int min=1000,max=0,med=0;
		int index=20;
		Vector<Integer> mesi=new Vector<>(index);
		
		
		for(Eventi e: eve_s) {
			LocalDate data_evento= LocalDate.parse(e.getData());
			if(data_evento.isBefore(fine_anno)) {// se la data che analizzo è prima del nuovo anno
				switch(data_evento.getMonthValue()) {
				
				case 1: gen++ ;break;
				case 2:  feb++;break;
				case 3: marz++;break;
				case 4:  apr++;break;
				case 5: magg++;break;
				case 6:  giu++;break;
				case 7:lu++;break;
				case 8:  ago++;break;
				case 9:   sett++;break;
				case 10: ott++;break;
				case 11: nov++;break;
				case 12:  dic++;break;
				}
				mesi.add(gen);
				mesi.add(feb);
				mesi.add(marz);
				mesi.add(apr);
				mesi.add(magg);
				mesi.add(giu);
				mesi.add(lu);
				mesi.add(ago);
				mesi.add(sett);
				mesi.add(ott);
				mesi.add(nov);
				mesi.add(dic);
				
				
			}
		}
		for(int cont:mesi) {
			if(cont!=0) {//non conto i mesi che già sono passati nell'anno
			if(cont>max) max=cont;
			if(cont<min) min=cont;
					}
			 
		}
		med= (max+min)/2;
		event_men.put("max", max);
		event_men.put("min", min);
		event_men.put("media", med);
		
	return event_men;	
	}
	/**
	 * Metodo che compone il mio JSONObject, relativo alle staistiche di una regione 
	 * @see StasReg#CalcoloEvento()
	 * @see StasReg#CalcoloGenere()
	 * @see StasReg#CalcoloTot()
	 * @return Jsonobject contenete la mie statistiche 
	 */
	@SuppressWarnings("unchecked")
	public JSONObject getJSONObject() {
		DownloadEvent st= new DownloadEvent();
		 JSONObject region= new JSONObject();
		 region.put("Regione", st.Associa(regioneStatecode));
		 region.put("Tot_Prom", CalcoloTot());
		 region.put("Tot_Prom_Genere", CalcoloGenere());
		 region.put("Eventi_Mensili",CalcoloEvento());
		 eve_s.clear();
		 return region;
		
		 
	}
	
	
	
	

}
