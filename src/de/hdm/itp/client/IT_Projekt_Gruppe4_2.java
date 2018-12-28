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
	MainPanel mp = new MainPanel();
	HeaderPanel hp = new HeaderPanel();

	public void onModuleLoad() {

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
					Window.alert("loggedin");
				} else {
					Window.alert("else");

					loadLogin();
				}
			}

		});
		loadUserInformation();

	}

	public void loadUserInformation() {

		if (editorAdministration == null) {
			editorAdministration = ClientsideSettings.getEditorAdministration();
		}

		editorAdministration.isUserKnown(loginInfo.getEmailAddress(), new AsyncCallback<Boolean>() {

			public void onFailure(Throwable t) {
				System.out.println(t.getMessage());
			}

			public void onSuccess(Boolean result) {
				Window.alert("KnownUserSuccess");

				if (result) {
					Window.alert("UserisKnown");

					// Der Nutzer konnte in der Datenbank gefunden werden und ist somit bereits
					// bestehender Nutzer der Applikation
					editorAdministration.getUserByEmail(loginInfo.getEmailAddress(), new AsyncCallback<User>() {
						public void onFailure(Throwable t) {
							System.out.println(t.getMessage());
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

						public void onClick(ClickEvent arg0) {
							editorAdministration.createUser(createAccountBox.getMultiUseTextBox().getText(),
									createAccountBox.getNameTextBox().getText(),
									createAccountBox.getNickNameTextBox().getText(),
									createAccountBox.getListBox().getSelectedItemText(), loginInfo.getEmailAddress(),
									new AsyncCallback<User>() {
										public void onFailure(Throwable t) {
											System.out.println(t.getMessage());
											createAccountBox.hide();
										}

										public void onSuccess(User arg0) {

											if (arg0 != null) {

												createAccountBox.hide();
												// das zurückkommende Nutzer-Objekt wird in den ClientsideSettings
												// hinterlegt und in einer Instanzenvariable gespeichert.
												ClientsideSettings.setUser(arg0);
												user = arg0;
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
		RootPanel.get("Head").add(hp);
		RootPanel.get("Nav").add(np);
		RootPanel.get("Main").add(mp);

		Window.alert("Main panels are loaded");

	}

	private void loadLogin() {

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
		 * Die Information über den aktuell angemeldeten Nutzer wird ebenfalls dem
		 * loginPanel hinzugefügt
		 */
		signedInUser = new Label();
		signedInUser.addStyleName("signedInUser");

		editorAdministration.getFullNameOfUser(user, new AsyncCallback<String>() {
			public void onFailure(Throwable t) {
				System.out.println(t.getMessage());

			}

			public void onSuccess(String result) {

				signedInUser.setText("Angemeldet als: " + result);
			}
		});

		loginPanel.add(signedInUser);

		// das loginPanel wird dem div mit der id "Login" hinzugefügt

		RootPanel.get("Login").add(loginPanel);
		RootPanel.get("Navi").add(np);
		RootPanel.get("Main").add(mp);

	}
}
//		Window.alert("loginTesting");
//		
//		LoginServiceAsync loginService = GWT.create(LoginService.class);
//		
//		Window.alert("hier gehts noch ");
//
//		loginService.login(GWT.getHostPageBaseURL(), new AsyncCallback<LoginInfo>() {
//			
//			public void onFailure(Throwable caught) {
//				Window.alert("Fehler: " + caught.toString());
//			}
//
//			@Override
//			public void onSuccess(LoginInfo result) {
//				loginInfo = result;
//				
//				Window.alert("Webcontent der hrnshn");
//				if(loginInfo.isLoggedIn()) {
//					Window.alert("loggedin");
//				}
//			}	
//	
//	});
//		loadUserInformation();

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

//	
//	public void loadUserInformation() {
//		
//		loadApplication();
//		
//	}
//	
//	public void loadApplication(){
//		RootPanel.get("Head").add(hp);
//		RootPanel.get("Nav").add(np);
//		RootPanel.get("Main").add(mp);
//		
//	}
//	
//	private void loadLogin() {
//		  
//		signInLink.setHref(loginInfo.getLoginUrl());
//	    loginPanel.add(loginLabel);
//	    loginPanel.add(signInLink);
//	    RootPanel.get("Login").add(loginPanel);
//	}

//	}

//
//
//public class RegisterForm extends VerticalPanel {
//
//	private LoginInfo localLoginInfo = loginInfo;
//
//	/**
//	 * Instantiation of panels, tables, text boxes, buttons and lables.
//	 */
//	VerticalPanel vPanel = new VerticalPanel();
//	HorizontalPanel btnPanel = new HorizontalPanel();
//	FlexTable ft_register = new FlexTable();
//
//	TextBox box_user = new TextBox();
//
//	Button btn_register = new Button("Registrieren");
//	Button btn_cancel = new Button("Abbrechen");
//
//	Label lbl_headLine = new Label("Registrieren");
//
//	/**
//	 * Method enables the registering.
//	 */
//	public void onLoad() {
//		// Styling
//		btn_register.setStylePrimaryName("myButton");
//		lbl_headLine.addStyleName("formHeader");
//		this.setStylePrimaryName("pnl-border");
//
//		// Assemble GUI elements
//		this.add(lbl_headLine);
//		btnPanel.add(btn_register);
//		// btnPanel.add(btn_cancel);
//
//		ft_register.setText(0, 0, "Benutzer Name:");
//		ft_register.setWidget(0, 1, box_user);
//
//		vPanel.add(ft_register);
//		vPanel.add(btnPanel);
//		this.setSpacing(8);
//		this.add(vPanel);
//
//		// Clickhandler
//		btn_register.addClickHandler(new ClickHandler() {
//			@Override
//			public void onClick(ClickEvent event) {
//				if (box_user.getText() != null) {
//					admin.createUser(localLoginInfo.getEmailAddress(), box_user.getText(),
//							new AsyncCallback<User>() {
//								@Override
//								public void onFailure(Throwable caught) {
//									Window.alert("Benutzer registrieren fehlgeschlagen!");
//								}
//
//								@Override
//								public void onSuccess(User result) {
//									if (result != null) {
//										Window.alert("Glückwunsch " + result.getName()
//												+ " sie sind jetzt Teilnehmer von SharedContacts");
//										userId = result.getId();
//										RootPanel.get("content").clear();
//										loadSharedContacts();
//									} else {
//										Window.alert("Dieser Benutzername ist leider schon vergeben");
//									}
//								}
//							});
//				} else {
//					Window.alert("Bitte geben Sie einen Benutzername ein");
//				}
//			}
//
//		});
//
//		btn_cancel.addClickHandler(new ClickHandler() {
//			@Override
//			public void onClick(ClickEvent event) {
//			}
//
//		});
//		
//		// Keydown
//		box_user.addKeyDownHandler(new KeyDownHandler() {
//			public void onKeyDown(KeyDownEvent event) {
//				if (event.getNativeKeyCode() == KeyCodes.KEY_ENTER) {
//					btn_register.click();
//				}
//			}
//		});
//
//	}
//
//}
