/**
 * 
 */
package it.univpm.TicketMasterApp.utils.stats;

import java.time.LocalDate;
import java.util.Vector;

import org.json.simple.JSONObject;

import it.univpm.TicketMasterApp.model.Eventi;
import it.univpm.TicketMasterApp.model.Promoter;

/**Sottoclasee che calcola la statistiche per ogni regione
 * @author Elisa Pace
 * @author Marco Vassallo
 *
 */
public class StasReg extends Stats {
	
/**
 * Costruttore
 * @param eventi vettore di eventi passati 
 * @param parametro regione su  cui si effettua la statistica
 */
	public StasReg(Vector<Eventi> eventi,String parametro) {
		super(eventi, parametro);
		
	}
	/**Metodo che calcola il numero totale di promoter che sponsorizzano eventi in quella regione
	 * @see Promoter#getID()
	 * @see Promoter#equals(Object)
	 * @return cont intero con totale dei promoter in quella regione
	 */
	public int CalcoloTot() {
		int cont=0;
		//esploro il vettore di eventi, al suo interno prendo il vettore dei promoter ed esploro anche quello
		for(Eventi e: super.eventi) {
			for(Promoter p: e.getPromoters()) {
				//conto solo i promoter che non sono nulli o che non sono NOT DEFINE con ID "320"
				if(!(p.getID().equals("") || p.getID().equals("320"))) 
					cont++;		
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
		for(Eventi e:super.eventi) {
			
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
	public JSONObject CalcoloEvento() {
		LocalDate oggi=LocalDate.now();
	return null;	
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
		 JSONObject region= new JSONObject();
		 region.put("Regione", super.parametro);
		 region.put("Tot_Prom", CalcoloTot());
		 region.put("Tot_Prom_Genere", CalcoloGenere());
		 region.put("Eventi_Mensili",CalcoloEvento());
		 return region;
		
		 
	}
	
	
	
	

}
