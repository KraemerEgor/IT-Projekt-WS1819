package de.hdm.itp.shared.bo;

import java.sql.Timestamp;
import java.text.SimpleDateFormat;

/**
 * Die Klasse Contact, Datenstruktur f�r das Kontakt Business Objekt.
 */
public class Post extends BusinessObject {

	
	/** The Constant serialVersionUID. */
	private static final long serialVersionUID = 1L;

	/** Der Vorname des Kontakts. */
	private String content;
		
	/** Der Besitzer des Kontakts. */
	private int ownerId;
		
	/** Modifikationsdatum des Kontakts. */
	private Timestamp modDate;
	
	//private SimpleDateFormat format = new SimpleDateFormat("yyyy-MM-dd");
	
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
	 * @param content the new content
	 */
	public void setContent(String content) {
		this.content = content;
	}
	

	/**
	 * Gets the owner id.
	 *
	 * @return the owner id
	 */
	public int getOwnerId() {
		return ownerId;
	}
	
	/**
	 * Setzen des Owners. 
	 *
	 * @param ownerId the new owner id
	 */
	public void setOwnerId(int ownerId) {
		this.ownerId = ownerId;
	}
		
	/**
	 *Auslesen des Modifikationsdatums. 
	 *
	 *@return Timestamp, welcher das Modifiktaionsdatum des Kontakts respr�sentiert.
	 */
	public Timestamp getModDate(){
		return modDate;
	}
	
	/**
	 * Setzen des Modifikationsdatums. 
	 *
	 * @param modDate the new mod date
	 */
	public void setModDate(Timestamp modDate){
		this.modDate = modDate;
	}

	
}
