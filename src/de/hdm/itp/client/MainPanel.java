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

import de.hdm.itp.shared.EditorAdministrationAsync;
import de.hdm.itp.shared.bo.User;

public class MainPanel extends ScrollPanel {
	
	
	
	private EditorAdministrationAsync editorAdministration = null;
	private Label header_lbl = new Label("My Profile");
	private PinboardPanel pp = new PinboardPanel();
	private HorizontalPanel userProfile = new HorizontalPanel();
	private User user = new User();
	
	
	
public void onLoad() {
		
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
		
		this.addStyleName("Main");
		
		
		userProfile.setWidth("100%");
		userProfile.setHeight("300px");
		userProfile.setStylePrimaryName("userProfile");
		
		/**
		 * Zusammenbauen des User-Profils
		 */
		Image avatar = new Image("man.png");
		avatar.setStylePrimaryName("avatar");
		
		
		Grid profileGrid = new Grid(4, 3);
		profileGrid.setStylePrimaryName("profileGrid");
		userProfile.add(profileGrid);
		profileGrid.setWidget(0, 1, avatar);
		
		
		Label firstname_lbl = new Label(user.getFirstname());
		firstname_lbl.setStylePrimaryName("label");
		profileGrid.setWidget(0, 2, firstname_lbl);
		
		RootPanel.get("Main").add(userProfile);
		
		header_lbl.setStylePrimaryName("Header");
		this.add(header_lbl);
		
		
		this.add(pp);
}

}
