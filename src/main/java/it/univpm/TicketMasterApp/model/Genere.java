/**
 * 
 */
package it.univpm.TicketMasterApp.model;

/**
 * La classe Genere mi fornisce informazioni riguardo al genere di un dato evento
 * @author Marco Vassallo
 * @author Elisa Pace
 *
 */
public class Genere {
	private String ID;
	private String Name;
	/**
	 * Costruttore dell'oggetto
	 */
	
	public Genere() {
		super();
	}
/**
 * Costruttore dell'oggetto
 * @param iD è l'identificatore dellla tipologia del genere dell'evento
 * @param name è il nome della tipologia del genere dell'evento
 */
	public Genere(String iD, String name) {
		super();
		ID = iD;
		this.Name = name;
	}
/**
 * questo metodo mi restituisce l'ID del genere dell'evento
 * @return ID
 */
	public String getID() {
		return ID;
	}
/**Metodo che setta l'ID del genere del''evento
 * @param iD
 */
	public void setID(String iD) {
		ID = iD;
	}
/**
 * questo metodo mi restituisce invece il nome del genere di un evento
 * @return Name
 */
	public String getName() {
		return Name;
	}
/**
 * Metodo che setta il nome della tipologia del genere del''evento
 * @param name
 */
	public void setName(String name) {
		this.Name = name;
	}
/**
 * 	@Override
 */
	public String toString() {
		return "Genere: [ID=" + ID + ", name=" + Name + "]";
	}
}
