package de.hdm.itp.client;


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
import de.hdm.itp.shared.bo.User;
import de.hdm.itp.client.NavPanelReport;




public class userProfilePanel extends HorizontalPanel {
	
	/**
	 * Zusammenbauen des userProfiles
	 */
	
	private static EditorAdministrationAsync editorAdministration = null;
	
	private User u = new User();
	
	private Image avatar_man = new Image("man.png");
	private Image avatar_girl = new Image("girl.png");
	private FlexTable profile = new FlexTable();
	private Label nickname_lbl = new Label("Nickname: ");
	private Label firstname_lbl = new Label("Vorname: ");
	private Label lastname_lbl = new Label("Nachname: ");
	private Label email_lbl = new Label("Email: ");
	private User currentUser = ClientsideSettings.getUser();
	private TextArea postInput = new TextArea();
	private Button submitBtn = new Button("Post veröffentlichen");
	
	public void onLoad() {
		
		super.onLoad();
		
		if (editorAdministration == null) {
			editorAdministration = ClientsideSettings.getEditorAdministration();
		}
		
		
		editorAdministration.getUserById(10000001, new AsyncCallback<User>() {
			
			@Override
			public void onFailure(Throwable caught) {
				// TODO Auto-generated method stub
				
			}

			@Override
			public void onSuccess(User result) {
				
				u = result;
				firstname_lbl = new Label("Vorname: " + u.getFirstname());
				lastname_lbl = new Label("Nachname: " + u.getLastname());
				email_lbl = new Label("Email: " + u.getEmail());
				profile.setWidget(0, 1, firstname_lbl);
				profile.setWidget(1, 1, lastname_lbl);
				profile.setWidget(0, 2, email_lbl);
				
				if (u.getGender() == "m") {
					profile.setWidget(0, 0, avatar_man);
				}
				else {
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

		
		
		//submitBtn.addClickHandler(publicPost);
		
		submitBtn.setStylePrimaryName("submit");
		profile.setWidget(2, 3, submitBtn);
		
		
		this.add(this.profile);
		
				
		
		
		
		
		
	}
	
	
	
	
	
}


