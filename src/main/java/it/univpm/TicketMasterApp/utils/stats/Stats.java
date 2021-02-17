/**
 * 
 */
package it.univpm.TicketMasterApp.utils.stats;

import java.util.Vector;

import org.json.simple.JSONObject;

import it.univpm.TicketMasterApp.model.Eventi;

/**Superclasse che calcola le statistiche
 * @author Elisa Pace
 * @Author Marco Vassallo
 *
 */
public abstract class Stats {
	/**
	 * Vettore di eventi che contiene i dati su cui calcolare le statistiche
	 */
	 Vector<Eventi> eventi=new Vector<>();
	 /**
	  * parametro che mi indica su che cosa sto facendo la statistica
	  */
	 String parametro;
	
	
	/**
	 * Costruttore
	 * @param eventi  Vettore di eventi che contiene i dati su cui calcolare le statistiche
	 * @param parametro  parametro che mi indica su che cosa sto facendo la statistica
	 */
	public Stats(Vector<Eventi> eventi,String parametro) {
		super();
		
		
	}
	
	
	/**
	 * @return the parametro
	 */
	public String getParametro() {
		return parametro;
	}


	/**
	 * @param parametro the parametro to set
	 */
	public void setParametro(String parametro) {
		this.parametro = parametro;
	}


	public abstract int CalcoloTot();
	
	public abstract JSONObject CalcoloGenere();
	
	public abstract JSONObject CalcoloEvento();
	
	public  JSONObject getJSONObject() {
		return null;
	}
	
	
	

}
