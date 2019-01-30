package de.hdm.itp.client;

import com.google.gwt.event.dom.client.ClickEvent;
import com.google.gwt.event.dom.client.ClickHandler;
import com.google.gwt.user.client.Window;
import com.google.gwt.user.client.rpc.AsyncCallback;
import com.google.gwt.user.client.ui.Button;
import com.google.gwt.user.client.ui.FlexTable;
import com.google.gwt.user.client.ui.Grid;
import com.google.gwt.user.client.ui.HorizontalPanel;
import com.google.gwt.user.client.ui.Image;
import com.google.gwt.user.client.ui.IsWidget;
import com.google.gwt.user.client.ui.Label;
import com.google.gwt.user.client.ui.TextArea;
import com.google.gwt.user.client.ui.TextBox;
import com.google.gwt.user.client.ui.VerticalPanel;
import com.google.gwt.user.client.ui.Widget;

import de.hdm.itp.shared.EditorAdministrationAsync;
import de.hdm.itp.shared.bo.Post;
import de.hdm.itp.shared.bo.User;
import de.hdm.itp.client.NavPanelReport;

/**
 * The Class userProfilePanel.
 */
public class userProfilePanel extends HorizontalPanel {

	/** Zusammenbauen des userProfiles. */

	private EditorAdministrationAsync editorAdministration = null;

	/** The u. */
	private User u = new User();
	
	/** The main panel. */
	static MainPanel mainPanel = new MainPanel();

	/** The avatar man. */
	private Image avatar_man = new Image("man.png");
	
	/** The avatar girl. */
	private Image avatar_girl = new Image("girl.png");
	
	/** The avatar other. */
	private Image avatar_other = new Image("user.png");
	
	/** The profile. */
	private FlexTable profile = new FlexTable();
	
	/** The nickname lbl. */
	private Label nickname_lbl = new Label("Nickname: ");
	
	/** The firstname lbl. */
	private Label firstname_lbl = new Label("Vorname: ");
	
	/** The lastname lbl. */
	private Label lastname_lbl = new Label("Nachname: ");
	
	/** The email lbl. */
	private Label email_lbl = new Label("Email: ");
	
	/** The current user. */
	private User currentUser = new User();
	
	/** The post input. */
	private TextArea postInput = new TextArea();
	
	/** The submit btn. */
	private Button submitBtn = new Button("Post veröffentlichen");
	
	/** The input. */
	TextArea input = new TextArea();

	/**
	 * Gets the main panel.
	 *
	 * @return the main panel
	 */
	public MainPanel getMainPanel() {
		return mainPanel;
	}

	/**
	 * Sets the main panel.
	 *
	 * @param mainPanel the new main panel
	 */
	public void setMainPanel(MainPanel mainPanel) {
		this.mainPanel = mainPanel;
	}

	
	/* (non-Javadoc)
	 * @see com.google.gwt.user.client.ui.Widget#onLoad()
	 */
	public void onLoad() {

		super.onLoad();

		if (editorAdministration == null) {
			editorAdministration = ClientsideSettings.getEditorAdministration();
		}

		currentUser = ClientsideSettings.getUser();

		editorAdministration.getUserById(currentUser.getId(), new AsyncCallback<User>() {

			@Override
			public void onFailure(Throwable caught) {
				ClientsideFunctions.AlertDialogBox adb = new ClientsideFunctions.AlertDialogBox(caught.getMessage());

			}

			@Override
			public void onSuccess(User result) {

				u = result;
				firstname_lbl = new Label("Vorname: " + currentUser.getFirstname());
				lastname_lbl = new Label("Nachname: " + currentUser.getLastname());
				email_lbl = new Label("Email: " + currentUser.getEmail());
				nickname_lbl = new Label("Nickname: " + currentUser.getNickname());
				profile.setWidget(0, 2, firstname_lbl);
				profile.setWidget(1, 2, lastname_lbl);
				profile.setWidget(1, 1, email_lbl);
				profile.setWidget(0, 1, nickname_lbl);

				if (currentUser.getGender() == "m") {
					profile.setWidget(0, 0, avatar_man);
				} else {
					profile.setWidget(0, 0, avatar_girl);
				}

			}

		});

		this.setStylePrimaryName("profile");
		avatar_man.setStylePrimaryName("avatar");
		avatar_girl.setStylePrimaryName("avatar");

		profile.setWidget(2, 1, postInput);
		postInput.setStylePrimaryName("postInput");
		profile.getFlexCellFormatter().setColSpan(2, 1, 2);

		submitBtn.setStylePrimaryName("submit");
		profile.setWidget(3, 1, submitBtn);
		submitBtn.setStyleDependentName("userProfileButton", true);
		profile.setWidget(2, 3, new Label(""));
		SubmitClickHandler sch =new SubmitClickHandler();
		submitBtn.addClickHandler(sch);

		this.add(this.profile);

	}

	/**
	 * The Class SubmitClickHandler.
	 */
	private class SubmitClickHandler implements ClickHandler {
		
		/* (non-Javadoc)
		 * @see com.google.gwt.event.dom.client.ClickHandler#onClick(com.google.gwt.event.dom.client.ClickEvent)
		 */
		public void onClick(ClickEvent event) {
			currentUser = ClientsideSettings.getUser();
			if (editorAdministration == null) {
				editorAdministration = ClientsideSettings.getAdministration();
			}
			String inputText = postInput.getValue();
			if(inputText == "") {
				ClientsideFunctions.AlertDialogBox adb = new ClientsideFunctions.AlertDialogBox("Sie können keine leeren Posts erstellen.");
			}else {
			
			editorAdministration.createPost(postInput.getValue(), ClientsideSettings.getUser(),
					new AsyncCallback<Post>() {
				
						public void onFailure(Throwable caught) {
							ClientsideFunctions.AlertDialogBox adb = new ClientsideFunctions.AlertDialogBox(caught.getMessage());
							
						}

						public void onSuccess(Post success) {
							profile.clear();
							profile.clearCell(2, 1);
							
							input.setStylePrimaryName("postInput");
							profile.setWidget(2, 1, input);
							postInput.setText("");
							mainPanel.createPinnboard(u);

						}
					});

		}}

	}

	/**
	 * Creates the user profile.
	 *
	 * @param user the user
	 */
	public void createUserProfile(User user) {

		if (editorAdministration == null) {
			editorAdministration = ClientsideSettings.getEditorAdministration();
		}

		firstname_lbl = new Label("Vorname: " + user.getFirstname());
		lastname_lbl = new Label("Nachname: " + user.getLastname());
		email_lbl = new Label("Email: " + user.getEmail());
		nickname_lbl = new Label("Nickname: " + user.getNickname());
		profile.setWidget(0, 2, firstname_lbl);
		profile.setWidget(1, 2, lastname_lbl);
		profile.setWidget(1, 1, email_lbl);
		profile.setWidget(0, 1, nickname_lbl);
		Label lbl = new Label(" ");

		if (user.getGender() == "m") {
			profile.setWidget(0, 0, avatar_man);
		} 
		else if(user.getGender() == "o") {
			profile.setWidget(0, 0, avatar_other);
		}
		
		else {
			profile.setWidget(0, 0, avatar_girl);
		}

		this.setStylePrimaryName("profile");
		avatar_man.setStylePrimaryName("avatar");
		avatar_girl.setStylePrimaryName("avatar");
		avatar_other.setStylePrimaryName("avatar_gn");

		if (user.getId() == currentUser.getId()) {
			profile.setWidget(2, 1, postInput);
			postInput.setStylePrimaryName("postInput");
			profile.getFlexCellFormatter().setColSpan(2, 1, 2);

			submitBtn.setStylePrimaryName("submit");
			profile.setWidget(3, 1, submitBtn);
			//seit ich das auskommentiert habe werden Post nicht mehr exponentiell gepostet
			//submitBtn.addClickHandler(new SubmitClickHandler());
		} else {
			profile.setWidget(2, 1, lbl);
			profile.getFlexCellFormatter().setColSpan(2, 1, 2);
			profile.setWidget(2, 3, lbl);
			profile.setWidget(3, 1, new Label(""));
		}

		this.add(this.profile);

	}

	
}
