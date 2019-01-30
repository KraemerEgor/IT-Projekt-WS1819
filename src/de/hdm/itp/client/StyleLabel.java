package de.hdm.itp.client;

import com.google.gwt.event.dom.client.ClickHandler;
import com.google.gwt.user.client.ui.Label;

/**
 * The Class StyleLabel.
 */
public class StyleLabel extends Label{
	
	/**
	 * Instantiates a new style label.
	 *
	 * @param content the content
	 * @param style the style
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
	 * @param content the content
	 * @param ch the ch
	 */
	public StyleLabel(String content, ClickHandler ch){

		this.setText(content);
		this.addClickHandler(ch);
		
		return;
		
	}
	
	/**
	 * Instantiates a new style label.
	 *
	 * @param content the content
	 * @param ch the ch
	 * @param style the style
	 */
	public StyleLabel(String content, ClickHandler ch, String style){

		this.setText(content);
		this.addClickHandler(ch);
		this.setStyleName(style);
		this.setStylePrimaryName(style);
		return;
		
	}
	
}
