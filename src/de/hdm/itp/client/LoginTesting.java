package de.hdm.itp.client;

import com.google.gwt.core.client.EntryPoint;
import com.google.gwt.core.client.GWT;
import com.google.gwt.user.client.Window;
import com.google.gwt.user.client.rpc.AsyncCallback;
import com.google.gwt.user.client.ui.Anchor;
import com.google.gwt.user.client.ui.Button;
import com.google.gwt.user.client.ui.Label;
import com.google.gwt.user.client.ui.RootPanel;
import com.google.gwt.user.client.ui.VerticalPanel;

import de.hdm.itp.shared.EditorAdministrationAsync;

public class LoginTesting implements EntryPoint{
	
	private LoginInfo loginInfo = null;
	private EditorAdministrationAsync admin = ClientsideSettings.getAdministration();

	
	private VerticalPanel loginPanel = new VerticalPanel();

	private Label loginLabel = new Label(
			"Bitte melden sich sich mit Ihrer Google Adresse an um Shared Contacts zu nutzen.");
	private Label lbl_navHeader = new Label("Navigation");
	private Anchor signInLink = new Anchor("Sign In");
	private Anchor signOutLink = new Anchor("Sign Out");
	private Button logoutBtn = new Button("Logout");
	private Button testBtn = new Button("testing");

	public void onModuleLoad() {
		
		RootPanel.get().add(testBtn);
		
		LoginServiceAsync loginService = GWT.create(LoginService.class);
	

		loginService.login(GWT.getHostPageBaseURL(), new AsyncCallback<LoginInfo>() {
			
			public void onFailure(Throwable caught) {
				Window.alert("Fehler: " + caught.toString());
			}

			@Override
			public void onSuccess(LoginInfo result) {
				loginInfo = result;
				if(loginInfo.isLoggedIn()) {
					Window.alert("loggedin");
				}
			}	
	
	});
	}
}
