/**
 * 
 */
package it.univpm.TicketMasterApp.service;
import java.util.Vector;

import org.json.simple.JSONArray;


import it.univpm.TicketMasterApp.exception.NoPromoterException;
import it.univpm.TicketMasterApp.exception.WrongStateCodeException;
import it.univpm.TicketMasterApp.model.Promoter;
/**Interfaccia che contiene i metodo chiamati dal ControllerApp
 * @author  Elisa Pace
 * @author Marco Vassallo
 *
 */

public interface EventService {
	
	public  abstract Vector<Promoter> getPromoter(String stateCode) throws WrongStateCodeException, NoPromoterException;
	public  abstract JSONArray StatsRegion();
	//public  abstract JSONArray  StatsProm(String id);

}
