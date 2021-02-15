/**
 * 
 */
package it.univpm.TicketMasterApp.service;
import java.util.Vector;

import org.json.simple.JSONArray;
import org.springframework.stereotype.Service;

import it.univpm.TicketMasterApp.exception.NoPromoterException;
import it.univpm.TicketMasterApp.exception.WrongStateCodeException;
import it.univpm.TicketMasterApp.model.Promoter;
/**
 * @author  Elisa Pace
 * @author Marco Vassallo
 *
 */
@Service
public interface EventService {
	
	public  abstract Vector<Promoter> getPromoter(String stateCode) throws WrongStateCodeException, NoPromoterException;
	//public  abstract JSONArray StatsReg();
	//public  abstract JSONArray  StatsProm(String id);

}
