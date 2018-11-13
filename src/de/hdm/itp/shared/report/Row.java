package de.hdm.itp.shared.report;

import java.io.Serializable;
import java.util.Vector;

/**
 *
 */
public class Row implements Serializable{
	
	/**
	 * Initialisierung einer serialVersionUID.
	 */
	
	private static final long serialVersionUID = 1L;
	
	/**
	 * Initialisierung eines Vektors von Spalten Objekt. 
	 */
	private Vector<Column> columns = new Vector<Column>();
	
	/**
	 * Hinzufügen einer Spalte.
	 */
	public void addColumn(Column c){
		this.columns.addElement(c);
	}
	
	/**
	 * Entfernen einer Spalte.
	 */
	public void removeColumn(Column c){
		this.columns.removeElement(c);
	}
	
	
	/** 
	 * Auslesen einer Spalte
	 */
	public Vector<Column>getColumns(){
		return this.columns;
	}
	
	
	/**
	 * Auslesen der Anzahl an Spalten.
	 */
	public int getColumnsSize(){
		return this.columns.size();
	}
	
	/**
	 *  Auslesen eines Spalten Objekts.
	 */
	public Column getColumnByIndex(int index){
		return this.columns.elementAt(index);
	}
}
