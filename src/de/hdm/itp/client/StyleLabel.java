package de.hdm.itp.client;

import com.google.gwt.event.dom.client.ClickHandler;
import com.google.gwt.user.client.ui.Label;

public class StyleLabel extends Label{
	public StyleLabel(String content, String style){

		this.setText(content);
		this.setStyleName(style);
		this.setStylePrimaryName(style);
		return;
		
	}
	public StyleLabel(String content, ClickHandler ch){

		this.setText(content);
		this.addClickHandler(ch);
		
		return;
		
	}
	public StyleLabel(String content, ClickHandler ch, String style){

		this.setText(content);
		this.addClickHandler(ch);
		this.setStyleName(style);
		this.setStylePrimaryName(style);
		return;
		
	}
	
}
