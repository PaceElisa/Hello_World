/**
 * 
 */
package it.univpm.TicketMasterApp.utils.stats;

import java.time.LocalDate;
import java.util.Vector;

import org.json.simple.JSONObject;

import it.univpm.TicketMasterApp.model.Eventi;
import it.univpm.TicketMasterApp.model.Promoter;

/**
 * @author Elisa Pace
 * @author Marco Vassallo
 *
 */
public class StasReg extends Stats {
	/**
	 * 
	 */
	
/**
 * 
 * @param eventi
 * @param parametro
 */
	public StasReg(Vector<Eventi> eventi,String parametro) {
		super(eventi, parametro);
		
	}
	/**
	 * @return 
	 */
	public int CalcoloTot() {
		int cont=0;
		for(Eventi e: super.eventi) {
			for(Promoter p: e.getPromoters()) {
				if(!(p.getID().equals("") || p.getID().equals("320"))) 
					cont++;		
			}
			
		}
		return cont;
		
	}
	
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
