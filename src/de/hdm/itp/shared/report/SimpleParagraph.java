package de.hdm.itp.shared.report;

/**
 //TODO Klassenbeschreibung SP
 *
 */
public class SimpleParagraph extends Paragraph{
	
	/**
	 * Initialisierung einer serialVersionUID.
	 */
	
	private static final long serialVersionUID = 1L;
	
	/**
	 * Initalisierung einer Variablen.
	 */
	private String text = "";
	
	/**
	 * No-argument Konstruktor.
	 */
	public SimpleParagraph(){
	}

	/**
	 * 
	 */
	public SimpleParagraph(String t){
		this.text=t;
	}
	
	/** 
	 * Auslesen des Inhalts.
	 */
	public String getText(){
		return text;
	}
	
	/**
	 * 
	 * Inhalt eines Paragraphen setzen.
	 */
	public void setText(String t){
		this.text = t;
	}
	
	/**
	 * SimpleParagraph Objekt in einen String umwandeln.
	 */
	public String toString(){
		return this.text;
	}
}
