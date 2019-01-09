package de.hdm.itp.client;

import com.google.gwt.user.client.ui.Label;

public class StyleLabel extends Label{
	public StyleLabel(String content, String style){

		this.setText(content);
		this.setStyleName(style);
		this.setStylePrimaryName(style);
		return;
		
	}
	
}
