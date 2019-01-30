package de.hdm.itp.shared.bo;

import java.io.Serializable;
import java.sql.Timestamp;
import java.util.Date;

/**
 * Basisklasse f�r alle Business Objekte.
 */
public abstract class BusinessObject implements Serializable {
	
	
	private static final long serialVersionUID = 1L;
	
	
	/**
	 * Eindeutige Identifikationsnummer einer Instanz dieser Klasse und M�glichkeit zur Indetifizierung, 
	 * welche Rolle dieses Objekt in der DB hat.
	 */
	
	/**
	 * Date Attribut zum sp�teren bspw. ausw�hlen Start Zeitpunkt f�r Ausgabe im Report Generator
	 */
	private Date dateFrom = null;
	
	/**
	 * Date Attribut zum sp�teren bspw. ausw�hlen End Zeitpunkt f�r Ausgabe im Report Generator
	 */
	private Date dateTill = null;

	private int id = 0;
	/**
	 * Erstellungsdatum des Objektes
	 */		
	private Timestamp createDate;
	/**
	 * Setter f�r die ID.
	 *
	 * @param new_id die neue ID des BO
	 */
	
	public void setId (int new_id) {
		this.id = new_id;
	}
	
	/**
	 * Getter f�r die ID.
	 *
	 * @return id
	 */
	public int getId() {
		return this.id;
	}
	
	/**
	 *Auslesen des Erstelldatums. 
	 *
	 *@return Timestamp, welcher das Erstelldatum des BOs respr�sentiert.
	 */
	public Timestamp getCreateDate(){
		return createDate;
	}
	
	/**
	 *Setzen des Erstelldatums. 
	 *
	 *@param modificationdate Timestamp, welcher das Erstelldatum des BOs respr�sentiert. 
	 */
	public void setCreateDate(Timestamp ts){
		this.createDate = ts;
	}
	
	/**
	 * �berpr�ft die inhaltliche Gleichheit zweier BusinessObject Objekte (die ID).
	 *
	 * @param object das zu vergleichende Objekt
	 * @return true, wenn inhaltlich gleich
	 */
	public boolean equals(Object object) {
		if (object != null && object instanceof BusinessObject) {
		      BusinessObject bo = (BusinessObject) object;
		      try {
		        if (bo.getId() == getId())
		          return true;
		    }
		
			catch (IllegalArgumentException e) {
				return false;  
			}
		}
		return false;
	}
	
	/**
	 * Einfache Ausgabe des Klassennamens einer BusinessObject Instanz und der ID des Business Objekts.
	 *
	 * @return Der oben beschriebene String
	 */
	public String toString() {
		return this.getClass().getName() + "ID:" + this.getId();
	}

	/**
	 * R�ckgabe des Start Datums
	 */
	public Date getDateFrom() {
		return dateFrom;
	}

	/**
	 * Setzen des Start Zeitpunkts
	 */
	public void setDateFrom(Date dateFrom) {
		this.dateFrom = dateFrom;
	}

	/**
	 * R�ckgabe des End Zeitpunkts
	 */
	public Date getDateTill() {
		return dateTill;
	}

	/**
	 * Setzen des End Zeitpunkts
	 */
	public void setDateTill(Date dateTill) {
		this.dateTill = dateTill;
	}
	
}
