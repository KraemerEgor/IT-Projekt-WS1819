package de.hdm.itp.client;

import com.google.gwt.core.client.GWT;
import com.google.gwt.event.dom.client.ClickEvent;
import com.google.gwt.event.dom.client.ClickHandler;
import com.google.gwt.user.client.Window;
import com.google.gwt.user.client.ui.Anchor;
import com.google.gwt.user.client.ui.Button;
import com.google.gwt.user.client.ui.FlowPanel;
import com.google.gwt.user.client.ui.SuggestBox;
import com.google.gwt.user.client.ui.VerticalPanel;

import de.hdm.itp.client.gui.report.AllPostsFromUserReportForm;
import de.hdm.itp.shared.bo.User;

import com.google.gwt.user.client.ui.Label;
import com.google.gwt.user.client.ui.MultiWordSuggestOracle;
import com.google.gwt.user.client.ui.RootPanel;





public class SearchPanel extends FlowPanel {
	
	VerticalPanel resultPanel = new VerticalPanel();
	
	private Anchor reportLink = new Anchor("Report");
	private Button reportBtn = new Button("Zum Report-Generator");
	private User u = new User();
	private Label header_lbl = new Label("Navigation"); 
	private Button profileBtn = new Button("My Profile");
	private Button addBtn = new Button("Add");
	
	
	
	
	public void onLoad() {
		
		super.onLoad();
		
		reportBtn.addClickHandler(new ClickHandler(){
			public void onClick(ClickEvent event) {
				reportLink.setHref(GWT.getHostPageBaseURL() + "FeedReports.html");
				Window.open(reportLink.getHref(), "_self", "");
				}
			});
		profileBtn.addClickHandler(new ClickHandler() {
			public void onClick(ClickEvent event) {		
				resultPanel.clear();
				
				RootPanel.get().add(resultPanel);
				}
			});
			
		addBtn.addClickHandler(new ClickHandler() {
			public void onClick(ClickEvent event) {
				Window.alert("Klappt!");
				}
			});
		
		MultiWordSuggestOracle oracle = new MultiWordSuggestOracle();
		SuggestBox suggestbox = new SuggestBox(oracle);	
		   oracle.add("John");
		   oracle.add("Kevin");
		   oracle.add("Lisa");
		   oracle.add("Anna");
		   
		   
		this.setStylePrimaryName("Search");
		
		header_lbl.setStylePrimaryName("search_lbl");
		this.add(header_lbl);
		
		reportBtn.setStylePrimaryName("report_btn");
		this.add(reportBtn);
		
		profileBtn.setStylePrimaryName("sp_profile_btn");
		this.add(profileBtn);
		
		suggestbox.setStylePrimaryName("suggestbox");
		this.add(suggestbox);
		
		addBtn.setStylePrimaryName("sp_add_btn");
		this.add(addBtn);
		
		
		
		
		
				
		
		
		

	}
		
	
	
}



