package de.hdm.itp.client.gui;

import com.google.gwt.core.client.EntryPoint;

import com.google.gwt.user.client.ui.RootPanel;
import com.google.gwt.user.client.ui.VerticalPanel;
import de.hdm.itp.client.HeaderPanelReport;
import de.hdm.itp.client.MainPanelReport;
import de.hdm.itp.client.NavPanelReport;

public class ReportMenue implements EntryPoint {




	public void onModuleLoad() {
		
		
		HeaderPanelReport headP = new HeaderPanelReport();
		MainPanelReport mainP = new MainPanelReport();
		NavPanelReport navP = new NavPanelReport();

		
		RootPanel.get().add(headP);

		//RootPanel.get().add(mainP);

		RootPanel.get().add(navP);

		
	}
}
