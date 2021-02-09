package it.univpm.TicketMasterApp.service;
import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStream;
import java.io.InputStreamReader;
import java.net.HttpURLConnection;
import java.net.URL;
import java.net.URLConnection;

import org.json.simple.JSONArray;
import org.json.simple.JSONObject;
import org.json.simple.JSONValue;
/**
 * @author Elisa Pace
 * @author Marco Vassallo
 *
 */
public class DownloadData2 {
    public static void main(String[] args) throws Exception {
        String url="https://app.ticketmaster.com/discovery/v2/events.json?countryCode=CA&apikey=n2GxsGGAemGGl1tFNAO23oGpb5NGoDx4";    
        String url2 = "https://app.ticketmaster.com";
        String url3 = "&apikey=n2GxsGGAemGGl1tFNAO23oGpb5NGoDx4";
          if(args.length == 1) {
                url = args[0];
          }
         /* else
          {
              return;
          }*/
          
        DownloadData2 obj= new DownloadData2();
        System.out.println("Testing 1- Send Http GET Request");
        obj.sendGet(url);
        System.out.println("OK");
    }
    
    public void sendGet(String url) throws Exception{
    	  
        String data = "";
        String line = "";
        
        try {
            HttpURLConnection connessione= (HttpURLConnection) new URL(url).openConnection();
            connessione.setRequestMethod("GET");
            connessione.setRequestProperty("User-Agent", "Mozilla/5.0");
            connessione.setRequestProperty("Content-Type", "application/json");
            
        //URLConnection openConnection = new URL(url).openConnection();
        InputStream in = connessione.getInputStream(); 
         System.out.println("Lettura dei dati...");
         try {
               InputStreamReader inR = new InputStreamReader( in );
               BufferedReader buf = new BufferedReader( inR );
              
               while ( ( line = buf.readLine() ) != null ) {
                   data+= line;
                   System.out.println(line);
               }
             } finally {
               in.close();
             }
        
        } catch (IOException e) {
            e.printStackTrace();
            System.out.println("Errore");
        } catch (Exception e) {
            e.printStackTrace();
        }
        JSONObject totalobj = (JSONObject)JSONValue.parseWithException(data);
        System.out.println("OK");
        JSONObject embedded = (JSONObject)(totalobj.get("_embedded"));
        System.out.println("OK");
        JSONArray events = (JSONArray)(embedded.get("events"));
        System.out.println("OK");
        for(Object o : events) {
       	   if(o instanceof JSONObject) {
        		JSONObject obj = (JSONObject) o;
        		//evento
				String id_evento = (String) obj.get("id");
				String nome_evento = (String) obj.get("name");
				String url_evento = (String) obj.get("url");
				String descr_evento = (String) obj.get("description");
				System.out.println(id_evento);
				//data
				JSONObject dates = (JSONObject) obj.get("dates");         
				JSONObject start = (JSONObject) dates.get("start");       
				String data_locale = (String) start.get("localDate");  //*
				// promoter 
/*				JSONObject promoter = (JSONObject) obj.get("promoter"); 
				if(promoter != null) {
					   String id_promoter = (String) promoter.get("id");               
					   String nome_promoter = (String) promoter.get("name");           
					   String promoter_descr = (String) promoter.get("description");
				} else {
				   String id_promoter = null;               
				   String nome_promoter = null;           
				   String promoter_descr = null;
				}                                                                            */
				// promoters
				JSONArray promoters = (JSONArray) obj.get("promoters");  
				if(promoters == null) {
					String id_promoters = null;
			        String nome_promoters = null;             
			        String promoters_descr = null;
				} else {
						for(Object er : promoters) {
						JSONObject op = (JSONObject) er;
						String id_promoters = (String) op.get("id");
				        String nome_promoters = (String) op.get("name");             
				        String promoters_descr = (String) op.get("description");
				        }
				}
				//CLASSIFICATIONS:
				JSONArray class_evento = (JSONArray) obj.get("classifications");
				for(Object p : class_evento) {
					JSONObject io = (JSONObject) p; 
					boolean primary = (boolean) io.get("primary");
				    //genere primario:
				    JSONObject gen_primario = (JSONObject) io.get("segment");  //se si tratta di un concerto, sport, ecc...
				    String id_gen_primario = (String) gen_primario.get("id");
				    String nome_gen_primario = (String) gen_primario.get("name");
				    // genere secondario:
/*				    JSONObject gen_secondario = (JSONObject) io.get("genre");  // definisce il genere di evento: musica rock, classica, partita di calcio, ecc...
				    String id_gen_seconadrio = (String) gen_secondario.get("id");
				    String nome_gen_seconadrio = (String) gen_secondario.get("name");  */
				}                                                                                              
				//VENUES:
				JSONObject embedded2 = (JSONObject) obj.get("_embedded");
				JSONArray venues = (JSONArray) embedded2.get("venues");
				// struttura
				for(Object r : venues) {
					JSONObject s = (JSONObject) r;
//					String nome_struttura = (String) s.get("name");
				// città
//				JSONObject città = (JSONObject) s.get("city");  // città in cui si svolge l'evento
//				String nome_città = (String) città.get("name");
				//provincia o regione
				JSONObject provincia = (JSONObject) s.get("state"); // provincia/regione/stato in cui si svolge l'evento
				String codex_prov = (String) provincia.get("stateCode"); // codice della provincia o regione
				String nome_prov = (String) provincia.get("name");  // nome della provincia o regione
				}
				
//				JSONObject self = (JSONObject) link.get("self");
//				String linkdataset = (String) self.get("href");
//				System.out.println(linkdataset);
//				System.out.println(urlcompleto (linkdataset));
       	    }
       	  System.out.println("OK"); 
		}
        System.out.println("OK");  
    }
    public String urlcompleto(String url) {
    	 String url2 = "https://app.ticketmaster.com";
         String url3 = "&apikey=n2GxsGGAemGGl1tFNAO23oGpb5NGoDx4";
         String risultato = url2 + url + url3;
         return risultato;
    }
}