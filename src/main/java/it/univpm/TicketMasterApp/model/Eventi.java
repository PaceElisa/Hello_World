/**
 * 
 */
package it.univpm.TicketMasterApp.model;

import java.util.Vector;

/**
 * Classe  che fornisce le informazioni necessarie su un evento.
 * @author Marco Vassallo
 * @author Elisa Pace
 * 
 */
public class Eventi {
	
	private String ID;
	private String Name;
	private String URL;
	private String Descrizione;
	private String Regione;
	private String stateCode;
	private String Data;
	private Genere genere;
/**
 * @see Promoter
 */
	private Vector <Promoter> promoters;	
/**
 * 
 * @param iD
 * @param name
 * @param uRL
 * @param descrizione
 * @param data
 * @param genere
 */
	public Eventi(String iD, String name, String uRL, String descrizione, String data, Genere genere) {
		super();
		ID = iD;
		this.Name = name;
		URL = uRL;
		this.Descrizione = descrizione;
		this.Data = data;
		this.genere = genere;
	}

/**
 * Metodo che restituisce l'ID dell'evento
 * @return ID
 */
	public String getID() {
		return ID;
	}
/**
 * Metodo che setta l'ID dell'evento
 * @param iD
 */
	public void setID(String iD) {
		ID = iD;
	}
/**
 * Metodo che restituisce il nome dell'evento
 * @return Name
 */
	public String getName() {
		return Name;
	}
/**
 * Metodo che setta il nome dell'evento
 * @param name
 */
	public void setName(String name) {
		this.Name = name;
	}
/**
 * Metodo che restituisce l'URL dell'evento
 * @return URL
 */
	public String getURL() {
		return URL;
	}
/**
 * Metodo che setta l'URL dell'evento
 * @param uRL
 */
	public void setURL(String uRL) {
		URL = uRL;
	}
/**
 * Metodo  che restituisce la descrizione dell'evento
 * @return Descrizione
 */
	public String getDescrizione() {
		return Descrizione;
	}
/**
 * Metodo che setta la descrizione dell'evento
 * @param descrizione
 */
	public void setDescrizione(String descrizione) {
		this.Descrizione = descrizione;
	}
	/**
	 * Metodo che restituisce il nome della regione del Canada in cui si svolge l'evento
	 * @return Regione
	 */
	public String getRegione() {
		return Regione;
	}
	/**
	 * Metodo che setta il nome della regione del Canada in cui si svolge l'evento
	 * @param regione
	 */
	public void setRegione(String regione) {
		Regione = regione;
	}
	/**
	 * Metodo che restituisce il codice postale della regione
	 * @return stateCode
	 */
	public String getStateCode() {
		return stateCode;
	}
	/**
	 * Metodo che setta il codice postale della regione
	 * @param stateCode
	 */
	public void setStateCode(String stateCode) {
		this.stateCode = stateCode;
	}
	
/**
 * Metodo che restituisce la data locale dell'evento
 * @return Data
 */
	public String getData() {
		return Data;
	}
/**
 * Metodo che setta la data locale dell'evento
 * @param data
 */
	public void setData(String data) {
		this.Data = data;
	}
/**
 * Metodo che restituisce il genere dell'evento
 * @return genere
 */
	public Genere getGenere() {
		return genere;
	}
/**
 * Metodo che setta il genere dell'evento
 * @param genere
 */
	public void setGenere(Genere genere) {
		this.genere = genere;
	}
	/**
	 * Metodo che mi ritorna il vettore dei promoters che sponsorizzano l'evento
	 * @return promoters
	 */
	
	public Vector<Promoter> getPromoters() {
		return promoters;
	}
	/**
	 * Metodo che setta il vettore dei promoters che sponsorizzano l'evento
	 * @param promoters
	 */
	
	public void setPromoters(Vector<Promoter> promoters) {
		this.promoters = promoters;
	}
/**
 * Metodo che stampa gli elementi del vettore promoters che organizzano l'evento 
 * @return vettore
 */
	public String toStringpromoters() {
		String vettore = "";
		for(int i=0; i<promoters.size(); i++) 
			vettore += promoters.get(i).toString();
		return vettore;
	}
	
/**
 * @Override
 */
	public String toString() {
		return "Eventi [ID=" + ID + ", name=" + Name + ", URL=" + URL + ", descrizione=" + Descrizione + ", data="
				+ Data + ", genere=" + genere + ", promoters=" + toStringpromoters() + "]";
	}
}
