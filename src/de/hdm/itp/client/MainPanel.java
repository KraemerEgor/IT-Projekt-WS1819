package de.hdm.itp.client;

import java.util.Vector;

import com.google.gwt.dom.client.Document;
import com.google.gwt.dom.client.HeadingElement;
import com.google.gwt.user.client.DOM;
import com.google.gwt.user.client.Window;
import com.google.gwt.user.client.rpc.AsyncCallback;
import com.google.gwt.user.client.ui.Grid;
import com.google.gwt.user.client.ui.HTMLPanel;
import com.google.gwt.user.client.ui.HorizontalPanel;
import com.google.gwt.user.client.ui.Image;
import com.google.gwt.user.client.ui.Label;
import com.google.gwt.user.client.ui.RootPanel;
import com.google.gwt.user.client.ui.ScrollPanel;
import com.google.gwt.user.client.ui.VerticalPanel;

import de.hdm.itp.shared.EditorAdministrationAsync;
import de.hdm.itp.shared.bo.User;
import de.hdm.itp.client.*;

public class MainPanel extends VerticalPanel {
	
	
	
	private EditorAdministrationAsync editorAdministration = null;
	private Label header_lbl = new Label("My Profile");
	private PinboardPanel pp = new PinboardPanel();
	private userProfilePanel userProfile = new userProfilePanel();
	private User user = new User();
	
	
	
public void onLoad() {
		super.onLoad();
		
		
//		this.addStyleName("Main");
		
		
		
		
		
		
		
		
		header_lbl.setStylePrimaryName("Header");
		
		
		this.add(this.header_lbl);
		
		/**
		 * Hinzufügen des User-Profils
		 */
		this.add(this.userProfile);
		
		this.add(this.pp);
		

		
		

		if (editorAdministration == null) {
			editorAdministration = ClientsideSettings.getAdministration();
		}
		
		editorAdministration.getUserById(10000001, new AsyncCallback<User>() {
			public void onFailure(Throwable t){
				System.out.println("fail");
				Window.alert(t.getMessage());
			}
			
			public void onSuccess(User u) {
				
				user = u;
			}
		});
		
		
}

}
