/**
 * 
 */
package it.univpm.TicketMasterApp.controller;

import it.univpm.TicketMasterApp.exception.EmptyIDException;
import it.univpm.TicketMasterApp.exception.NoBodyException;

//import org.springframework.web.bind.annotation.RestController;

import it.univpm.TicketMasterApp.exception.NoPromoterException;
import it.univpm.TicketMasterApp.exception.WrongIDExceotion;
import it.univpm.TicketMasterApp.exception.WrongStateCodeException;
import it.univpm.TicketMasterApp.service.EventService;
import it.univpm.TicketMasterApp.model.*;

import java.util.Vector;

import org.json.simple.JSONArray;
import org.json.simple.JSONObject;
import org.json.simple.JSONValue;
import org.json.simple.parser.ParseException;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;
//import org.springframework.web.bind.annotation.GetMapping;
//import org.springframework.web.bind.annotation.RequestParam;

/**Metodo che gestisce le chiamate alle relative rotte
 * @author Marco Vassallo
 * @author Elisa Pace
 *
 */
@RestController
public class ControllerApp {
	
	@Autowired
	EventService e;
	
	
	@GetMapping("/promoter")
	public ResponseEntity<Object> getpromoter(@RequestParam(name="stateCode", defaultValue= "MB") String stateCode) throws NoPromoterException, WrongStateCodeException
	{
		return new ResponseEntity<>(e.getPromoter(stateCode), HttpStatus.OK);
	}
	
	@ExceptionHandler(WrongStateCodeException.class)
	public ResponseEntity<Object> handleIOException(WrongStateCodeException err){
		return new ResponseEntity<> (err.StateCodeError(),HttpStatus.BAD_REQUEST);
		
	}
	
	@ExceptionHandler(NoPromoterException.class)
	public ResponseEntity<Object> handleIOException(NoPromoterException err){
		return new ResponseEntity<> (err.PromoterError(),HttpStatus.BAD_REQUEST);
		
	}
	
	@RequestMapping(value="statsReg")
	public ResponseEntity<Object> getStasRegion()
	{
		return new ResponseEntity<>(e.StatsRegion(),HttpStatus.OK);
	}
	
	@PostMapping(value="statsProm")
	public ResponseEntity<Object> getStatsPromoter(@RequestBody String body) throws NoBodyException, WrongIDExceotion,EmptyIDException
	{
		JSONObject obj=(JSONObject) JSONValue.parse(body);
		JSONArray vector= new JSONArray();
		if(obj.isEmpty()) throw new NoBodyException("BodyErrorStats");
		Vector<String> id_promoter=new Vector<String>();
		
		 vector=(JSONArray)obj.get("promoter");
		
		for(Object o: vector) {
			JSONObject object=(JSONObject)o;
			id_promoter.add((String) object.get("ID"));
			}
		return new ResponseEntity<>(e.StatsPromoter(id_promoter),HttpStatus.OK);
}
	
	
	@ExceptionHandler(WrongIDExceotion.class)
	public ResponseEntity<Object> handleIOException(WrongIDExceotion err){
		return new ResponseEntity<> (err.IDError(),HttpStatus.BAD_REQUEST);
	}
	
	@ExceptionHandler(EmptyIDException.class)
	public ResponseEntity<Object> handleIOException(EmptyIDException err){
		return new ResponseEntity<> (err.EmptyIDError(),HttpStatus.BAD_REQUEST);
	}
	@ExceptionHandler(NoBodyException.class)
	public ResponseEntity<Object> handleIOException(NoBodyException err){
		return new ResponseEntity<> (err.BodyErrorStats(),HttpStatus.BAD_REQUEST);
	}
	
	
	
		
	
	
	
	
	
	

}
