package de.hdm.itp.client;

import java.util.Vector;

import com.google.gwt.user.client.ui.Label;
import com.google.gwt.user.client.ui.ScrollPanel;
import com.google.gwt.user.client.ui.VerticalPanel;

import de.hdm.itp.shared.bo.Post;
import de.hdm.itp.shared.bo.User;

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
	
	public void createPinboard(User u) {
		
		boolean own = false;
		
		//if (u == currentUser) {
			own = true;
		//}
		
	}

}
