package de.hdm.itp.client.gui;

import com.google.gwt.core.client.EntryPoint;
import com.google.gwt.core.client.GWT;
import com.google.gwt.user.client.rpc.AsyncCallback;
import com.google.gwt.user.client.ui.Anchor;
import com.google.gwt.user.client.ui.Label;
import com.google.gwt.user.client.ui.RootPanel;
import com.google.gwt.user.client.ui.VerticalPanel;

import de.hdm.itp.client.ClientsideSettings;
import de.hdm.itp.client.LoginInfo;
import de.hdm.itp.client.LoginService;
import de.hdm.itp.client.LoginServiceAsync;
import de.hdm.itp.shared.EditorAdministrationAsync;
import de.hdm.itp.shared.bo.User;

public class ITProjektWS1819 implements EntryPoint {

	/**
	 * Diese Nachricht wird angezeigt, wenn der Client keine Verbindung zum Server aufbauen 
	 * kann.  
	 */
	
	private static final String SERVER_ERROR = "An error occurred while "
			+ "attempting to contact the server. Please check your network " + "connection and try again.";

	
	/** Die Instanzenvariablen, die mit dem Login-Service zusammenhängen. */
	private LoginInfo loginInfo = null;
	private VerticalPanel loginPanel = new VerticalPanel();
	private Label loginLabel = new Label(
	      "Bitte loggen Sie sich mit ihrem Google-Account ein, um die Anwendung zu nutzen.");
	private Anchor signInLink = new Anchor("Sign In");
	private Anchor signOutLink = new Anchor("Sign Out");
	private Label signedInUser = null;
	
	
	User user = null;
	EditorAdministrationAsync editorAdministration = null;
	
	public void onModuleLoad() {
		
		LoginServiceAsync loginService = GWT.create(LoginService.class);
	    loginService.login("https://it-projekt-gruppe-10-203610.appspot.com/ITProjekt_SS18_Gr_10.html", new AsyncCallback<LoginInfo>() {
		    public void onFailure(Throwable t) {
		    	System.out.println(t.getMessage());
		    }
	
		    public void onSuccess(LoginInfo result) {
		    	loginInfo = result;
		    	if(loginInfo.isLoggedIn()) {
		    		//ist der Benutzer mit seinem Google Account im Browser eingeloggt, wird die Methode loadUserInformatino() aufgerufen
		    		//TODO hier starte ich unsere GUI
		    		
		    		
		    	}
		    	else {
		    		//ist der Benutzer nicht eingeloggt, so wird er auf die LoginSeite weitergeleitet 
		    		//TODO
		    		loadLogin();
		    	}
		    }
	    });
		
	}
	
	
	
	private void loadLogin() {
		  
		signInLink.setHref(loginInfo.getLoginUrl());
	    loginPanel.add(loginLabel);
	    loginPanel.add(signInLink);
	    RootPanel.get("Login").add(loginPanel);
	}	
}
