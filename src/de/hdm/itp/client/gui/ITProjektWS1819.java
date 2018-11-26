package de.hdm.itp.client.gui;

import com.google.gwt.core.client.EntryPoint;
import com.google.gwt.core.client.GWT;
import com.google.gwt.event.dom.client.ClickEvent;
import com.google.gwt.event.dom.client.ClickHandler;
import com.google.gwt.user.client.rpc.AsyncCallback;
import com.google.gwt.user.client.ui.Anchor;
import com.google.gwt.user.client.ui.Label;
import com.google.gwt.user.client.ui.RootPanel;
import com.google.gwt.user.client.ui.VerticalPanel;
import de.hdm.itp.client.ClientsideFunctions;
import de.hdm.itp.client.ClientsideSettings;
import de.hdm.itp.client.HeaderPanel;
import de.hdm.itp.client.LoginInfo;
import de.hdm.itp.client.LoginService;
import de.hdm.itp.client.LoginServiceAsync;
import de.hdm.itp.client.MainPanel;
import de.hdm.itp.client.NavPanel;
import de.hdm.itp.client.SearchPanel;
import de.hdm.itp.client.SubsPanel;
import de.hdm.itp.client.SubsTreeViewModel;
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
	/** Die DialogBox, die bei erstmaliger Registrierung des Nutzers erscheint */
	ClientsideFunctions.InputDialogBox createAccountBox = null;
	
	SubsTreeViewModel stvm = new SubsTreeViewModel();
	SubsPanel sp = new SubsPanel();
	SearchPanel sep = new SearchPanel();
	NavPanel np = new NavPanel();
	MainPanel mp = new MainPanel();
	HeaderPanel hp = new HeaderPanel();
	
	public void onModuleLoad() {
		
		LoginServiceAsync loginService = GWT.create(LoginService.class);
	    loginService.login("https://it-projekt-gruppe-10-203610.appspot.com/ITProjekt_SS18_Gr_10.html", new AsyncCallback<LoginInfo>() {
		    public void onFailure(Throwable t) {
		    	System.out.println(t.getMessage());
		    }
	
		    public void onSuccess(LoginInfo result) {
		    	loginInfo = result;
		    	if(loginInfo.isLoggedIn()) {
		    		loadUserInformation();
		    		
		    		
		    	}
		    	else {
		    		//ist der Benutzer nicht eingeloggt, so wird er auf die LoginSeite weitergeleitet 
		    		//TODO
		    		loadLogin();
		    	}
		    }
	    });
		
	}
	
	public void loadUserInformation() {
    	
		if(editorAdministration == null) {
			editorAdministration = ClientsideSettings.getEditorAdministration();
	    }

	    
	    editorAdministration.isUserKnown(loginInfo.getEmailAddress(), new AsyncCallback<Boolean>() {
			
	    	public void onFailure(Throwable t) {
	    		System.out.println(t.getMessage());	
			}

			public void onSuccess(Boolean result) {
				if (result) {
					//Der Nutzer konnte in der Datenbank gefunden werden und ist somit bereits bestehender Nutzer der Applikation
					editorAdministration.getUserByEmail(loginInfo.getEmailAddress(), new AsyncCallback<User>() {
						public void onFailure(Throwable t) {
							System.out.println(t.getMessage());
						}
						public void onSuccess(User arg0) {
							//das zurückkommende Nutzer-Objekt wird in den ClientsideSettings hinterlegt und in einer Instanzenvariable gespeichert.
							ClientsideSettings.setUser(arg0);
							user = arg0;
							//da der Nutzer bereits bekannt ist, wird für ihn im Folgenden die Applikation geladen
							loadApplication();
						}
					});
				}
				
				else {
					/*Wenn kein Nutzer mit dieser e-Mail in der Datenbank gefunden wurde, wird die DialogBox für die erstmalige Registrierung 
					 * aufgebaut. In diese muss der Nutzer seinen Vor- und Nachnamen eintragen und sein Geschlecht auswählen. */
					createAccountBox = new ClientsideFunctions.InputDialogBox(loginInfo.getEmailAddress());
					
					createAccountBox.getOKButton().addClickHandler(new ClickHandler() {
						
						public void onClick(ClickEvent arg0) {
															editorAdministration.createUser(createAccountBox.getMultiUseTextBox().getText(), createAccountBox.getNameTextBox().getText(), createAccountBox.getNickNameTextBox().getText(), createAccountBox.getListBox().getSelectedItemText(), loginInfo.getEmailAddress(), new AsyncCallback<User>() {
									public void onFailure(Throwable t) {
										System.out.println(t.getMessage());
										createAccountBox.hide();
									}
									public void onSuccess(User arg0) {
										if(arg0 != null) {										
		
										createAccountBox.hide();
										//das zurückkommende Nutzer-Objekt wird in den ClientsideSettings hinterlegt und in einer Instanzenvariable gespeichert.
										ClientsideSettings.setUser(arg0);
										user = arg0;
										//danach wird für den neu registrierten Nutzer ebenfalls die Applikation geladen
										loadApplication();
	}}});
														}
					});}}
 });
 }		
			
	
	public void loadApplication(){
	 		
		/*
		 * Das loginPanel wird aufgebaut
		 */
	    VerticalPanel loginPanel = new VerticalPanel();
	    
	    /*
	     * Der signOutLink wird dem loginPanel hinzugefügt
	     */
	    signOutLink.setHref(loginInfo.getLogoutUrl());
	    signOutLink.addStyleName("signout");
		signInLink.addStyleName("reportbutton");
		
		
		loginPanel.add(signOutLink);
		
		
		/*
		 * Die Information über den aktuell angemeldeten Nutzer wird ebenfalls dem loginPanel hinzugefügt
		 */
	    signedInUser = new Label();
	    signedInUser.addStyleName("signedInUser");
	    	    
	    editorAdministration.getFullNameOfUser(user, new AsyncCallback<String>(){
	    	public void onFailure(Throwable t) {
	    		System.out.println(t.getMessage());
	    		
	    	}
	    	public void onSuccess(String result) {
	    		
	    		signedInUser.setText("Angemeldet als: " +result);
	    	}
	    });
	    
		loginPanel.add(signedInUser);
		
		
		//das loginPanel wird dem div mit der id "Login" hinzugefügt
		RootPanel.get("Login").add(loginPanel);
		RootPanel.get("Navi").add(np);
		RootPanel.get("Main").add(mp);
		

	  }
	
	private void loadLogin() {
		  
		signInLink.setHref(loginInfo.getLoginUrl());
	    loginPanel.add(loginLabel);
	    loginPanel.add(signInLink);
	    RootPanel.get("Login").add(loginPanel);
	}	
}
