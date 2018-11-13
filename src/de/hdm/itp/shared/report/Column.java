package de.hdm.itp.shared.report;

import java.io.Serializable;
import java.util.Vector;

/**
 * 
 */

public class Column implements Serializable{

	/**
	 * Initialisierung einer serialVersionUID.
	 */
	
	private static final long serialVersionUID = 1L;

	
	/** 
	 * 
	 */
	private String value = "";
	
	
	/**
	 *
	 * 
	 */
	public Column(){
	}
	
	/**
	 * 
	 * Vector for sub table.
	 */
	
	private Vector<Row> subRow = new Vector<Row>();
	
	                                                                                                               
	/** 
	 * 
	 */
	public Column(String v){
		this.value=v;
	}
	
	/**
	 * Auslesen des column Werts.
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
	 * 
	 */
	public Vector<Row> getSubRow() {
		return subRow;
	}

	/**
	 * S
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
	 * Zeilen entfernen..
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
