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
	private String ID;
	private String Nome;
	private String Descrizione;
/**
 * costruttore dell'oggetto
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

/**
 * questo metodo mi restituisce i'ID del promoter
 * @return ID
 */
	public String getID() {
		return ID;
	}
/**
 * Metodo che setta l'ID del promoter
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
 * Metodo che setta il nome del promoter
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
 * Metodo che setta la descrizione del promoter
 * @param descrizione
 */
	public void setDescrizione(String descrizione) {
		Descrizione = descrizione;
	}
/**
 * 	@Override
 */
	public String toString() {
		return "Promoter: [ID=" + ID + ", Nome=" + Nome + ", Descrizione=" + Descrizione + "]";
	}


@Override
public boolean equals(Object obj) {
	if (this == obj)
		return true;
	if (obj == null)
		return false;
	if (getClass() != obj.getClass())
		return false;
	Promoter other = (Promoter) obj;
	if (Descrizione == null) {
		if (other.Descrizione != null)
			return false;
	} else if (!Descrizione.equals(other.Descrizione))
		return false;
	if (ID == null) {
		if (other.ID != null)
			return false;
	} else if (!ID.equals(other.ID))
		return false;
	if (Nome == null) {
		if (other.Nome != null)
			return false;
	} else if (!Nome.equals(other.Nome))
		return false;
	return true;
}
	
}
