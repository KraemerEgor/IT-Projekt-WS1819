package de.hdm.itp.client;

import com.google.gwt.user.client.ui.Button;

import com.google.gwt.user.client.ui.SuggestBox;

import com.google.gwt.user.client.ui.Label;

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
	

	Label header_lbl = new Label("Search Panel");
	Button search_btn = new Button("Suche");
	
	public void onLoad() {
			
			super.onLoad();
			this.addStyleName("Search");
			
			header_lbl.addStyleName("label_test");
			header_lbl.setWidth("210px");
			this.add(header_lbl);
			this.add(search_btn);
	}


	
	
}
