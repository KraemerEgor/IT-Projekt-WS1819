package de.hdm.itp.client;

import de.hdm.itp.shared.EditorAdministrationAsync;

import de.hdm.itp.shared.bo.User;



import com.google.gwt.core.client.EntryPoint;
import com.google.gwt.core.client.GWT;
import com.google.gwt.event.dom.client.ClickEvent;
import com.google.gwt.event.dom.client.ClickHandler;
import com.google.gwt.user.client.Window;
import com.google.gwt.user.client.rpc.AsyncCallback;
import com.google.gwt.user.client.ui.Anchor;
import com.google.gwt.user.client.ui.Label;
import com.google.gwt.user.client.ui.RootPanel;
import com.google.gwt.user.client.ui.VerticalPanel;

// TODO: Auto-generated Javadoc
/**
 * Entry point classes define <code>onModuleLoad()</code>.
 */
public class IT_Projekt_Gruppe4_2 implements EntryPoint {

	/**
	 * Diese Nachricht wird angezeigt, wenn der Client keine Verbindung zum Server
	 * aufbauen kann.
	 */

	private static final String SERVER_ERROR = "An error occurred while "
			+ "attempting to contact the server. Please check your network " + "connection and try again.";

	/** Die Instanzenvariablen, die mit dem Login-Service zusammenhängen. */
	private LoginInfo loginInfo = null;
	
	/** The login panel. */
	private VerticalPanel loginPanel = new VerticalPanel();
	
	/** The login label. */
	private Label loginLabel = new Label(
			"Bitte loggen Sie sich mit ihrem Google-Account ein, um die Anwendung zu nutzen.");
	
	/** The sign in link. */
	private Anchor signInLink = new Anchor("Sign In");
	
	/** The sign out link. */
	private Anchor signOutLink = new Anchor("Sign Out");
	
	/** The signed in user. */
	private Label signedInUser = null;

	/** The admin. */
	private EditorAdministrationAsync admin = ClientsideSettings.getAdministration();

	/** The user. */
	User user = null;
	
	/** The editor administration. */
	EditorAdministrationAsync editorAdministration = null;
	
	/**  Die DialogBox, die bei erstmaliger Registrierung des Nutzers erscheint. */
	ClientsideFunctions.InputDialogBox createAccountBox = null;

	/** The np. */
	NavPanel np = new NavPanel();
	
	/** The sp. */
	SubsPanel sp = new SubsPanel();
	
	/** The mp. */
	MainPanel mp = new MainPanel();
	
	/** The hp. */
	HeaderPanel hp = new HeaderPanel();

	/* (non-Javadoc)
	 * @see com.google.gwt.core.client.EntryPoint#onModuleLoad()
	 */
	public void onModuleLoad() {
		//Diese 9 Lines entkommentieren, um lokal auszuführen
//		signInLink.setStylePrimaryName("signin_btn");
//		User test = new User();
//		test.setId(10000002);
//		test.setFirstname("Egor");
//		test.setLastname("Kramu");
//		test.setNickname("KK");
//		test.setGender("m");
//		test.setEmail("egor.kramu@gmail.com");
//		ClientsideSettings.setUser(test);
//		loadApplication();

		//Window.alert("loginTesting");
		/** Hier wird das Proxy-Objekt erzeugt 
		 * Außerdem entstehen hier die Compilate für verschiedene Browser*/
		LoginServiceAsync loginService = GWT.create(LoginService.class);

		//Window.alert("hier gehts noch ");
		/** Hier wird die Google Methode login() aufgerufen
		 * Diese versucht den User durch seinen Google-Account anzumelden */
		loginService.login(GWT.getHostPageBaseURL(), new AsyncCallback<LoginInfo>() {

			public void onFailure(Throwable caught) {
				ClientsideFunctions.AlertDialogBox adb = new ClientsideFunctions.AlertDialogBox(caught.getMessage());
			}

			@Override
			public void onSuccess(LoginInfo result) {
				loginInfo = result;

				if (loginInfo.isLoggedIn()) {
					//Window.alert("if !!!loggedin and loadUserInformation");
					loadUserInformation();

				} else {
					//Window.alert("else loadLogin");

					loadLogin();

				}
			}

		});

	}

	/**
	 * In dieser Methoden werden die Daten des Users in die ClientsideSettings geschrieben.
	 * Wenn der User sich das Erste Mal anmeldet, so muss dieser erst die Daten angeben zur Anmeldung.
	 * Diese Daten werden dann in die Datenbank geschrieben und der Nutzer gilt als angemeldet.
	 */
	public void loadUserInformation() {

		if (editorAdministration == null) {
			editorAdministration = ClientsideSettings.getEditorAdministration();
		}

		editorAdministration.isUserKnown(loginInfo.getEmailAddress(), new AsyncCallback<Boolean>() {

			public void onFailure(Throwable t) {
				ClientsideFunctions.AlertDialogBox adb = new ClientsideFunctions.AlertDialogBox(t.getMessage());
			}

			public void onSuccess(Boolean result) {
				//Window.alert("KnownUserSuccess");

				if (result) {
					//Window.alert("UserisKnown");

					// Der Nutzer konnte in der Datenbank gefunden werden und ist somit bereits
					// bestehender Nutzer der Applikation
					editorAdministration.getUserByEmail(loginInfo.getEmailAddress(), new AsyncCallback<User>() {
						public void onFailure(Throwable t) {
							ClientsideFunctions.AlertDialogBox adb = new ClientsideFunctions.AlertDialogBox(t.getMessage());
						}

						public void onSuccess(User arg0) {

							// das zurückkommende Nutzer-Objekt wird in den ClientsideSettings hinterlegt
							// und in einer Instanzenvariable gespeichert.
							ClientsideSettings.setUser(arg0);
							user = arg0;
							//Window.alert("UserWurdegesetzt");

							// da der Nutzer bereits bekannt ist, wird für ihn im Folgenden die Applikation
							// geladen
							loadApplication();
						}
					});
				}

				else {
					//Window.alert("Userisunknown");

					/*
					 * Wenn kein Nutzer mit dieser e-Mail in der Datenbank gefunden wurde, wird die
					 * DialogBox für die erstmalige Registrierung aufgebaut. In diese muss der
					 * Nutzer seinen Vor- und Nachnamen eintragen und sein Geschlecht auswählen.
					 */
					createAccountBox = new ClientsideFunctions.InputDialogBox(loginInfo.getEmailAddress());

					createAccountBox.getOKButton().addClickHandler(new ClickHandler() {

						public void onClick(ClickEvent UserRegister) {

							String getGender = createAccountBox.getListBox().getSelectedItemText();
							
							switch (getGender) {

							case "Männlich":
								getGender = "m";
								break;

							case "Weiblich":
								getGender = "f";
								break;

							case "Divers":
								getGender = "o";
								break;
							}
							
							//Window.alert("after creatACCountBox switch case " + loginInfo.getEmailAddress());
							editorAdministration.createUser(loginInfo.getEmailAddress(),
									createAccountBox.getFirstnameTextBox().getText(),
									createAccountBox.getLastnameTextBox().getText(),
									createAccountBox.getNicknameTextBox().getText(), getGender,
									new AsyncCallback<User>() {
										public void onFailure(Throwable t) {
											ClientsideFunctions.AlertDialogBox adb = new ClientsideFunctions.AlertDialogBox(t.getMessage());
											createAccountBox.hide();
										}

										public void onSuccess(User UserRegister) {

											if (UserRegister != null) {

												createAccountBox.hide();
												// das zurückkommende Nutzer-Objekt wird in den ClientsideSettings
												// hinterlegt und in einer Instanzenvariable gespeichert.
												ClientsideSettings.setUser(UserRegister);
												user = UserRegister;
												// danach wird für den neu registrierten Nutzer ebenfalls die
												// Applikation geladen
												loadApplication();
											}
										}
									});
						}
					});
				}
			}
		});

	}

	/**
	 * In dieser Methode wird die eigentliche Sturkur der Applikation aufgebaut.
	 * Hier werden die einzelnen Bestandteile in der richtigen Reihenfolge dem RootPanel hinzugefügt.
	 * RootPanel
	 * HeadPanel, NavPanel, MainPanel
	 * MainPanel Sub: userProfilePanel, PinnboardPanel
	 */
	public void loadApplication() {
		
		signOutLink.setHref(loginInfo.getLogoutUrl());
		signOutLink.setStylePrimaryName("submit");
		signOutLink.setStyleDependentName("logout", true);
		
		
		hp.add(signOutLink);


		
		RootPanel.get("Head").add(hp);
		RootPanel.get("Nav").add(sp);
		RootPanel.get("Main").add(mp);
		sp.setMainPanel(mp);
		





	}

	/**
	 * Hier wird der LoginButton hinzugefügt 
	 * und alles weitere für den Login eingeleitet.
	 */
	private void loadLogin() {
		
		

		signInLink.setHref(loginInfo.getLoginUrl());
//		signInLink.setStylePrimaryName("submit");
//		signInLink.setStyleDependentName("logout", true);
		loginPanel.add(signInLink);
		loginPanel.setStylePrimaryName("login");
		RootPanel.get("Login").add(loginPanel);
		

	
//		loginPanel.add(loginLabel);

	}
}