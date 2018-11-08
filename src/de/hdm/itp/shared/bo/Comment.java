package de.hdm.itp.shared.bo;

import java.sql.Timestamp;


public class Comment extends BusinessObject {
	
	
	private static final long serialVersionUID = 1L;
	

	/** Der Text des Kommentars */
	private String text;
	
	/** Das Modifikationsdatum des Kommentars */
	private Timestamp modDate;
	
	/** Der Verfasser des Kommentars */
	private int ownerId;
	
	/** Post, auf den sich der Kommentar bezieht */
	private int postId;
	
	
	/**
	 * Auslesen des Textes eines Kommentars
	 * 
	 * @return String, welcher den Text des Kommentars enthält
	 */
	public String getText () {
		return text;
	}
	
	/**
	 * Setzen des Textes
	 * 
	 * @param Text, der im Kommentar steht
	 */
	public void setText (String text) {
		this.text = text;
	}
	
	/**
	 * Auslesen des Datums, an dem der Kommentar verändert wurde
	 * @return Timestamp des genauen Modifizierungszeitpunkts
	 */

	public Timestamp getModDate() {
		return modDate;
	}
	
	/**
	 * Setzen des Zeitpunktes, an dem ein Kommentar modifiziert wird
	 * @param modDate, an dem der Kommentar verändert wird
	 */

	public void setModDate(Timestamp modDate) {
		this.modDate = modDate;
	}
	
	/**
	 * Auslesen des Users, der den Kommentar verfasst hat
	 * @return ownerId, welche den User eindeutig identifiziert
	 */

	public int getOwnerId() {
		return ownerId;
	}
	
	/**
	 * Setzen des Users, der den Kommentar verfasst
	 * @param ownerId, die den User eindeutig identifiziert
	 */

	public void setOwnerId(int ownerId) {
		this.ownerId = ownerId;
	}
	
	/**
	 * Auslesen des Posts, auf den sich der Kommentar bezieht
	 * @return postId, die den Post eindeutig identifiziert
	 */

	public int getPostId() {
		return postId;
	}
	
	/**
	 * Setzen des Posts, auf den sich der Kommentar bezieht
	 * @param postId, die den Post eindeutig identifiziert
	 */

	public void setPostId(int postId) {
		this.postId = postId;
	}
	
	
	
	
	
}
