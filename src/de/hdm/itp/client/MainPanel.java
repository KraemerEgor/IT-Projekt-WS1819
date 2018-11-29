package de.hdm.itp.client;

import com.google.gwt.user.client.ui.HorizontalPanel;
import com.google.gwt.user.client.ui.Label;

public class MainPanel extends HorizontalPanel {
	
	Label header_lbl = new Label("Jonger bei mir läuft, mies Main");
	
public void onLoad() {
		
		super.onLoad();
		this.addStyleName("Main");
		
		header_lbl.addStyleName("label_test");
		header_lbl.setWidth("210px");
		this.add(header_lbl);
		this.add(header_lbl);
		
}

}
