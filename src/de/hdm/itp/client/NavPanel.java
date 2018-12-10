package de.hdm.itp.client;

import com.google.gwt.dom.client.Document;
import com.google.gwt.dom.client.HeadingElement;
import com.google.gwt.user.client.ui.Label;
import com.google.gwt.user.client.ui.RootPanel;
import com.google.gwt.user.client.ui.VerticalPanel;

public class NavPanel extends VerticalPanel {
	
	/**
	 * adding the header-element for the navigation panel
	 */
	protected HeadingElement navHeader = Document.get().createHElement(1);
	
	
	VerticalPanel vp = new VerticalPanel();
	SearchPanel sp = new SearchPanel();
	SubsPanel sup = new SubsPanel();
	
	
	public void onLoad() {
		
		this.getElement().appendChild(navHeader);
		
		super.onLoad();
		this.addStyleName("Nav");
		
		
		sp.addStyleName("Search");
		sup.addStyleName("Subs");
		
		navHeader.setInnerText("Navigation");
		
		
		this.add(sp);
		this.add(sup);
		
	}

}
