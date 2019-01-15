package de.hdm.itp.shared.report;

import java.io.Serializable;
import java.util.Vector;

/**
 * Die Klasse Column legt fest wie eine Spalte im Report Generator gebildet wird.
 */

public class Column implements Serializable{

	/**
	 * Initialisierung einer serialVersionUID.
	 */
	
	private static final long serialVersionUID = 1L;

	
	/** 
	 * Wert des jeweiligen Eintrags
	 */
	private String value = "";
	
	
	/**
	 *Null-Argument Konstruktor
	 * 
	 */
	public Column(){
	}
	
	/**
	 * Vektor der Sub-Tabelle
	 */
	
	private Vector<Row> subRow = new Vector<Row>();
	
	                                                                                                               
	/** 
	 * Konstruktor mit Value
	 */
	public Column(String v){
		this.value=v;
	}
	
	/**
	 * Auslesen des column Werts.
	 * @return Value
	 */
	public String getValue() {
		return value;
	}
	
	/** Setzen des column Werts.
	 */
	public void setValue(String value){
		this.value= value;
	}
	
	/**
	 * Auslesen der Sub-Reihen
	 * @return Sub-Reihe
	 */
	public Vector<Row> getSubRow() {
		return subRow;
	}

	/**
	 * Setzen der Sub-Reihe
	 */
	public void setSubRow(Vector<Row> subRow) {
		this.subRow = subRow;
	}
	
	/**
	 * Zeilen hinzufügen.
	 * @param r
	 */
	public void addRow(Row r){
		this.subRow.add(r);
	}
	
	/**
	 * Zeilen entfernen.
	 * @param r
	 */
	public void removeRow(Row r){
		this.subRow.remove(r);
	}

	/**
	 * Umwandlung des Column Objekts in einen String.
	 */
	public String toString(){
		return this.value;
	}
}
