package de.hdm.itp.client;

import com.google.gwt.user.client.ui.Button;
import com.google.gwt.user.client.ui.Label;
import com.google.gwt.user.client.ui.VerticalPanel;

public class SearchPanel extends VerticalPanel {
	Label header_lbl = new Label("Search Panel");
	Button search_btn = new Button("Suche");
	
	public void onLoad() {
			
			super.onLoad();
			this.addStyleName("Search");
			
			header_lbl.addStyleName("label_test");
			header_lbl.setWidth("210px");
			this.add(header_lbl);
			this.add(search_btn);
	}

}
