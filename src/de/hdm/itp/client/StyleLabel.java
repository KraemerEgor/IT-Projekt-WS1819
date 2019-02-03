package de.hdm.itp.client;

import com.google.gwt.event.dom.client.ClickHandler;
import com.google.gwt.user.client.ui.Label;

/**
 * The Class StyleLabel.
 * Diese Klasse ist eine Hilfklasse zum Erstellen von anonymen Labeels mit einem zugewiesenen
 * StylePrimaryName und im besonderen Fall auch ein ClickHandler.
 */
public class StyleLabel extends Label{
	
	/**
	 * Instantiates a new style label.
	 *
	 * @param content the content of the label
	 * @param style the stylePrimaryName of the Label
	 */
	public StyleLabel(String content, String style){

		this.setText(content);
		this.setStyleName(style);
		this.setStylePrimaryName(style);
		return;
		
	}
	
	/**
	 * Instantiates a new style label.
	 *
	 * @param content the content of the label
	 * @param ch the ClickHandler which should be added to the label
	 */
	public StyleLabel(String content, ClickHandler ch){

		this.setText(content);
		this.addClickHandler(ch);
		
		return;
		
	}
	
	/**
	 * Instantiates a new style label.
	 *
	 * @param content the content of the label
	 * @param ch the ClickHandler which should be added to the label
	 * @param style the stylePrimaryName of the Label
	 */
	public StyleLabel(String content, ClickHandler ch, String style){

		this.setText(content);
		this.addClickHandler(ch);
		this.setStyleName(style);
		this.setStylePrimaryName(style);
		return;
		
	}
	
}
