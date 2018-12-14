package de.hdm.itp.client;

import com.google.gwt.core.client.GWT;
import com.google.gwt.event.dom.client.ClickEvent;
import com.google.gwt.event.dom.client.ClickHandler;
import com.google.gwt.user.client.Window;
import com.google.gwt.user.client.ui.Anchor;
import com.google.gwt.user.client.ui.Button;
import com.google.gwt.user.client.ui.FlowPanel;
import com.google.gwt.user.client.ui.SuggestBox;


import de.hdm.itp.client.gui.report.AllPostsFromUserReportForm;
import de.hdm.itp.shared.bo.User;

import com.google.gwt.user.client.ui.Label;
import com.google.gwt.user.client.ui.MultiWordSuggestOracle;
import com.google.gwt.user.client.ui.RootPanel;





public class SearchPanel extends FlowPanel {
	
	FlowPanel resultPanel = new FlowPanel();
	
	private Anchor reportLink = new Anchor("Report");
	private Button reportBtn = new Button("Zum Report-Generator");
	private User u = new User();

	
	Label header_lbl = new Label("Test"); 
	
	private Button profileBtn = new Button("My Profile");
	private Button addBtn = new Button("Add");
	
	MultiWordSuggestOracle oracle = new MultiWordSuggestOracle();
	SuggestBox suggestbox = new SuggestBox(oracle);	
	
	
	
	
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
			
		this.addStyleName("Search");			
			header_lbl.addStyleName("label_test");
		this.add(header_lbl);
			profileBtn.setStylePrimaryName("test_btn");
		this.add(profileBtn);
			addBtn.setStylePrimaryName("test_btn");
		this.add(addBtn);
		this.add(suggestbox);
		this.add(reportBtn);
		
		
				
		
		
		

	}
		
	
	
}



