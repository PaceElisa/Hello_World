/**
 * 
 */
package it.univpm.TicketMasterApp.model;

/**
 * La classe Promoter mi fornisce le informazioni circa il promoter dell'evento
 * @author Marco Vassallo
 * @author Elisa Pace
 *
 */
public class Promoter {
/**
 * 
 * @param iD
 * @param nome
 * @param descrizione
 */
	public Promoter(String iD, String nome, String descrizione) {
		super();
		ID = iD;
		Nome = nome;
		Descrizione = descrizione;
	}
	private String ID;
	private String Nome;
	private String Descrizione;
/**
 * questo metodo mi restituisce i'ID del promoter
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
 * questo metodo mi restituisce il nome del promoter dell'evento
 * @return Nome
 */
	public String getNome() {
		return Nome;
	}
/**
 * 
 * @param nome
 */
	public void setNome(String nome) {
		Nome = nome;
	}
/**
 * questo metodo mi dà informazioni riguardo il promoter
 * @return Descrizione
 */
	public String getDescrizione() {
		return Descrizione;
	}
/**
 * 
 * @param descrizione
 */
	public void setDescrizione(String descrizione) {
		Descrizione = descrizione;
	}
/**
 * 	@Override
 */
	public String toString() {
		return "Promoter [ID=" + ID + ", Nome=" + Nome + ", Descrizione=" + Descrizione + "]";
	}
}
