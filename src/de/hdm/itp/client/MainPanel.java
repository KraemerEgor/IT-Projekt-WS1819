package de.hdm.itp.client;

import java.util.Vector;

import com.google.gwt.dom.client.Document;
import com.google.gwt.dom.client.HeadingElement;
import com.google.gwt.user.client.DOM;
import com.google.gwt.user.client.Window;
<<<<<<< HEAD
=======
import com.google.gwt.user.client.rpc.AsyncCallback;
import com.google.gwt.user.client.ui.Grid;
>>>>>>> master
import com.google.gwt.user.client.ui.HTMLPanel;
import com.google.gwt.user.client.ui.HorizontalPanel;
import com.google.gwt.user.client.ui.Image;
import com.google.gwt.user.client.ui.Label;
import com.google.gwt.user.client.ui.RootPanel;
import com.google.gwt.user.client.ui.ScrollPanel;
import com.google.gwt.user.client.ui.VerticalPanel;
<<<<<<< HEAD
=======

import de.hdm.itp.shared.EditorAdministrationAsync;
import de.hdm.itp.shared.bo.User;
import de.hdm.itp.client.*;
>>>>>>> master

public class MainPanel extends VerticalPanel {
	
	
	
	private EditorAdministrationAsync editorAdministration = null;
	private Label header_lbl = new Label("Mein Profil");
	private PinboardPanel pp = new PinboardPanel();
	private userProfilePanel userProfile = new userProfilePanel();
	private User user = new User();
	
<<<<<<< HEAD
	protected HeadingElement mainHeader = Document.get().createHElement(1);
	Label header_lbl = new Label("MainPanel");
	PinboardPanel pp = new PinboardPanel();
	VerticalPanel vp = new VerticalPanel();
=======
>>>>>>> master
	
	
public void onLoad() {
		super.onLoad();
<<<<<<< HEAD
		this.add(header_lbl);
		this.addStyleName("Main");
		
		
		this.add(new HTMLPanel("<div id=\"mainHeader\"></div>"));
		mainHeader.setInnerHTML("My Profile");
		mainHeader.setClassName("Header");
		DOM.getElementById("mainHeader").appendChild(mainHeader);
		
		vp.add(pp);
		this.add(vp);
=======
		
		
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
>>>>>>> master
		
		
}

}
