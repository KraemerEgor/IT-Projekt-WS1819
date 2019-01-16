package de.hdm.itp.shared.report;

import java.util.Vector;

/**
 //TODO Klassenbeschreibung ComposteParagraph
  * 
 */
public class CompositeParagraph extends Paragraph {
	
	/**
	 * Initialisierung einer serialVersionUID.
	 */
	private static final long serialVersionUID = 1L;
	
	/**
	 * Unterabschnitte werden in den Vektor gespeichert.
	 */
	private Vector<SimpleParagraph> subParagraphs = new Vector<SimpleParagraph>();
	
	
	/**
	 * Hinzuf�gen von Unterabschnitten
	 */
	public void addSubparagraph(SimpleParagraph sp){
		this.subParagraphs.addElement(sp);
	}
	
	/**
	 * Entfernen von Unterabschnitten.
	 */	
	public void removeSubParagraph(SimpleParagraph sp){
		this.subParagraphs.removeElement(sp);
	}
	
	/**
	 * Auslesen von Unterabschnitten.
	 */
	public Vector<SimpleParagraph> getSubparagraph(){
		return this.subParagraphs;
	}
	
	/**
	 * Anzahl der Unterabschnitte auslesen.
	 */
	public int getParagraphsSize(){
		return this.subParagraphs.size();
	}
	
	/**
	 * Auslesen eines bestimmten Unterabschnitts.
	 * @param index
	 * @return this.subParagraphs.elementAt(index)
	 */
	public SimpleParagraph getParagraphByIndex(int index){
		return this.subParagraphs.elementAt(index);
	}
	
	
	/** 
	 * Umwandlung eines CompositeParagrahs in einen String.
	 */
	public String toString(){
		
		/**
		 * Erstellen eines leeren Buffers, um alle String-Repr�sentationen 
		 * eines Unterabschnitts nacheinander einzutragen.
		 */
		StringBuffer result = new StringBuffer();
		
		//Schleife über alle Unterabschnitte
		for (int i = 0; i < this.subParagraphs.size(); i++) {
			SimpleParagraph sp = this.subParagraphs.elementAt(i);
			
			//In einen String umwadeln und dem Buffer anhängen
			result.append(sp.toString() + "\n");
		}
		
		// Buffer in einen String umwandeln und zurückgeben.
		return result.toString();
	}
}
