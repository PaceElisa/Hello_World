/**
 * 
 */
package it.univpm.TicketMasterApp.utils.stats;

import java.util.Vector;

import org.json.simple.JSONObject;

import it.univpm.TicketMasterApp.model.Eventi;

/**
 * @author Elisa Pace
 * @Author Marco Vassallo
 *
 */
public abstract class Stats {
	
	private Vector<Eventi> eventi=new Vector<>();
	
	
	
	public Stats(Vector<Eventi> eventi) {
		super();
		this.eventi = eventi;
		
	}
	
	public abstract int CalcoloTot();
	
	public abstract JSONObject CalcoloGenere();
	
	public abstract JSONObject CalcoloEvento();
	
	public  JSONObject getJSONObject() {
		return null;
	}
	
	
	

}
