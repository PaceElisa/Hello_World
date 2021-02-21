/**
 * 
 */
package it.univpm.TicketMasterApp.controller;

import it.univpm.TicketMasterApp.exception.EmptyFieldException;
import it.univpm.TicketMasterApp.exception.EmptyIDException;
import it.univpm.TicketMasterApp.exception.NoBodyException;
import it.univpm.TicketMasterApp.exception.NoBodyFilterException;

//import org.springframework.web.bind.annotation.RestController;

import it.univpm.TicketMasterApp.exception.NoPromoterException;
import it.univpm.TicketMasterApp.exception.WrongIDExceotion;
import it.univpm.TicketMasterApp.exception.WrongParamException;
import it.univpm.TicketMasterApp.exception.WrongPeriodException;
import it.univpm.TicketMasterApp.exception.WrongStateCodeException;
import it.univpm.TicketMasterApp.service.EventService;


import java.util.Vector;

import org.json.simple.JSONArray;
import org.json.simple.JSONObject;
import org.json.simple.JSONValue;

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
	
	/**
	 * Metodo che lancia una chiamata di tipo GET che ritorna una lisat di promoter relativo alla regione Manitoba(MB)
	 * se non viene specificato un'altro stateCode dall'utente
	 * @param stateCode
	 * @return un JSONArray con tanti JSONObject con id, nome e descrizione del promoter
	 * @throws NoPromoterException
	 * @throws WrongStateCodeException
	 */
	@GetMapping("/promoter")
	public ResponseEntity<Object> getpromoter(@RequestParam(name="stateCode", defaultValue= "MB") String stateCode) throws NoPromoterException, WrongStateCodeException
	{
		return new ResponseEntity<>(e.getPromoter(stateCode), HttpStatus.OK);
	}
	/**
	 * Metodo che gestisce l'eccezione WrongStateCodeException e manda una risposta di tipo bad_request
	 * @param err
	 * @return un messaggio di errore
	 */
	@ExceptionHandler(WrongStateCodeException.class)
	public ResponseEntity<Object> handleIOException(WrongStateCodeException err){
		return new ResponseEntity<> (err.StateCodeError(),HttpStatus.BAD_REQUEST);
		
	}
	/**
	 * Metodo che gestisce l'eccezione NoPromoterException e manda una risposta di tipo bad_request
	 * @param err
	 * @return un messaggio di errore
	 */
	@ExceptionHandler(NoPromoterException.class)
	public ResponseEntity<Object> handleIOException(NoPromoterException err){
		return new ResponseEntity<> (err.PromoterError(),HttpStatus.BAD_REQUEST);
		
	}
	/**
	 * Metodo che lancia una chiamata di tipo Get e restituisce le statistiche per ogni regione
	 * @return un JSONArray con tanti JSONObject 
	 */
	@RequestMapping(value="statsReg")
	public ResponseEntity<Object> getStasRegion()
	{
		return new ResponseEntity<>(e.StatsRegion(),HttpStatus.OK);
	}
	/**
	 * Metodo che lancia una chiamatata di tipo POST a cui viene passato un body tipo questo:
	 * {
    "promoter": [
        {
            "ID": "850"
        },
        {
            "ID": "653"
        },
        {
            "ID": "494"
        }
       ]
      }
	 * @param body
	 * @return un JSONArray con tanti JSONObject che contengono le statistiche relative a uno o più promoter inserito dall'utente
	 * @throws NoBodyException
	 * @throws WrongIDExceotion
	 * @throws EmptyIDException
	 */
	@PostMapping(value="statsProm")
	public ResponseEntity<Object> getStatsPromoter(@RequestBody String body) throws NoBodyException, WrongIDExceotion,EmptyIDException
	{
		JSONObject obj=(JSONObject) JSONValue.parse(body);
		JSONArray vector= new JSONArray();
		if(obj.isEmpty()) throw new NoBodyException();
		Vector<String> id_promoter=new Vector<String>();
		
		 vector=(JSONArray)obj.get("promoter");
		
		for(Object o: vector) {
			JSONObject object=(JSONObject)o;
			id_promoter.add((String) object.get("ID"));
			}
		return new ResponseEntity<>(e.StatsPromoter(id_promoter),HttpStatus.OK);
}
	
	/**
	 * Metodo che gestisce l'eccezione WrongIDException e manda una risposta di tipo bad_request
	 * @param err
	 * @return un messaggio di errore
	 */
	@ExceptionHandler(WrongIDExceotion.class)
	public ResponseEntity<Object> handleIOException(WrongIDExceotion err){
		return new ResponseEntity<> (err.IDError(),HttpStatus.BAD_REQUEST);
	}
	/**
	 * Metodo che gestisce l'eccezione EmptyIDException e manda una risposta di tipo bad_request
	 * @param err
	 * @return un messaggio di errore
	 */
	@ExceptionHandler(EmptyIDException.class)
	public ResponseEntity<Object> handleIOException(EmptyIDException err){
		return new ResponseEntity<> (err.EmptyIDError(),HttpStatus.BAD_REQUEST);
	}
	/**
	 * Metodo che gestisce l'eccezione NoBodyException e manda una risposta di tipo bad_request
	 * @param err
	 * @return un messaggio di errore
	 */
	@ExceptionHandler(NoBodyException.class)
	public ResponseEntity<Object> handleIOException(NoBodyException err){
		return new ResponseEntity<> (err.BodyErrorStats(),HttpStatus.BAD_REQUEST);
	}
	/**
	 * Metodo che  gestisce una chiamat di tipo POST e che filtra le statistiche in base a dei filtri inseriti dall'utente, come:
	 *   {
    "regione":[
        {
          "stateCode": "AB"
        },
        {
            "stateCode":"QC"
        }
    ],
    "genere":[
        {
            "name": "Music"
        },
        {
            "name": "Sport"
        }
    ],
    "periodo": 3,
    "param": "statsReg"
   }
        Dove period indica il periodo sul quale effettuare il calcolo del numero medio, minimo e massimo di eventi trimestrali e 
        semestrali, mentre stats indica quale delle due statistiche l'utente vuole filtrare
	 * @param bodyfilter
	 * @return un jsonarray con tanti JSONObject con le statistiche filtrate
	 * @throws NoBodyFilterException
	 * @throws EmptyFieldException
	 * @throws WrongStateCodeException
	 * @throws WrongParamException
	 * @throws WrongPeriodException
	 */
	@PostMapping(value="filterstats")
	public ResponseEntity<Object> getFilterStats(@RequestBody String bodyfilter) throws NoBodyFilterException, EmptyFieldException,WrongStateCodeException, WrongParamException, WrongPeriodException{
		//estraggo elementi dal body e controllo che i parametri siano giusti
		JSONObject obj=(JSONObject) JSONValue.parse(bodyfilter);
		JSONArray vectors= new JSONArray();
		JSONArray vectorg= new JSONArray();
		if(obj.isEmpty()) throw new NoBodyFilterException();
		Vector<String> stateCode=new Vector<String>();
		Vector<String> genere=new Vector<String>();
		if(obj.containsKey("regione")) {
			 vectors=(JSONArray)obj.get("regione");
				
				for(Object o: vectors) {
					JSONObject object=(JSONObject)o;
					String s=(String) object.get("stateCode");
					if(s!=null) {
					stateCode.add(s);	
				}
				}
			
		} if(obj.containsKey("genere")) {
			 vectorg=(JSONArray) obj.get("genere");
			 
			 for(Object o: vectorg) {
					JSONObject object=(JSONObject)o;
					String g=(String) object.get("name");
					if(g!=null) {
						genere.add(g);
					}
						
				}
			
		}
		
		if(!(obj.containsKey("param")))
			throw new EmptyFieldException("Hai dimenticato di inserire  \"param\" nel body... Riprova aggiungendolo");
		 String param=(String)obj.get("param");
		 if(!(obj.containsKey("periodo")))
			 throw new EmptyFieldException("Hai dimenticato di inserire  \"periodo\" nel body... Riprova aggiungendolo");
		 long periodo=(Long) obj.get("periodo");
		
		 return new ResponseEntity<>(e.FilterStats(stateCode,genere,param,periodo), HttpStatus.OK);
		
	}
		
		
		
		
	
	@ExceptionHandler(NoBodyFilterException.class)
	public ResponseEntity<Object> handleIOException(NoBodyFilterException err){
		return new ResponseEntity<> (err.BodyErrorFilter(),HttpStatus.BAD_REQUEST);
	}
	@ExceptionHandler(EmptyFieldException.class)
	public ResponseEntity<Object> handleIOException(EmptyFieldException err){
		return new ResponseEntity<> (err.Messaggio(),HttpStatus.BAD_REQUEST);
	}
	@ExceptionHandler(WrongParamException.class)
	public ResponseEntity<Object> handleIOException(WrongParamException err){
		return new ResponseEntity<> (err.Messaggio(),HttpStatus.BAD_REQUEST);
	}
	@ExceptionHandler(WrongPeriodException.class)
	public ResponseEntity<Object> handleIOException(WrongPeriodException err){
		return new ResponseEntity<> (err.Messaggio(),HttpStatus.BAD_REQUEST);
	}
	
	
	
		
	
	
	
	
	
	

}
