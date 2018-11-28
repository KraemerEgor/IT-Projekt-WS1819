package de.hdm.itp.client;

import com.google.gwt.user.client.ui.Button;
import com.google.gwt.user.client.ui.SuggestBox;
import com.google.gwt.user.client.ui.VerticalPanel;

public class SearchPanel extends VerticalPanel {
	
	
	public static void myprofile(String[] args) {
		String myprofile = "My Profile";
		System.out.println(myprofile);
	}
	
	public void createbutton() {
		//Dem neuen Button eine Aktion zuweisen
		Button b = new Button("+"); 
		
	}
		
	
	public static void newPost(String[] args) {
		String newpost = "create new post";
		System.out.println(newpost);
	}
	
	public SuggestBox sb = new SuggestBox();
	

	
	
}
