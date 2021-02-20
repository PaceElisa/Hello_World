/**
 * 
 */
package it.univpm.TicketMasterApp.service;
import java.util.Vector;

import org.json.simple.JSONArray;
import org.json.simple.JSONObject;

import it.univpm.TicketMasterApp.exception.EmptyFieldException;
import it.univpm.TicketMasterApp.exception.EmptyIDException;
import it.univpm.TicketMasterApp.exception.NoPromoterException;
import it.univpm.TicketMasterApp.exception.WrongIDExceotion;
import it.univpm.TicketMasterApp.exception.WrongParamException;
import it.univpm.TicketMasterApp.exception.WrongPeriodException;
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
	public  abstract JSONArray  StatsPromoter(Vector<String> id) throws EmptyIDException, WrongIDExceotion;
	public abstract JSONArray FilterStats(Vector<String> stCod,Vector<String> gen, String param, int period) throws EmptyFieldException, WrongStateCodeException, WrongParamException, WrongPeriodException;

}
