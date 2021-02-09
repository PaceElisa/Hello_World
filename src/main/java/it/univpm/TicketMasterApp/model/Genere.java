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
 * @param iD
 * @param name
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
/**
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
 * 
 * @param name
 */
	public void setName(String name) {
		this.Name = name;
	}
/**
 * 	@Override
 */
	public String toString() {
		return "Genere [ID=" + ID + ", name=" + Name + "]";
	}
}
