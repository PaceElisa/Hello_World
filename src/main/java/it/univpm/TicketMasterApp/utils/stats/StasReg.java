/**
 * 
 */
package it.univpm.TicketMasterApp.utils.stats;

import java.time.LocalDate;
import java.time.temporal.TemporalAdjusters;
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
	private long periodo;
	
/**
 * Costruttore
 * @param eventi vettore di eventi passati 
 * @param parametro regione su  cui si effettua la statistica
 */
	public StasReg(Vector<Eventi> eventi,String parametro) {
		super(eventi, parametro);
		periodo=1;
	}
	
	public StasReg(Vector<Eventi> eventi,String parametro, long periodo) {
		super(eventi, parametro);
		this.periodo=periodo;
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
		Vector<Integer> mesi=new Vector<>();
		
		for(Eventi e: super.eventi) {
			LocalDate data_evento= LocalDate.parse(e.getData());
			if(data_evento.isBefore(fine_anno)) {// se la data che analizzo è prima del nuovo anno
				switch(data_evento.getMonthValue()) {
				
				case 1:mesi.set(0, gen++) ;break;
				case 2: mesi.set(1, feb++);break;
				case 3: mesi.set(2, marz++);break;
				case 4:mesi.set(3,  apr++);break;
				case 5:mesi.set(4,  magg++);break;
				case 6: mesi.set(5,  giu++);break;
				case 7:mesi.set(6,  lu++);break;
				case 8:mesi.set(7,  ago++);break;
				case 9: mesi.set(8,  sett++);break;
				case 10:mesi.set(9,  ott++);break;
				case 11:mesi.set(10,  nov++);break;
				case 12:mesi.set(11,  dic++);break;
				}
				
				
			}
		}
		for(int cont:mesi) {
			if(cont>max) max=cont;
			if(cont<min) min=cont;
			 
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
		 JSONObject region= new JSONObject();
		 region.put("Regione", super.parametro);
		 region.put("Tot_Prom", CalcoloTot());
		 region.put("Tot_Prom_Genere", CalcoloGenere());
		 region.put("Eventi_Mensili",CalcoloEvento());
		 return region;
		
		 
	}
	
	
	
	

}
