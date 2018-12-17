package de.hdm.itp.client;

import com.google.gwt.dom.client.Document;
import com.google.gwt.dom.client.HeadingElement;
import com.google.gwt.user.client.DOM;
import com.google.gwt.user.client.ui.HTMLPanel;
import com.google.gwt.user.client.ui.HorizontalPanel;
import com.google.gwt.user.client.ui.Label;
import com.google.gwt.user.client.ui.ScrollPanel;

public class MainPanel extends ScrollPanel {
	
	
	protected HeadingElement mainHeader = Document.get().createHElement(1);
	Label header_lbl = new Label("MainPanel");
	PinboardPanel pp = new PinboardPanel();
	
	
public void onLoad() {
		
		super.onLoad();
		this.addStyleName("Main");
		
		this.add(new HTMLPanel("<div id=\"mainHeader\"></div>"));
		mainHeader.setInnerHTML("My Profile");
		mainHeader.setClassName("Header");
		DOM.getElementById("mainHeader").appendChild(mainHeader);
		
		this.add(pp);
}

}
