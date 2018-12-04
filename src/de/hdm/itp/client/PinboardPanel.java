package de.hdm.itp.client;

import com.google.gwt.user.client.ui.Label;
import com.google.gwt.user.client.ui.ScrollPanel;
import com.google.gwt.user.client.ui.VerticalPanel;

public class PinboardPanel extends ScrollPanel {
	
	Label name_lbl = new Label("Nils Kaper");
	
	public void onLoad() {
		
		
		super.onLoad();
		this.addStyleName("Pinboard");
		this.add(name_lbl);
		this.getElement().getStyle().setBackgroundColor("red");
		this.setHeight("400px");
		name_lbl.addStyleName("TestLabel");
		
	}

}
