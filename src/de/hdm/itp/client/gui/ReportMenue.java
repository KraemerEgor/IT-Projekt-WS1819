package de.hdm.itp.client.gui;

import de.hdm.itp.shared.EditorAdministrationAsync;

import de.hdm.itp.shared.bo.User;

import de.hdm.itp.client.*;

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

/**
 * The Class ReportMenue.
 */
public class ReportMenue implements EntryPoint {

	/** The Constant SERVER_ERROR. */
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

	

	/* (non-Javadoc)
	 * @see com.google.gwt.core.client.EntryPoint#onModuleLoad()
	 */
	public void onModuleLoad() {
		
//		User test = new User();
//		test.setId(10000010);
//		test.setFirstname("Egor");
//		test.setLastname("Kramu");
//		test.setNickname("KK");
//		test.setGender("m");
//		test.setEmail("egor.kramu@gmail.com");
//		ClientsideSettings.setUser(test);
//		loadApplication();
		
		Window.alert("working on report login first");
		
		LoginServiceAsync loginService = GWT.create(LoginService.class);

		Window.alert("second");
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

	}

	/**
	 * Load user information.
	 */
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

	/**
	 * Load application.
	 */
	public void loadApplication() {

		signOutLink.setHref(loginInfo.getLogoutUrl());
		
		HeaderPanelReport headP = new HeaderPanelReport();
		NavPanelReport navP = new NavPanelReport();

		headP.add(signOutLink);

		
		RootPanel.get().add(headP);
		RootPanel.get().add(navP);

		

	}

	/**
	 * Load login.
	 */
	private void loadLogin() {
		
		

		signInLink.setHref(loginInfo.getLoginUrl());
		loginPanel.add(loginLabel);
		loginPanel.add(signInLink);
		RootPanel.get("Login").add(loginPanel);
	}

}
