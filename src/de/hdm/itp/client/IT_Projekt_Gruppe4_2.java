package de.hdm.itp.client;

import de.hdm.itp.shared.EditorAdministrationAsync;
import de.hdm.itp.shared.FieldVerifier;
import de.hdm.itp.shared.bo.User;

import com.google.gwt.core.client.EntryPoint;
import com.google.gwt.core.client.GWT;
import com.google.gwt.event.dom.client.ClickEvent;
import com.google.gwt.event.dom.client.ClickHandler;
import com.google.gwt.event.dom.client.KeyCodes;
import com.google.gwt.event.dom.client.KeyUpEvent;
import com.google.gwt.event.dom.client.KeyUpHandler;
import com.google.gwt.user.client.Window;
import com.google.gwt.user.client.rpc.AsyncCallback;
import com.google.gwt.user.client.ui.Anchor;
import com.google.gwt.user.client.ui.Button;
import com.google.gwt.user.client.ui.DialogBox;
import com.google.gwt.user.client.ui.HTML;
import com.google.gwt.user.client.ui.Label;
import com.google.gwt.user.client.ui.RootPanel;
import com.google.gwt.user.client.ui.TextBox;
import com.google.gwt.user.client.ui.Tree;
import com.google.gwt.user.client.ui.TreeItem;
import com.google.gwt.user.client.ui.VerticalPanel;

/**
 * Entry point classes define <code>onModuleLoad()</code>.
 */
public class IT_Projekt_Gruppe4_2 implements EntryPoint {
	

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
	/** Die DialogBox, die bei erstmaliger Registrierung des Nutzers erscheint */
	ClientsideFunctions.InputDialogBox createAccountBox = null;
	
	NavPanel np = new NavPanel();
	MainPanel mp = new MainPanel();
	HeaderPanel hp = new HeaderPanel();
	
	
	public void onModuleLoad() {
		
		Window.alert("loginTesting");
		
		LoginServiceAsync loginService = GWT.create(LoginService.class);
		
		Window.alert("hier gehts noch ");

		loginService.login(GWT.getHostPageBaseURL(), new AsyncCallback<LoginInfo>() {
			
			public void onFailure(Throwable caught) {
				Window.alert("Fehler: " + caught.toString());
			}

			@Override
			public void onSuccess(LoginInfo result) {
				loginInfo = result;
				
				Window.alert("Webcontent der hrnshn");
				if(loginInfo.isLoggedIn()) {
					Window.alert("loggedin");
				}
			}	
	
	});
		loadUserInformation();

	
//		LoginServiceAsync loginService = GWT.create(LoginService.class);
//	    loginService.login("https://it-projekt-gruppe4.appspot.com/IT_Projekt_Gruppe4_2.html", new AsyncCallback<LoginInfo>() {
//		    public void onFailure(Throwable t) {
//		    	System.out.println(t.getMessage());
//		    }
//	
//		    public void onSuccess(LoginInfo result) {
//		    	loginInfo = result;
//		    	if(loginInfo.isLoggedIn()) {
//		    		loadUserInformation();
//		    		
//		    		
//		    	}
//		    	else {
//		    		//ist der Benutzer nicht eingeloggt, so wird er auf die LoginSeite weitergeleitet 
//		    		//TODO
//		    		loadLogin();
//		    	}
//		    }
//	    });
//		loadUserInformation();
//				
	}
//	
	public void loadUserInformation() {
		
		loadApplication();
		
	}
	
	public void loadApplication(){
		RootPanel.get("Head").add(hp);
		RootPanel.get("Nav").add(np);
		RootPanel.get("Main").add(mp);
		
	}
//	
//	private void loadLogin() {
//		  
//		signInLink.setHref(loginInfo.getLoginUrl());
//	    loginPanel.add(loginLabel);
//	    loginPanel.add(signInLink);
//	    RootPanel.get("Login").add(loginPanel);
//	}
	
	}


