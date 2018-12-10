package de.hdm.itp.client;

import com.google.gwt.user.client.ui.HorizontalPanel;
import com.google.gwt.user.client.ui.Label;

public class HeaderPanelReport  extends HorizontalPanel {
	
	Label header_lbl = new Label("Das ist ein Label vom HeaderPanel");
	
	public void onLoad() {
		
		super.onLoad();
		this.addStyleName("Head");
		
		header_lbl.addStyleName("label_test");
		header_lbl.setWidth("210px");
		this.add(header_lbl);
		this.add(header_lbl);
		
	
	}
}
