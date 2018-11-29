package de.hdm.itp.client.gui.report;

import com.google.gwt.user.client.ui.HTML;
import com.google.gwt.user.client.ui.VerticalPanel;

public class ReportResultPanel extends VerticalPanel{
	
	public void append(String text){
		HTML content = new HTML(text);
		this.add(content);
	}

}
