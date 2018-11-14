package de.hdm.itp.client;

import com.google.gwt.core.client.EntryPoint;
import com.google.gwt.core.client.GWT;
import com.google.gwt.user.client.ui.Button;
import com.google.gwt.user.client.ui.VerticalPanel;

public class ReportManager implements EntryPoint {

VerticalPanel mainPanel = new VerticalPanel();
	
	Button getReportButton = new Button ("Report");

	public void onModuleLoad() {

	    GWT.log("Hello World!", null);

		mainPanel.add(getReportButton);
		
		
	}
}
