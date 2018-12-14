package de.hdm.itp.client;

import com.google.gwt.core.client.GWT;
import com.google.gwt.event.dom.client.ClickEvent;
import com.google.gwt.event.dom.client.ClickHandler;
import com.google.gwt.user.client.Window;
import com.google.gwt.user.client.ui.Anchor;
import com.google.gwt.user.client.ui.Button;
import com.google.gwt.user.client.ui.HTMLPanel;
import com.google.gwt.user.client.ui.HorizontalPanel;
import com.google.gwt.user.client.ui.Label;
import com.google.gwt.user.client.ui.RootPanel;

public class HeaderPanel extends HorizontalPanel {
	
	private Label header_lbl = new Label("Das ist ein Label vom HeaderPanel");
	private Button reportBtn = new Button("Report-Generator");
	private Anchor reportLink = new Anchor("Report");
	
	
	public void onLoad() {
		
		super.onLoad();
		this.addStyleName("Head");
		
		header_lbl.addStyleName("label_test");
		header_lbl.setWidth("210px");
		this.add(header_lbl);
		this.add(header_lbl);
		
		RootPanel.get("Head").add(reportBtn);
		reportBtn.setStylePrimaryName("report");
		
		reportBtn.addClickHandler(new ClickHandler(){
			public void onClick(ClickEvent event) {
				reportLink.setHref(GWT.getHostPageBaseURL() + "FeedReports.html");
				Window.open(reportLink.getHref(), "_blank", "");
				}
			});
	
		
	
	}

}
