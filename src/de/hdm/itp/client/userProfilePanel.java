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

public class userProfilePanel extends HorizontalPanel {

	/**
	 * Zusammenbauen des userProfiles
	 */

	private EditorAdministrationAsync editorAdministration = null;

	private User u = new User();
	static MainPanel mainPanel = new MainPanel();

	private Image avatar_man = new Image("man.png");
	private Image avatar_girl = new Image("girl.png");
	private Image avatar_other = new Image("user.png");
	private FlexTable profile = new FlexTable();
	private Label nickname_lbl = new Label("Nickname: ");
	private Label firstname_lbl = new Label("Vorname: ");
	private Label lastname_lbl = new Label("Nachname: ");
	private Label email_lbl = new Label("Email: ");
	private User currentUser = new User();
	private TextArea postInput = new TextArea();
	private Button submitBtn = new Button("Post veröffentlichen");

	public MainPanel getMainPanel() {
		return mainPanel;
	}

	public void setMainPanel(MainPanel mainPanel) {
		this.mainPanel = mainPanel;
	}

	
	public void onLoad() {

		super.onLoad();

		if (editorAdministration == null) {
			editorAdministration = ClientsideSettings.getEditorAdministration();
		}

		currentUser = ClientsideSettings.getUser();

		editorAdministration.getUserById(currentUser.getId(), new AsyncCallback<User>() {

			@Override
			public void onFailure(Throwable caught) {
				Window.alert(caught.getMessage());

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
		profile.setWidget(2, 3, submitBtn);
		submitBtn.addClickHandler(new SubmitClickHandler());

		this.add(this.profile);

	}

	private class SubmitClickHandler implements ClickHandler {
		public void onClick(ClickEvent event) {
			currentUser = ClientsideSettings.getUser();
			if (editorAdministration == null) {
				editorAdministration = ClientsideSettings.getAdministration();
			}
			String inputText = postInput.getValue();
			if(inputText == "") {
				Window.alert("Sie können keine leeren Posts erstellen.");
			}else {
			
			editorAdministration.createPost(postInput.getValue(), ClientsideSettings.getUser(),
					new AsyncCallback<Post>() {
				
						public void onFailure(Throwable caught) {
							Window.alert(caught.getMessage());
							
						}

						public void onSuccess(Post success) {
							Window.alert("hat geklappt");
							profile.clearCell(2, 1);
							TextArea input = new TextArea();
							input.setStylePrimaryName("postInput");
							profile.setWidget(2, 1, input);
							postInput.setText("");
							mainPanel.createPinnboard(u);

						}
					});

		}}

	}

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
			profile.setWidget(2, 3, submitBtn);
			submitBtn.addClickHandler(new SubmitClickHandler());
		} else {
			profile.setWidget(2, 1, lbl);
			profile.getFlexCellFormatter().setColSpan(2, 1, 2);
			profile.setWidget(2, 3, lbl);

		}

		this.add(this.profile);

	}

	
}
