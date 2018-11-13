package de.hdm.itp.client.gui;

import com.google.gwt.core.client.EntryPoint;
import com.google.gwt.user.client.ui.Button;
import com.google.gwt.user.client.ui.VerticalPanel;

public class ReportManager implements EntryPoint {

VerticalPanel mainPanel = new VerticalPanel();
	
	Button getReportButton = new Button ("Report");

	public void onModuleLoad() {
		
		mainPanel.add(getReportButton);
		
		
	}
}
