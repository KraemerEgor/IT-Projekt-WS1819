package de.hdm.itp.shared.bo;

import java.sql.Timestamp;

/**
 * Die Klasse Contact, Datenstruktur f�r das Kontakt Business Objekt.
 */

public class Post extends BusinessObject {

	
	private static final long serialVersionUID = 1L;

	/** Der Vorname des Kontakts. */
	private String content;
		
	/** Der Besitzer des Kontakts. */
	private int ownerid;
		
	/** Modifikationsdatum des Kontakts. */
	private Timestamp mod_date;
	
	/**
	 * Auslesen des Vornamens. 
	 * 
	 * @return String, welcher den Vornamen des Kontakts repr�sentiert. 
	 */
	public String getContent() {
		return content;
	}
	
	/**
	 * Setzen des Vornamens. 
	 * 
	 * @param firstname String, welcher den Vornamen des Kontakts repr�sentiert. 
	 */
	public void setContent(String content) {
		this.content = content;
	}
	

	public int getOwnerId() {
		return ownerid;
	}
	
	/**
	 *Setzen des Owners. 
	 *
	 *@param owner Integer, welcher den Eigent�mer des Kontakts repr�sentiert.
	 */

	public void setOwnerId(int ownerid) {
		this.ownerid = ownerid;
	}
		
	/**
	 *Auslesen des Modifikationsdatums. 
	 *
	 *@return Timestamp, welcher das Modifiktaionsdatum des Kontakts respr�sentiert.
	 */
		
	public Timestamp getModificationDate(){
		return mod_date;
	}
	
	/**
	 *Setzen des Modifikationsdatums. 
	 *
	 *@param modificationdate Timestamp, welcher das Modifiktaionsdatum des Kontakts respr�sentiert. 
	 */
	
	public void setModificationDate(Timestamp mod_date){
		this.mod_date = mod_date;
	}

	
}
