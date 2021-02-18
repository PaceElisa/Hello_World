/**
 * 
 */
package it.univpm.TicketMasterApp.utils.Filter;

import java.time.LocalDate;
import java.util.Vector;

import org.json.simple.JSONObject;

import it.univpm.TicketMasterApp.model.Eventi;

/**Metodo che analizza e calcola il minimo, massimo e medio di eventi, per periodi trimestarli e semstrali
 * @author Elisa Pace
 * @author Marco Vassallo
 *
 */
public class FilterPeriodo {
	//vettore in cui vengono inseriti i conteggi 
	Vector<Integer> mesi= new Vector<>();
	//vettore che viene passato al costruttore
	Vector<Eventi> evento=new Vector<>();
	/**
	 * Costruttore
	 * @param evento vettori di eventi
	 */
	public FilterPeriodo(Vector<Eventi> evento) {
		this.evento=evento;
	}
	
	/**Metodo che calcola il numero minimo, massimo e medio degli eventi trimestrali, nell'anno corrente
	 * 
	 * @return un jsonobject con dentro min max e media degli eventi
	 */
	@SuppressWarnings("unchecked")
	public JSONObject trimestrale() {
		JSONObject event_tri= new JSONObject();
		//operazioni che serve per accertarsi che gli eventi che si analizzano siano dell'anno corrente
		LocalDate oggi=LocalDate.now();
		String anno_prossimo=Integer.toString(oggi.plusYears(1).getYear());
		String primo_giorno_anno= anno_prossimo+"-01-01";
		LocalDate fine_anno= LocalDate.parse(primo_giorno_anno);
		int cont1=0,cont2=0,cont3=0,cont4=0;
		int min=1000,max=0,med=0;
		
		for(Eventi e: evento) {
			LocalDate data_evento= LocalDate.parse(e.getData());
			if(data_evento.isBefore(fine_anno)) {// se la data che analizzo è prima del nuovo anno
				switch(data_evento.getMonthValue()) {
				
				case 1: cont1++ ;break;
				case 2:  cont1++;break;
				case 3: cont1++;break;
				case 4:  cont2++;break;
				case 5: cont2++;break;
				case 6:  cont2++;break;
				case 7:cont3++;break;
				case 8:  cont3++;break;
				case 9:   cont3++;break;
				case 10: cont4++;break;
				case 11: cont4++;break;
				case 12:  cont4++;break;
				}
			}
				mesi.add(cont1);
				mesi.add(cont2);
				mesi.add(cont3);
				mesi.add(cont4);
		}
				for(int cont:mesi) {
				if(cont!=0) {//non conto i mesi che già sono passati nell'anno
				if(cont>max) max=cont;
				if(cont<min) min=cont;
							}
					 
		
	}
				med= (max+min)/2;
				event_tri.put("max", max);
				event_tri.put("min", min);
				event_tri.put("media", med);
				
				return event_tri;
				

         }
	/**
	 * Metodo che calcola il numero minimo, massimo e medio degli eventi semestrali, nell'anno corrente
	 * @return un jsonobject con dentro min max e media degli eventi
	 */
	@SuppressWarnings("unchecked")
	public JSONObject semestrale() {
		JSONObject event_sem= new JSONObject();
		//operazioni che serve per accertarsi che gli eventi che si analizzano siano dell'anno corrente
		LocalDate oggi=LocalDate.now();
		String anno_prossimo=Integer.toString(oggi.plusYears(1).getYear());
		String primo_giorno_anno= anno_prossimo+"-01-01";
		LocalDate fine_anno= LocalDate.parse(primo_giorno_anno);
		int cont1=0,cont2=0;
		int min=1000,max=0,med=0;
		
		for(Eventi e: evento) {
			LocalDate data_evento= LocalDate.parse(e.getData());
			if(data_evento.isBefore(fine_anno)) {// se la data che analizzo è prima del nuovo anno
				switch(data_evento.getMonthValue()) {
				
				case 1: cont1++ ;break;
				case 2:  cont1++;break;
				case 3: cont1++;break;
				case 4:  cont1++;break;
				case 5: cont1++;break;
				case 6:  cont1++;break;
				case 7:cont2++;break;
				case 8:  cont2++;break;
				case 9:   cont2++;break;
				case 10: cont2++;break;
				case 11: cont2++;break;
				case 12:  cont2++;break;
				}
			}
				mesi.add(cont1);
				mesi.add(cont2);
			
		}
				for(int cont:mesi) {
				if(cont!=0) {//non conto i mesi che già sono passati nell'anno
				if(cont>max) max=cont;
				if(cont<min) min=cont;
							}
					 
		
	}
				med= (max+min)/2;
				event_sem.put("max", max);
				event_sem.put("min", min);
				event_sem.put("media", med);
				
				return event_sem;
				

         }
		
	}
      

   