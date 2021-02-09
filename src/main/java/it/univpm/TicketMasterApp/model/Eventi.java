/**
 * 
 */
package it.univpm.TicketMasterApp.model;

import java.util.Vector;

/**
 * La classe Eventi mi dà le informazioni necessarie su un evento
 * @author Marco Vassallo
 * @author Elisa Pace
 * 
 */
public class Eventi {
	private String ID;
	private String Name;
	private String URL;
	private String Descrizione;
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
 * questo metodo mi restituisce l'ID dell'evento
 * @return ID
 */
	public String getID() {
		return ID;
	}
/**
 * 
 * @param iD
 */
	public void setID(String iD) {
		ID = iD;
	}
/**
 * questo metodo mi restituisce il nome dell'evento
 * @return Name
 */
	public String getName() {
		return Name;
	}
/**
 * 
 * @param name
 */
	public void setName(String name) {
		this.Name = name;
	}
/**
 * questo metodo mi restituisce l'URL dell'evento
 * @return URL
 */
	public String getURL() {
		return URL;
	}
/**
 * 
 * @param uRL
 */
	public void setURL(String uRL) {
		URL = uRL;
	}
/**
 * questo metodo mi restituisce la descrizione dell'evento
 * @return Descrizione
 */
	public String getDescrizione() {
		return Descrizione;
	}
/**
 * @param descrizione
 */
	public void setDescrizione(String descrizione) {
		this.Descrizione = descrizione;
	}
/**
 * questo metodo mi restituisce la data dell'evento
 * @return Data
 */
	public String getData() {
		return Data;
	}
/**
 * 
 * @param data
 */
	public void setData(String data) {
		this.Data = data;
	}
/**
 * questo metodo mi restituisce il genere dell'evento
 * @return genere
 */
	public Genere getGenere() {
		return genere;
	}
/**
 * 
 * @param genere
 */
	public void setGenere(Genere genere) {
		this.genere = genere;
	}
/**
 * questo metodo mi restituisce la lista dei promoter che organizzano l'evento
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
