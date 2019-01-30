package de.hdm.itp.client.gui.report;

import com.google.gwt.user.client.ui.HTML;
import com.google.gwt.user.client.ui.VerticalPanel;

/**
 * The Class ReportResultPanel.
 */
public class ReportResultPanel extends VerticalPanel{
	
	/**
	 * Append method to add the ReportResultPanel.
	 *
	 * @param text the text
	 */
	public void append(String text){
		HTML content = new HTML(text);
		this.add(content);
	}

}
