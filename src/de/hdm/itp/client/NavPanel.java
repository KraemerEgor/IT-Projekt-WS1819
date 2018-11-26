package de.hdm.itp.client;

import com.google.gwt.user.client.ui.VerticalPanel;

public class NavPanel extends VerticalPanel {
	
	
	public void onLoad() {
		super.onLoad();
		SearchPanel sp = new SearchPanel();
		SubsPanel sup = new SubsPanel();
		
		this.add(sp);
		this.add(sup);
	}

}
