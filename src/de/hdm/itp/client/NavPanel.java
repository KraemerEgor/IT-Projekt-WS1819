package de.hdm.itp.client;

import com.google.gwt.dom.client.Document;
import com.google.gwt.dom.client.HeadingElement;
import com.google.gwt.user.client.DOM;
import com.google.gwt.user.client.ui.HTMLPanel;
import com.google.gwt.user.client.ui.Label;
import com.google.gwt.user.client.ui.RootPanel;
import com.google.gwt.user.client.ui.VerticalPanel;

public class NavPanel extends VerticalPanel {
	
	/**
	 * adding the header-element for the navigation panel
	 */
	protected HeadingElement navHeader = Document.get().createHElement(1);
	
	VerticalPanel headerPanel = new VerticalPanel();
	VerticalPanel vp = new VerticalPanel();
	SearchPanel sp = new SearchPanel();
	SubsPanel sup = new SubsPanel();
	
	
	public void onLoad() {
		

		super.onLoad();
		this.addStyleName("Nav");
		
//		this.add(new HTMLPanel("<div id=\"HeaderNav\"></div>"));
//		
//		
//		
//		
//		DOM.getElementById("HeaderNav").appendChild(navHeader);
		
		
		navHeader.addClassName("Header");
		navHeader.setInnerText("Navigation");
		
		
		
		
		sp.addStyleName("Search");
		sup.addStyleName("Subs");
		
		
		
		
		this.add(sp);
		this.add(sup);
		
	}

}
