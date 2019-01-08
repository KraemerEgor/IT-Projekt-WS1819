package de.hdm.itp.client;

import de.hdm.itp.shared.EditorAdministrationAsync;
import de.hdm.itp.shared.FieldVerifier;
import de.hdm.itp.shared.bo.User;

import java.util.Vector;

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

	// miro sign in button auf google verweis checken

	// wo ist der signOut Button hin ?
	// Logininfo schrieben wer angemeldet ist

	/**
	 * Diese Nachricht wird angezeigt, wenn der Client keine Verbindung zum Server
	 * aufbauen kann.
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

	private EditorAdministrationAsync admin = ClientsideSettings.getAdministration();

	User user = null;
	EditorAdministrationAsync editorAdministration = null;
	/** Die DialogBox, die bei erstmaliger Registrierung des Nutzers erscheint */
	ClientsideFunctions.InputDialogBox createAccountBox = null;

	NavPanel np = new NavPanel();
	SubsPanel sp = new SubsPanel();
	MainPanel mp = new MainPanel();
	HeaderPanel hp = new HeaderPanel();

	public void onModuleLoad() {
		//Diese 9 Lines entkommentieren, um lokal auszuführen
		signInLink.setStylePrimaryName("signin_btn");
		User test = new User();
		test.setId(10000004);
		test.setFirstname("Egor");
		test.setLastname("Kramu");
		test.setNickname("KK");
		test.setGender("m");
		test.setEmail("egor.kramu@gmail.com");
		ClientsideSettings.setUser(test);
		loadApplication();

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

				if (loginInfo.isLoggedIn()) {
					Window.alert("if !!!loggedin and loadUserInformation");
					loadUserInformation();

				} else {
					Window.alert("else loadLogin");

					loadLogin();

				}
			}

		});
//		loadApplication();

	}

	public void loadUserInformation() {

		if (editorAdministration == null) {
			editorAdministration = ClientsideSettings.getEditorAdministration();
		}

		editorAdministration.isUserKnown(loginInfo.getEmailAddress(), new AsyncCallback<Boolean>() {

			public void onFailure(Throwable t) {
				Window.alert(t.getMessage());
			}

			public void onSuccess(Boolean result) {
				Window.alert("KnownUserSuccess");

				if (result) {
					Window.alert("UserisKnown");

					// Der Nutzer konnte in der Datenbank gefunden werden und ist somit bereits
					// bestehender Nutzer der Applikation
					editorAdministration.getUserByEmail(loginInfo.getEmailAddress(), new AsyncCallback<User>() {
						public void onFailure(Throwable t) {
							Window.alert(t.getMessage());
						}

						public void onSuccess(User arg0) {

							// das zurückkommende Nutzer-Objekt wird in den ClientsideSettings hinterlegt
							// und in einer Instanzenvariable gespeichert.
							ClientsideSettings.setUser(arg0);
							user = arg0;
							Window.alert("UserWurdegesetzt");

							// da der Nutzer bereits bekannt ist, wird für ihn im Folgenden die Applikation
							// geladen
							loadApplication();
						}
					});
				}

				else {
					Window.alert("Userisunknown");

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
							
							Window.alert("after creatACCountBox switch case " + loginInfo.getEmailAddress());
							editorAdministration.createUser(loginInfo.getEmailAddress(),
									createAccountBox.getFirstnameTextBox().getText(),
									createAccountBox.getLastnameTextBox().getText(),
									createAccountBox.getNicknameTextBox().getText(), getGender,
									new AsyncCallback<User>() {
										public void onFailure(Throwable t) {
											Window.alert(t.getMessage());
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

	public void loadApplication() {
		
//		signOutLink.setHref(loginInfo.getLogoutUrl());
//		
//		hp.add(signOutLink);


		
		RootPanel.get("Head").add(hp);
		RootPanel.get("Nav").add(sp);
		RootPanel.get("Main").add(mp);
		sp.setMainPanel(mp);
		




		Window.alert("Main panels are loaded");

		Window.alert(ClientsideSettings.getUser().getNickname());

	}

	private void loadLogin() {
		
		

		signInLink.setHref(loginInfo.getLoginUrl());
		loginPanel.add(loginLabel);
		loginPanel.add(signInLink);
		RootPanel.get("Login").add(loginPanel);

//		/*
//		 * Das loginPanel wird aufgebaut
//		 */		
//		Window.alert("loadLogin°!°");
//		/*
//		 * Der signOutLink wird dem loginPanel hinzugefügt
//		 */
//		signOutLink.setHref(loginInfo.getLogoutUrl());
//		signOutLink.setStylePrimaryName("signout");
//		signInLink.setStylePrimaryName("reportbutton");
//
//		loginPanel.add(loginLabel);
//	    loginPanel.add(signInLink);
//		loginPanel.add(signOutLink);
//		
//		Window.alert("loginpanel.add");
//
//		/*
//		 * Die Information über den aktuell angemeldeten Nutzer wird ebenfalls dem
//		 * loginPanel hinzugefügt
//		 */
//		signedInUser = new Label();
//		signedInUser.addStyleName("signedInUser");
//
//		editorAdministration.getFullNameOfUser(user, new AsyncCallback<String>() {
//			public void onFailure(Throwable t) {
//				System.out.println(t.getMessage());
//
//			}
//
//			public void onSuccess(String result) {
//
//				signedInUser.setText("Angemeldet als: " + result);
//			}
//		});
//
//		loginPanel.add(signedInUser);
//	    RootPanel.get("Login").add(loginPanel);

		// das loginPanel wird dem div mit der id "Login" hinzugefügt

	}
}