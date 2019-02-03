package de.hdm.itp.shared.report;

import java.util.Vector;

/*
 *die Klasse SimpleReport beschreibt eine Tabelle mit Daten. Die Klasse erbt von der Klasse Report.   
 */

public class SimpleReport extends Report{

	/**
	 * Initialisierung einer serialVersionUID.
	 */
	private static final long serialVersionUID = 1L;

	/*
	 * Der Tabelle werden Positionsdaten hinzugefügt und
	 * zeilenweise im Vektor gespeichert.
	 */
	
	private Vector<Row> table = new Vector<Row>();
	
	/*
	 * Hinzufügen einer Zeile.
	 */
	
	public void addRow(Row r) {
		this.table.addElement(r);
	}
	
	/*
	 * Entfernen einer Zeile.
	 */
	
	public void removeRow(Row r) {
		this.table.removeElement(r);
	}
	
	/*
	 * Auslesen aller Zeilen(Positionsdaten)
	 */
	
	public Vector<Row> getRows(){
		return this.table;
	}
}
