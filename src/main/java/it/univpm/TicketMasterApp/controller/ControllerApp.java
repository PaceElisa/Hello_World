/**
 * 
 */
package it.univpm.TicketMasterApp.controller;

//import org.springframework.web.bind.annotation.RestController;

import it.univpm.TicketMasterApp.exception.NoPromoterException;
import it.univpm.TicketMasterApp.exception.WrongStateCodeException;
import it.univpm.TicketMasterApp.service.EventService;
import it.univpm.TicketMasterApp.model.*;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;
//import org.springframework.web.bind.annotation.GetMapping;
//import org.springframework.web.bind.annotation.RequestParam;

/**
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
/*	
	@RequestMapping(value="statsReg")
	public ResponseEntity<Object> getStasRegion()
	{
		return new ResponseEntity<>(e.StatsReg().toString(),HttpStatus.OK);
	}
	*/
	
	
	
	
	
	

}
