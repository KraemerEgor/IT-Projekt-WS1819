package de.hdm.itp.client.gui.report;

import com.google.gwt.user.client.ui.HTML;
import com.google.gwt.user.client.ui.VerticalPanel;

/**
 * The Class ReportResultPanel.
 */

/**
 * Hier wird das ResultPanel estellt, das von VerticalPanel erbt und 
 * auf das die Ergebnisse der einzelnen Reports geschrieben werden.
 * @author nilskaper
 *
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
		this.setStylePrimaryName("resultPanel");
	}

}
