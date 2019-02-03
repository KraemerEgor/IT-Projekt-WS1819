package de.hdm.itp.shared.report;

import java.io.Serializable;
import java.util.Date;

/*
 * Basisklasse aller Reports.
 */

public class Report implements Serializable{

	/**
	 * Initialisierung einer serialVersionUID.
	 */
	private static final long serialVersionUID = 1L;

	/**
	 * Deklarieren der variabel "titel" für den Namen des Reports.
	 */
	private String titel = "";
	
	/**
	 * Erstellungsdatum des Berichts.
	 */
	private Date createDate = null;
	
	/**
	 * Kopfdaten des Paragraphhen.
	 */
	private Paragraph header=null;
	
	private String Amount = "";
	
	/**
	 * Auslesen des Report Titels.
	 * @return titel
	 */
	public String getTitel(){
		return this.titel;
	}
	
	/**
	 * Setzen des Report Titels.
	 * @param titel
	 */
	public void setTitel(String titel) {
		this.titel = titel;
	}
	
	/**
	 * Auslesen des Erstellungsdatum.
	 * @return createDate
	 */
	public Date getCreateDate(){
		return this.createDate;
	}
	
	/**
	 * Setzen des Erstellungsdatum.
	 * @param createDate
	 */
	public void setCreateDate(Date createDate){
		this.createDate = createDate;
	}
	
	/**
	 * Auslesen der Kopfdaten.
	 * @return
	 */
	public Paragraph getHeader(){
		return this.header;
	}
	
	/**
	 * Setzen der Kopfdaten
	 * @param header
	 */
	public void setHeader (Paragraph header){
		this.header = header;
	}

	public String getAmount() {
		return Amount;
	}

	public void setAmount(String amount) {
		Amount = amount;
	}
}
