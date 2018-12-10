package de.hdm.itp.client;

import com.google.gwt.user.client.ui.HorizontalPanel;
import com.google.gwt.user.client.ui.Label;

public class MainPanelReport extends HorizontalPanel {
	
		
		Label header_lbl = new Label("Jonger bei mir läuft, mies Main");
		PinboardPanel content = new PinboardPanel();
		
	public void onLoad() {
			
			super.onLoad();
			
			
			
			this.add(header_lbl);
			
	}

}
