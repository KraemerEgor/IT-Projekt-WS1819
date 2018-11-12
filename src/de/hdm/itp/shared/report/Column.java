package de.hdm.itp.shared.report;

import java.io.Serializable;
import java.util.Vector;

/**
 * The class <code>Column</code> forms a column of a row object. Columns objects implement
 * the serializable interface and can therefore be 
 * transferred between client and server.
 */

public class Column implements Serializable{

	/**
	 * Initialisierung einer serialVersionUID.
	 */
	
	private static final long serialVersionUID = 1L;

	
	/** 
	 * The value corresponds to the entry of a cell in a table
	 */
	private String value = "";
	
	
	/**
	 *
	 * Serializable classes that are to be transported using GWT-RPC require a no-argument constructor.
	 * This must be explicitly implemented.
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
	 * During initialization, this constructor forces a "v" = column value
	 */
	public Column(String v){
		this.value=v;
	}
	
	/**Read out of the column value.
	 */
	public String getValue() {
		return value;
	}
	
	/** Set of the column value.
	 */
	public void setValue(String value){
		this.value= value;
	}
	
	/**
	 * Read out of a vector of Row objects which in this case are handled as sub rows for the values of the properties.
	 * @return subRow
	 */
	public Vector<Row> getSubRow() {
		return subRow;
	}

	/**
	 * Set of a vector of Row objects which in this case are handled as sub rows for the values of the properties.
	 * @param subRow
	 */
	public void setSubRow(Vector<Row> subRow) {
		this.subRow = subRow;
	}
	
	/**
	 * Addition of rows.
	 * @param r
	 */
	public void addRow(Row r){
		this.subRow.add(r);
	}
	
	/**
	 * Removing rows.
	 * @param r
	 */
	public void removeRow(Row r){
		this.subRow.remove(r);
	}

	/**
	 * Transform column object into a string
	 */
	public String toString(){
		return this.value;
	}
}
