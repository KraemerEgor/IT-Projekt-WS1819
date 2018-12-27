package de.hdm.itp.client;

import java.util.Vector;

import com.google.gwt.dom.client.Document;
import com.google.gwt.dom.client.HeadingElement;
import com.google.gwt.user.client.DOM;
import com.google.gwt.user.client.Window;

import com.google.gwt.user.client.rpc.AsyncCallback;

import com.google.gwt.user.client.ui.HTMLPanel;
import com.google.gwt.user.client.ui.Label;
import com.google.gwt.user.client.ui.VerticalPanel;


import de.hdm.itp.shared.EditorAdministrationAsync;
import de.hdm.itp.shared.bo.User;


public class MainPanel extends VerticalPanel {
	
	
	
	private EditorAdministrationAsync editorAdministration = null;
	private Label header_lbl = new Label("Mein Profil");
	private PinboardPanel pp = new PinboardPanel();
	private userProfilePanel userProfile = new userProfilePanel();
	User user = new User();
	
	protected HeadingElement mainHeader = Document.get().createHElement(1);
	VerticalPanel vp = new VerticalPanel();
	
	
public void onLoad() {
		super.onLoad();

		this.add(header_lbl);
		this.addStyleName("Main");
		
		
		this.add(new HTMLPanel("<div id=\"mainHeader\"></div>"));
		mainHeader.setInnerHTML("My Profile");
		mainHeader.setClassName("Header");
		DOM.getElementById("mainHeader").appendChild(mainHeader);
		
		vp.add(pp);
		this.add(vp);
		
		
//		this.addStyleName("Main");
		
		
		
		
		
		
		
		
		header_lbl.setStylePrimaryName("Header");
		
		
		this.add(this.header_lbl);
		
		/**
		 * Hinzufügen des User-Profils
		 */
		userProfile.setWidth("100%");
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
