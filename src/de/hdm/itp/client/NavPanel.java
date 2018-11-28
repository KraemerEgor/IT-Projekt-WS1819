package de.hdm.itp.client;

import com.google.gwt.user.client.ui.Label;
import com.google.gwt.user.client.ui.VerticalPanel;

public class NavPanel extends VerticalPanel {
	Label header_lbl = new Label("Jonger bei mir läuft, mies Nav");
	
	
	public void onLoad() {
		super.onLoad();
		this.addStyleName("Nav");
		SearchPanel sp = new SearchPanel();
		SubsPanel sup = new SubsPanel();
		
		header_lbl.addStyleName("label_test");
		header_lbl.setWidth("210px");
		this.add(header_lbl);
		this.add(header_lbl);
		
		this.add(sp);
		this.add(sup);
	}

}
