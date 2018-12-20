package de.hdm.itp.client;

import com.google.gwt.user.client.ui.Button;
import com.google.gwt.user.client.ui.FlexTable;
import com.google.gwt.user.client.ui.Grid;
import com.google.gwt.user.client.ui.HorizontalPanel;
import com.google.gwt.user.client.ui.Image;
import com.google.gwt.user.client.ui.IsWidget;
import com.google.gwt.user.client.ui.Label;
import com.google.gwt.user.client.ui.TextBox;
import com.google.gwt.user.client.ui.VerticalPanel;
import com.google.gwt.user.client.ui.Widget;

import de.hdm.itp.shared.bo.User;




public class userProfilePanel extends HorizontalPanel {
	
	/**
	 * Zusammenbauen des userProfiles
	 */
	
	private Image avatar_man = new Image("man.png");
	private Image avatar_girl = new Image("girl.png");
	private FlexTable profile = new FlexTable();
	private Label test = new Label("Test");
	private User currentUser = ClientsideSettings.getUser();
	private TextBox postInput = new TextBox();
	
	public void onLoad() {
		
		super.onLoad();
		this.setStylePrimaryName("profile");
		avatar_man.setStylePrimaryName("avatar");
		
		profile.setWidget(0, 0, avatar_man);
		profile.getFlexCellFormatter().setColSpan(0, 0, 1);
		profile.getFlexCellFormatter().setRowSpan(0, 0, 2);
		profile.setWidget(0, 1, new Label(" Mein Name ist Nils Kaper"));
		profile.setWidget(1, 1, new Label("Das hier ist meine Mail-Adresse:"));
		profile.getFlexCellFormatter().setColSpan(0, 1, 2);
		

		
		
		this.add(this.profile);
		
				
		
		
		
		
	}
	
	
	
	
	
}
